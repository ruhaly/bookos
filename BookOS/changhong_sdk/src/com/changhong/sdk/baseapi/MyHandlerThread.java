package com.changhong.sdk.baseapi;

import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;

public class MyHandlerThread extends HandlerThread implements Callback
{
    
    public MyHandlerThread(String name)
    {
        super(name);
    }
    
    @Override
    public boolean handleMessage(Message arg0)
    {
        
        return true;
    }
    
}
