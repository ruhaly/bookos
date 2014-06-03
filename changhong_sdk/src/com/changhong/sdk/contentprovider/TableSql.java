package com.changhong.sdk.contentprovider;

import com.changhong.sdk.contentprovider.ChangHong.Foundation;
import com.changhong.sdk.contentprovider.ChangHong.Friend;
import com.changhong.sdk.contentprovider.ChangHong.Shopping;

/**
 * [建表语句]<BR>
 * [功能详细描述]
 * @author 王磊
 * @version [ChangHong SmartHome V100R001C03, 2014-4-22] 
 */
public class TableSql
{
    /**
     * 创建商品表
     */
    public static final String CREATE_SHOPPING = "create table if not exists "
            + Shopping.TABLE_NAME + "(" + Shopping.ACTUALPRICE + " double,"
            + Shopping.ADDRESS + " text," + Shopping.DESC + " text,"
            + Shopping.GOODSID + " text," + Shopping.NAME + " text,"
            + Shopping.PICURL + " text," + Shopping.SPID + " text,"
            + Shopping.STOCK + " Integer " + ")";
    
    /**
     * 删除商品表
     */
    public static final String DROP_PHOTO = "drop table if exists "
            + Shopping.TABLE_NAME;
    
    /**
     * 创建好友表
     */
    public static final String CREATE_FRIEND = "create table if not exists "
            + Friend.TABLE_NAME + "(" + Friend.FRIENDID + " text,"
            + Friend.FRIENDNAME + " text," + Friend.FRIENDPHOTOPATH + " text,"
            + Friend.FRIENDBIRTH + " text," + Friend.FRIENDSEX + " text,"
            + Friend.FRIENDADDRESS + " text )";
    
    /**
     * 删除好友表
     */
    public static final String DROP_FRIEND = "drop table if exists "
            + Friend.TABLE_NAME;
    
    //
    //    /**
    //     * 创建时间间隔
    //     */
    //    public static final String CREATE_TIMEINTERVAL = "create table if not exists " + TimeInterval.TABLE_NAME + "("
    //            + TimeInterval._ID + " Integer primary key autoincrement," + TimeInterval.INTERVAL + " text,"
    //            + TimeInterval.IS_CHECKED + " Integer" + ")";
    //
    //    /**
    //     * 删除时间间隔
    //     */
    //    public static final String DROP_TIMEINTERVAL = "drop table if exists " + TimeInterval.TABLE_NAME;
    //
    //    /**
    //     * 创建特效表
    //     */
    //    public static final String CREATE_EFFECTS = "create table if not exists " + Effects.TABLE_NAME + "(" + Effects._ID
    //            + " Integer primary key autoincrement," + Effects.EFFECT + " text," + Effects.IS_CHECKED + " Integer" + ")";
    //
    //    /**
    //     * 删除特效表
    //     */
    //    public static final String DROP_EFFECTS = "drop table if exists " + Effects.TABLE_NAME;
    //
    //    /**
    //     * 创建默认天气城市表
    //     */
    //    public static final String CREATE_WEATHERCITY = "create table if not exists " + WeatherCity.TABLE_NAME + "("
    //            + WeatherCity._ID + " Integer primary key autoincrement," + WeatherCity.PROVINCE + " text,"
    //            + WeatherCity.CITY + " text," + WeatherCity.AREACODE + " text " + ")";
    //
    //    /**
    //     * 删除默认天气城市表
    //     */
    //    public static final String DROP_WEATHERCITY = "drop table if exists " + WeatherCity.TABLE_NAME;
    //
    //    /**
    //     * 创建省市表
    //     */
    //    public static final String CREATE_PROVINCECITY = "create table if not exists " + ProvinceCity.TABLE_NAME + "("
    //            + ProvinceCity._ID + " Integer primary key autoincrement," + ProvinceCity.PROVINCE + " text,"
    //            + ProvinceCity.CITY + " text," + WeatherCity.AREACODE + " text " + ")";
    //
    //    /**
    //     * 删除省市表
    //     */
    //    public static final String DROP_PROVINCECITY = "drop table if exists " + ProvinceCity.TABLE_NAME;
    //
    //    /**
    //     * 创建声音表
    //     */
    //    public static final String CREATE_VOICE = "create table if not exists " + Voice.TABLE_NAME + "(" + Voice._ID
    //            + " Integer primary key autoincrement," + Voice.VOICE_ITEM + " text," + Voice.IS_CHECKED + " Integer" + ")";
    //
    //    /**
    //     * 删除声音表
    //     */
    //    public static final String DROP_VOICE = "drop table if exists " + Voice.TABLE_NAME;
    //
    //    /**
    //     * 创建字幕表
    //     */
    //    public static final String CREATE_CAPTION = "create table if not exists " + Caption.TABLE_NAME + "(" + Caption._ID
    //            + " Integer primary key autoincrement," + Caption.CAPTION_ITEM + " text," + Caption.IS_CHECKED + " Integer"
    //            + ")";
    //
    //    /**
    //     * 删除字幕表
    //     */
    //    public static final String DROP_CAPTION = "drop table if exists " + Caption.TABLE_NAME;
    //
    //    /**
    //     * 创建用户表
    //     */
    //    public static final String CREATE_USERINFO = "create table if not exists " + UserInfo.TABLE_NAME + "("
    //            + UserInfo.ISFIRSTRUN + " text," + UserInfo.REGNUM + " text," + UserInfo.ISREG + " text" + ")";
    //
    //    /**
    //     * 创建好友表
    //     */
    //    public static final String CREATE_FRIEND = "create table if not exists " + Friend.TABLE_NAME + "("
    //            + Friend.FRIENDID + " text," + Friend.NICKNAME + " text" + ")";
    
