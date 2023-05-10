package com.hcwl.uc.sdk.core.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author zhangdx
 * @date 2022-06-15
 */
@Getter
@Setter
@ToString
public class Page<T> {

    /**
     * 总计记录数
     */
    private Long totalCount;

    /**
     * 记录列表
     */
    private List<T> elements;

}
