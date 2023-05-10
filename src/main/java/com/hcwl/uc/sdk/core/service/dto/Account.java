package com.hcwl.uc.sdk.core.service.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 账户类.
 * @author zhangdx
 * @date 2022-06-06
 */
@Data
@ToString
public class Account {

    /**
     * 账户ID
     */
    private Long id;
    /**
     * 用户名(账号)
     */
    @JsonProperty("user_name")
    private String userName;

    /**
     * 真实姓名
     */
    @JsonProperty("real_name")
    private String realName;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮件
     */
    private String email;

    /**
     * 性别: 1 男,2 女
     */
    private Integer sex;

    /**
     * 状态: 1 正常,2 冻结
     */
    private Integer status;



}


