package com.hcwl.uc.sdk.core.service.param;

import lombok.Data;
import lombok.ToString;

/**
 * @author zhangdx
 * @date 2022-06-17
 */
@Data
@ToString
public class GetOrganizationCountParams {
    /**
     * [可选]企业ID
     */
    private Long projectId;
}
