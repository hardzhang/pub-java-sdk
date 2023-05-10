package com.hcwl.uc.sdk.core.service.impl;

import com.alibaba.fastjson.TypeReference;
import com.hcwl.uc.sdk.core.config.UcSdkConfigurationProperties;
import com.hcwl.uc.sdk.core.service.dto.Node;
import com.hcwl.uc.sdk.core.service.dto.Organization;
import com.hcwl.uc.sdk.core.service.dto.Page;
import com.hcwl.uc.sdk.core.exception.UCSdkException;
import com.hcwl.uc.sdk.core.service.IOrgService;
import com.hcwl.uc.sdk.core.service.param.GetOrganizationCountParams;
import com.hcwl.uc.sdk.core.service.param.GetOrganizationsParams;
import com.hcwl.uc.sdk.core.utils.UcAdminRestApiClient;
import com.hcwl.uc.sdk.core.utils.UcClientRestApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author luoxy
 * @date 2022-06-08
 */
@Service("ucOrgService")
public class OrgServiceImpl implements IOrgService {
    @Autowired
    private UcSdkConfigurationProperties ucSdkProperties;

    public Page<Organization> getOrganizations(GetOrganizationsParams params) throws UCSdkException {
        String host = ucSdkProperties.getUri();
        String url = host + "/auth/tenants/page-tenant-sdk?name={name}&tenantCode={tenantCode}&offset={offset}&limit={limit}";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", params.getName());
        map.put("tenantCode", params.getCode());
        map.put("offset", params.getOffset());
        map.put("limit", params.getLimit());
        return UcAdminRestApiClient.get(url, map,new UcClientRestApiClient.ResultType(new TypeReference<Page<Organization>>(){}));
    }

    public Organization getOrganization(Long orgId) throws UCSdkException {
        Organization organization = new Organization();
        String host = ucSdkProperties.getUri();
        String url = host + "/auth/tenants/sdk/" + orgId;
        return UcAdminRestApiClient.get(url, null,new UcClientRestApiClient.ResultType(Organization.class));
    }

    public Node getNode(Long nodeId) throws UCSdkException {
        Node node = new Node();
        String host = ucSdkProperties.getUri();
        String url = host + "/auth/departs/" + nodeId;
        LinkedHashMap result = UcAdminRestApiClient.get(url, null, new UcClientRestApiClient.ResultType(LinkedHashMap.class));
        node.setId(Long.parseLong((String) result.get("id")));
        node.setParentNodeId(Long.parseLong((String) result.get("parentId")));
        node.setOrgId(Long.parseLong((String) result.get("tenantId")));
        node.setType(Integer.parseInt((String) result.get("orgType")));
        node.setName((String) result.get("departName"));
        node.setShortName((String) result.get("departNameAbbr"));
        node.setDesc((String) result.get("description"));
        return node;
    }

    public Long getOrganizationCount(GetOrganizationCountParams params) throws UCSdkException {
        String host = ucSdkProperties.getUri();
        String url = host + "/auth/tenants/count-tenant-sdk?projectId={projectId}";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("projectId", params.getProjectId());
        return UcAdminRestApiClient.get(url, map, new UcClientRestApiClient.ResultType(Long.class));
    }
}
