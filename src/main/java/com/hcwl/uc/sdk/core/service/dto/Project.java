package com.hcwl.uc.sdk.core.service.dto;

import lombok.Data;
import lombok.ToString;

/**
 * 项目类.
 * @author zhangdx
 * @date 2022-06-06
 */
@Data
@ToString
public class Project {

    /**
     * 项目ID
     */
    private Long id;

    /**
     * 项目名
     */
    private String name;

    /**
     * 项目类型
     * 数据字典，见:https://alidocs.dingtalk.com/i/team/dy0mVMn0Lnq3RX89/docs/dy0mVDLZdexkjz89
     */
    private String type;

    /**
     * 工程状态
     * 数据字典，见:https://alidocs.dingtalk.com/i/team/dy0mVMn0Lnq3RX89/docs/dy0mVDLZdexkjz89
     */
    private String status;

    /**
     * 区划
     * 数据字典，见:https://alidocs.dingtalk.com/i/team/dy0mVMn0Lnq3RX89/docs/dy0mVDLZdexkjz89
     */
    private Districts districts;

    /**
     * 项目在应用系统侧的ID
     */
    private String bizId;

}
