/*
 * Pactera Technologies Co., Ltd. Copyright 2013, All rights reserved.
 * 文件名  :ServiceResponse.java
 * 创建人  :b
 * 创建时间:2013-11-21
*/

package com.changhong.sdk.httpbean;

/**
 * [简要描述]:通信响应消息对象
 *
 * @author zxr
 * @version 1.0, 2013-11-21
 */
public class ServiceResponse
{
    private Header header;
    
    private ResponseBody body;
    
    public ServiceResponse()
    {
        this.header = new Header();
        this.body = new ResponseBody();
    }
    
    public ServiceResponse(Header header, ResponseBody body)
    {
        this.header = header;
        this.body = body;
    }
    
    public ServiceResponse(Header header)
    {
        this.header = header;
        this.body = new ResponseBody();
    }
    
    public ServiceResponse(ResponseBody body)
    {
        this.header = new Header();
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
    public ResponseBody getBody()
    {
        return body;
    }
    
    /**
     * 设置body属性
     * @param body body属性
     */
    public void setBody(ResponseBody body)
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
        return "ServiceResponse [Header=" + header + ", Body=" + body + "]";
    }
}
