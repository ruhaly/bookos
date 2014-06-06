package com.changhong.sdk.baseapi;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.changhong.sdk.R;

public class SuperApplication extends Application
{
    private static final String MY_TAG = "CHApplication";
    
    private static SuperApplication ins;
    
    public static SuperApplication getIns()
    {
        if (null == ins)
        {
            ins = new SuperApplication();
        }
        return ins;
    }
    
    public void exitApp()
    {
        AppLog.out(MY_TAG, "exitApp()", AppLog.LEVEL_INFO);
        
        ActivityStack.getIns().popupAllActivity();
        int sdkVersion = android.os.Build.VERSION.SDK_INT;
        if (sdkVersion <= 7)
        {
            String name = getPackageName();
            ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
            manager.restartPackage(name);
        }
        else
        {
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }
    
    public void onCreate()
    {
        AppLog.out(MY_TAG, "onCreate()", AppLog.LEVEL_INFO);
        CrashHandler.handler.init(getString(R.string.crashversion), MY_TAG);
        super.onCreate();
    }
}