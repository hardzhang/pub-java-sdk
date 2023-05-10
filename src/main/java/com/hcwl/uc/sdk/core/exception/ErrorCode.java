package com.hcwl.uc.sdk.core.exception;

import org.apache.commons.lang3.StringUtils;

/**
 * 错误码枚举类.
 * @author zhangdx
 * @date 2022-06-15
 */
public enum ErrorCode {

    INVALID_IDENTIFY_CODE, //无效的验证码
    PASSWORD_NOT_CORRECT, //账号或密码错误
    ACCOUNT_NOT_EXIST, //账户不存在
    ACCOUNT_DISABLED, //账户被冻结
    ACCOUNT_CANCELLED, //账户被注销
    INVALID_MOBILE, //无效的手机号
    INVALID_SMS_CODE, //无效的短信验证码
    REPEATED_SENDING_SMS_CODE, //重复发送短信验证码
    SMS_CODE_EXPIRED, //短信验证码已过期
    INVALID_SMS_TYPE, //无效的短信类型
    SMS_OVER_FREQUENT, //短信发送次数超过阀值
    INVALID_KEY, //无效的Key
    ORGANIZATION_NOT_EXIST,//组织不存在
    NODE_NOT_EXIST,//节点不存在

    INVALID_PROJECT_TYPE,//无效的项目类型值
    INVALID_PROJECT_STATUS,//无效的工程状态值
    INVALID_COUNTY_CODE,//无效的区县编码
    INVALID_CITY_CODE,//无效的地市编码
    INVALID_PROVINCE_CODE,//无效的省份编码

    MOBILE_REGISTERED, //手机号已被注册
    SEND_SMS_CODE_FAIL, //短信验证码发送失败

    INVALID_TOKEN,//无效的Token,
    INVALID_CLIENT_ID,//无效的Client Id值
    INVALID_CLIENT_SECRET,//无效的Client Secret值
    CLIENT_DISABLED,//Client被禁用

    REQUIRE_ARGUMENT, //缺少参数
    INVALID_ARGUMENT, //无效的参数
    ACCESS_DENIED,//拒绝访问
    UNAUTHORIZED,//未认证或认证信息无效
    UNSUPPORTED_AUTHORIZATION,//不支持的Authorization头
    INTERNAL_CLIENT_ERROR, //UCSDK端内部错误
    INTERNAL_SERVER_ERROR; //UC服务端内部错误

    public static ErrorCode nameOf(String name){
        for (ErrorCode errorCode:values()){
            if(StringUtils.equalsIgnoreCase(name,errorCode.name())){
                return errorCode;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
