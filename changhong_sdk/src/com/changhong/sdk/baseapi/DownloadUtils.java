package com.changhong.sdk.baseapi;

import java.io.File;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import com.changhong.sdk.R;
import com.changhong.sdk.entity.BusinessInfo;
import com.changhong.sdk.logic.DownloadLogic;

/**
 * 
 * 下载管理类
 * [功能详细描述]
 * @author hanliangru
 * @version [智慧社区-终端底座, 2014年3月10日]
 */
public class DownloadUtils
{
    public static final String TAG = "DownloadUtils";
    
    public static DownloadManagerPro downloadManagerPro;
    
    private static DownloadManager downloadManager;
    
    private static long downloadId = 0;
    
    public static final String DOWNLOAD_FOLDER_NAME = "ch_apk";
    
    public static final String KEY_NAME_DOWNLOAD_ID = "downloadId";
    
    public static final String MMIMETYPE = "application/cn.changhong.download.file";
    
    private static DownloadLogic dlLogic = DownloadLogic.getInstance();
    
    public static final String APK_SUFFIX = ".apk";
    
    public static void initDownload(Context context)
    {
        /**
         * get download id from preferences.<br/>
         * if download id bigger than 0, means it has been downloaded, then query status and show right text;
         */
        downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        downloadManagerPro = new DownloadManagerPro(downloadManager);
        initDownloaderDir();
    }
    
    public static File initDownloaderDir()
    {
        //        File folder = new File(
        //                CHUtils.getDiskCacheDir(getActivity().getBaseContext(),
        //                        DOWNLOAD_FOLDER_NAME));
        //        if (!folder.exists() || !folder.isDirectory())
        //        {
        //            folder.mkdirs();
        //        }
        
        File folder = new File(DOWNLOAD_FOLDER_NAME);
        if (!folder.exists() || !folder.isDirectory())
        {
            folder.mkdirs();
        }
        return folder;
    }
    
    /**
     * 
     * 下载apk
     * [功能详细描述]
     * @param bi
     */
    @SuppressLint({ "InlinedApi", "NewApi" })
    public static void download(Context context, BusinessInfo bi, String hostUrl)
    {
        initDownload(context);
        String pkgName = bi.getPackageName();
        if (StringUtils.isEmpty(pkgName))
        {
            return;
        }
        
        if (PreferencesUtils.isContainsKey(context, pkgName))
        {
            
            int downId = downloadManagerPro.getStatusById(PreferencesUtils.getLong(context,
                    pkgName));
            if (downId == DownloadManager.STATUS_RUNNING)
            {
                Toast.makeText(context,
                        context.getResources()
                                .getString(R.string.app_installing),
                        Toast.LENGTH_SHORT).show();
                return;
            }
            
        }
        
        AppLog.out(TAG,
                "下载地址:" + hostUrl + bi.getSourceUrl(),
                AppLog.LEVEL_INFO);
        DownloadManager.Request request = new DownloadManager.Request(
                Uri.parse(hostUrl + bi.getSourceUrl()));
        request.setDestinationInExternalPublicDir(DOWNLOAD_FOLDER_NAME,
                null == bi.getBusinessName() ? "" : bi.getBusinessName());
        request.setTitle(bi.getBusinessName().contains(APK_SUFFIX) ? bi.getBusinessName()
                .replace(APK_SUFFIX, "")
                : bi.getBusinessName());
        request.setDescription(context.getResources()
                .getString(R.string.appName));
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setVisibleInDownloadsUi(false);
        //        request.setMimeType(MMIMETYPE);
        downloadId = downloadManager.enqueue(request);
        dlLogic.map.put(bi.getPackageName(), downloadId);
        dlLogic.map.put("" + downloadId, downloadId);
        PreferencesUtils.putLong(context, bi.getPackageName(), downloadId);
        /** save download id to preferences **/
        PreferencesUtils.putLong(context, KEY_NAME_DOWNLOAD_ID, downloadId);
    }
    
    //    private void queryDownloadStatus(Context context, String downloadId)
    //    {
    //        DownloadManager.Query query = new DownloadManager.Query();
    //        query.setFilterById(PreferencesUtils.getLong(context, downloadId));
    //        //        Cursor c = downloadManager.query(query);
    //        //        if (c.moveToFirst())
    //        //        {
    //        //            int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
    //        //            switch (status)
    //        //            {
    //        //                case DownloadManager.STATUS_PAUSED:
    //        //                    Log.v("down", "STATUS_PAUSED");
    //        //                case DownloadManager.STATUS_PENDING:
    //        //                    Log.v("down", "STATUS_PENDING");
    //        //                case DownloadManager.STATUS_RUNNING:
    //        //                    //正在下载，不做任何事情  
    //        //                    Log.v("down", "STATUS_RUNNING");
    //        //                    break;
    //        //                case DownloadManager.STATUS_SUCCESSFUL:
    //        //                    //完成  
    //        //                    Log.v("down", "下载完成");
    //        //                    break;
    //        //                case DownloadManager.STATUS_FAILED:
    //        //                    //清除已下载的内容，重新下载  
    //        //                    Log.v("down", "STATUS_FAILED");
    //        //                    downloadManager.remove(prefs.getLong(DL_ID, 0));
    //        //                    prefs.edit().clear().commit();
    //        //                    break;
    //        //            }
    //        //        }
    //    }
}
