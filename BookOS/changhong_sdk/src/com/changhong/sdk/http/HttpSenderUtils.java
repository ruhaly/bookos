package com.changhong.sdk.http;

import com.changhong.sdk.baseapi.AppLog;
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
 * 
 * @author hanliangru
 * @version [智慧社区-终端底座, 2014年1月20日]
 */
public class HttpSenderUtils {

	public static final String HOST = "http://www.ryzls.com/skyTeam/mobile";

	public static final byte METHOD_GET = 0;

	public static final byte METHOD_POST = 1;

	private static final String TAG = "HttpUtils";

	public static HttpHandler<String> sendMsgImpl(String action,
			RequestParams params, int method, HttpUtils http,
			final int requestId, final SuperLogic logic, boolean isStream) {
		String url = HOST + action;
		HttpHandler<String> httpHandler = null;
		HttpMethod httpMethod = method == METHOD_GET ? HttpMethod.GET
				: HttpMethod.POST;
		if (isStream) {

			ResponseStream sendSync;
			try {
				sendSync = http.sendSync(httpMethod, url, params);
				logic.handleHttpResponse("", requestId, sendSync);
			} catch (HttpException e) {
				logic.handleHttpException(e, e.getMessage());
			}
		} else {
			if (method == METHOD_GET) {
				httpHandler = http.send(httpMethod, url,
						new RequestCallBack<String>() {

							@Override
							public void onStart() {
							}

							@Override
							public void onFailure(HttpException error,
									String msg) {
								AppLog.out(
										TAG,
										"返回异常响应：exceptionCode="
												+ error.getExceptionCode()
												+ ";msg=" + msg + ";requestId="
												+ requestId, AppLog.LEVEL_INFO);
								logic.handleHttpException(error, msg);
							}

							@Override
							public void onSuccess(ResponseInfo<String> ri) {
								AppLog.out(TAG, "返回响应：" + ri.result,
										AppLog.LEVEL_INFO);
								logic.handleHttpResponse(ri.result, 0,
										requestId);
							}

						});
			} else {
				httpHandler = http.send(httpMethod, url, params,
						new RequestCallBack<String>() {

							@Override
							public void onStart() {
							}

							@Override
							public void onFailure(HttpException error,
									String msg) {
								AppLog.out(
										TAG,
										"返回异常响应：exceptionCode="
												+ error.getExceptionCode()
												+ ";msg=" + msg + ";requestId="
												+ requestId, AppLog.LEVEL_INFO);
								logic.handleHttpException(error, msg);
							}

							@Override
							public void onSuccess(ResponseInfo<String> ri) {
								AppLog.out(TAG, "返回响应：" + ri.result,
										AppLog.LEVEL_INFO);
								logic.handleHttpResponse(ri.result, requestId,
										null);
							}

						});
			}

		}

		return httpHandler;
	}

}
