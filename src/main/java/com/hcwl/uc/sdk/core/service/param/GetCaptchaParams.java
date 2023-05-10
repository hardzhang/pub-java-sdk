package com.hcwl.uc.sdk.core.service.param;

import lombok.Data;
import lombok.ToString;

/**
 * 获取图形验证码的参数类.
 * @author zhangdx
 * @date 2022-06-06
 */
@Data
@ToString
public class GetCaptchaParams {

    /**
     * [必须]图形验证码的key,需要调用方保证全局唯一.
     */
    private String key;
}
