package com.hcwl.uc.sdk.core.service;


import com.hcwl.uc.sdk.core.service.dto.Account;
import com.hcwl.uc.sdk.core.service.dto.AccountOrgRelation;
import com.hcwl.uc.sdk.core.exception.UCSdkException;
import com.hcwl.uc.sdk.core.service.dto.Page;
import com.hcwl.uc.sdk.core.service.param.FindAccountParams;
import com.hcwl.uc.sdk.core.service.param.GetAccountsParams;

import java.util.List;

/**
 * 此类定义了账户相关的方法.
 * @author zhangdx
 * @date 2022-06-02
 */
public interface IAccountService {

    /**
     * 获取账户信息.
     * @param accountId
     * @return
     * @throws UCSdkException
     */
    Account getAccount(Long accountId) throws UCSdkException;

    /**
     * 根据条件获取账户信息.
     * @param params
     * @return
     * @throws UCSdkException
     */
    Account findAccount(FindAccountParams params) throws UCSdkException;

    /**
     * 获取账户与组织的关系信息.
     * @param accountId
     * @return
     * @throws UCSdkException
     */
    List<AccountOrgRelation> getAccountOrgRelations(Long accountId) throws UCSdkException;

    /**
     * 获取账户列表.
     * @param params
     * @return
     * @throws UCSdkException
     */
    Page<Account> getAccounts(GetAccountsParams params) throws UCSdkException;
}
