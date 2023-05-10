package com.hcwl.uc.sdk.core.service.dto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 权限类.
 * @author zhangdx
 * @date 2022-06-06
 */
@Data
@ToString
public class Permission {

    /**
     * 权限ID
     */
    private Long id;

    /**
     * 归属应用的ID
     */
    private Long applicationId;

    /**
     * 权限名
     */
    private String name;

    /**
     * 权限编码
     */
    private String code;

    /**
     * 状态: 1 启用,0 禁用
     */
    private int status;

    /**
     * 说明
     */
    private String desc;

}
