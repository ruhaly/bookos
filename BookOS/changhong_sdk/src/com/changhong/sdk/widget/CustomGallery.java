/**
 * CustomGallery.java
 * com.pactera.ch_bedframe.widget
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2013-12-6 		b
 *
 * Copyright (c) 2013, TNT All Rights Reserved.
 */

package com.changhong.sdk.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Gallery;

/**
 * ClassName:CustomGallery Function: TODO ADD FUNCTION
 * 解决gallery滑动过快的问题
 * @author hanliangru
 * @version
 * @since Ver 1.1
 * @Date 2013-12-6 下午3:32:13
 */
public class CustomGallery extends Gallery
{
    public CustomGallery(Context context, AttributeSet attrs)
    {
        
        super(context, attrs);
        
    }
    
    private boolean isScrollingLeft(MotionEvent e1, MotionEvent e2)
    {
        
        return e2.getX() > e1.getX();
        
    }
    
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
    
    float velocityY)
    {
        
        int keyCode;
        
        if (isScrollingLeft(e1, e2))
        {
            
            keyCode = KeyEvent.KEYCODE_DPAD_LEFT;
            
        }
        else
        {
            
            keyCode = KeyEvent.KEYCODE_DPAD_RIGHT;
            
        }
        
        onKeyDown(keyCode, null);
        
        return true;
        
    }
}
