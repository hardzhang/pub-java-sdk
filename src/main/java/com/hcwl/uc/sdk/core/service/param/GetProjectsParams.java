package com.hcwl.uc.sdk.core.service.param;

import com.hcwl.uc.sdk.core.service.dto.Districts;
import lombok.Data;
import lombok.ToString;

/**
 * 获取项目列表的参数类,参数之间是AND的关系.
 * @author zhangdx
 * @date 2022-06-07
 */
@Data
@ToString
public class GetProjectsParams extends PageParams{

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

    /**
     * [可选]项目名称
     */
    private String name;

    /**
     * [可选]项目类型
     */
    private String type;

    /**
     * [可选]工程状态
     */
    private String status;

    /**
     * [可选]区划
     */
    private Districts districts;


}
