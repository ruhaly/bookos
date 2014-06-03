package com.changhong.sdk.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 
 * 圆形imageView
 * [功能详细描述]
 * @author hanliangru
 * @version [智慧社区-终端底座, 2014年3月27日]
 */
public class RoundImageView extends ImageView
{
    
    public RoundImageView(Context context)
    {
        super(context);
    }
    
    public RoundImageView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }
    
    public RoundImageView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }
    
    @Override
    protected void onDraw(Canvas canvas)
    {
        
        Drawable drawable = getDrawable();
        
        if (drawable == null)
        {
            return;
        }
        
        if (getWidth() == 0 || getHeight() == 0)
        {
            return;
        }
        
        if (drawable instanceof BitmapDrawable)
        {
            Bitmap b = ((BitmapDrawable) drawable).getBitmap();
            Bitmap bitmap = b.copy(Bitmap.Config.ARGB_8888, true);
            
            Bitmap roundBitmap = getCroppedBitmap(bitmap, 90);
            canvas.drawBitmap(roundBitmap, 0, 0, null);
        }
        
    }
    
    public static Bitmap getCroppedBitmap(Bitmap bitmap, int pixels)
    {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(),
                Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }
}
