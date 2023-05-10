package com.hcwl.uc.sdk.core.exception;

import com.hcwl.uc.sdk.core.service.dto.ResponseResult;
import lombok.Data;
import lombok.ToString;

/**
 * @author zhangdx
 * @date 2022-06-02
 */
@Data
@ToString
public class UCSdkException extends RuntimeException{

    private ErrorCode code;
    private String message;

    public UCSdkException(){
    }

    public UCSdkException(ErrorCode code) {
        this.code = code;
    }

    public UCSdkException(String message, ErrorCode code) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public UCSdkException(String message, Throwable cause, ErrorCode code) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }

    public UCSdkException(Throwable cause, ErrorCode code) {
        super(cause);
        this.code = code;
    }

    public UCSdkException(ResponseResult responseResult) {
        if(responseResult!=null){
            this.setMessage(responseResult.getMessage());
            if(responseResult.getErrorCode()!=null){
                this.setCode(responseResult.getErrorCode());
            }else {
                this.setCode(ErrorCode.INTERNAL_SERVER_ERROR);
            }
        }else {
            this.setCode(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

}
