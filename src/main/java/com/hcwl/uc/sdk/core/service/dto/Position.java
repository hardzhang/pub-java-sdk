package com.hcwl.uc.sdk.core.service.dto;

import lombok.Data;
import lombok.ToString;

/**
 * 职务类.
 * @author zhangdx
 * @date 2022-06-06
 */
@Data
@ToString
public class Position {

    /**
     * 职务ID
     */
    private Long id;

    /**
     * 职务名称
     */
    private String name;


}
