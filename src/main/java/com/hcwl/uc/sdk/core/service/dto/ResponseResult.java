package com.hcwl.uc.sdk.core.service.dto;

import com.hcwl.uc.sdk.core.exception.ErrorCode;
import lombok.Data;

@Data
public class ResponseResult {
    private Boolean success;
    private Integer code;
    private ErrorCode errorCode;
    private String message;
    private Object result;
    private String onlTable;
}
