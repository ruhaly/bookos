/**
 * CustomViewPager.java
 * com.pactera.ch_bedframe.widget
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2013-12-4 		b
 *
 * Copyright (c) 2013, TNT All Rights Reserved.
 */

package com.changhong.sdk.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * ClassName:CustomViewPager Function: TODO ADD FUNCTION
 * 
 * @author hanliangru
 * @version
 * @since Ver 1.1
 * @Date 2013-12-4 上午10:56:54
 */
public class CustomViewPager extends ViewPager
{
    private boolean isScrollable;
    
    public CustomViewPager(Context context)
    {
        super(context);
    }
    
    public CustomViewPager(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent ev)
    {
        if (isScrollable == false)
        {
            return false;
        }
        else
        {
            return super.onTouchEvent(ev);
        }
        
    }
    
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev)
    {
        if (isScrollable == false)
        {
            return false;
        }
        else
        {
            return super.onInterceptTouchEvent(ev);
        }
        
    }
    
    public boolean isScrollable()
    {
        return isScrollable;
    }
    
    public void setScrollable(boolean isScrollable)
    {
        this.isScrollable = isScrollable;
    }
    
}
