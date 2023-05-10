package com.hcwl.uc.sdk.core.service.impl;

import com.alibaba.fastjson.TypeReference;
import com.hcwl.uc.sdk.core.config.UcSdkConfigurationProperties;
import com.hcwl.uc.sdk.core.service.dto.Account;
import com.hcwl.uc.sdk.core.service.dto.Permission;
import com.hcwl.uc.sdk.core.service.dto.Role;
import com.hcwl.uc.sdk.core.exception.UCSdkException;
import com.hcwl.uc.sdk.core.service.IPermissionService;
import com.hcwl.uc.sdk.core.utils.UcAdminRestApiClient;
import com.hcwl.uc.sdk.core.utils.UcClientRestApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author luoxy
 * @date 2022-06-15
 */
@Service("ucPermissionService")
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private UcSdkConfigurationProperties ucSdkProperties;

    public List<Role> getRoles(Long orgId, Long accountId) throws UCSdkException {
        String host = ucSdkProperties.getUri();
        String url = host + "/auth/users/"+accountId+"/tenants/"+orgId+"/sdk";
        return UcAdminRestApiClient.get(url, null, new UcClientRestApiClient.ResultType(new TypeReference<List<Role>>(){}));
    }

    public List<Permission> getPermissions(Long applicationId, Long accountId) throws UCSdkException {
        String host = ucSdkProperties.getUri();
        String url = host + "/auth/permissions/sdk/users/"+accountId+"/apps/"+applicationId;
        return UcAdminRestApiClient.get(url, null,new UcClientRestApiClient.ResultType(new TypeReference<List<Permission>>(){}));
    }

    public List<Role> getApplicationDefautRoles(Long applicationId) throws UCSdkException {
        throw new UnsupportedOperationException("此方法还未实现");
    }

    public List<Account> getAccountWithApplicationDefautRoles(Long applicationId, Long[] roleIds) throws UCSdkException {
        throw new UnsupportedOperationException("此方法还未实现");
    }

}
