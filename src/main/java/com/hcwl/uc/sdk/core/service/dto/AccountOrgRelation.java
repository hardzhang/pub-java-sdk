package com.hcwl.uc.sdk.core.service.dto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 账户与组织关系类.
 * @author zhangdx
 * @date 2022-06-06
 */
@Data
@ToString
public class AccountOrgRelation {

    /**
     * 账户ID
     */
    private Long accountId;

    /**
     * 关联的组织
     */
    private Organization organization;

    /**
     * 所在节点
     */
    private List<Node> nodes;

    /**
     * 职务列表
     */
    private List<Position> positions;

    /**
     * 工号
     */
    private String orgCode;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 状态: 1 正常,2 冻结
     */
    private int status;

}
