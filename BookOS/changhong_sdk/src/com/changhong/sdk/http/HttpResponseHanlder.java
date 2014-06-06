package com.changhong.sdk.http;

import java.io.InputStream;

import com.changhong.sdk.httpbean.ServiceResponse;
import com.lidroid.xutils.exception.HttpException;

public abstract interface HttpResponseHanlder
{
    public abstract void handleHttpResponse(String response, int rspCode,
            int requestId);
    
    public abstract void handleHttpResponse(String response, int requestId,
            InputStream is);
    
    public abstract void handleHttpException(HttpException error, String msg);
    
    public abstract void handleHttpResponse(ServiceResponse serviceRes,
            int requestId, InputStream is);
    
    public abstract void handleHttpResponse(ServiceResponse serviceRes,
            int requestId, InputStream is, int pos);
}