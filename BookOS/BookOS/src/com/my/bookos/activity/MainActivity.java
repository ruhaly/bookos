package com.my.bookos.activity;

import java.text.DecimalFormat;
import java.util.Calendar;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.changhong.sdk.activity.SuperActivity;
import com.changhong.sdk.baseapi.AppLog;
import com.changhong.sdk.baseapi.DateUtils;
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

	@ViewInject(R.id.etTelephone)
	private EditText etTelephone;

	private MainLogic logic;

	private int pageNum = 1;

	private int tempPageNum = 1;

	private boolean isSave = true;

	private boolean isSearchBtnClick = true;

	private String phoneAddress = "";

	@ViewInject(R.id.tvTime)
	private TextView tvTime;

	@ViewInject(R.id.tvUsertelephone)
	private TextView tvUsertelephone;

	@ViewInject(R.id.tvUsertelephone)
	private TextView tvUserMobilephone;

	@ViewInject(R.id.tvWatertype)
	private TextView tvWatertype;

	@ViewInject(R.id.tvThisWater)
	private TextView tvThisWater;

	@Override
	public void initData() {
		logic = MainLogic.getInstance();
	}

	@Override
	public void initLayout(Bundle paramBundle) {
		setContentView(R.layout.main_layout);
		ViewUtils.inject(this);
		tvTime.setText(DateUtils.getYearAndMonth());
		LocationUtils.getInstant().startLocate(getApplicationContext(),
				new CallBack() {

					@Override
					public void update(Object object) {
						BDLocation loca = (BDLocation) object;
						phoneAddress = loca.getAddrStr()
								+ loca.getStreetNumber();
						AppLog.out(TAG, "详细地址" + phoneAddress,
								AppLog.LEVEL_INFO);
					}
				});
		etThisReading.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				changeWater();
			}
		});
	}

	@Override
	public void clearData() {

	}

	public void requestSerach(int pageNum, boolean isSave,
			boolean isSearchBtnClick) {

		if (StringUtils.isEmpty(tvTime.getText().toString())) {
			showToast(tvTime.getHint().toString());
			return;
		}

		showProcessDialog(new OnDismissListener() {

			@Override
			public void onDismiss(DialogInterface dialog) {
				logic.stopRequest();
			}
		});
		this.isSave = isSave;
		this.isSearchBtnClick = isSearchBtnClick;
		logic.setData(mHandler);
		logic.requestSerach(etBookId.getText().toString(), etName.getText()
				.toString(), etCode.getText().toString(), etPhone.getText()
				.toString(), LoginLogic.getInstance().user.getEmployeeid(),
				tvTime.getText().toString(), etTelephone.getText().toString(),
				pageNum, new HttpUtils());
	}

	@OnClick(R.id.btnSearch)
	public void btnSearchClick(View view) {

		tempPageNum = 1;
		requestSerach(tempPageNum, true, true);
	}

	@Override
	public void handleMsg(Message msg) {
		switch (msg.what) {
		case MSG_SEARCH_SUCCESS: {

			if (StringUtils.isEmpty(logic.bd.getUserid())) {
				showToast(getString(R.string.data_empty));
			}
			if (isSearchBtnClick) {
				pageNum = 1;
			} else {
				if (!isSave) {
					pageNum = tempPageNum;
				}
			}

			updateView();
			break;
		}
		case MSG_SAVE_SUCCESS: {
			super.handleMsg(msg);
			showToast(getString(R.string.save_success));
			if (isSearchBtnClick) {
				pageNum = 1;
			} else {
				requestSerach(pageNum, true, false);
			}
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
		tvUsertelephone.setText(logic.bd.getTelephone());
		tvUserMobilephone.setText(logic.bd.getMobilephone());
		tvWatertype.setText(logic.bd.getWatertype());
		etThisReading.setText("");
	}

	@OnClick(R.id.btnSave)
	public void btnSaveClick(View view) {
		saveData(true);
	}

	public void changeWater() {
		if (StringUtils.isEmpty(logic.bd.getLastreading())) {
			tvThisWater.setText("0");
			return;
		}
		if (StringUtils.isEmpty(etThisReading.getText().toString())) {
			tvThisWater.setText("0");
		} else {
			float lastReading = Float.valueOf(logic.bd.getLastreading());
			float currentReading = Float.valueOf(etThisReading.getText()
					.toString());

			float currentwateramount = currentReading - lastReading;
			DecimalFormat df = new DecimalFormat("#.00");
			tvThisWater.setText(df.format(currentwateramount));
		}

	}

	public void saveData(boolean isSave) {
		isSearchBtnClick = false;
		if (StringUtils.isEmpty(phoneAddress)) {
			showToast(getString(R.string.location_null));
			return;
		}

		if (StringUtils.isEmpty(etThisReading.getText().toString())) {
			showToast(etThisReading.getHint().toString());
			return;
		}
		if (StringUtils.isEmpty(logic.bd.getLastreading())) {
			showToast("请先获取数据");
			return;
		}
		float lastReading = Float.valueOf(logic.bd.getLastreading());
		float currentReading = Float
				.valueOf(etThisReading.getText().toString());

		float currentwateramount = currentReading - lastReading;

		if (currentwateramount < 0) {
			showToast(getString(R.string.tip_save));
			return;
		}
		showProcessDialog(new OnDismissListener() {

			@Override
			public void onDismiss(DialogInterface dialog) {
				logic.stopRequest();
			}
		});
		isSave = true;
		logic.setData(mHandler);
		logic.requestSave(logic.bd.getEmployeeid(), logic.bd.getUserid(),
				logic.bd.getLastreading(), logic.bd.getLastwateramount(),
				etThisReading.getText().toString(), String
						.valueOf(currentwateramount), phoneAddress, tvTime
						.getText().toString(), logic.bd.getMeterkind(),
				new HttpUtils());
	}

	@OnClick(R.id.btnLast)
	public void btnLastClick(View view) {
		if (StringUtils.isEmpty(logic.bd.getRowCount()) || pageNum == 1) {
			showToast(getString(R.string.no_more_data));
		} else {
			tempPageNum = pageNum - 1;
			requestSerach(tempPageNum, false, false);
		}
	}

	@OnClick(R.id.btnNext)
	public void btnNextClick(View view) {
		if (StringUtils.isEmpty(logic.bd.getRowCount())
				|| String.valueOf(pageNum).equals(logic.bd.getRowCount())) {
			showToast(getString(R.string.no_more_data));
		} else {
			tempPageNum = pageNum + 1;
			requestSerach(tempPageNum, false, false);
		}
	}

	@Override
	public void finish() {
		super.finish();
		LocationUtils.getInstant().stopLocate();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			showLogoutDialog();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@OnClick(R.id.tvTime)
	public void tvTimeClick(View view) {
		showTimerPicker();
	}

	private void showTimerPicker() {
		View timerView = LayoutInflater.from(getBaseContext()).inflate(
				R.layout.time_picker_layout, null);
		final DatePicker dp = (DatePicker) timerView
				.findViewById(R.id.datePicker);
		Button btnConfirm = (Button) timerView.findViewById(R.id.btnConfirm);
		Button btnCancel = (Button) timerView.findViewById(R.id.btnCancel);
		Calendar c = Calendar.getInstance();
		final int month = c.get(Calendar.MONTH) + 1;
		btnCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dismissDialog();
			}
		});
		btnConfirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if ((dp.getMonth() + 1) > month) {
					showToast(getString(R.string.tip_date));
					return;
				}
				tvTime.setText(dp.getYear() + "-" + (dp.getMonth() + 1));
				dismissDialog();
			}
		});
		if (dp != null) {
			((ViewGroup) ((ViewGroup) dp.getChildAt(0)).getChildAt(0))
					.getChildAt(2).setVisibility(View.GONE);
		}
		showDialog(timerView, false, R.style.MyDialog);
	}

	@ViewInject(R.id.frameCondition)
	public LinearLayout frameCondition;

	@OnClick(R.id.btnMore)
	public void btnMoreClick(View view) {
		if (frameCondition.getVisibility() == View.VISIBLE) {
			frameCondition.setVisibility(View.GONE);
		} else {
			frameCondition.setVisibility(View.VISIBLE);
		}
	}
}
