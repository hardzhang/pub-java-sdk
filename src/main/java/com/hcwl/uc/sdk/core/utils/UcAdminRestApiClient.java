package com.hcwl.uc.sdk.core.utils;

import com.alibaba.fastjson.JSON;
import com.hcwl.uc.sdk.core.exception.ErrorCode;
import com.hcwl.uc.sdk.core.exception.UCSdkException;
import com.hcwl.uc.sdk.core.service.dto.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import java.util.Map;

/**
 * 此类提供调用UC服务端提供的面向管理端(如UC管理后台等)的restful api的方法.
 * @author zhangdx
 * @date 2022-07-04
 */
@Slf4j
public abstract class UcAdminRestApiClient extends UcClientRestApiClient {

    /**
     * get请求
     *
     * @param url
     * @param uriVariables
     * @return
     */
    public static  <T> T get(String url, Map<String, Object> uriVariables,ResultType resultType) {
        return exchangeForResult(url,HttpMethod.GET,null,null,uriVariables,resultType);
    }

    /**
     * post请求
     *
     * @param url
     * @param body
     * @return
     */
    public static <T> T post(String url, Object body,ResultType resultType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return exchangeForResult(url,HttpMethod.POST,body,headers,null,resultType);
    }

    public static <T> T exchangeForResult(String url,HttpMethod method,Object body,HttpHeaders headers,Map<String,Object> uriVariables,ResultType resultType){
        ResponseResult responseResult = exchange(url,method,body,headers,uriVariables,ResponseResult.class,false);
        if (responseResult.getSuccess()){
            if(resultType!=null){
                return transformResult(responseResult.getResult(),resultType);
            }else {
                return null;
            }
        }else {
            throw new UCSdkException(responseResult);
        }
    }

    private static <T> T transformResult(Object result,ResultType resultType){
        try {
            if(resultType.getClazz()!=null){
                Class resultClass = resultType.getClazz();
                if(result==null || result.getClass().isAssignableFrom(resultClass)){
                    return (T)result;
                }
                if(String.class.equals(resultClass)){
                    return (T)result.toString();
                }
                if(Long.class.equals(resultClass) && String.class.equals(result.getClass())){
                    return (T)Long.valueOf((String)result);
                }
                if(Integer.class.equals(resultClass) && String.class.equals(result.getClass())){
                    return (T)Integer.valueOf((String)result);
                }
                String json = JSON.toJSONString(result);
                return (T)JSON.parseObject(json,resultType.getClazz());
            }else {
                String json = JSON.toJSONString(result);
                return (T)JSON.parseObject(json,resultType.getTypeReference());
            }
        }catch (Exception e){
            throw new UCSdkException("transform "+result.getClass()+"["+result+"] to "+resultType+" failed.casued by:"+e.getMessage(),e,ErrorCode.INTERNAL_CLIENT_ERROR);
        }
    }

}
