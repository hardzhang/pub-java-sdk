package com.hcwl.uc.sdk.core.service.impl;

import com.alibaba.fastjson.TypeReference;
import com.hcwl.uc.sdk.core.config.UcSdkConfigurationProperties;
import com.hcwl.uc.sdk.core.service.dto.Account;
import com.hcwl.uc.sdk.core.service.dto.AccountOrgRelation;
import com.hcwl.uc.sdk.core.service.dto.Page;
import com.hcwl.uc.sdk.core.exception.UCSdkException;
import com.hcwl.uc.sdk.core.service.IAccountService;
import com.hcwl.uc.sdk.core.service.param.FindAccountParams;
import com.hcwl.uc.sdk.core.service.param.GetAccountsParams;
import com.hcwl.uc.sdk.core.utils.UcAdminRestApiClient;
import com.hcwl.uc.sdk.core.utils.UcClientRestApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author luoxy
 * @date 2022-06-08
 */
@Service("ucAccountService")
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private UcSdkConfigurationProperties ucSdkProperties;

    @Override
    public Account getAccount(Long accountId) throws UCSdkException {
        Account account = new Account();
        String host = ucSdkProperties.getUri();
        String url = host + "/auth/users/" + accountId;
        LinkedHashMap result = UcAdminRestApiClient.get(url, null, new UcClientRestApiClient.ResultType(LinkedHashMap.class));
        account.setId(Long.parseLong(result.get("id").toString()));
        account.setMobile(result.get("phone").toString());
        account.setSex((Integer) result.get("sex"));
        account.setUserName(result.get("username").toString());
        account.setStatus(Integer.parseInt(result.get("status").toString()));
        return account;
    }

    @Override
    public Account findAccount(FindAccountParams params) throws UCSdkException {
        String url = "/uc/client/v1.0/accounts/actions/find_one";
        return UcClientRestApiClient.post(url, params,new UcClientRestApiClient.ResultType(Account.class),true);
    }

    @Override
    public List<AccountOrgRelation> getAccountOrgRelations(Long accountId) throws UCSdkException {
        String host = ucSdkProperties.getUri();
        String url = host + "/auth/users/" + accountId + "/list-user-tenant-relation";
        return  UcAdminRestApiClient.get(url, null,new UcClientRestApiClient.ResultType(new TypeReference<List<AccountOrgRelation>>(){}));
    }

    @Override
    public Page<Account> getAccounts(GetAccountsParams params) throws UCSdkException {
        String url = "/uc/client/v1.0/accounts";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("roleId", params.getRoleId());
        map.put("projectId", params.getProjectId());
        map.put("offset", params.getOffset());
        map.put("limit", params.getLimit());
        return UcClientRestApiClient.post(url, map,new UcClientRestApiClient.ResultType(new TypeReference<Page<Account>>(){}),true);
    }
}
