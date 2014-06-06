/*
 * Pactera Technology Co. Ltd. Copyright 2013, All rights reserved.
 * 文件名  :SoapHeader.java
 * 创建人  :zhangxurong
 * 创建时间:2013-6-17
*/

package com.changhong.sdk.httpbean;

import java.util.LinkedHashMap;
import java.util.Map;

import com.changhong.sdk.baseapi.StringUtils;
import com.changhong.sdk.http.SeqCache;
import com.changhong.sdk.http.TransferConstants;

/**
 * [简要描述]:通信消息头
 *
 * @author zhangxurong
 * @version 1.0, 2013-6-17
 */
public class Header
{
    private Map<String, String> params = new LinkedHashMap<String, String>();
    
    /**
     * 返回params属性
     * @return params属性
     */
    public Map<String, String> getParams()
    {
        return params;
    }
    
    /**
     * 设置params属性
     * @param params params属性
     */
    public void setParams(Map<String, String> params)
    {
        this.params.putAll(params);
    }
    
    public String get(String name)
    {
        if (StringUtils.isNotEmpty(name))
        {
            return this.params.get(name);
        }
        return null;
    }
    
    public void put(String name, String value)
    {
        if (StringUtils.isNotEmpty(name))
        {
            this.params.put(name, value);
        }
    }
    
    /**
     * 返回to属性
     * @return to属性
     */
    public String getTo()
    {
        return get(TransferConstants.COMM_HEADER_NAME_TO);
    }
    
    /**
     * 设置to属性
     * @param to to属性
     */
    public void setTo(String to)
    {
        put(TransferConstants.COMM_HEADER_NAME_TO, to);
    }
    
    /**
     * 返回toType属性
     * @return toType属性
     */
    public String getToType()
    {
        return get(TransferConstants.COMM_HEADER_NAME_TOTYPE);
    }
    
    /**
     * 设置toType属性
     * @param toType toType属性
     */
    public void setToType(String toType)
    {
        put(TransferConstants.COMM_HEADER_NAME_TOTYPE, toType);
    }
    
    /**
     * 返回from属性
     * @return from属性
     */
    public String getFrom()
    {
        return get(TransferConstants.COMM_HEADER_NAME_FROM);
    }
    
    /**
     * 设置from属性
     * @param from from属性
     */
    public void setFrom(String from)
    {
        put(TransferConstants.COMM_HEADER_NAME_FROM, from);
    }
    
    /**
     * 返回fromType属性
     * @return fromType属性
     */
    public String getFromType()
    {
        return get(TransferConstants.COMM_HEADER_NAME_FROMTYPE);
    }
    
    /**
     * 设置fromType属性
     * @param fromType fromType属性
     */
    public void setFromType(String fromType)
    {
        put(TransferConstants.COMM_HEADER_NAME_FROMTYPE, fromType);
    }
    
    /**
     * 返回messageType属性
     * @return messageType属性
     */
    public String getMessageType()
    {
        return get(TransferConstants.COMM_HEADER_NAME_MESSAGETYPE);
    }
    
    /**
     * 设置messageType属性
     * @param messageType messageType属性
     */
    public void setMessageType(String messageType)
    {
        put(TransferConstants.COMM_HEADER_NAME_MESSAGETYPE, messageType);
    }
    
    /**
     * 返回seq属性
     * @return seq属性
     */
    public String getSeq()
    {
        return get(TransferConstants.COMM_HEADER_NAME_SEQ);
    }
    
    /**
     * 设置seq属性
     * @param seq seq属性
     */
    public void setSeq(String seq)
    {
        put(TransferConstants.COMM_HEADER_NAME_SEQ, seq);
    }
    
    /**
     * 设置seq属性
     * @param seq seq属性
     */
    public void newSeq()
    {
        put(TransferConstants.COMM_HEADER_NAME_SEQ, SeqCache.newSeq());
    }
    
    /**
     * 返回statusCode属性
     * @return statusCode属性
     */
    public String getStatusCode()
    {
        return get(TransferConstants.COMM_HEADER_NAME_STATUSCODE);
    }
    
    /**
     * 设置statusCode属性
     * @param seq statusCode属性
     */
    public void setStatusCode(String statusCode)
    {
        put(TransferConstants.COMM_HEADER_NAME_STATUSCODE, statusCode);
    }
    
    /**
     * [简要描述]:
     * 
     * @author zhangxurong
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        if (null == params)
        {
            return null;
        }
        return params.toString();
    }
}
