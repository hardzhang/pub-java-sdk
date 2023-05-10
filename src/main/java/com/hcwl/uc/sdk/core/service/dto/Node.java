package com.hcwl.uc.sdk.core.service.dto;

import lombok.Data;
import lombok.ToString;

/**
 * 节点（部门）类.
 * @author zhangdx
 * @date 2022-06-06
 */
@Data
@ToString
public class Node {

    /**
     * 节点ID
     */
    private Long id;

    /**
     * 父节点ID
     */
    private Long parentNodeId;

    /**
     * 归属的组织的ID
     */
    private Long orgId;


    /**
     * 节点类型
     */
    private int type;

    /**
     * 节点名
     */
    private String name;

    /**
     * 节点简称
     */
    private String shortName;

    /**
     * 说明
     */
    private String desc;

}
