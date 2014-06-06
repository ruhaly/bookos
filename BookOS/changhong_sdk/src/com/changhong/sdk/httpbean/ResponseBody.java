/*
 * Pactera Technology Co. Ltd. Copyright 2013, All rights reserved.
 * 文件名  :ServiceResponse.java
 * 创建人  :zhangxurong
 * 创建时间:2013-6-10
 */
package com.changhong.sdk.httpbean;

/**
 * [简要描述]:该类定义了MSP各网元内部统一的通信响应消息体的信息
 * 
 * @author zhangxurong
 * @version 1.0, 2013-6-10
 */
public class ResponseBody extends Body
{
    // 响应服务的结果，默认响应结果为成功
    protected String result;
    
    // 响应服务的结果描述
    protected String resultMessage;
    
    /**
     * 返回result属性
     * @return result属性
     */
    public String getResult()
    {
        return result;
    }
    
    /**
     * 设置result属性
     * @param result result属性
     */
    public void setResult(String result)
    {
        this.result = result;
    }
    
    /**
     * 返回resultMessage属性
     * @return resultMessage属性
     */
    public String getResultMessage()
    {
        return resultMessage;
    }
    
    /**
     * 设置resultMessage属性
     * @param resultMessage resultMessage属性
     */
    public void setResultMessage(String resultMessage)
    {
        this.resultMessage = resultMessage;
    }
    
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
        return "ResponseBody [Action=" + action + ", Result=" + result
                + ", ResultMessage=" + resultMessage + ", Params="
                + getParamsString() + "]";
    }
}
