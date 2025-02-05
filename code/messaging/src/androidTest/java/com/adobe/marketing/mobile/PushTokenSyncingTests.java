/*
  Copyright 2021 Adobe. All rights reserved.
  This file is licensed to you under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License. You may obtain a copy
  of the License at http://www.apache.org/licenses/LICENSE-2.0
  Unless required by applicable law or agreed to in writing, software distributed under
  the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR REPRESENTATIONS
  OF ANY KIND, either express or implied. See the License for the specific language
  governing permissions and limitations under the License.
*/

package com.adobe.marketing.mobile;

import android.content.Intent;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static com.adobe.marketing.mobile.TestHelper.getDispatchedEventsWith;
import static com.adobe.marketing.mobile.TestHelper.resetTestExpectations;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class PushTokenSyncingTests {
    @Rule
    public RuleChain rule = RuleChain.outerRule(new TestHelper.SetupCoreRule())
            .around(new TestHelper.RegisterMonitorExtensionRule());

    // --------------------------------------------------------------------------------------------
    // Setup
    // --------------------------------------------------------------------------------------------

    @Before
    public void setup() throws Exception {
        MessagingFunctionalTestUtil.setEdgeIdentityPersistence(MessagingFunctionalTestUtil.createIdentityMap("ECID", "mockECID"));
        Messaging.registerExtension();
        com.adobe.marketing.mobile.edge.identity.Identity.registerExtension();

        final CountDownLatch latch = new CountDownLatch(1);
        MobileCore.start(new AdobeCallback() {
            @Override
            public void call(Object o) {
                latch.countDown();
            }
        });

        latch.await();
        resetTestExpectations();
    }

    // --------------------------------------------------------------------------------------------
    // Syncing push token
    // --------------------------------------------------------------------------------------------
    @Test
    public void testPushTokenSync() throws InterruptedException {
        // expected results
        String expectedEdgeEvent = "{\"data\":{\"pushNotificationDetails\":[{\"denylisted\":false,\"identity\":{\"namespace\":{\"code\":\"ECID\"},\"id\":\"mockECID\"},\"appID\":\"com.adobe.marketing.mobile.messaging.test\",\"platform\":\"fcm\",\"token\":\"mockPushToken\"}]}}";
        // test
        MobileCore.setPushIdentifier("mockPushToken");

        // verify messaging event
        List<Event> genericIdentityEvents = getDispatchedEventsWith(EventType.GENERIC_IDENTITY.getName(),
                EventSource.REQUEST_CONTENT.getName());
        assertEquals(1, genericIdentityEvents.size());

        // verify edge event
        List<Event> edgeRequestEvents = getDispatchedEventsWith(MessagingConstant.EventType.EDGE,
                EventSource.REQUEST_CONTENT.getName());
        assertEquals(1, edgeRequestEvents.size());
        assertEquals(expectedEdgeEvent, edgeRequestEvents.get(0).getData().toString());
    }

    @Test
    public void testPushTokenSync_pushTokenIsNull() throws InterruptedException {
        // test
        MobileCore.setPushIdentifier(null);

        // verify messaging event
        List<Event> genericIdentityEvents = getDispatchedEventsWith(EventType.GENERIC_IDENTITY.getName(),
                EventSource.REQUEST_CONTENT.getName());
        assertEquals(1, genericIdentityEvents.size());

        // verify edge event
        List<Event> edgeRequestEvents = getDispatchedEventsWith(MessagingConstant.EventType.EDGE,
                EventSource.REQUEST_CONTENT.getName());
        assertEquals(0, edgeRequestEvents.size());
    }

    @Test
    public void testPushTokenSync_pushTokenIsEmpty() throws InterruptedException {
        // test
        MobileCore.setPushIdentifier("");

        // verify messaging event
        List<Event> genericIdentityEvents = getDispatchedEventsWith(EventType.GENERIC_IDENTITY.getName(),
                EventSource.REQUEST_CONTENT.getName());
        assertEquals(1, genericIdentityEvents.size());

        // verify edge event
        List<Event> edgeRequestEvents = getDispatchedEventsWith(MessagingConstant.EventType.EDGE,
                EventSource.REQUEST_CONTENT.getName());
        assertEquals(0, edgeRequestEvents.size());
    }
}
