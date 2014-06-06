package com.my.bookos.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.changhong.sdk.activity.SuperActivity;
import com.changhong.sdk.baseapi.AppLog;
import com.changhong.sdk.baseapi.StringUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.my.bookos.R;
import com.my.bookos.baseapi.LocationUtils;
import com.my.bookos.baseapi.MsgWhat;
import com.my.bookos.bean.CallBack;
import com.my.bookos.logic.LoginLogic;
import com.my.bookos.logic.MainLogic;

public class MainActivity extends SuperActivity implements MsgWhat {

	@ViewInject(R.id.etBookId)
	private EditText etBookId;

	@ViewInject(R.id.etName)
	private EditText etName;

	@ViewInject(R.id.etCode)
	private EditText etCode;

	@ViewInject(R.id.etPhone)
	private EditText etPhone;

	@ViewInject(R.id.tvUserCode)
	private TextView tvUserCode;

	@ViewInject(R.id.tvUserName)
	private TextView tvUserName;

	@ViewInject(R.id.tvUserAddress)
	private TextView tvUserAddress;

	@ViewInject(R.id.tvLastWater)
	private TextView tvLastWater;

	@ViewInject(R.id.tvLastReading)
	private TextView tvLastReading;

	@ViewInject(R.id.etThisReading)
	private EditText etThisReading;

	private MainLogic logic;

	private int pageNum = 1;

	private String location = "";

	@Override
	public void initData() {
		logic = MainLogic.getInstance();
	}

	@Override
	public void initLayout(Bundle paramBundle) {
		setContentView(R.layout.main_layout);
		ViewUtils.inject(this);
		LocationUtils.getInstant().startLocate(getApplicationContext(),
				new CallBack() {

					@Override
					public void update(Object object) {
						BDLocation loca = (BDLocation) object;
						AppLog.out(TAG, "城市编码" + loca.getAddrStr(),
								AppLog.LEVEL_INFO);
					}
				});
	}

	@Override
	public void clearData() {

	}

	public void requestSerach(int pageNum) {

		if (StringUtils.isEmpty(etBookId.getText().toString())) {
			showToast(etBookId.getHint().toString());
			return;
		}

		showProcessDialog(new OnDismissListener() {

			@Override
			public void onDismiss(DialogInterface dialog) {
				logic.stopRequest();
			}
		});
		logic.setData(mHandler);
		logic.requestSerach(etBookId.getText().toString(), etName.getText()
				.toString(), etCode.getText().toString(), etPhone.getText()
				.toString(), LoginLogic.getInstance().user.getEmployeeid(), "",
				pageNum, new HttpUtils());
	}

	@OnClick(R.id.btnSearch)
	public void btnSearchClick(View view) {
		requestSerach(pageNum);
	}

	@Override
	public void handleMsg(Message msg) {
		switch (msg.what) {
		case MSG_SEARCH_SUCCESS: {
			updateView();
			break;
		}
		case MSG_SAVE_SUCCESS: {
			super.handleMsg(msg);
			requestSerach(pageNum);
			return;
		}
		default:
			break;
		}
		super.handleMsg(msg);
	}

	private void updateView() {
		tvUserCode.setText(logic.bd.getUsercode());
		tvUserName.setText(logic.bd.getUsername());
		tvUserAddress.setText(logic.bd.getUseraddr());
		tvLastWater.setText(logic.bd.getLastwateramount());
		tvLastReading.setText(logic.bd.getLastreading());
		etThisReading.setText("");
	}

	@OnClick(R.id.btnSave)
	public void btnSaveClick(View view) {

		if (StringUtils.isEmpty(etThisReading.getText().toString())) {
			showToast(etThisReading.getHint().toString());
			return;
		}
		String currentwateramount = String.valueOf(Float.valueOf(etThisReading
				.getText().toString())
				- Float.valueOf(logic.bd.getLastreading()));
		showProcessDialog(new OnDismissListener() {

			@Override
			public void onDismiss(DialogInterface dialog) {
				logic.stopRequest();
			}
		});
		logic.setData(mHandler);
		logic.requestSave(logic.bd.getEmployeeid(), logic.bd.getUserid(),
				logic.bd.getLastreading(), logic.bd.getLastwateramount(),
				etThisReading.getText().toString(), currentwateramount,
				"江苏省南京市雨花区铁心桥街道", new HttpUtils());

	}

	@OnClick(R.id.btnLast)
	public void btnLastClick(View view) {
		if (StringUtils.isEmpty(logic.bd.getRowCount())
				&& Integer.valueOf(logic.bd.getRowCount()) > 1) {
			requestSerach(--pageNum);
		} else {
			showToast("已是第一条");
		}
	}

	@OnClick(R.id.btnNext)
	public void btnNextClick(View view) {
		if (StringUtils.isEmpty(logic.bd.getRowCount())
				|| String.valueOf(pageNum).equals(logic.bd.getRowCount())) {
			showToast("已是最后一条");
		} else {
			requestSerach(++pageNum);
		}
	}
}
