package com.hcwl.uc.sdk.core.service.dto;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 设备类.
 * @author zhangdx
 * @date 2022-06-06
 */
@Data
@ToString
public class Device {

    /**
     * 设备ID
     */
    private Long id;

    /**
     * 所属项目ID
     */
    private Long projectId;

    /**
     * 设备类型
     * 数据字段，见:https://alidocs.dingtalk.com/i/team/dy0mVMn0Lnq3RX89/docs/dy0mVDLZdexkjz89
     */
    private String type;

    /**
     * 设备名
     */
    private String name;

}
