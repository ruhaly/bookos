package com.changhong.sdk.widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.TextView;

import com.changhong.sdk.R;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author yKF67326
 * @version [版本号, 2012-3-7]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class MCloudProgressDialog extends ProgressDialog
{
    
    private String progressText;
    
    public MCloudProgressDialog(Context context)
    {
        super(context);
    }
    
    public MCloudProgressDialog(Context context, String text)
    {
        super(context);
        this.progressText = text;
    }
    
    public MCloudProgressDialog(Context context, String text, boolean canBack)
    {
        super(context);
        this.progressText = text;
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_progress_dialog);
        
        if (!"".equals(progressText))
        {
            TextView textView = (TextView) findViewById(R.id.product_msg);
            textView.setText(progressText);
        }
    }
    
    public void setDismissListener(OnDismissListener listener)
    {
        setOnDismissListener(listener);
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        return super.onKeyDown(keyCode, event);
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        
        return false;
    }
}
