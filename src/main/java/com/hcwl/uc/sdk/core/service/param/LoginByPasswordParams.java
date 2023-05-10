package com.hcwl.uc.sdk.core.service.param;

import lombok.Data;
import lombok.ToString;

/**
 * 密码登录的参数类.
 * @author zhangdx
 * @date 2022-06-06
 */
@Data
@ToString
public class LoginByPasswordParams{

    /**
     * [必须]登录凭证
     */
    private String loginName;
    /**
     * [必须]密码
     */
    private String password;

    /**
     * [必须]图形验证码的KEY,调用IAuthenService#getCaptcha(GetCaptchaParams)方法是传递的GetCaptchaParams.key参数的值
     */
    private String captchaKey;

    /**
     * [必须]图形验证码的值
     */
    private String captchaValue;

}
