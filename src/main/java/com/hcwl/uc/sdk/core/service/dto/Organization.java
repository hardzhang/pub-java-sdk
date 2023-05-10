package com.hcwl.uc.sdk.core.service.dto;

import lombok.Data;
import lombok.ToString;

/**
 * 组织(企业)类.
 * @author zhangdx
 * @date 2022-06-06
 */
@Data
@ToString
public class Organization {

    /**
     * 组织ID
     */
    private Long id;

    /**
     * 组织编码
     */
    private String code;

    /**
     * 组织名
     */
    private String name;

    /**
     * 组织简称
     */
    private String shortName;

    /**
     * 组织类型
     * 数据字典，见:https://alidocs.dingtalk.com/i/team/dy0mVMn0Lnq3RX89/docs/dy0mVDLZdexkjz89
     */
    private String type;

    /**
     * 地址
     */
    private String address;

    /**
     * 统一社会信用代码
     */
    private String usci;

    /**
     * 说明
     */
    private String desc;

    /**
     * 超管账户
     */
    private Account adminAccount;

    /**
     * 区划
     * 数据字典，见:https://alidocs.dingtalk.com/i/team/dy0mVMn0Lnq3RX89/docs/dy0mVDLZdexkjz89
     */
    private Districts districts;

    /**
     * 联系人姓名
     */
    private String contactName;
    /**
     * 联系人电话
     */
    private String contactPhone;

    /**
     * 企业邮箱
     */
    private String email;

    /**
     * 企业网址
     */
    private String url;

    /**
     * 是否平台组织
     */
    private Boolean isPlatformOrg;

}
