package com.my.bookos.baseapi;

import android.content.Context;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.changhong.sdk.baseapi.AppLog;
import com.changhong.sdk.baseapi.StringUtils;
import com.my.bookos.bean.CallBack;

/**
 * 
 * 定位类 [功能详细描述]
 */
public class LocationUtils {

	public static final String TAG = "LocationUtils";

	public LocationClient mLocationClient = null;

	public BDLocationListener myListener = new MyLocationListener();

	public CallBack callBack;

	public static LocationUtils ins;

	public static synchronized LocationUtils getInstant() {
		if (null == ins) {
			ins = new LocationUtils();
		}
		return ins;
	}

	public void init(Context context, CallBack callBack) {
		if (null == mLocationClient) {
			this.callBack = callBack;
			mLocationClient = new LocationClient(context); // 声明LocationClient类
			mLocationClient.registerLocationListener(myListener); // 注册监听函数
			LocationClientOption option = new LocationClientOption();
			option.setLocationMode(LocationMode.Hight_Accuracy);// 设置定位模式
			option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度，默认值gcj02
			// option.setScanType(5000);//设置发起定位请求的间隔时间为5000ms
			option.setScanSpan(5000);
			option.setIsNeedAddress(true);// 返回的定位结果包含地址信息
			// option.setNeedDeviceDirect(true);//返回的定位结果包含手机机头的方向
			mLocationClient.setLocOption(option);
			mLocationClient.start();
		}
	}

	/**
	 * 
	 * 启动定位 [功能详细描述]
	 * 
	 * @param context
	 * @param myListener
	 */
	public void startLocate(Context context, CallBack callBack) {
		ins.start(context, callBack);
	}

	/**
	 * 停止定位 [一句话功能简述]<BR>
	 * [功能详细描述]
	 */
	public void stopLocate() {
		if (null != mLocationClient) {
			mLocationClient.stop();
		}
	}

	public class MyLocationListener implements BDLocationListener {
		@Override
		public void onReceiveLocation(BDLocation location) {
			if (location == null)
				return;
			StringBuffer sb = new StringBuffer(256);
			sb.append("time : ");
			sb.append(location.getTime());
			sb.append("\nerror code : ");
			sb.append(location.getLocType());
			sb.append("\nlatitude : ");
			sb.append(location.getLatitude());
			sb.append("\nlontitude : ");
			sb.append(location.getLongitude());
			sb.append("\nradius : ");
			sb.append(location.getRadius());
			if (location.getLocType() == BDLocation.TypeGpsLocation) {
				sb.append("\nspeed : ");
				sb.append(location.getSpeed());
				sb.append("\nsatellite : ");
				sb.append(location.getSatelliteNumber());
			} else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
				sb.append("\naddr : ");
				sb.append(location.getCity());
			}

			AppLog.out(
					TAG,
					"城市编码" + location.getCityCode() + "城市" + location.getCity(),
					AppLog.LEVEL_INFO);
			if (!StringUtils.isEmpty(location.getCity())) {
				callBack.update(location);
//				mLocationClient.stop();
//				mLocationClient = null;
			}
			logMsg(sb.toString());
		}

		public void logMsg(String str) {
			AppLog.out(TAG, str, AppLog.LEVEL_INFO);
		}

		@Override
		public void onReceivePoi(BDLocation arg0) {
			// TODO Auto-generated method stub

		}
	}

	public void start(Context context, CallBack callBack) {
		init(context, callBack);
		if (mLocationClient != null) {
			mLocationClient.requestLocation();
		} else {
			AppLog.out(TAG, "locClient is null or not started",
					AppLog.LEVEL_INFO);
		}
	}

}
