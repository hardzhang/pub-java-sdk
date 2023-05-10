package com.hcwl.uc.sdk.core.service.param;

import lombok.Data;
import lombok.ToString;

/**
 * 短信验证码登录的参数类.
 * @author zhangdx
 * @date 2022-06-06
 */
@Data
@ToString
public class LoginBySmsCodeParams {

    /**
     * [必须]手机号
     */
    private String mobile;

    /**
     * [必须]短信验证码
     */
    private String smsCode;

}
