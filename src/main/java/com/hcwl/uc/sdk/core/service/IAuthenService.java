package com.hcwl.uc.sdk.core.service;

import com.hcwl.uc.sdk.core.service.dto.Account;
import com.hcwl.uc.sdk.core.exception.UCSdkException;
import com.hcwl.uc.sdk.core.service.param.GetCaptchaParams;
import com.hcwl.uc.sdk.core.service.param.LoginByPasswordParams;
import com.hcwl.uc.sdk.core.service.param.LoginBySmsCodeParams;
import com.hcwl.uc.sdk.core.service.param.SendSmsCodeParams;

/**
 * 此类定义了认证相关的方法.
 * @author zhangdx
 * @date 2022-06-02
 */
public interface IAuthenService {

    /**
     * 密码登录.
     * @param params
     * @return 登录账户信息
     * @exception UCSdkException 失败时，抛出此异常。其中UCSdkException对象的code字段的表示具体错误。
     *
     * 错误代码说明:
     *    REQUIRE_ARGUMENT 缺少参数
     *    INVALID_IDENTIFY_CODE 图形验证码错误
     *    PASSWORD_NOT_CORRECT ⽤户名或密码错误（⽤户名不存在也是报该错误）
     *    ACCOUNT_DISABLE 账户被冻结
     *    ACCOUNT_CANCELLED 账户被注销
     *    INTERNAL_SERVER_ERROR 内部错误
     *
     */
    Account loginByPassword(LoginByPasswordParams params) throws UCSdkException;

    /**
     * 短信验证码登录.
     * @param params
     * @return 登录账户信息
     * @exception UCSdkException 失败时，抛出此异常。其中UCSdkException对象的code字段的表示具体错误。
     *
     * 错误代码说明:
     *    REQUIRE_ARGUMENT 缺少参数
     *    INVALID_MOBILE 无效的手机号
     *    ACCOUNT_NOT_EXIST 账户不存在
     *    INVALID_SMS_CODE 无效的短信验证码
     *    ACCOUNT_DISABLED 账户被冻结
     *    ACCOUNT_CANCELLED 账户被注销
     *    INTERNAL_SERVER_ERROR 内部错误
     */
    Account loginBySmsCode(LoginBySmsCodeParams params) throws UCSdkException;

    /**
     * 发送短信验证码.
     * @param params
     * @exception UCSdkException 失败时，抛出此异常。其中UCSdkException对象的code字段的表示具体错误。
     *
     * 错误代码说明:
     *    REQUIRE_ARGUMENT 缺少参数
     *    INVALID_MOBILE 无效的手机号
     *    INVALID_SMS_TYPE 无效的短信类型
     *    ACCOUNT_NOT_EXIST 账户不存在
     *    REPEATED_SENDING_SMS_CODE 重复发送，上一次发送的短信验证码还有效
     *    MOBILE_REGISTERED 手机号已被注册
     *    SEND_SMS_CODE_FAIL 短信验证码发送失败
     *    INTERNAL_SERVER_ERROR 内部错误
     *
     */
    void sendSmsCode(SendSmsCodeParams params) throws UCSdkException;

    /**
     * 获取图形验证码.
     * @param params
     * @return 图形验证
     * @exception UCSdkException 失败时，抛出此异常。其中UCSdkException对象的code字段的表示具体错误。
     *
     * 错误代码说明:
     *    REQUIRE_ARGUMENT 缺少参数
     *    INVALID_KEY 无效的KEY
     *    INTERNAL_SERVER_ERROR 内部错误
     */
    String getCaptcha(GetCaptchaParams params) throws UCSdkException;

}
