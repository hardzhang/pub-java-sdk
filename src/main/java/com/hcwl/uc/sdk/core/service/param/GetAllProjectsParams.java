package com.hcwl.uc.sdk.core.service.param;

import lombok.Data;
import lombok.ToString;

/**
 * 获取项目列表（不分页）的参数类,参数之间是AND的关系.
 * @author zhangdx
 * @date 2022-06-20
 */
@Data
@ToString
public class GetAllProjectsParams {

    /**
     * [可选]应用系统ID
     */
    private Long applicationId;

    /**
     * [可选]账户ID
     */
    private Long accountId;

}
