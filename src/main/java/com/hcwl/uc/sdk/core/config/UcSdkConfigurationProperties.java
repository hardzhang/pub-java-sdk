package com.hcwl.uc.sdk.core.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


/**
 * @author: lidch
 * @date 2022/2/18 18:23
 * @description: uc springboot 配置类
 */
@Component
@Getter
@Setter
public class UcSdkConfigurationProperties {

    /**
     * UC服务的地址
     */
    @Value("${uc.sdk.uri}")
    private String uri;

    /**
     * UC颁发的应用系统ID
     */
    @Value("${uc.sdk.application.id}")
    private Long applicationId;

    /**
     * UC颁发的客户端ID
     */
    @Value("${uc.sdk.client.id}")
    private String clientId;

    /**
     * UC颁发的客户端密钥
     */
    @Value("${uc.sdk.client.secret}")
    private String clientSecret;

    @Value("${uc.sdk.sso.cas-url:}")
    private String casUrl;

    @Value("${uc.sdk.sso.biz-app-url:}")
    private String bizAppUrl;

    @Value("${uc.sdk.sso.cas-service:}")
    private String casService;

    @Value("${uc.sdk.sso.cas-validate-login-url:}")
    private String casValidateLoginUrl;

    public UcSdkConfigurationProperties() {
    }

    @Component
    public static class UcSdkConfigurationPropertiesHolder implements ApplicationContextAware {

        private static UcSdkConfigurationProperties ucSdkConfigurationProperties;

        public static UcSdkConfigurationProperties getUcSdkConfigurationProperties() {
            return ucSdkConfigurationProperties;
        }

        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            ucSdkConfigurationProperties = applicationContext.getBean(UcSdkConfigurationProperties.class);
        }
    }

}
