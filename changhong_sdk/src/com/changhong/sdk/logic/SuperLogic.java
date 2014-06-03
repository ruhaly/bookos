package com.changhong.sdk.logic;

import java.io.InputStream;
import java.util.Map;

import android.os.Handler;

import com.changhong.sdk.baseapi.SuperMsgWhat;
import com.changhong.sdk.http.HttpResponseHanlder;
import com.changhong.sdk.http.TransferConstants;
import com.changhong.sdk.httpbean.CommonConstants;
import com.changhong.sdk.httpbean.Header;
import com.changhong.sdk.httpbean.ServiceRequest;
import com.changhong.sdk.httpbean.ServiceResponse;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;

/**
 * 业务层基类,所有logic继承这个父类
 * @author hanliangru
 * @version [智慧社区-终端底座, 2014年1月20日]
 */
public abstract class SuperLogic implements HttpResponseHanlder, SuperMsgWhat
{
    
    public abstract void clear();
    
    public abstract void stopRequest();
    
    public Handler handler;
    
    public void setHandler(Handler handler)
    {
        this.handler = handler;
    }
    
    public void setData(Handler handler)
    {
        this.handler = handler;
    }
    
    @Override
    public void handleHttpResponse(ServiceResponse serviceRes, int requestId,
            InputStream is, int pos)
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void handleHttpResponse(String response, int rspCode, int requestId)
    {
        
    }
    
    @Override
    public void handleHttpResponse(String response, int requestId,
            InputStream is)
    {
        
    }
    
    @Override
    public void handleHttpException(HttpException error, String msg)
    {
        
    }
    
    @Override
    public void handleHttpResponse(ServiceResponse serviceRes, int requestId,
            InputStream is)
    {
        
    }
    
    public void fixRequestParams(RequestParams params,
            Map<String, Object> serviceInfo, String action, String toType,
            String fromType, String from)
    {
        
        // 构造消息对象
        ServiceRequest request = new ServiceRequest();
        // 设置消息头
        request.getHeader()
                .setMessageType(TransferConstants.COMM_MESSAGE_TYPE_REQUEST);
        // request.getHeader().newSeq();
        request.getHeader().setTo(CommonConstants.SERVICE_CODE_ESB);
        request.getHeader().setToType(toType);
        request.getHeader().setFrom(from);
        request.getHeader().setFromType(fromType);
        
        // 设置消息体
        request.getBody().setAction(action);// (TransferConstants.COMM_ACTION_TYPE_PUBLISH);
        request.getBody().setParams(serviceInfo);
        Header header = request.getHeader();
        if (null != header && null != header.getParams())
        {
            params.addHeader(TransferConstants.COMM_HEADER_NAME_PROTOCOLTYPE,
                    TransferConstants.PROTOCOL_TYPE_MSP);
            for (Map.Entry<String, String> entry : header.getParams()
                    .entrySet())
            {
                params.addHeader(TransferConstants.PROTOCOL_TYPE_MSP_PREFIX
                        + entry.getKey(), entry.getValue());
            }
        }
        params.addBodyParameter("Action", request.getBody().getAction());
        params.addBodyParameter("Params", request.getBody().getParamsString());
        
    }
    
    public int parseIntegerValue(String value)
    {
        if(null == value || value.equals(""))
        {
            return -1;
        }
        return Integer.parseInt(value);
    }
    
    public double parseDoubleValue(String value)
    {
        if(null == value || value.equals(""))
        {
            return -1;
        }
        return Double.parseDouble(value);
    }
}
