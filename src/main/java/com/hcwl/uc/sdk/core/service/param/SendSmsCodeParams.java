package com.hcwl.uc.sdk.core.service.param;

import lombok.Data;
import lombok.ToString;

/**
 * 发送短信验证码的参数类.
 * @author zhangdx
 * @date 2022-06-06
 */
@Data
@ToString
public class SendSmsCodeParams {

    /**
     * [必须]手机号
     */
    private String mobile;

    /**
     * [必须]短信类型:0 登录短信,1 注册短信,2 忘记密码
     */
    private int smsType;

}
