package com.hcwl.uc.sdk.core.service;

import com.hcwl.uc.sdk.core.exception.UCSdkException;

/**
 * 此类定义了认证上下文相关的方法.
 * @author zhangdx
 * @date 2022-06-02
 */
public interface IAuthenContextService {

    /**
     * 获取当前登录账户的ID
     * @return 账户ID
     */
    Long getAccountId() throws UCSdkException;

}
