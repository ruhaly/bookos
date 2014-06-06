/*
 * Pactera Technology Co. Ltd. Copyright 2013, All rights reserved.
 * 文件名  :SoapCacher.java
 * 创建人  :zhangxurong
 * 创建时间:2013-6-14
*/

package com.changhong.sdk.http;

/**
 * [简要描述]:Soap缓存器，缓存服务相关信息
 *
 * @author zhangxurong
 * @version 1.0, 2013-6-14
 */
public final class SeqCache
{
    //序列号
    private static long seq;
    
    //首次访问该类时初始化参数
    static
    {
        seq = 0L;
    }
    
    /**
     * [简要描述]:增加Server访问的计数
     * 
     * @author zhangxurong
     */
    public synchronized static final String newSeq()
    {
        seq++;
        return String.valueOf(seq);
    }
}
