package com.changhong.sdk.contentprovider;

import java.util.HashMap;
import java.util.Map;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * [共享数据库常量定义]<BR>
 * [功能详细描述]
 * @author 王磊
 * @version [ChangHong SmartHome V100R001C03, 2014-4-22] 
 */
public class ChangHong
{
    /**
     * AUTHORITY
     */
    public static final String AUTHORITY = "com.changhong.sdk";
    
    /**
     * segment
     */
    public static String segment = "/";
    
    /**
     * prefix
     */
    private static String prefix = "content:".concat(segment.concat(segment));
    
    /**
     * 
     * 底座
     * [功能详细描述]
     * @author hanliangru
     * @version [智慧社区-终端底座, 2014年3月4日]
     */
    public static class Foundation implements BaseColumns
    {
        
        public static final String UID = "UID";
        
        /**
         * 表名
         */
        public static final String TABLE_NAME_ATOM = "ATOMBUSINESS";
        
        /**
         * URI
         */
        public static final String URI_ATOM = prefix + AUTHORITY + segment
                + TABLE_NAME_ATOM;
        
        //                + segment
        //                + ChangHongContentProvider.ATOMBUSINESS;
        
        /**
         * CONTENT_URI
         */
        public static final Uri CONTENT_URI_ATOM = Uri.parse(URI_ATOM);
        
        //apk名字
        public static final String APKNAME = "apkName";
        
        //包名
        public static final String PACKAGENAME = "packageName";
        
        //apk主界面全路径
        public static final String MAINACTIVITYPATH = "mainActivityPath";
        
        //后台传的id
        public static final String APKID = "apkId";
        
        //原子业务编码
        public static final String BUSINESSCODE = "businessCode";
        
        //业务类型名字
        public static final String BUSINESSNAME = "businessName";
        
        //apk大小
        public static final String SIZE = "size";
        
        //原子业务类型
        public static final String BUSINESSTYPE = "businessType";
        
        //权限
        //0:已购买
        //1:未购买
        //2:免费使用
        public static final String ISAUTHORITY = "isAuthority";
        
        //序列号
        public static final String SERIALNUMBER = "serialNumber";
        
        //安装包地址
        public static final String SOURCEURL = "sourceUrl";
        
        //所属SP
        public static final String SPID = "spId";
        
        //最新版本号
        public static final String VERSIONNO = "versionNo";
        
        //安装状态 未安装0 已安装1 已卸载2
        public static final String INSTALLSTATE = "installState";
        
        //图标如果是服务器的就下载，如果是预装的直接加上 可能为int跟string类型 
        public static final String ICON = "icon";
        
        //是否有快捷方式  1：有 ;其他无 用于主页定制
        public static final String SHORTCUTSTATE = "shortcutState";
        
        public static final String STARNUM = "starNum";
        
        /**
         *原子业务 列
         * ATOMBUSINESS_COLUMNS
         */
        
        public static final String[] ATOMBUSINESS_COLUMNS = new String[] {
                APKNAME, PACKAGENAME, MAINACTIVITYPATH, APKID, BUSINESSCODE,
                BUSINESSNAME, SIZE, BUSINESSTYPE, ISAUTHORITY, SERIALNUMBER,
                SOURCEURL, SPID, VERSIONNO, INSTALLSTATE, ICON, SHORTCUTSTATE,
                STARNUM };
        
        //        消息
        /**
         * 表名
         */
        public static final String TABLE_NAME_MESSAGE = "MESSAGE";
        
        /**
         * URI
         */
        public static final String URI_MESSAGE = prefix + AUTHORITY + segment
                + TABLE_NAME_MESSAGE;
        
        //                + segment
        //                + ChangHongContentProvider.ATOMBUSINESS;
        
        /**
         * CONTENT_URI
         */
        public static final Uri CONTENT_URI_MESSAGE = Uri.parse(URI_MESSAGE);
        
        /**
         * 表名
         */
        public static final String TABLE_NAME_NOTICE = "NOTICE";
        
        //通知
        /**
         * URI
         */
        public static final String URI_NOTICE = prefix + AUTHORITY + segment
                + TABLE_NAME_NOTICE;
        
        //                + segment
        //                + ChangHongContentProvider.ATOMBUSINESS;
        
        /**
         * CONTENT_URI
         */
        public static final Uri CONTENT_URI_NOTICE = Uri.parse(URI_NOTICE);
        
        //消息id
        public static final String MSG_ID = "messageId";
        
        //消息图标
        public static final String MSG_IMG = "img";
        
        //消息内容
        public static final String MSG_CONTENT = "content";
        
        //消息发布时间
        public static final String MSG_DATE = "date";
        
        //消息标题
        public static final String MSG_TITLE = "title";
        
        //消息类型
        public static final String MSG_MSGTYPE = "msgtype";
        
        //消息原始类型
        public static final String MSG_ORIGINALTYPE = "originaltype";
        
