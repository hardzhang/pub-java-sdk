package com.hcwl.uc.sdk.core.service.dto;

import lombok.Data;
import lombok.ToString;

/**
 * 模块类.
 * @author zhangdx
 * @date 2022-06-06
 */
@Data
@ToString
public class Module {

    /**
     * 模块ID
     */
    private Long id;

    /**
     * 父模块ID
     */
    private Long parentModuleId;

    /**
     * 归属应用的ID
     */
    private Long applicationId;

    /**
     * 模块名
     */
    private String name;

    /**
     * 说明
     */
    private String desc;

}
