package com.changhong.sdk.logic;

import java.io.IOException;

import android.os.Handler;
import android.util.Log;

import com.changhong.sdk.http.ContinueFTP;
import com.changhong.sdk.http.ContinueFTP.UploadStatus;

public class FTPLogic
{
    public final static int FTP_CONNECT_FAIL = 8001;
    
    public final static int FTP_UPLOAD_FAIL = 8002;
    
    public final static int FTP_UPLOAD_SUCCESS = 8003;
    
    private static FTPLogic ins;
    
    public static synchronized FTPLogic getInstance()
    {
        if (null == ins)
        {
            ins = new FTPLogic();
        }
        return ins;
    }
    
    /**
     * [ftp上传文件]<BR>
     * [功能详细描述]
     * @param hostname 主机名 
     * @param port 端口 
     * @param username 用户名 
     * @param password 密码 
     * @param localFilePath  本地文件名称，绝对路径  如：mnt/sdcard/changhong/file.mav
     * @param remoteDir   远程文件路径，使用/home/directory1/subdirectory/file.ext 按照Linux上的路径指定方式，支持多级目录嵌套，支持递归创建不存在的目录结构 
     * @param Handler   返回给界面的信息
     */
    public void uploadByFtp(final String hostname, final int port,final String username,
            final String password,final String localFilePath,final String remoteDir,final Handler uiHandler)
    {
        
        new Thread()
        {
            @Override
            public void run()
            {
                super.run();
                ContinueFTP myFtp = new ContinueFTP();
                UploadStatus ret = UploadStatus.Upload_New_File_Failed;
                try
                {
                    boolean isConnect = myFtp.connect(hostname, port, username, password);
                    
                    if(!isConnect)
                    {
                        uiHandler.sendEmptyMessage(FTP_CONNECT_FAIL);
                        return;
                    }
                    
                    ret = myFtp.upload(localFilePath, remoteDir);
                    
                    myFtp.disconnect();
                    
                    
                }
                catch (IOException e)
                {
                    Log.e("FTPLogic", "连接FTP出错：" + e.getMessage());
                }
                finally
                {
                    try
                    {
                        myFtp.disconnect();
                    }
                    catch (IOException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    
                    Log.d("FTPLogic", "uploadByFtp | ret = " + ret);
                    if(ret == UploadStatus.Upload_From_Break_Success || ret == UploadStatus.Upload_New_File_Success)
                    {
                        uiHandler.sendEmptyMessage(FTP_UPLOAD_SUCCESS);
                    }
                    else
                    {
                        uiHandler.sendEmptyMessage(FTP_UPLOAD_FAIL);
                    }
                }
            }
        }.start();
    }
    
    
    
}
