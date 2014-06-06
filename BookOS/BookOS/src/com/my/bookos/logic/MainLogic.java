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
import com.my.bookos.bean.BookData;

public class MainLogic extends SuperLogic implements MsgWhat {

	private static MainLogic ins;

	private HttpHandler<String> httpHanlderSave;

	private HttpHandler<String> httpHanlder;

	public BookData bd = new BookData();

	public static synchronized MainLogic getInstance() {
		if (null == ins) {
			ins = new MainLogic();
		}
		return ins;
	}

	public void requestSerach(String bookid, String username, String usercode,
			String mobilephone, String employeeid, String readmonth,
			int pageNum, HttpUtils httpUtils) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("bookid", bookid);
		params.addBodyParameter("username", username);
		params.addBodyParameter("usercode", usercode);
		params.addBodyParameter("mobilephone", mobilephone);
		params.addBodyParameter("employeeid", employeeid);
		params.addBodyParameter("readmonth", readmonth);
		params.addBodyParameter("pageNum", pageNum + "");
		httpHanlder = HttpSenderUtils.sendMsgImpl(HttpAction.ACTION_SEARCH,
				params, HttpSenderUtils.METHOD_POST, httpUtils,
				RequestId.RID_SEARCH, this, false);
	}

	public void requestSave(String employeeid, String userid,
			String lastreading, String lastwateramount, String currentreading,
			String currentwateramount, String readaddr, HttpUtils httpUtils) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("employeeid", employeeid);
		params.addBodyParameter("userid", userid);
		params.addBodyParameter("lastreading", lastreading);
		params.addBodyParameter("lastwateramount", lastwateramount);
		params.addBodyParameter("currentreading", currentreading);
		params.addBodyParameter("currentwateramount", currentwateramount);
		params.addBodyParameter("readaddr", readaddr);
		httpHanlderSave = HttpSenderUtils.sendMsgImpl(HttpAction.ACTION_SAVE,
				params, HttpSenderUtils.METHOD_POST, httpUtils,
				RequestId.RID_SAVE, this, false);
	}

	@Override
	public void handleHttpResponse(String response, int requestId,
			InputStream is) {
		switch (requestId) {
		case RequestId.RID_SEARCH: {
			handleSearchData(response);
			break;
		}
		case RequestId.RID_SAVE: {
			handleSaveData(response);
			break;
		}
		default:
			break;
		}
	}

	private void handleSearchData(String response) {
		try {

			JSONObject jsonObject = new JSONObject(response);
			String status = jsonObject.optString("status");
			if (status.endsWith(ResultCode.RESULT_SUCCESS)) {
				bd = JsonParse.parseSearchData(response);
			} else {
				bd = new BookData();
			}
			handler.sendEmptyMessage(MSG_SEARCH_SUCCESS);
		} catch (Exception e) {
			handler.sendEmptyMessage(DATA_FORMAT_ERROR_MSGWHAT);
		}
	}

	private void handleSaveData(String response) {
		try {

			JSONObject jsonObject = new JSONObject(response);
			String status = jsonObject.optString("status");
			if (status.endsWith(ResultCode.RESULT_SUCCESS)) {
				handler.sendEmptyMessage(MSG_SAVE_SUCCESS);
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
			// httpHanlder.stop();
		}
	}

}
