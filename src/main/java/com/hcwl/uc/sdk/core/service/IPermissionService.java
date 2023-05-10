package com.hcwl.uc.sdk.core.service;

import com.hcwl.uc.sdk.core.service.dto.Account;
import com.hcwl.uc.sdk.core.service.dto.Permission;
import com.hcwl.uc.sdk.core.service.dto.Role;
import com.hcwl.uc.sdk.core.exception.UCSdkException;

import java.util.List;

/**
 * 此类定义了权限相关的方法.
 * @author zhangdx
 * @date 2022-06-02
 */
public interface IPermissionService {

    /**
     * 获取账户在指定组织下的角色列表.
     * 说明:
     *   除了不满足过滤条件的角色，以下情况的角色不会被返回:
     *     1.如果权限归属的角色为禁用状态，那么此权限不返回;
     *
     * @param orgId  组织ID
     * @param accountId
     * @return
     */
    List<Role> getRoles(Long orgId, Long accountId) throws UCSdkException;

    /**
     * 获取账户在指定应用下的权限列表.
     * 说明:
     *    除了不满足过滤条件的权限，以下情况的权限也不会被返回:
     *      1.如果权限的状态为禁用，那么此权限不返回;
     *      2.如果权限归属的角色为禁用状态，那么此权限不返回;
     *
     * @param applicationId
     * @param accountId
     * @return
     */
    List<Permission> getPermissions(Long applicationId, Long accountId) throws UCSdkException;

    /**
     * 获取应用默认角色列表.
     * @param applicationId
     * @return
     * @throws UCSdkException
     */
    List<Role> getApplicationDefautRoles(Long applicationId) throws UCSdkException;

    /**
     * 获取拥有应用默认角色的账户列表.
     * @param applicationId
     * @param roleIds
     * @return
     * @throws UCSdkException
     */
    List<Account> getAccountWithApplicationDefautRoles(Long applicationId, Long[] roleIds) throws UCSdkException;

}
