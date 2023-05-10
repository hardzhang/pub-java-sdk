package com.hcwl.uc.sdk.sso;

import com.hcwl.uc.sdk.core.exception.UCSdkException;

import javax.servlet.http.HttpServletResponse;


/**
 * 应用系统实现的SPI接口，实现应用系统本地认证上下文的管理.
 * @author zhangdx
 * @date 2022-06-02
 */
public interface ISSOCallback {

     /**
      * 用户通过SSO进入应用系统时，SDK在完成SSO处理逻辑之后调用，用于创建应用系统本地认证上下文，如创建本地的会话等.
      * @param accountId  当前登录的账户的账户的ID
      * @param response
      * @return 本地认证上下文标识
      * @exception UCSdkException
      */
     String afterLogin(Long accountId, HttpServletResponse response) throws UCSdkException;

     /**
      * 在用户进行SSO登出时，SDK调用此方法，用于销毁应用系统本地认证上下文，如删除本地的会话等.
      * @param localAuthenContextId afterLogin()方法返回的本地认证上下文标识
      * @param response
      * @exception UCSdkException
      */
     void postLogout(String localAuthenContextId,HttpServletResponse response) throws UCSdkException;

}
