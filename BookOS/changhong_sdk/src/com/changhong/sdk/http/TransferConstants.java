/*
 * Pactera Technology Co. Ltd. Copyright 2013, All rights reserved.
 * 文件名  :ServiceConstants.java
 * 创建人  :zhangxurong
 * 创建时间:2013-6-10
*/

package com.changhong.sdk.http;

/**
 * [简要描述]:COMM
 *
 * @author zhangxurong
 * @version 1.0, 2013-6-10
 */
public final class TransferConstants
{
    //通信消息体中传递的参数使用换行符分隔
    public static final String MESSAGE_PARAMS_SPLIT_NEWLINE = "\r\n";
    
    //通信消息体中传递的参数的Key和Value之间使用等号分隔
    public static final String MESSAGE_PARAMS_SPLIT_EQUALS = "=";
    
    //通信响应结果：0 成功
    public static final String RESPONSE_RESULT_SUCCESS = "0";
    
    //通信响应结果：1 失败
    public static final String RESPONSE_RESULT_FAIL = "1";
    
    //通信消息头协议类型
    public static final String PROTOCOL_TYPE_MSP = "msp";
    
    //通信消息头协议前缀
    public static final String PROTOCOL_TYPE_MSP_PREFIX = "msp-";
    
    //接收的通信消息头
    public static final String INBOUND_COMM_HEADER = "inbound.soapheader";
    
    //发送的通信消息头
    public static final String OUTBOUND_COMM_HEADER = "outbound.soapheader";
    
    //通信消息类型：注册
    public static final String COMM_MESSAGE_TYPE_SIGNUP = "1001";
    
    //通信消息类型：登录
    public static final String COMM_MESSAGE_TYPE_SIGNIN = "1002";
    
    //通信消息类型：请求
    public static final String COMM_MESSAGE_TYPE_REQUEST = "1003";
    
    //通信消息类型：订阅事件
    public static final String COMM_MESSAGE_TYPE_SUBSCRIBE = "1004";
    
    //通信消息类型：通知事件
    public static final String COMM_MESSAGE_TYPE_NOTIFY = "1005";
    
    //通信消息类型：发布事件
    public static final String COMM_MESSAGE_TYPE_PUBLISH = "1006";
    
    //通信消息头结点：ProtocolType
    public static final String COMM_HEADER_NAME_PROTOCOLTYPE = "protocol-type";
    
    //通信消息头结点：MessageType
    public static final String COMM_HEADER_NAME_MESSAGETYPE = "message-type";
    
    //通信消息头结点：Seq
    public static final String COMM_HEADER_NAME_SEQ = "seq";
    
    //通信消息头结点：To
    public static final String COMM_HEADER_NAME_TO = "to";
    
    //通信消息头结点：ToType
    public static final String COMM_HEADER_NAME_TOTYPE = "to-type";
    
    //通信消息头结点：From
    public static final String COMM_HEADER_NAME_FROM = "from";
    
    //通信消息头结点：FromType
    public static final String COMM_HEADER_NAME_FROMTYPE = "from-type";
    
    //通信消息头结点：Via
    public static final String COMM_HEADER_NAME_VIA = "via";
    
    //通信消息头结点：Event-Name
    public static final String COMM_HEADER_NAME_EVENTNAME = "event-name";
    
    //通信消息头结点：ServiceAddress
    public static final String COMM_HEADER_NAME_SERVICEADDRESS = "service-address";
    
    //通信消息头结点：StatusCode
    public static final String COMM_HEADER_NAME_STATUSCODE = "status-code";
    
    //通信消息体Action类型：订阅事件
    public static final String COMM_ACTION_TYPE_SUBSCRIBE = "Subscribe";
    
    //通信消息体Action类型：去订阅事件
    public static final String COMM_ACTION_TYPE_UNSUBSCRIBE = "Unsubscribe";
    
    //通信消息体Action类型：通知事件
    public static final String COMM_ACTION_TYPE_NOTIFY = "Notify";
    
    //通信消息体Action类型：发布事件
    public static final String COMM_ACTION_TYPE_PUBLISH = "Publish";
    
    //通信消息体参数类型：发布事件
    public static final String COMM_PARAMS_TYPE_SERVICEINFO = "ServiceInfo";
    
}
