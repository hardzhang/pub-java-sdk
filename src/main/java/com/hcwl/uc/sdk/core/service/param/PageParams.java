package com.hcwl.uc.sdk.core.service.param;

import lombok.Data;

/**
 * 分页参数.
 * @author zhangdx
 * @date 2022-06-15
 */
@Data
public class PageParams {

    /**
     * [可选]偏移量,默认值为:0.
     */
    private Long offset = 0l;

    /**
     * [可选]每页最大记录数,取值范围:最小值1,最大值:500;默认值为10.
     */
    private Long limit = 10l;

}
