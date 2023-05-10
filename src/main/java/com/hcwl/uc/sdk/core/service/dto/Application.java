package com.hcwl.uc.sdk.core.service.dto;

import lombok.Data;
import lombok.ToString;

/**
 * 应用类.
 * @author zhangdx
 * @date 2022-06-06
 */
@Data
@ToString
public class Application {

    /**
     * 应用ID
     */
    private Long id;

    /**
     * 应用名
     */
    private String name;

    /**
     * 说明
     */
    private String desc;

}
