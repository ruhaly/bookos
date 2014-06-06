/*
 * Pactera Technology Co. Ltd. Copyright 2013, All rights reserved.
 * 文件名  :RequestBody.java
 * 创建人  :zhangxurong
 * 创建时间:2013-6-10
 */
package com.changhong.sdk.httpbean;

/**
 * [简要描述]:该类定义了MSP各网元内部统一的通信请求消息体的信息
 * 
 * @author zhangxurong
 * @version 1.0, 2013-6-10
 */
public class RequestBody extends Body
{
    /**
     * [简要描述]:
     * 
     * @author 张旭荣
     * @return
     * @exception 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "RequestBody [Action=" + action + ", Params="
                + getParamsString() + "]";
    }
}
