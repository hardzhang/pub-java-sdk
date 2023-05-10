package com.hcwl.uc.sdk.core.service.dto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 角色类.
 * @author zhangdx
 * @date 2022-06-06
 */
@Data
@ToString
public class Role {

    /**
     * 角色ID
     */
    private Long id;

    /**
     * 归属组织的ID
     */
    private Long orgId;

    /**
     * 角色名
     */
    private String name;

    /**
     * 角色编码
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
