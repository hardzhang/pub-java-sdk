package com.hcwl.uc.sdk.core.service.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 根据条件获取账户信息接口的参数,条件采取等值比较,条件之间是AND的关系,必须有一个条件不为空.
 * @author zhangdx
 * @date 2022-07-08
 */
@Data
public class FindAccountParams {

    /**
     * 用户名(账号)
     */
    @JsonProperty("user_name")
    private String userName;

}
