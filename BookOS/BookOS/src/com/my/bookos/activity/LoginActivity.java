package com.my.bookos.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.EditText;

import com.changhong.sdk.activity.SuperActivity;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.my.bookos.R;
import com.my.bookos.baseapi.MsgWhat;
import com.my.bookos.logic.LoginLogic;

public class LoginActivity extends SuperActivity implements MsgWhat {

	@ViewInject(R.id.etAccount)
	public EditText etAccount;

	@ViewInject(R.id.etPwd)
	public EditText etPwd;

	public LoginLogic logic;

	@Override
	public void initData() {
		logic = LoginLogic.getInstance();
	}

	@Override
	public void initLayout(Bundle paramBundle) {
		setContentView(R.layout.login_layout);
		ViewUtils.inject(this);
	}

	@Override
	public void clearData() {
	}

	@OnClick(R.id.btnLogin)
	public void btnLoginClick(View view) {
		if (checkEditTextIsEmpty()) {
			return;
		}
		showProcessDialog(new OnDismissListener() {

			@Override
			public void onDismiss(DialogInterface dialog) {
				logic.stopRequest();
			}
		});
		logic.setData(mHandler);
		logic.requestLogin(etAccount.getText().toString(), etPwd.getText()
				.toString(), getPhoneNumber(), new HttpUtils());
	}

	@Override
	public void handleMsg(Message msg) {
		switch (msg.what) {
		case MSG_LOGIN_SUCCESS: {

			logic.user.setAccount(etAccount.getText().toString());
			logic.user.setPwd(etPwd.getText().toString());
			logic.user.setPhone(getPhoneNumber());
			super.handleMsg(msg);
			finish();
			startActivity(new Intent(getBaseContext(), MainActivity.class));
			return;
		}
		case MSG_LOGIN_FAILED: {
			showToast(getString(R.string.login_failed));
			break;
		}

		default:
			break;
		}
		super.handleMsg(msg);
	}
}
