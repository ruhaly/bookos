package com.changhong.sdk.contentprovider;

//import java.io.File;
//import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import com.changhong.sdk.contentprovider.ChangHong.Foundation;
import com.changhong.sdk.contentprovider.ChangHong.Friend;
import com.changhong.sdk.contentprovider.ChangHong.Shopping;

/**
 * [简要描述]: <br/>[详细描述]:
 * 
 * @author fu_jinhu
 * @version [revision],Feb 16, 2011
 * @since WihomeV100R001C02LGDM05
 */
public class ChangHongContentProvider extends ContentProvider
{
    /**
     * 照片表匹配数字
     */
    private static final int SHOPPING = 1;
    
    /**
     * 好友表匹配数字
     */
    private static final int FRIEND = 2;
    
    /**
     * 原子业务匹配数字
     */
    public static final int ATOMBUSINESS = 3;
    
    /**
     * 消息匹配数字
     */
    public static final int MESSAGE = 4;
    
    /**
     * 物管通知匹配数字
     */
    public static final int NOTICE = 5;
    
    /**
     * UriMatcher URI匹配类
     */
    private static UriMatcher mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    
    /**
     * projectionMaps用来设置表的所有查询字段
     */
    private static final Map<String, HashMap<String, String>> PROJECTIONMAPS;
    
    /**
     * projectionMaps用来设置好友表的所有查询字段
     */
    private static final Map<String, HashMap<String, String>> FRIENDPROJECTIONMAPS;
    
    /**
     * ATOMBUSINESSMAPS用来设置原子业务表的所有查询字段
     */
    private static final Map<String, HashMap<String, String>> ATOMBUSINESSMAPS;
    
    /**
     * MESSAGEMAPS用来消息中心表的所有查询字段
     */
    private static final Map<String, HashMap<String, String>> MESSAGEMAPS;
    
    /**
     * MESSAGEMAPS用来消息中心表的所有查询字段
     */
    private static final Map<String, HashMap<String, String>> NOTICEMAPS;
    
    /**
     * ContentResolver
     */
    private ContentResolver cr = null;
    
    static
    {
        mUriMatcher.addURI(ChangHong.AUTHORITY, Shopping.TABLE_NAME, SHOPPING);
        mUriMatcher.addURI(ChangHong.AUTHORITY, Friend.TABLE_NAME, FRIEND);
        mUriMatcher.addURI(ChangHong.AUTHORITY,
                Foundation.TABLE_NAME_ATOM,
                ATOMBUSINESS);
        mUriMatcher.addURI(ChangHong.AUTHORITY,
                Foundation.TABLE_NAME_MESSAGE,
                MESSAGE);
        mUriMatcher.addURI(ChangHong.AUTHORITY,
                Foundation.TABLE_NAME_NOTICE,
                NOTICE);
    }
    
    static
    {
        
        PROJECTIONMAPS = new HashMap<String, HashMap<String, String>>();
        FRIENDPROJECTIONMAPS = new HashMap<String, HashMap<String, String>>();
        ATOMBUSINESSMAPS = new HashMap<String, HashMap<String, String>>();
        MESSAGEMAPS = new HashMap<String, HashMap<String, String>>();
        NOTICEMAPS = new HashMap<String, HashMap<String, String>>();
        ChangHong.initializeColumns(Shopping.TABLE_NAME,
                Shopping.SHOPPING_COLUMNS,
                PROJECTIONMAPS);
        ChangHong.initializeColumns(Friend.TABLE_NAME,
                Friend.FRIEND_COLUMNS,
                FRIENDPROJECTIONMAPS);
        ChangHong.initializeColumns(Foundation.TABLE_NAME_ATOM,
                Foundation.ATOMBUSINESS_COLUMNS,
                ATOMBUSINESSMAPS);
        ChangHong.initializeColumns(Foundation.TABLE_NAME_MESSAGE,
                Foundation.MESSAGE_COLUMNS,
                MESSAGEMAPS);
        ChangHong.initializeColumns(Foundation.TABLE_NAME_NOTICE,
                Foundation.NOTICE_COLUMNS,
                NOTICEMAPS);
    }
    
