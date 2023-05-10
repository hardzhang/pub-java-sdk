package com.hcwl.uc.sdk.core.service.dto;

import lombok.Data;
import lombok.ToString;

/**
 * 区划
 * @author zhangdx
 * @date 2022-06-07
 */
@Data
@ToString
public class Districts {

    /**
     * 省份的区划编码
     */
    private String province;
    /**
     * 市的区划编码
     */
    private String city;

    /**
     * 区县的区划编码
     */
    private String county;

}
