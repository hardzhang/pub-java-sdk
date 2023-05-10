package com.hcwl.uc.sdk.core.service.param;

import lombok.Data;
import lombok.ToString;

/**
 * 获取组织(企业)列表参数.
 * @author zhangdx
 * @date 2022-06-17
 */
@Data
@ToString
public class GetOrganizationsParams extends PageParams{

    /**
     * [可选]组织名
     */
     private String name;

    /**
     * [可选]组织编码
     */
    private String code;

}
