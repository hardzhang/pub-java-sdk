package com.hcwl.uc.sdk.core.service.param;

import lombok.Data;

/**
 * 获取账户类别接口的参数,参数之间是AND的关系.
 * @author zhangdx
 * @date 2022-06-29
 */
@Data
public class GetAccountsParams extends PageParams{

    /**
     * [可选]项目ID
     */
    private Long projectId;

    /**
     * [可选]角色ID
     */
    private Long roleId;

}
