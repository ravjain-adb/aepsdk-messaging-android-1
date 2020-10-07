/*
  Copyright 2020 Adobe. All rights reserved.
  This file is licensed to you under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License. You may obtain a copy
  of the License at http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software distributed under
  the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR REPRESENTATIONS
  OF ANY KIND, either express or implied. See the License for the specific language
  governing permissions and limitations under the License.
 */
package com.adobe.marketing.mobile;

final class MessagingConstant {

    static final String LOG_TAG = "Messaging";
    static final String EXTENSION_VERSION = "1.0.0";
    static final String EXTENSION_NAME = "com.adobe.messaging";

    private MessagingConstant() {}

    static final class TrackingKeys {
        static final String CUSTOMER_JOURNEY_MANAGEMENT = "cjm";
        static final String EXPERIENCE = "_experience";
        static final String MESSAGE_PROFILE_JSON = "{\n" +
                "   \"messageProfile\":{\n" +
                "      \"channel\":{\n" +
                "         \"_type\":\"https://ns.adobe.com/xdm/channels/push\"\n" +
                "      }\n" +
                "   },\n" +
                "   \"pushChannelContext\":{\n" +
                "      \"platform\":\"fcm\"\n" +
                "   }\n" +
                "}";
    }

    static final class EventDataKeys {

        static final class Identity {
            static final String PUSH_IDENTIFIER = "pushidentifier";
            static final String VISITOR_ID_MID = "mid";

            private Identity() {
            }
        }

        static final class Messaging {
            static final String TRACK_INFO_KEY_EVENT_TYPE = "eventType";
            static final String TRACK_INFO_KEY_MESSAGE_ID = "id";
            static final String TRACK_INFO_KEY_APPLICATION_OPENED = "applicationOpened";
            static final String TRACK_INFO_KEY_ACTION_ID = "actionId";

            // TEMP todo we need to define if this is the right key or do we need an extra api for this
            static final String TRACK_INFO_KEY_ADOBE = "adobe";

            private Messaging() {
            }
        }

        static final class Configuration {
            static final String GLOBAL_PRIVACY_STATUS = "global.privacy";
            // Temp
            static final String DCCS_URL = "messaging.dccs";
            static final String EXPERIENCE_CLOUD_ORG = "experienceCloud.org";

            static final String PROFILE_DATASET_ID = "messaging.profileDataset";
            static final String EXPERIENCE_EVENT_DATASET_ID = "messaging.eventDataset";
            private Configuration() {
            }
        }
    }

    static final class EventType {
        public static final String HUB = "com.adobe.eventType.hub";
        public static final String GENERIC_IDENTITY = "com.adobe.eventType.generic.identity";
        public static final String GENERIC_DATA = "com.adobe.eventType.generic.data";

        private EventType() {
        }
    }

    static final class EventSource {
        static final String SHARED_STATE = "com.adobe.eventSource.sharedState";
        static final String REQUEST_CONTENT = "com.adobe.eventSource.requestContent";
        static final String RESPONSE_CONTENT = "com.adobe.eventSource.responseContent";
        static final String OS = "com.adobe.eventSource.os";

        private EventSource() {
        }
    }

    static final class SharedState {

        static final String STATE_OWNER = "stateowner";

        private SharedState() {
        }

        static final class Configuration {
            static final String EXTENSION_NAME = "com.adobe.module.configuration";
            static final String PRIVACY_STATUS = "global.privacy";

            // Messaging
            static final String PROFILE_DATASET_ID = "messaging.profileDataset";
            static final String EXPERIENCE_EVENT_DATASET_ID = "messaging.eventDataset";

            // Temp
            static final String DCCS_URL = "messaging.dccs";
            static final String EXPERIENCE_CLOUD_ORG = "experienceCloud.org";

            private Configuration() {
            }
        }

        static final class Identity {
            static final String EXTENSION_NAME = "com.adobe.module.identity";

            private Identity() {
            }
        }
    }
}
