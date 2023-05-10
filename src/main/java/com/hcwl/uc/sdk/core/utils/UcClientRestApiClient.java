package com.hcwl.uc.sdk.core.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.hcwl.uc.sdk.core.config.UcSdkConfigurationProperties;
import com.hcwl.uc.sdk.core.constant.CommonConstants;
import com.hcwl.uc.sdk.core.exception.ErrorCode;
import com.hcwl.uc.sdk.core.exception.UCSdkException;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 此类提供调用UC服务端提供的面向客户端(如SDK等)的restful api的方法.
 *
 * @author zhangdx
 * @date 2022-07-04
 */
@Slf4j
public abstract class UcClientRestApiClient {

    @Data
    public static class ErrorMessage {
        private String code;
        private String message;
        private String hostId;
        private String requestId;
        private String serverTime;
    }

    @Getter
    public static class ResultType {
        private Class clazz;
        private TypeReference typeReference;

        public ResultType(Class clazz) {
            this.clazz = clazz;
        }

        public ResultType(TypeReference typeReference) {
            this.typeReference = typeReference;
        }
    }

    private static RestTemplate template;

    private static BearerTokenStorage bearerTokenStorage = new BearerTokenStorage();

    /**
     * get请求
     *
     * @param url
     * @param urlVariables
     * @return
     */
    public static <T> T get(String url, Map<String, Object> urlVariables, ResultType resultType, boolean needAuthen) {
        return exchange(url, HttpMethod.GET, null, null, urlVariables, resultType, needAuthen);
    }

    /**
     * put请求
     *
     * @param url
     * @param body
     * @param urlVariables
     * @return
     */
    public static <T> T put(String url, Object body, Map<String, Object> urlVariables, ResultType resultType, boolean needAuthen) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return exchange(url, HttpMethod.PUT, body, headers, urlVariables, resultType, needAuthen);
    }

    /**
     * delete请求
     *
     * @param url
     * @param uriVariables
     * @return
     */
    public static <T> T delete(String url, Map<String, Object> uriVariables, ResultType resultType, boolean needAuthen) {
        return exchange(url, HttpMethod.DELETE, null, null, uriVariables, resultType, needAuthen);
    }

    /**
     * post请求
     *
     * @param url
     * @param body
     * @return
     */
    public static <T> T post(String url, Object body, ResultType resultType, boolean needAuthen) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return exchange(url, HttpMethod.POST, body, headers, null, resultType, needAuthen);
    }

    public static <T> T exchange(String url, HttpMethod method, Object body, HttpHeaders headers, Map<String, Object> uriVariables, ResultType resultType, boolean needAuthen) {
        if (resultType != null && resultType.getTypeReference() != null) {
            String json = exchange(url, method, body, headers, uriVariables, String.class, needAuthen);
            return (T) JSON.parseObject(json, resultType.getTypeReference());
        } else if (resultType != null && resultType.getClazz() != null) {
            return (T) exchange(url, method, body, headers, uriVariables, resultType.getClazz(), needAuthen);
        } else {
            return (T) exchange(url, method, body, headers, uriVariables, Object.class, needAuthen);
        }
    }

    public static <T> T exchange(String url, HttpMethod method, Object body, HttpHeaders headers, Map<String, Object> uriVariables, Class<T> responseType, boolean needAuthen) {
        try {
            url = getApiUrl(url);
            if (log.isDebugEnabled()) {
                log.debug("request - url:{},uriVariables:{},body:{}", url, JSON.toJSONString(uriVariables), JSON.toJSONString(body));
            }
            if (uriVariables == null) {
                uriVariables = new HashMap<String, Object>();
            }
            if (headers == null) {
                headers = new HttpHeaders();
            }
            if (needAuthen) {
                addAuthorizationHeader(headers);
            }
            ResponseEntity<T> response = getRestTemplate().exchange(url, method, new HttpEntity<Object>(body, headers), responseType, uriVariables);
            T resquestBody = response.getBody();
            if (log.isDebugEnabled()) {
                log.debug("response - status:{},body:{}", response.getStatusCodeValue(), JSON.toJSONString(resquestBody));
            }
            return resquestBody;
        } catch (RestClientResponseException e) {
            if (log.isInfoEnabled()) {
                log.info("uc server response:{}", e.getResponseBodyAsString());
            }
            ErrorMessage errorMessage = extractErrorMessage(e);
            ErrorCode errorCode = ErrorCode.nameOf(errorMessage.getCode());
            if (errorCode == null) {
                errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
            }
            if (ErrorCode.INVALID_TOKEN == errorCode) {
                bearerTokenStorage.clearBearerToken();
            }
            throw new UCSdkException(errorMessage.getMessage(), e, errorCode);
        } catch (Exception e) {
            throw new UCSdkException(e.getMessage(), e, ErrorCode.INTERNAL_CLIENT_ERROR);
        }
    }

    public static ErrorMessage extractErrorMessage(RestClientResponseException e) {
        return JSON.parseObject(e.getResponseBodyAsString(), ErrorMessage.class);
    }

    private static void addAuthorizationHeader(HttpHeaders headers) {
        String value = String.format(CommonConstants.Token.BEARER_TOKEN_AUTHORIZATION_HEADER_VALUE_TEMPLATE, bearerTokenStorage.getBearerToken());
        headers.add(CommonConstants.HeaderName.AUTHORIZATION, value);
    }

    private static void addCommonHeader(HttpHeaders headers) {
        UcSdkConfigurationProperties properties = UcSdkConfigurationProperties.UcSdkConfigurationPropertiesHolder.getUcSdkConfigurationProperties();
        headers.add(CommonConstants.HeaderName.UC_APPLICATION_ID, properties.getApplicationId().toString());
    }

    private static RestTemplate getRestTemplate() {
        if (template == null) {
            try{
                TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
                SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
                SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
                CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).build();
                HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
                factory.setHttpClient(httpClient);
                factory.setConnectTimeout(5000);
                factory.setReadTimeout(1000 * 30);
                template = new RestTemplate(factory);
                template.setInterceptors(new ArrayList<ClientHttpRequestInterceptor>() {
                    {
                        add(new ClientHttpRequestInterceptor() {
                            public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
                                HttpHeaders headers = request.getHeaders();
                                if (headers == null) {
                                    headers = new HttpHeaders();
                                }
                                addCommonHeader(headers);
                                return execution.execute(request, body);
                            }
                        });
                    }
                });
            }catch (Exception e){
                throw new UCSdkException("get RestTemplate error.caused by:"+e.getMessage(),e,ErrorCode.INTERNAL_CLIENT_ERROR);
            }
        }
        return template;
    }

    public static String getApiUrl(String segment){
        if(StringUtils.startsWithAny(segment.toLowerCase(),"http","https")){
            return segment;
        }
        UcSdkConfigurationProperties properties = UcSdkConfigurationProperties.UcSdkConfigurationPropertiesHolder.getUcSdkConfigurationProperties();
        String host = properties.getUri();
        return host + segment;
    }

}
