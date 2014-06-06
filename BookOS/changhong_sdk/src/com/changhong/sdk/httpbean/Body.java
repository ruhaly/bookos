/*
 * Pactera Technologies Co., Ltd. Copyright 2013, All rights reserved.
 * 文件名  :Body.java
 * 创建人  :张旭荣
 * 创建时间:2013-12-6
 */

package com.changhong.sdk.httpbean;

import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import com.changhong.sdk.baseapi.StringUtils;

/**
 * [简要描述]:
 * 
 * @author 张旭荣
 * @version 1.0, 2013-12-6
 */
public abstract class Body
{
    protected String action;
    
    protected String params;
    
    /**
     * Gets the value of the action property.
     * 
     * @return possible object is {@link String }
     */
    public String getAction()
    {
        return action;
    }
    
    /**
     * Sets the value of the action property.
     * 
     * @param value
     *            allowed object is {@link String }
     */
    public void setAction(String value)
    {
        this.action = value;
    }
    
    /**
     * 返回params属性
     * 
     * @return params属性
     */
    public String getParams()
    {
        return params;
    }
    
    /**
     * 设置params属性
     * 
     * @param params
     *            params属性
     */
    public void setParams(String value)
    {
        if (StringUtils.isNotEmpty(value))
        {
            try
            {
                //                ObjectMapper objectMapper = new ObjectMapper();
                //                this.params = objectMapper.writeValueAsString(value);// JSONObject.fromObject(value.trim());
                this.params = value.toString();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 设置map属性
     * 
     * @param map
     *            map属性
     */
    @SuppressWarnings("rawtypes")
    public void setParams(Map map)
    {
        //        this.params = new JSONObject(map);// JSONObject.fromObject(map);
        
        try
        {
            ObjectMapper objectMapper = new ObjectMapper();
            this.params = objectMapper.writeValueAsString(map);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
    }
    
    /**
     * 返回params属性
     * 
     * @return params属性
     */
    public String getParamsString()
    {
        if (null != params)
        {
            return params.toString();
        }
        return null;
    }
    
}
