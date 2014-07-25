package com.my.bookos.baseapi;

import org.json.JSONObject;

import com.my.bookos.bean.BookData;

public class JsonParse {

	public static String parseLoginData(String response) throws Exception {
		String str = "";
		JSONObject jsonObject = new JSONObject(response);
		str = jsonObject.optString("employeeid");
		return str;
	}

	public static BookData parseSearchData(String response) throws Exception {
		BookData bd = new BookData();
		JSONObject jsonObject = new JSONObject(response);
		bd.setMobilephone(jsonObject.optString("mobilephone"));
		bd.setEmployeeid(jsonObject.optString("employeeid"));
		bd.setUsername(jsonObject.optString("username"));
		bd.setUsercode(jsonObject.optString("usercode"));
		bd.setStatus(jsonObject.optString("status"));
		bd.setLastreading(jsonObject.optString("lastreading"));
		bd.setUserid(jsonObject.optString("userid"));
		bd.setUseraddr(jsonObject.optString("useraddr"));
		bd.setRowCount(jsonObject.optString("rowCount"));
		bd.setTelephone(jsonObject.optString("telephone"));
		bd.setPageNum(jsonObject.optString("pageNum"));
		bd.setLastwateramount(jsonObject.optString("lastwateramount"));
		bd.setWatertype(jsonObject.optString("watertype"));
		bd.setMeterkind(jsonObject.optString("meterkind"));
		bd.setBookid(jsonObject.optString("bookid"));
		return bd;
	}
}
