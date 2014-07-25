package com.changhong.sdk.activity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.changhong.sdk.R;
import com.changhong.sdk.baseapi.ActivityStack;
import com.changhong.sdk.baseapi.AppLog;
import com.changhong.sdk.baseapi.CHUtils;
import com.changhong.sdk.baseapi.Constant;
import com.changhong.sdk.baseapi.StringUtils;
import com.changhong.sdk.baseapi.SuperApplication;
import com.changhong.sdk.baseapi.SuperMsgWhat;
import com.changhong.sdk.entity.BusinessInfo;

public abstract class SuperActivity extends DialogActivity implements
		View.OnClickListener, SuperMsgWhat {
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onRestart()
	 */
	@Override
	protected void onRestart() {

		AppLog.out(MY_TAG, "onRestart", AppLog.LEVEL_INFO);
		super.onRestart();
	}

	static final String ACCOUNT_SPLITER = "Q";

	public PopupWindow menuPopupWindow;

	private static final String MY_TAG = "SuperActivity";

	public static String TAG;

	public SuperActivity() {
	}

	/**
	 * 初始化数据.
	 */
	public abstract void initData();

	/**
	 * 
	 * 初始化界面布局.
	 * 
	 * @param paramBundle
	 */
	public abstract void initLayout(Bundle paramBundle);

	/**
	 * 初始化界面布局 .
	 * 
	 * @param paramBundle
	 */
	public void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		// 全屏
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		// WindowManager.LayoutParams.FLAG_FULLSCREEN);
		initData();
		initLayout(paramBundle);
		TAG = this.getClass().getName();
		ActivityStack.getIns().pushActivity(this);
	}

	/**
	 * 
	 * 后台运行
	 * 
	 * @param appName
	 *            TODO
	 */
	private void sendBackGroundNotify(String appName) {
		// Intent intent = null;
		// try
		// {
		// intent = new Intent(getApplicationContext(),
		// Class.forName(this.getClass().getName()));
		// }
		// catch (ClassNotFoundException e)
		// {
		// intent = null;
		// }
		// if (null == intent)
		// {
		// return;
		// }
		//
		// intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		// showNotification(null,
		// null,
		// intent,
		// getString(R.string.app_running),
		// ID_BACKGROUND,
		// getString(R.string.app_running));
	}

	/**
	 * 清除数据.
	 */
	public abstract void clearData();

	public void finish() {
		ActivityStack.getIns().popupActivity(this.getClass().getName());
		super.finish();
	}

	public void showToast(final String str) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(getBaseContext(), str, Toast.LENGTH_SHORT)
						.show();
			}
		});
	}

	public void handleHttpResponseCode(int code) {
		switch (code) {
		}
	}

	public boolean moveTaskToBack(boolean paramBoolean) {
		if (paramBoolean)
			sendBackGroundNotify("");
		return super.moveTaskToBack(paramBoolean);

	}

	protected void onDestroy() {
		clearData();
		super.onDestroy();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return super.onKeyDown(keyCode, event);
	}

	protected void onResume() {
		clearBackGroundNotification();
		super.onResume();
	}

	protected void onUserLeaveHint() {
		if (!CHUtils.isTopApp(getBaseContext()))
			sendBackGroundNotify("");
		super.onUserLeaveHint();
	}

	/**
	 * 模拟事件
	 * 
	 * @param event
	 */
	public void imitateEvent() {
		Intent intent = new Intent(Constant.ACTION_BACKGROUND);
		intent.putExtra("nonRoot", true);
		sendBroadcast(intent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.app.Activity#onConfigurationChanged(android.content.res.Configuration
	 * )
	 */
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);

	}

	/**
	 * 
	 * 全屏
	 */
	public void fullScreen() {
		WindowManager.LayoutParams attrs = getParent().getWindow()
				.getAttributes();
		attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
		getWindow().setAttributes(attrs);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
	}

	/**
	 * 
	 * 取消全屏
	 */
	public void cancelFullScreen() {
		WindowManager.LayoutParams attrs = getParent().getWindow()
				.getAttributes();
		attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().setAttributes(attrs);
		getWindow()
				.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
	}

	public void showProcessDialog(DialogInterface.OnDismissListener dismiss) {
		showProgressDialog("", getString(R.string.loading), false, dismiss);
	}

	public String parseXML(String xmlPath) {
		StringBuffer sb = new StringBuffer();

		ClassLoader cl = SuperActivity.class.getClassLoader();
		InputStream stream = cl.getResourceAsStream(xmlPath);
		int length = 0;
		byte[] b = new byte[1024];
		try {
			while ((length = stream.read(b)) != -1) {
				sb.append(new String(b, 0, length));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sb.toString();

	}

	/**
	 * 
	 * 是否带有动画效果
	 * 
	 * @param intent
	 * @param isAnimation
	 */
	public void startActivity(Intent intent, boolean isAnimation) {
		startActivity(intent);
		// if (isAnimation)
		// overridePendingTransition(R.anim.push_left_out, R.anim.push_left_in);
	}

	/**
	 * mHandler
	 */
	public MewwHandler mHandler = new MewwHandler();

	@SuppressLint("HandlerLeak")
	public class MewwHandler extends Handler {
		/*
		 * (non-Javadoc) (覆盖方法) 方法名称： handleMessage 作者： hanliangru 方法描述：
		 * 
		 * @see android.os.Handler#handleMessage(android.os.Message)
		 */
		@Override
		public void handleMessage(Message msg) {
			handleMsg(msg);
		}
	}

	/**
	 * 
	 * 消息处理
	 * 
	 * @param msg
	 */
	public void handleMsg(Message msg) {
		switch (msg.what) {
		case CONNECT_ERROR_MSGWHAT: {
			showToast(getString(R.string.error_net));
			break;
		}
		case DATA_FORMAT_ERROR_MSGWHAT: {
			showToast(getString(R.string.date_format_error));
			break;
		}
		default:
			break;
		}
		dismissDialog();
		dismissProgress();
	}

	/**
	 * 
	 * 校验当前界面下面的子所有EditView是否为空
	 * 
	 * @param view
	 * @return
	 */
	public boolean checkEditTextIsEmpty() {
		List<View> list = getAllChildViews();
		for (View view : list) {
			if (view instanceof EditText && ((EditText) view).isEnabled()) {
				if (StringUtils.isEmpty(((EditText) view).getText().toString())) {
					showToast(((EditText) view).getHint().toString());
					// showToast(getString(R.string.please_input_all));
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * 
	 * decorView是window中的最顶层view，可以从window中获取到decorView
	 * 
	 * @return
	 */
	public List<View> getAllChildViews() {
		View view = getWindow().getDecorView();
		return getAllChildViews(view);
	}

	/**
	 * 
	 * 获取当前界面所有的子控件
	 * 
	 * @param view
	 * @return
	 */
	private List<View> getAllChildViews(View view) {
		List<View> allchildren = new ArrayList<View>();
		if (view instanceof ViewGroup && view.getVisibility() == View.VISIBLE) {
			ViewGroup vp = (ViewGroup) view;
			for (int i = 0; i < vp.getChildCount(); i++) {
				View viewchild = vp.getChildAt(i);
				allchildren.add(viewchild);
				allchildren.addAll(getAllChildViews(viewchild));
			}
		}
		return allchildren;
	}

	/**
	 * 
	 * 获取当前apk的版本信息 [功能详细描述]
	 * 
	 * @return
	 */
	public BusinessInfo getPackageInfo(String pkgName) {
		BusinessInfo plugin = new BusinessInfo();
		if (StringUtils.isEmpty(pkgName)) {
			pkgName = this.getPackageName();
		}
		try {
			PackageInfo info = this.getPackageManager().getPackageInfo(pkgName,
					0);
			if (null != info) {
				// 当前应用的版本名称 用于版本比较
				plugin.setVersionName(info.versionName);
				// 当前版本的包名
				plugin.setPackageName(info.packageName);
				plugin.setShorcut(getPackageManager().getApplicationIcon(
						pkgName));
			}
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return plugin;
	}

	/**
	 * install app
	 * 
	 * @param context
	 * @param filePath
	 * @return whether apk exist
	 */
	public boolean install(Context context, String filePath) {
		Intent i = new Intent(Intent.ACTION_VIEW);
		File file = new File(filePath);
		if (file != null && file.length() > 0 && file.exists() && file.isFile()) {
			i.setDataAndType(Uri.parse("file://" + filePath),
					"application/vnd.android.package-archive");
			i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(i);
			return true;
		}
		return false;
	}

	/**
	 * 
	 * 卸载apk [功能详细描述]
	 * 
	 * @param pkgName
	 */
	public void unInstall(String pkgName) {
		// 通过程序的包名创建URI
		Uri packageURI = Uri.parse("package:" + pkgName);
		// 创建Intent意图
		Intent intent = new Intent(Intent.ACTION_DELETE, packageURI);
		// 执行卸载程序
		startActivity(intent);
	}

	/**
	 * 
	 * isSuccessLogin(是否成功登录过)
	 * 
	 * @return boolean
	 * @exception
	 * @since 1.0.0
	 */
	public boolean isSuccessLogin() {
		return getAppShare().getBoolean(Constant.LOGGED_ON, false);
	}

	/**
	 * 
	 * 
	 * isAutoLogin(是否自动登陆)
	 * 
	 * TODO(这里描述这个方法适用条件 – 可选)
	 * 
	 * @param name
	 * 
	 * @param @return 设定文件
	 * 
	 * @return String DOM对象
	 * 
	 * @Exception 异常对象
	 * 
	 * @since 2014年1月14日
	 */
	public boolean isAutoLogin() {
		return getAppShare().getBoolean(Constant.AUTO_LOGIN, false);
	}

	public void setIsAutoLogin(boolean b) {
		getAppShare().edit().putBoolean(Constant.AUTO_LOGIN, b).commit();
	}

	public SharedPreferences getAppShare() {
		return getSharedPreferences(Constant.CONFIG_NAME, 0);
	}

	public void putOSSURL(String url) {
		getAppShare().edit().putString("OSS", url).commit();
	}

	public void putCBSURL(String url) {
		getAppShare().edit().putString("CBS", url).commit();
	}

	public void putSNSURL(String url) {
		getAppShare().edit().putString("SNS", url).commit();
	}

	public OnClickListener ok = new OnClickListener() {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			logoutDirect();
		}
	};

	public void logoutDirect() {
		SuperApplication.getIns().exitApp();
	}

	public void showLogoutDialog() {
		showAlertDialog(0, getString(R.string.tip),
				getString(R.string.is_logout), null, ok, DEFAULT_BTN, null,
				true, true);
	}

	/**
	 * 获取手机号码
	 * 
	 * @return
	 */
	public String getPhoneNumber() {
		String phoneId = "";
		try {
			// 创建电话管理与手机建立连接
			TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
			phoneId = tm.getLine1Number();
			if (phoneId.startsWith("+86")) {
				phoneId = phoneId.replace("+86", "");
			}
		} catch (Exception e) {
			phoneId = "";
		}

		return phoneId;
	}
}