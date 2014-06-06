package com.changhong.sdk.logic;

import java.util.HashMap;
import java.util.Map;

public class DownloadLogic extends SuperLogic
{
    
    public Map<String, Long> map = new HashMap<String, Long>();
    
    private static DownloadLogic ins;
    
    public static synchronized DownloadLogic getInstance()
    {
        if (null == ins)
        {
            ins = new DownloadLogic();
        }
        return ins;
    }
    
    @Override
    public void clear()
    {
        
    }
    
    @Override
    public void stopRequest()
    {
        
    }
    
}
