package com.changhong.sdk.baseapi;

import java.lang.Thread.UncaughtExceptionHandler;
import java.sql.Timestamp;

/**
 * 
 * 系统异常处理类
 * @author hanliangru
 * @version [智慧社区-终端底座, 2014年1月21日]
 */
public class CrashHandler implements UncaughtExceptionHandler
{
    
    public static CrashHandler handler = new CrashHandler();
    
    private UncaughtExceptionHandler defaultHandler;
    
    private String version = "v1.0.0";
    
    private String tag = "CHApplication";
    
    private CrashHandler()
    {
        
    }
    
    public void init(String version, String tag)
    {
        this.tag = tag;
        this.version = version;
        defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }
    
    @Override
    public void uncaughtException(Thread thread, Throwable ex)
    {
        StringBuilder builder = new StringBuilder(version);
        builder.append(ex.toString() + "\n");
        builder.append(ex.getLocalizedMessage() + "\n");
        StackTraceElement[] stack = ex.getStackTrace();
        for (StackTraceElement element : stack)
        {
            builder.append(element.toString() + "\n");
        }
        Throwable throwable = ex.getCause();
        while (null != throwable)
        {
            StackTraceElement[] currentStack = throwable.getStackTrace();
            for (StackTraceElement element : currentStack)
            {
                builder.append(element.toString() + "\n");
            }
            throwable = throwable.getCause();
        }
        AppLog.logToFile(new Timestamp(System.currentTimeMillis()).toString(),
                builder,
                tag);
        defaultHandler.uncaughtException(thread, ex);
    }
}
