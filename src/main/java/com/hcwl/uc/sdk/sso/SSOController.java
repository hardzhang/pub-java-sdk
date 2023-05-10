package com.hcwl.uc.sdk.sso;

import com.hcwl.uc.sdk.core.config.UcSdkConfigurationProperties;
import com.hcwl.uc.sdk.core.utils.UcAdminRestApiClient;
import com.hcwl.uc.sdk.core.utils.UcClientRestApiClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 此类提供用于实现由统一用户中心到应用系统方向SSO的需要的Restful接口.
 * @author zhangdx
 * @date 2022-06-06
 */
@Slf4j
@RestController("ucSSOController")
@RequestMapping(value = "/sso")
@ConditionalOnProperty(value = "uc.sdk.sso.enabled",havingValue = "true")
public class SSOController {

    @Autowired
    private ISSOCallback ssoCallback;
    @Autowired
    UcSdkConfigurationProperties ucSdkProperties;

    Map<String,String> map = new HashMap();
    /**
     * 用于实现单点登录的接口，用于用户在统一用户中心上点击应用首页图标之后重定向到CAS.
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public void login(HttpServletResponse response) {
        String casUrl = ucSdkProperties.getCasUrl(); //TODO 没有拼接上重定向地址，完整的应该是这样的: http://
        response.encodeRedirectURL(casUrl);
    }

    /**
     * 用于实现单点登录的接口,用于用户在Cas上完成登录之后，Cas重定向的URL指向的接口.
     * @param ticket
     */
    @RequestMapping(value = "/login/callback",method = RequestMethod.GET)
    public void callback(@RequestParam(name="ticket") String ticket,HttpServletResponse response) {
        log.debug("ticket:{}",ticket);
        Long accountId = 0L;
        String casValidateLoginUrl = ucSdkProperties.getCasValidateLoginUrl();
        String casService = ucSdkProperties.getCasService(); //TODO 此参数是否可以不配置
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("ticket", casValidateLoginUrl);
        paramMap.put("service", casService);
        LinkedHashMap userInfo = UcAdminRestApiClient.get(casValidateLoginUrl, paramMap,new UcClientRestApiClient.ResultType(LinkedHashMap.class));
        accountId = Long.parseLong(userInfo.get("id").toString());
        String localAuthenContextId = ssoCallback.afterLogin(accountId,response);
        map.put(ticket,localAuthenContextId);
        String bizAppUrl = ucSdkProperties.getBizAppUrl();
        if (bizAppUrl.contains("?")){
            bizAppUrl += "&localAuthenContextId=" +localAuthenContextId;
        } else {
            bizAppUrl += "?localAuthenContextId=" +localAuthenContextId;
        }
        response.encodeRedirectURL(bizAppUrl);
    }

    /**
     * 用于实现单点登出的接口.
     * @param request
     */
    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public void logout(HttpServletRequest request,HttpServletResponse response){
        String ticket = request.getParameter("ticket"); //TODO CAS调用这个接口时，参数名不是这个
        String localAuthenContextId = map.get(ticket);
        ssoCallback.postLogout(localAuthenContextId,response);
        map.remove(ticket);
    }

}
