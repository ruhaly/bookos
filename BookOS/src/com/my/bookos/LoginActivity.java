package com.my.bookos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.changhong.sdk.activity.SuperActivity;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class LoginActivity extends SuperActivity {

	@Override
	public void initData() {

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
		startActivity(new Intent(getBaseContext(), MainActivity.class));
	}
}
