package com.hcwl.uc.sdk.core.utils;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.hcwl.uc.sdk.core.config.UcSdkConfigurationProperties;
import org.springframework.http.HttpMethod;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangdx
 * @date 2022-07-07
 */
public class BearerTokenStorage {

    private final static String KEY = "BEARER_TOKEN";

    private Cache<String, String> bearerTokenCache;

    public BearerTokenStorage() {
        this.bearerTokenCache = Caffeine.newBuilder()
                .initialCapacity(1)
                .maximumSize(1)
                .expireAfterWrite(1, TimeUnit.DAYS)
                .build();
    }

    public String getBearerToken(){
        return bearerTokenCache.get(KEY, this::fetchBearerTokenFromRemote);
    }

    public void clearBearerToken(){
        bearerTokenCache.invalidate(KEY);
    }

    private String fetchBearerTokenFromRemote(String key){
            UcSdkConfigurationProperties properties = UcSdkConfigurationProperties.UcSdkConfigurationPropertiesHolder.getUcSdkConfigurationProperties();
            String url = "/uc/client/v1.0/oauth2/token";
            Map<String,Object> body = new HashMap<String,Object>();
            body.put("grant_type","client_credentials");
            body.put("client_id",properties.getClientId());
            body.put("client_secret",properties.getClientSecret());
            HashMap response = UcClientRestApiClient.exchange(url, HttpMethod.POST,body,null,null,HashMap.class,false);
            return (String)response.get("access_token");
    }

}