        //消息个数
        public static final String MSG_COUNT = "msgcount";
        
        //小区id
        public static final String MSG_ORGID = "orgid";
        
        //第三方id
        public static final String MSG_THIRDID = "thirdid";
        
        public static final String MSG_USERID = "userid";
        
        //发送类型:0:群发,1:单发
        public static final String MSG_SENDTYPE = "sendtype";
        
        //是否已读0未读1已读
        public static final String MSG_READ_STATE = "readstate";
        
        /**
         *消息表 列
         * ATOMBUSINESS_COLUMNS
         */
        
        public static final String[] MESSAGE_COLUMNS = new String[] { UID,
                MSG_ID, MSG_IMG, MSG_CONTENT, MSG_DATE, MSG_TITLE, MSG_MSGTYPE,
                MSG_COUNT, MSG_ORGID, MSG_THIRDID, MSG_USERID, MSG_SENDTYPE,
                MSG_READ_STATE };
        
        /**
         *物管通知
         * NOTICE_COLUMNS
         */
        
        public static final String[] NOTICE_COLUMNS = new String[] { UID,
                MSG_ID, MSG_IMG, MSG_CONTENT, MSG_DATE, MSG_TITLE, MSG_MSGTYPE,
                MSG_ORIGINALTYPE, MSG_ORGID };
    }
    
    /**
     * [商品数据]<BR>
     * [功能详细描述]
     * @author b
     * @version [RCS Client V100R001C03, 2014-2-20] 
     */
    public static class Shopping implements BaseColumns
    {
        /**
         * 表名
         */
        public static final String TABLE_NAME = "SHOPPING";
        
        /**
         * URI
         */
        public static final String URI = prefix + AUTHORITY + segment
                + TABLE_NAME;
        
        /**
         * CONTENT_URI
         */
        public static final Uri CONTENT_URI = Uri.parse(URI);
        
        /**
         * 商品图片地址
         */
        public static final String PICURL = "picURL";
        
        /**
         * 商场地址
         */
        public static final String ADDRESS = "address";
        
        /**
         * 商品库存
         */
        public static final String STOCK = "stock";
        
        /**
         * 商品描述
         */
        public static final String DESC = "desc";
        
        /**
         * 商品实际价格
         */
        public static final String ACTUALPRICE = "actualPrice";
        
        /**
         * 商品名称
         */
        public static final String NAME = "name";
        
        /**
         * 商品ID
         */
        public static final String GOODSID = "id";
        
        /**
         *  所属SPID
         */
        public static final String SPID = "spId";
        
        /**
         * PHOTO_COLUMNS
         */
        
        public static final String[] SHOPPING_COLUMNS = new String[] { SPID,
                GOODSID, NAME, ACTUALPRICE, STOCK, ADDRESS, PICURL, DESC };
        
    }
    
    /**
     * SNS的好友
     * @author wanghonghong
     *
     */
    public static class Friend implements BaseColumns
    {
        /**
         * 表名
         */
        public static final String TABLE_NAME = "FRIEND";
        
        /**
         * URI
         */
        public static final String URI = prefix + AUTHORITY + segment
                + TABLE_NAME;
        
        /**
         * CONTENT_URI
         */
        public static final Uri CONTENT_URI = Uri.parse(URI);
        
        /**
         * 好友名字
         */
        public static final String FRIENDNAME = "friendName";
        
        /**
         * 好友ID
         */
        public static final String FRIENDID = "friendId";
        
        /**
         * 好友头像地址
         */
        public static final String FRIENDPHOTOPATH = "friendPhotoPath";
        
        /**
         * 好友生日
         */
        public static final String FRIENDBIRTH = "friendBirth";
        
        /**
         * 好友性别
         */
        public static final String FRIENDSEX = "friendSex";
        
        /**
         * 好友家庭地址
         */
        public static final String FRIENDADDRESS = "friendAddress";
        
        /**
         * Friend_COLUMNS
         */
        
        public static final String[] FRIEND_COLUMNS = new String[] { FRIENDID,
                FRIENDNAME, FRIENDPHOTOPATH, FRIENDBIRTH, FRIENDSEX,
                FRIENDADDRESS };
    }
    
    /**
     * [简要描述]:<br/>
     * [详细描述]:<br/>
     * 初始化ContentProvider中需要的projectionMaps
     *
     * @param tableName String
     * @param columns String[]
     * @param projectionMaps Map<String, HashMap<String, String>>
     * @exception
     */
    public static final void initializeColumns(String tableName,
            String[] columns,
            Map<String, HashMap<String, String>> projectionMaps)
    {
        HashMap<String, String> projectionMap = new HashMap<String, String>();
        
        int lenght = columns.length;
        for (int i = 0; i < lenght; i++)
        {
            
            projectionMap.put(columns[i], columns[i]);
            
        }
        
        projectionMaps.put(tableName, projectionMap);
        
    }
}
