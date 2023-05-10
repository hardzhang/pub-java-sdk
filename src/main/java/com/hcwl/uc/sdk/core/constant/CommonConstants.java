package com.hcwl.uc.sdk.core.constant;

/**
 * @author zhangdx
 * @date 2022-07-01
 */
public abstract class CommonConstants {

    public static class HeaderName {
        public final static String UC_APPLICATION_ID = "uc-application-id";
        public final static String AUTHORIZATION = "Authorization";
    }

    public static class Token {

        public final static String BEARER_TOKEN_AUTHORIZATION_HEADER_VALUE_TEMPLATE = "Bearer %s";

    }

}
