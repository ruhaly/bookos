package com.my.bookos;

import android.os.Bundle;

import com.changhong.sdk.activity.SuperActivity;
import com.lidroid.xutils.ViewUtils;

public class MainActivity extends SuperActivity {

	@Override
	public void initData() {

	}

	@Override
	public void initLayout(Bundle paramBundle) {
		setContentView(R.layout.main_layout);
		ViewUtils.inject(this);
	}

	@Override
	public void clearData() {

	}

}