    /**
     * 删除
     * 
     * @param uri Uri
     * @param where String
     * @param whereArgs String[]
     * @return int
     */
    public int delete(Uri uri, String where, String[] whereArgs)
    {
        db = dbHelper.getWritableDatabase();
        int count;
        switch (mUriMatcher.match(uri))
        {
            case SHOPPING:
                count = db.delete(Shopping.TABLE_NAME, where, whereArgs);
                break;
            case FRIEND:
                count = db.delete(Friend.TABLE_NAME, where, whereArgs);
                break;
            case ATOMBUSINESS:
                count = db.delete(Foundation.TABLE_NAME_ATOM, where, whereArgs);
                break;
            case MESSAGE:
                count = db.delete(Foundation.TABLE_NAME_MESSAGE,
                        where,
                        whereArgs);
                break;
            case NOTICE:
                count = db.delete(Foundation.TABLE_NAME_NOTICE,
                        where,
                        whereArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        
        cr.notifyChange(uri, null);
        
        return count;
    }
    
    /**
     * 获取type
     * 
     * @param uri Uri
     * @return String
     */
    public String getType(Uri uri)
    {
        return null;
    }
    
    /**
     * 插入
     * 
     * @param uri Uri
     * @param values ContentValues
     * @return Uri
     */
    public Uri insert(Uri uri, ContentValues values)
    {
        String table = null;
        
        switch (mUriMatcher.match(uri))
        {
            case SHOPPING:
                table = Shopping.TABLE_NAME;
                break;
            case FRIEND:
                table = Friend.TABLE_NAME;
                break;
            case ATOMBUSINESS:
            {
                table = Foundation.TABLE_NAME_ATOM;
                break;
            }
            case MESSAGE:
            {
                table = Foundation.TABLE_NAME_MESSAGE;
                break;
            }
            case NOTICE:
            {
                table = Foundation.TABLE_NAME_NOTICE;
                break;
            }
            default:
                throw new IllegalArgumentException("Error URI " + uri);
        }
        db = dbHelper.getWritableDatabase();
        long rowId = db.insert(table, null, values);
        if (rowId > 0)
        {
            Uri newUri = ContentUris.withAppendedId(uri, rowId);
            cr.notifyChange(newUri, null);
            return newUri;
        }
        throw new SQLException("Failed to insert row into " + uri);
    }
    
    /**
     * 重载
     * 
     * @return boolean
     */
    
    private SQLiteDatabase db;
    
    public boolean onCreate()
    {
        cr = getContext().getContentResolver();
        dbHelper = new DatabaseHelper(getContext());
        return (dbHelper == null) ? false : true;
    }
    
    /**
     * 查询
     * 
     * @param uri Uri
     * @param projection String[]
     * @param selection String
     * @param selectionArgs String[]
     * @param sortOrder String
     * @return Cursor
     */
    public Cursor query(Uri uri, String[] projection, String selection,
            String[] selectionArgs, String sortOrder)
    {
        
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        SQLiteDatabase qdb = dbHelper.getWritableDatabase();
        switch (mUriMatcher.match(uri))
        {
            case SHOPPING:
                qb.setTables(Shopping.TABLE_NAME);
                qb.setProjectionMap(PROJECTIONMAPS.get(Shopping.TABLE_NAME));
                break;
            case FRIEND:
                qb.setTables(Friend.TABLE_NAME);
                qb.setProjectionMap(FRIENDPROJECTIONMAPS.get(Friend.TABLE_NAME));
                break;
            case ATOMBUSINESS:
            {
                qb.setTables(Foundation.TABLE_NAME_ATOM);
                qb.setProjectionMap(ATOMBUSINESSMAPS.get(Foundation.TABLE_NAME_ATOM));
                break;
            }
            case MESSAGE:
            {
                qb.setTables(Foundation.TABLE_NAME_MESSAGE);
                qb.setProjectionMap(MESSAGEMAPS.get(Foundation.TABLE_NAME_MESSAGE));
                break;
            }
            case NOTICE:
            {
                qb.setTables(Foundation.TABLE_NAME_NOTICE);
                qb.setProjectionMap(NOTICEMAPS.get(Foundation.TABLE_NAME_NOTICE));
                break;
            }
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        
        Cursor c = qb.query(qdb,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);
        c.setNotificationUri(cr, uri);
        return c;
    }
    
    /**
     * 更新
     * 
     * @param uri Uri
     * @param values ContentValues
     * @param where String
     * @param whereArgs String[]
     * @return int
     */
    public int update(Uri uri, ContentValues values, String where,
            String[] whereArgs)
    {
        
        SQLiteDatabase udb = dbHelper.getWritableDatabase();
        int count;
        switch (mUriMatcher.match(uri))
        {
            case SHOPPING:
                count = udb.update(Shopping.TABLE_NAME,
                        values,
                        where,
                        whereArgs);
                break;
            case FRIEND:
                count = udb.update(Friend.TABLE_NAME, values, where, whereArgs);
                break;
            case MESSAGE:
                count = udb.update(Foundation.TABLE_NAME_MESSAGE,
                        values,
                        where,
                        whereArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        cr.notifyChange(uri, null);
        return count;
    }
    
    /**
     * 数据库辅助类
     */
    private DatabaseHelper dbHelper;
    
    /**
     * 数据库名称
     */
    private static final String DATABASE_NAME = "changhong.db";
    
    /**
     * 数据库版本
     */
    private static final int DATABASE_VERSION = 1;
    
    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        
        @Override
        public void onCreate(SQLiteDatabase db)
        {
            //创建用于存储数据的表
            db.execSQL(TableSql.CREATE_SHOPPING);
            db.execSQL(TableSql.CREATE_FRIEND);
            db.execSQL(TableSql.CREATE_ATOMBUSINESS);
            db.execSQL(TableSql.CREATE_NOTICE);
            db.execSQL(TableSql.CREATE_MESSAGE);
        }
        
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            db.execSQL(TableSql.DROP_PHOTO);
            db.execSQL(TableSql.DROP_FRIEND);
            db.execSQL(TableSql.DROP_ATOMBUSINESS);
            db.execSQL(TableSql.DROP_NOTICE);
            db.execSQL(TableSql.DROP_MESSAGE);
            onCreate(db);
        }
    }
    
}