    /**
     * 创建原子业务表
     */
    public static final String CREATE_ATOMBUSINESS = "create table if not exists "
            + Foundation.TABLE_NAME_ATOM
            + "("
            + Foundation.APKNAME
            + " text,"
            + Foundation.PACKAGENAME
            + " text,"
            + Foundation.MAINACTIVITYPATH
            + " text,"
            + Foundation.APKID
            + " text,"
            + Foundation.BUSINESSCODE
            + " text,"
            + Foundation.BUSINESSNAME
            + " text,"
            + Foundation.SIZE
            + " text,"
            + Foundation.BUSINESSTYPE
            + " text,"
            + Foundation.ISAUTHORITY
            + " text,"
            + Foundation.SERIALNUMBER
            + " text,"
            + Foundation.SOURCEURL
            + " text,"
            + Foundation.SPID
            + " text,"
            + Foundation.VERSIONNO
            + " text,"
            + Foundation.ICON
            + " text,"
            + Foundation.SHORTCUTSTATE
            + " text,"
            + Foundation.STARNUM
            + " text,"
            + Foundation.INSTALLSTATE
            + " text " + ")";
    
    /**
     * 删除原子业务表
     */
    public static final String DROP_ATOMBUSINESS = "drop table if exists "
            + Foundation.TABLE_NAME_ATOM;
    
    /**
     * 创建消息中心表
     */
    public static final String CREATE_MESSAGE = "create table if not exists "
            + Foundation.TABLE_NAME_MESSAGE + "(" + Foundation.MSG_ID
            + " text," + Foundation.UID + " text," + Foundation.MSG_IMG
            + " text," + Foundation.MSG_CONTENT + " text,"
            + Foundation.MSG_DATE + " text," + Foundation.MSG_TITLE + " text,"
            + Foundation.MSG_MSGTYPE + " text," + Foundation.MSG_SENDTYPE
            + " text," + Foundation.MSG_COUNT + " text, "
            + Foundation.MSG_ORGID + " text, " + Foundation.MSG_THIRDID
            + " text, " + Foundation.MSG_READ_STATE + " text, "
            + Foundation.MSG_USERID + " text " +
            
            ")";
    
    /**
     * 删除消息中心表
     */
    public static final String DROP_MESSAGE = "drop table if exists "
            + Foundation.TABLE_NAME_MESSAGE;
    
    /**
     * 创建物管通知表
     */
    public static final String CREATE_NOTICE = "create table if not exists "
            + Foundation.TABLE_NAME_NOTICE + "(" + Foundation.MSG_ID + " text,"
            + Foundation.UID + " text," + Foundation.MSG_IMG + " text,"
            + Foundation.MSG_CONTENT + " text," + Foundation.MSG_DATE
            + " text," + Foundation.MSG_TITLE + " text,"
            + Foundation.MSG_MSGTYPE + " text," + Foundation.MSG_ORGID
            + " text, " + Foundation.MSG_ORIGINALTYPE + " text " + ")";
    
    /**
     * 删除物管通知表
     */
    public static final String DROP_NOTICE = "drop table if exists "
            + Foundation.TABLE_NAME_NOTICE;
    
}
