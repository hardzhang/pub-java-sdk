package com.hcwl.uc.sdk.core.service.dto;

import lombok.Data;
import lombok.ToString;

/**
 * 资源类.
 * @author zhangdx
 * @date 2022-06-06
 */
@Data
@ToString
public class Resource {

    /**
     * 资源ID
     */
    private Long id;

    /**
     * 归属应用的ID
     */
    private Long applicationId;

    /**
     * 资源名
     */
    private String name;

    /**
     * 资源编码
     */
    private String code;

    /**
     * 资源属性
     */
    private String attributes;

    /**
     * 资源标签
     */
    private String tag;

    /**
     * 说明
     */
    private String desc;

}
