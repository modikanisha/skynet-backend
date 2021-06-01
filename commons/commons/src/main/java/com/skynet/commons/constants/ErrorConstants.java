package com.skynet.commons.constants;

public class ErrorConstants {

    public static class ErrorCode {
        public static final int ROLE_ERROR = 1;
        public static final int NETWORK_ERROR = 2;
    }


    public static class SubErrorCode {
        public static final int ROLE_ID_NOT_FOUND = 1;
        public static final int NETWORK_ID_NOT_FOUND = 2;
    }

    public static class ErrorMessage {
        public static final String ROLE_ID_NOT_FOUND = "Role not found";
        public static final String NETWORK_ID_NOT_FOUND = "Network not found";
    }

}
