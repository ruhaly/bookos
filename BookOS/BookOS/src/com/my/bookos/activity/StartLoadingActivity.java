package com.my.bookos.activity;

import android.content.Intent;
import android.os.Bundle;

import com.changhong.sdk.activity.SuperActivity;
import com.my.bookos.R;

public class StartLoadingActivity extends SuperActivity
{
    
    @Override
    public void initData()
    {
        
    }
    
    @Override
    public void initLayout(Bundle paramBundle)
    {
        setContentView(R.layout.startloading_layout);
        mHandler.postDelayed(new Runnable()
        {
            
            @Override
            public void run()
            {
                finish();
                startActivity(new Intent(getBaseContext(), LoginActivity.class));
            }
        },
                1500);
    }
    
    @Override
    public void clearData()
    {
        
    }
    
}
