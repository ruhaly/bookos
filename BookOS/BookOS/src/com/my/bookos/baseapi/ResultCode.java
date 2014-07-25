package com.my.bookos.baseapi;

public class ResultCode {

	// 响应成功
	public static final String RESULT_SUCCESS = "200";

	// 登入密码错误或用户不存在
	public static final String RESULT_LOGIN_ERROR_01 = "400";

	// 抄表员手机号不区配
	public static final String RESULT_LOGIN_ERROR_02 = "500";

	// 响应失败
	public static final String RESULT_FAILD = "400";

	// 500:优先抄分表 (改变页码查询下一条) ，
	public static final int RESULT_SAVE_ERROR_01 = 500;

	// 600:总表水量错误(不改页面继续查询))
	public static final int RESULT_SAVE_ERROR_02 = 600;

}
