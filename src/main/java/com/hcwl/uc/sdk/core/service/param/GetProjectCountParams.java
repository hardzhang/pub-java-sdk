package com.hcwl.uc.sdk.core.service.param;

import lombok.Data;
import lombok.ToString;

/**
 * 获取项目数的参数类.
 * @author zhangdx
 * @date 2022-06-13
 */
@Data
@ToString
public class GetProjectCountParams {

    /**
     * [可选]应用系统ID
     */
    private Long applicationId;

    /**
     * [可选]账户ID
     */
    private Long accountId;

    /**
     * [可选]组织ID
     */
    private Long orgId;

}
