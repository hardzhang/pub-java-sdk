package com.hcwl.uc.sdk.core.service.param;

import lombok.Data;
import lombok.ToString;

/**
 * 获取角色列表参数,参数之间是AND的关系.
 * @author zhangdx
 * @date 2022-06-22
 */
@Data
@ToString
public class GetRolesParams {

    /**
     * [可选]账户ID
     */
    private Long accountId;

    /**
     * [可选]组织ID
     */
    private Long orgId;

}
