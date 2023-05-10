package com.hcwl.uc.sdk.core.service;


import com.hcwl.uc.sdk.core.service.dto.Node;
import com.hcwl.uc.sdk.core.service.dto.Organization;
import com.hcwl.uc.sdk.core.exception.UCSdkException;
import com.hcwl.uc.sdk.core.service.dto.Page;
import com.hcwl.uc.sdk.core.service.param.GetOrganizationCountParams;
import com.hcwl.uc.sdk.core.service.param.GetOrganizationsParams;

/**
 * 此类定义了组织(企业)相关的方法.
 * @author zhangdx
 * @date 2022-06-02
 */
public interface IOrgService {

     /**
      * 获取组织列表.
      * @param params
      * @return
      * @throws UCSdkException
      */
     Page<Organization> getOrganizations(GetOrganizationsParams params) throws UCSdkException;

     /**
      * 获取组织信息.
      * @param orgId
      * @return
      * @throws UCSdkException
      */
     Organization getOrganization(Long orgId) throws UCSdkException;

     /**
      * 获取节点信息.
      * @param nodeId
      * @return
      * @throws UCSdkException
      */
     Node getNode(Long nodeId) throws UCSdkException;

     /**
      * 获取组织数.
      * @param params
      * @return
      */
     Long getOrganizationCount(GetOrganizationCountParams params) throws UCSdkException;

}
