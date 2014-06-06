/**
 * 
 * 类名称： Constant.java 
 * 作者： hlr 
 * 创建时间： 2012-5-7 下午4:06:28 
 * 版权声明 ： Copyright(C) 2008-2010 RichPeak
 *
 */
package com.changhong.sdk.baseapi;

/**
 * 
 * 
 * 项目名称：CH_foundation 类名称：Constant 类描述： 创建人：b 创建时间：2014年1月14日 上午9:39:40 修改人：b
 * 修改时间：2014年1月14日 上午9:39:40 修改备注：
 * 
 * @version
 * 
 */
public class Constant
{
    
    public static final String TAG = "ch_foundation";
    
    // 是否成功登陆过的 key
    public static final String LOGGED_ON = "logged_on";
    
    public static final String AUTO_LOGIN = "auto_login";
    
    public static final String PROVINCE = "0";
    
    public static final String CITY = "1";
    
    public static final String COMMUNITY = "2";
    
    public static final String ZUTUAN = "3";
    
    public static final String BUILDING = "4";
    
    public static final String UNIT = "5";
    
    public static final String DOORPLATE = "6";
    
    public static final String TYPE_REGISTER_VALICEDE = "1";
    
    public static final String TYPE_FORGET_PWD_VALICEDE = "2";
    
    public static final String TYPE_UPDATE_BINDPHONE_VALICODE = "3";
    
    //终端类型
    public static final int TERMINAL_TYPE = 1;
    
    //终端类型0:手机
    //    1:TV
    //    2:PAD    
    public static final String CLIENTTYPE = "0";
    
    public static final String OTHER_APP_MAIN_ACTIVITY = ".MainActivity";
    
    public static final String PREFIX = "com.changhong";
    
    public static final String CURRENT_PACKAGE = "com.changhong.foundation";
    
    //安装状态 未安装0 已安装1 已卸载2
    public static final String INSTALLED = "1";
    
    //权限
    //0:已购买
    //1:未购买
    //2:免费使用
    public static final String HAS_BUY = "0";
    
    public static final String NO_BUY = "1";
    
    public static final String PLUGIN_BIANMING = "1";
    
    public static final String PLUGIN_GUANGKONG = "2";
    
    public static final String PLUGIN_ANFANG = "3";
    
    public static final String PLUGIN_OTHER = "4";
    
    //0:已购买1:未购买2:免费使用
    public static final String FREE_USE = "2";
    
    public static final String UPDATE_PLUGIN_VIEW = "com.update_plugin_view";
    
    public static final String[] bianmingArray = { "com.changhong.cinema",
            "com.changhong.property", "com.changhong.store",
            "com.changhong.sns" };
    
    public static final String[] guankongArray = { "" };
    
    public static final String[] anfangArray = { "" };
    
    public static final String[] otherArray = { "" };
    
    public static final String apk_install_update = "0";
    
    public static final String apk_uninstall = "1";
    
    public static final String CONFIG_NAME = "ch_foundation_config";
    
    public static final String NOTIFYCATION_CHECK = "notifycation_check";
    
    public static final String ACTION_BACKGROUND = "com.ch_foundation.action_background";
    
    public static final int TIMEOUT = 20000;
    
    public static final int CITY_SELECT = 102;
    
    public static final String MAN = "1";
    
    public static final String WOMAN = "0";
    
    /**
     * 同意
     */
    public static final String PROCESSTYPE_ACCEPT = "0";
    
    /**
     * 拒绝
     */
    public static final String PROCESSTYPE_REJECT = "1";
    
    /**
     * 清空
     */
    public static final String PROCESSTYPE_CLEAR = "2";
    
    public static final String IS_EXIST = "0";
    
    public static final String NOT_EXIST = "1";
    
    public static final int UPDATE_VIEW = 800;
    
    public static final int UPDATE_VIEW_DATA = 801;
    
    //0物管1微生活2智能
    public static final String APP_PRO = "0";
    
    public static final String APP_WEILIFE = "1";
    
    public static final String APP_SMART = "2";
    
    public static final String PRO_NOTICE_ID = "pro_notice_id";
    
    //APK类型 1：底座版本  2：插件
    public static final String APK_TYPE = "1";
    
    //外卖
    public static final long TAKEOUT_FOOD = 10001l;
    
    //物业服务
    public static final long PROPERTY_SERVICE = 10002l;
    
    //家政服务
    public static final long HOUSEHOLD_SERVICE = 10003l;
    
    //维修
    public static final long MAINTAIN = 10004l;
    
    //开锁
    public static final long UNLOCK = 10005l;
    
    //疏通
    public static final long DREDGE = 10006l;
    
    //教育培训
    public static final long EDUTRAIN = 10007l;
    
    //快递
    public static final long DISPATCH = 10008l;
    
    //医院
    public static final long HOSPITAL = 10009l;
    
    //汽车服务
    public static final long BUS_SERVICE = 10010l;
    
    //搬家
    public static final long MOVE_HOUSE = 10011l;
    
    //出租车电招
    public static final long TAXI = 10012l;
    
    //其他
    public static final long OTHERS = 10013l;
    
    //呼叫物管
    public static final long CALL_PRO = 10014l;
    
    public static final String MSG_NUM = "msg_num";
    
    //是否已读0未读1已读
    public static final String MSG_STATE_UNREAD = "0";
    
    //是否已读0未读1已读
    public static final String MSG_STATE_READ = "1";
    
    //上一次请求消息中心数据的时间戳
    public static final String LAST_GETMSG_TIME = "last_getmsg_time";
    
    //是否已读0未读1已读
    public static final String MSG_QUNFA = "0";
    
    //是否已读0未读1已读
    public static final String MSG_DANFA = "1";
    
    //应用程序更新
    public static final String MSG_APK_UPDATE = "7";
    
    //消息提示开关0关闭1开启 默认开启
    public static final int MSG_TIP_ON = 1;
    
    public static final int MSG_TIP_OFF = 0;
    
    public static final int MSG_TYPE_BAOXIU = 0;
    
    public static final int MSG_TYPE_ZHOUBIAN = 1;
    
    public static final int MSG_TYPE_JIAOFEI = 2;
    
    public static final int MSG_TYPE_TUANGOU = 3;
    
    public static final int MSG_TYPE_SHAISHENGHUO = 4;
    
    public static final int MSG_TYPE_JIAOHUANKONGJIAN = 5;
    
    public static final int MSG_TYPE_YIJIANXIANG = 6;
    
    public static final int MSG_TYPE_APPUPDATE = 7;
    
    public static final String IS_FIRST_LOGIN = "is_first_login";
    
    public static class CommunityKey
    {
        public static final String COMMUNITY_ID = "community_id";
        
        public static final String COMMUNITY_NAME = "community_name";
        
        public static final String COMMUNITY_PICURL = "community_picurl";
        
        public static final String COMMUNITY_ADDRESS = "community_address";
        
        public static final String COMMUNITY_ISSELECT = "community_isselect";
        
        public static final String COMMUNITY_LAT = "community_lat";
        
        public static final String COMMUNITY_LNG = "community_lng";
    }
    
    public static class UserKey
    {
        public static final String USER_ACCOUNT = "user_account";
        
        public static final String USER_PWD = "user_pwd";
    }
}
