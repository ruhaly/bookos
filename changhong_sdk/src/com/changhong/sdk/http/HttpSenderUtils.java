package com.changhong.sdk.http;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicHeaderValueParser;
import org.apache.http.message.ParserCursor;
import org.apache.http.util.CharArrayBuffer;

import com.changhong.sdk.baseapi.AppLog;
import com.changhong.sdk.httpbean.Header;
import com.changhong.sdk.httpbean.ResponseBody;
import com.changhong.sdk.httpbean.ServiceResponse;
import com.changhong.sdk.logic.SuperLogic;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.ResponseStream;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

/**
 * 
 * http请求类
 * @author hanliangru
 * @version [智慧社区-终端底座, 2014年1月20日]
 */
public class HttpSenderUtils
{
    
    public static final byte METHOD_GET = 0;
    
    public static final byte METHOD_POST = 1;
    
    private static final char[] DELIM = new char[] { '&' };
    
    private static final String TAG = "HttpUtils";
    
    public static HttpHandler<String> sendMsgImpl(String action,
            RequestParams params, int method, HttpUtils http,
            final int requestId, final SuperLogic logic, boolean isStream,
            String url)
    {
        HttpHandler<String> httpHandler = null;
        HttpMethod httpMethod = method == METHOD_GET ? HttpMethod.GET
                : HttpMethod.POST;
        if (isStream)
        {
            
            ResponseStream sendSync;
            try
            {
                sendSync = http.sendSync(httpMethod, url, params);
                logic.handleHttpResponse("", requestId, sendSync);
            }
            catch (HttpException e)
            {
                logic.handleHttpException(e, e.getMessage());
            }
        }
        else
        {
            if (method == METHOD_GET)
            {
                httpHandler = http.send(httpMethod,
                        url,
                        new RequestCallBack<String>()
                        {
                            
                            @Override
                            public void onStart()
                            {
                            }
                            
                            @Override
                            public void onFailure(HttpException error,
                                    String msg)
                            {
                                AppLog.out(TAG,
                                        "返回异常响应：exceptionCode="
                                                + error.getExceptionCode()
                                                + ";msg=" + msg + ";requestId="
                                                + requestId,
                                        AppLog.LEVEL_INFO);
                                logic.handleHttpException(error, msg);
                            }
                            
                            @Override
                            public void onSuccess(ResponseInfo<String> ri)
                            {
                                AppLog.out(TAG,
                                        "返回响应：" + ri.result,
                                        AppLog.LEVEL_INFO);
                                logic.handleHttpResponse(ri.result,
                                        0,
                                        requestId);
                            }
                            
                        });
            }
            else
            {
                httpHandler = http.send(httpMethod,
                        url,
                        params,
                        new RequestCallBack<String>()
                        {
                            
                            @Override
                            public void onStart()
                            {
                            }
                            
                            @Override
                            public void onFailure(HttpException error,
                                    String msg)
                            {
                                AppLog.out(TAG,
                                        "返回异常响应：exceptionCode="
                                                + error.getExceptionCode()
                                                + ";msg=" + msg + ";requestId="
                                                + requestId,
                                        AppLog.LEVEL_INFO);
                                logic.handleHttpException(error, msg);
                            }
                            
                            @Override
                            public void onSuccess(ResponseInfo<String> ri)
                            {
                                AppLog.out(TAG,
                                        "返回响应：" + ri.result,
                                        AppLog.LEVEL_INFO);
                                logic.handleHttpResponse(getServiceResponse(ri),
                                        requestId,
                                        null);
                            }
                            
                        });
            }
            
        }
        
        return httpHandler;
    }
    
    /**
     * 
     * @param action
     * @param params
     * @param method
     * @param http
     * @param requestId
     * @param logic
     * @param isStream
     * @param url
     * @param pos 为了对应界面刷新
     * @return
     */
    public static HttpHandler<String> sendMsgImpl(String action,
            RequestParams params, int method, HttpUtils http,
            final int requestId, final SuperLogic logic, boolean isStream,
            String url, final int pos)
    {
        HttpHandler<String> httpHandler = null;
        HttpMethod httpMethod = method == METHOD_GET ? HttpMethod.GET
                : HttpMethod.POST;
        if (isStream)
        {
            
            ResponseStream sendSync;
            try
            {
                sendSync = http.sendSync(httpMethod, url, params);
                logic.handleHttpResponse("", requestId, sendSync);
            }
            catch (HttpException e)
            {
                logic.handleHttpException(e, e.getMessage());
            }
        }
        else
        {
            httpHandler = http.send(httpMethod,
                    url,
                    params,
                    new RequestCallBack<String>()
                    {
                        
                        @Override
                        public void onStart()
                        {
                        }
                        
                        @Override
                        public void onFailure(HttpException error, String msg)
                        {
                            AppLog.out(TAG,
                                    "返回异常响应：exceptionCode="
                                            + error.getExceptionCode()
                                            + ";msg=" + msg + ";requestId="
                                            + requestId,
                                    AppLog.LEVEL_INFO);
                            logic.handleHttpException(error, msg);
                        }
                        
                        @Override
                        public void onSuccess(ResponseInfo<String> ri)
                        {
                            AppLog.out(TAG,
                                    "返回响应：" + ri.result,
                                    AppLog.LEVEL_INFO);
                            logic.handleHttpResponse(getServiceResponse(ri),
                                    requestId,
                                    null,
                                    pos);
                        }
                        
                    });
        }
        
        return httpHandler;
    }
    
    /**
     * 
     * 组装response
     * @param ri
     * @return
     */
    public static ServiceResponse getServiceResponse(ResponseInfo<String> ri)
    {
        ServiceResponse serviceResponse = null;
        
        ResponseBody resBody = new ResponseBody();
        Header responseHeader = new Header();
        org.apache.http.Header[] headers = ri.getAllHeaders();
        for (org.apache.http.Header header : headers)
        {
            if (header.getName()
                    .startsWith(TransferConstants.PROTOCOL_TYPE_MSP_PREFIX))
            {
                responseHeader.put(header.getName()
                        .substring(TransferConstants.PROTOCOL_TYPE_MSP_PREFIX.length()),
                        header.getValue());
            }
        }
        
        BasicHeaderValueParser parser = BasicHeaderValueParser.DEFAULT;
        CharArrayBuffer buffer = new CharArrayBuffer(ri.result.length());
        buffer.append(ri.result);
        ParserCursor cursor = new ParserCursor(0, buffer.length());
        while (!cursor.atEnd())
        {
            NameValuePair nvp = parser.parseNameValuePair(buffer, cursor, DELIM);
            if (nvp.getName().length() > 0)
            {
                if ("Action".equals(nvp.getName()))
                {
                    resBody.setAction(nvp.getValue());
                }
                else if ("Result".equals(nvp.getName()))
                {
                    resBody.setResult(nvp.getValue());
                }
                else if ("ResultMessage".equals(nvp.getName()))
                {
                    resBody.setResultMessage(nvp.getValue());
                }
                else if ("Params".equals(nvp.getName()))
                {
                    resBody.setParams(nvp.getValue());
                }
            }
        }
        serviceResponse = new ServiceResponse(responseHeader, resBody);
        return serviceResponse;
    }
}
