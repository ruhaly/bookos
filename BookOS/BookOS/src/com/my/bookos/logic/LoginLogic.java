package com.my.bookos.logic;

import java.io.InputStream;

import org.json.JSONObject;

import com.changhong.sdk.http.HttpSenderUtils;
import com.changhong.sdk.logic.SuperLogic;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.RequestParams;
import com.my.bookos.baseapi.HttpAction;
import com.my.bookos.baseapi.JsonParse;
import com.my.bookos.baseapi.MsgWhat;
import com.my.bookos.baseapi.RequestId;
import com.my.bookos.baseapi.ResultCode;
import com.my.bookos.bean.User;

public class LoginLogic extends SuperLogic implements MsgWhat {

	private static LoginLogic ins;

	private HttpHandler<String> httpHanlder;

	public User user = new User();

	public static synchronized LoginLogic getInstance() {
		if (null == ins) {
			ins = new LoginLogic();
		}
		return ins;
	}

	public void requestLogin(String account, String pwd, String phone,
			HttpUtils httpUtils) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("username", account);
		params.addBodyParameter("password", pwd);
		params.addBodyParameter("telephone", phone);
		httpHanlder = HttpSenderUtils.sendMsgImpl(HttpAction.ACTION_LOGIN,
				params, HttpSenderUtils.METHOD_POST, httpUtils,
				RequestId.RID_LOGIN, this, false);
	}

	@Override
	public void handleHttpResponse(String response, int requestId,
			InputStream is) {
		switch (requestId) {
		case RequestId.RID_LOGIN:
			handleLoginData(response);
			break;

		default:
			break;
		}
	}

	private void handleLoginData(String response) {
		try {

			JSONObject jsonObject = new JSONObject(response);
			String status = jsonObject.optString("status");
			if (status.endsWith(ResultCode.RESULT_SUCCESS)) {
				user.setEmployeeid(JsonParse.parseLoginData(response));
				handler.sendEmptyMessage(MSG_LOGIN_SUCCESS);
			} else {
				handler.sendEmptyMessage(MSG_LOGIN_FAILED);
			}

		} catch (Exception e) {
			handler.sendEmptyMessage(DATA_FORMAT_ERROR_MSGWHAT);
		}
	}

	@Override
	public void handleHttpException(HttpException error, String msg) {
		handler.sendEmptyMessage(CONNECT_ERROR_MSGWHAT);
	}

	@Override
	public void clear() {

	}

	@Override
	public void stopRequest() {
		if (null != httpHanlder) {
			httpHanlder.stop();
		}
	}

}
