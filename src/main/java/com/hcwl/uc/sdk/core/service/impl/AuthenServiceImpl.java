package com.hcwl.uc.sdk.core.service.impl;

import com.hcwl.uc.sdk.core.config.UcSdkConfigurationProperties;
import com.hcwl.uc.sdk.core.service.dto.Account;
import com.hcwl.uc.sdk.core.exception.UCSdkException;
import com.hcwl.uc.sdk.core.service.IAuthenService;
import com.hcwl.uc.sdk.core.service.param.GetCaptchaParams;
import com.hcwl.uc.sdk.core.service.param.LoginByPasswordParams;
import com.hcwl.uc.sdk.core.service.param.LoginBySmsCodeParams;
import com.hcwl.uc.sdk.core.service.param.SendSmsCodeParams;
import com.hcwl.uc.sdk.core.utils.UcAdminRestApiClient;
import com.hcwl.uc.sdk.core.utils.UcClientRestApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author luoxy
 * @date 2022-06-14
 */
@Service("ucAuthenService")
public class AuthenServiceImpl implements IAuthenService {
    @Autowired
    private UcSdkConfigurationProperties ucSdkProperties;

    public Account loginByPassword(LoginByPasswordParams params) throws UCSdkException {
        String host = ucSdkProperties.getUri();
        String url = host + "/auth/login";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username", params.getLoginName());
        map.put("password", params.getPassword());
        map.put("checkKey", params.getCaptchaKey());
        map.put("captcha", params.getCaptchaValue());
        LinkedHashMap result = UcAdminRestApiClient.post(url, map, new UcClientRestApiClient.ResultType(LinkedHashMap.class));
        return extractAccount((LinkedHashMap) result.get("userInfo"));
    }

    public Account loginBySmsCode(LoginBySmsCodeParams params) throws UCSdkException {
        String host = ucSdkProperties.getUri();
        String url = host + "/auth/phone-login";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("mobile", params.getMobile());
        map.put("captcha", params.getSmsCode());
        LinkedHashMap result = UcAdminRestApiClient.post(url, map,new UcClientRestApiClient.ResultType(LinkedHashMap.class));
        return extractAccount((LinkedHashMap) result.get("userInfo"));
    }

    public void sendSmsCode(SendSmsCodeParams params) throws UCSdkException {
        String host = ucSdkProperties.getUri();
        String url = host + "/auth/send-sms";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("mobile", params.getMobile());
        map.put("smsMode", params.getSmsType());
        UcAdminRestApiClient.post(url, map,null);
    }

    public String getCaptcha(GetCaptchaParams params) throws UCSdkException {
        String host = ucSdkProperties.getUri();
        String url = host + "/auth/captcha/" + params.getKey();
        return UcAdminRestApiClient.get(url, null,new UcClientRestApiClient.ResultType(String.class));
    }

    private Account extractAccount(LinkedHashMap userInfo){
        Account account = new Account();
        account.setId(Long.parseLong(userInfo.get("id")+""));
        account.setUserName((String)userInfo.get("username"));
        account.setMobile((String)userInfo.get("phone"));
        account.setSex((Integer) userInfo.get("sex"));
        account.setStatus((Integer) userInfo.get("status"));
        return account;
    }

}
