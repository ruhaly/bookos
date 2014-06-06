/*
 * Pactera Technologies Co., Ltd. Copyright 2013, All rights reserved.
 * 文件名  :ServiceRequest.java
 * 创建人  :b
 * 创建时间:2013-11-21
*/

package com.changhong.sdk.httpbean;

/**
 * [简要描述]:通信请求消息对象
 *
 * @author zxr
 * @version 1.0, 2013-11-21
 */
public class ServiceRequest
{
    private Header header;
    
    private RequestBody body;
    
    public ServiceRequest()
    {
        this.header = new Header();
        this.body = new RequestBody();
    }
    
    public ServiceRequest(Header header, RequestBody body)
    {
        this.header = header;
        this.body = body;
    }
    
    /**
     * 返回header属性
     * @return header属性
     */
    public Header getHeader()
    {
        return header;
    }
    
    /**
     * 设置header属性
     * @param header header属性
     */
    public void setHeader(Header header)
    {
        this.header = header;
    }
    
    /**
     * 返回body属性
     * @return body属性
     */
    public RequestBody getBody()
    {
        return body;
    }
    
    /**
     * 设置body属性
     * @param body body属性
     */
    public void setBody(RequestBody body)
    {
        this.body = body;
    }
    
    /**
     * [简要描述]:
     * 
     * @author b
     * @return
     * @exception 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "ServiceRequest [Header=" + header + ", Body=" + body + "]";
    }
}
