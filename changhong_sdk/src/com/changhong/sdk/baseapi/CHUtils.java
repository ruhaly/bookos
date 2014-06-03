package com.changhong.sdk.baseapi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.changhong.sdk.entity.BusinessInfo;

/**
 * 
 * 工具类
 * @author hanliangru
 * @version [智慧社区-终端底座, 2014年1月20日]
 */
public final class CHUtils
{
    /**
     * 图片缓存
     */
    private static final Map<String, SoftReference<Bitmap>> imageCache = new HashMap<String, SoftReference<Bitmap>>();
    
    /**
     * drawableCache缓存
     */
    private static final Map<String, SoftReference<Drawable>> drawableCache = new HashMap<String, SoftReference<Drawable>>();
    
    /**
     * 
     * 根据资源id获取对应的图片
     * @param resId
     * @return
     */
    public static Bitmap getImage(int resId)
    {
        String key = String.valueOf(resId);
        SoftReference<Bitmap> bitmap = imageCache.get(key);
        if (null == bitmap || null == bitmap.get())
        {
            imageCache.remove(key);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            Bitmap b = BitmapFactory.decodeResource(SuperApplication.getIns()
                    .getResources(), resId, options);
            bitmap = new SoftReference<Bitmap>(b);
            b = null;
            imageCache.put(key, bitmap);
            AppLog.out("getImage", "getImage from local", AppLog.LEVEL_INFO);
        }
        return bitmap.get();
    }
    
    /**
     * 
     * 获取资源字符串 输入参数
     * @param resId
     * @return
     */
    public static String getString(int resId)
    {
        return SuperApplication.getIns().getString(resId);
    }
    
    /**
     * 
     * 根据资源id获取对应的图片drawable
     * @param resId
     * @return
     */
    public static Drawable getDrawable(int resId)
    {
        String key = String.valueOf(resId);
        SoftReference<Drawable> drawable = drawableCache.get(key);
        Bitmap bitmap = null;
        if (null != drawable)
        {
            BitmapDrawable d = (BitmapDrawable) drawable.get();
            if (null != d)
            {
                bitmap = d.getBitmap();
            }
            
        }
        if (null == bitmap || bitmap.isRecycled())
        {
            if (null != drawable)
            {
                drawable.clear();
            }
            drawableCache.remove(key);
            Drawable d = new BitmapDrawable(SuperApplication.getIns()
                    .getResources(), getImage(resId));
            drawable = new SoftReference<Drawable>(d);
            d = null;
            drawableCache.put(key, drawable);
        }
        return drawable.get();
    }
    
    /**
     * 
     *  获取指定资源的序号
     * @param tagName
     * @param array
     * @return
     */
    public static int getIndex(String tagName, String[] array)
    {
        int length = array.length;
        for (int i = 0; i < length; i++)
        {
            if (tagName.equals(array[i]))
            {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * 
     * 判断应用程序是否是最上层的应用 输入参数
     * @param context
     * @return
     */
    public static boolean isTopApp(Context context)
    {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> TaskList = am.getRunningTasks(2);
        if (TaskList == null || TaskList.isEmpty())
        {
            return false;
        }
        RunningTaskInfo rti = TaskList.get(0);
        String tmp = rti.topActivity.getPackageName();
        return tmp.equals(context.getPackageName());
    }
    
    /**
     * 
     * 获取本机IP
     * @return
     */
    public static String getLocalIpAddress()
    {
        try
        {
            Enumeration<NetworkInterface> networkInfo = NetworkInterface.getNetworkInterfaces();
            for (Enumeration<NetworkInterface> en = networkInfo; en.hasMoreElements();)
            {
                NetworkInterface intf = en.nextElement();
                Enumeration<InetAddress> intfAddress = intf.getInetAddresses();
                for (Enumeration<InetAddress> enumIpAddr = intfAddress; enumIpAddr.hasMoreElements();)
                {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress())
                    {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        }
        catch (SocketException ex)
        {
            
        }
        return null;
    }
    
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue)
    {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    
    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue)
    {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
    
    /**
     * 
     * 找出所有包名为pkg开头的应用
     * @param context
     * @param pkg
     * @return
     */
    public static List<BusinessInfo> getApplicationInfo(Context context,
            String pkg)
    {
        List<BusinessInfo> pList = new ArrayList<BusinessInfo>();
        PackageManager pkgMag = context.getPackageManager();
        
        List<PackageInfo> appList = getAllApps(context);
        for (int i = 0; i < appList.size(); i++)
        {
            PackageInfo pinfo = appList.get(i);
            String packageName = pinfo.applicationInfo.packageName;
            if ((packageName.startsWith(pkg))
                    && !packageName.equals(Constant.CURRENT_PACKAGE))
            {
                BusinessInfo p = new BusinessInfo();
                //                Intent intent = pkgMag.getLaunchIntentForPackage(packageName);
                //                if (null != intent)
                //                {
                //                    p.setIntent(intent);
                p.setPackageName(packageName);
                p.setBusinessName(pkgMag.getApplicationLabel(pinfo.applicationInfo)
                        .toString());
                p.setShorcut(pkgMag.getApplicationIcon(pinfo.applicationInfo));
                pList.add(p);
                
                //                }
            }
        }
        
        return pList;
        
    }
    
    /**
     * 
     * 通过包名，返回包信息
     * [功能详细描述]
     * @param context
     * @param pkg
     */
    public static PackageInfo getApkInfo(Context context, String pkg)
    {
        PackageManager pm = context.getPackageManager();
        PackageInfo pkgInfo = null;
        try
        {
            pkgInfo = pm.getPackageInfo(pkg, PackageManager.GET_PERMISSIONS);
        }
        catch (NameNotFoundException e)
        {
            e.printStackTrace();
        }
        return pkgInfo;
    }
    
    /**
     * 
     * 获取apk 图标
     * [功能详细描述]
     * @param context
     * @param pkg
     * @return
     */
    public static Drawable getApplicationIcon(Context context, String pkg)
    {
        PackageManager pkgMag = context.getPackageManager();
        
        List<PackageInfo> appList = getAllApps(context);
        for (int i = 0; i < appList.size(); i++)
        {
            PackageInfo pinfo = appList.get(i);
            String packageName = pinfo.applicationInfo.packageName;
            if (packageName.equals(pkg))
            {
                return pkgMag.getApplicationIcon(pinfo.applicationInfo);
            }
        }
        
        return null;
        
    }
    
    /**
     * 
     * 判断某个app是否存在
     * @param context
     * @param pkg 包名
     * @return
     */
    public static boolean checkIsExit(Context context, String pkg)
    {
        if (StringUtils.isEmpty(pkg))
        {
            return false;
        }
        boolean b = false;
        List<PackageInfo> appList = getAllApps(context);
        for (int i = 0; i < appList.size(); i++)
        {
            PackageInfo pinfo = appList.get(i);
            String packageName = pinfo.applicationInfo.packageName;
            if (packageName.contains(pkg)
                    && !packageName.equals("com.changhong.foundation"))
            {
                b = true;
            }
        }
        
        return b;
    }
    
    /**
     * 
     * 获取手机内所有应用
     * @param context
     * @return
     */
    public static List<PackageInfo> getAllApps(Context context)
    {
        List<PackageInfo> apps = new ArrayList<PackageInfo>();
        PackageManager pManager = context.getPackageManager();
        List<PackageInfo> paklist = pManager.getInstalledPackages(0);
        for (int i = 0; i < paklist.size(); i++)
        {
            PackageInfo pak = (PackageInfo) paklist.get(i);
            // 判断是否为非系统预装的应用程序
            if ((pak.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) <= 0)
            {
                // customs applications
                apps.add(pak);
            }
        }
        return apps;
    }
    
    /**
     * 
     * TextView添加下划线
     * @param tv
     */
    public static void addUnderlineTextView(TextView tv)
    {
        tv.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
    }
    
    public static void hiddenSoft(EditText view, Context context)
    {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        boolean isOpen = imm.isActive();// isOpen若返回true，则表示输入法打开
        if (isOpen)
        {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    
    /**
     * 
     * 判断是否是3G 或者wifi状态
     * @param context
     * @return
     */
    public static boolean checkNetworkConnection(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        State wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .getState();
        if (wifi == State.CONNECTED)
        {
            return true;
        }
        State mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                .getState();
        if (mobile == State.CONNECTED)
        {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            int type = tm.getNetworkType();
            if (type != TelephonyManager.NETWORK_TYPE_CDMA
                    && type != TelephonyManager.NETWORK_TYPE_EDGE
                    && type != TelephonyManager.NETWORK_TYPE_GPRS)
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 
     * 屏幕宽
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context)
    {
        DisplayMetrics dm = new DisplayMetrics();
        dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }
    
    /**
     * 
     * 屏幕高
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context)
    {
        DisplayMetrics dm = new DisplayMetrics();
        dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }
    
    public Bitmap getImage(String path, Context context)
    {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        // 这个isjustdecodebounds很重要
        opt.inJustDecodeBounds = true;//
        Bitmap bmp = BitmapFactory.decodeFile(path, opt);
        
        // 获取到这个图片的原始宽度和高度
        int picWidth = opt.outWidth;
        int picHeight = opt.outHeight;
        
        int screenWidth = CHUtils.getScreenWidth(context);
        int screenHeight = CHUtils.getScreenHeight(context);
        
        // isSampleSize是表示对图片的缩放程度，比如值为2图片的宽度和高度都变为以前的1/2
        opt.inSampleSize = 1;
        // // 根据屏的大小和图片大小计算出缩放比例
        if (picWidth > picHeight)
        {
            if (picWidth > screenWidth)
                opt.inSampleSize = picWidth / screenWidth;
        }
        else
        {
            if (picHeight > screenHeight)
                
                opt.inSampleSize = picHeight / screenHeight;
        }
        
        // 这次再真正地生成一个有像素的，经过缩放了的bitmap
        opt.inJustDecodeBounds = false;
        bmp = BitmapFactory.decodeFile(path, opt);
        return bmp;
    }
    
    /**
     * 
     * 将assets目录下的文件读取到手机内
     * @param context
     * @param fileName assets项目的文件名
     * @param path 手机存放路径
     * @param apkName 存放的文件名
     * @return
     */
    public static boolean copyApkFromAssets(Context context, String fileName,
            String path, String apkName)
    {
        boolean copyIsFinish = false;
        try
        {
            InputStream is = context.getAssets().open(fileName);
            File f = new File(path);
            if (!f.exists())
            {
                f.mkdir();
            }
            File file = new File(path + apkName);
            // 创建apk文件
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            byte[] temp = new byte[1024];
            int i = 0;
            while ((i = is.read(temp)) > 0)
            {
                fos.write(temp, 0, i);
            }
            fos.close();
            is.close();
            copyIsFinish = true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return copyIsFinish;
    }
    
    /**
     * 
     * 获取dirName目录路径，优先sd卡
     * @param context
     * @param dirName
     * @return
     */
    @SuppressLint("NewApi")
    public static String getDiskCacheDir(Context context, String dirName)
    {
        final String cachePath = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) ? context.getExternalCacheDir()
                .getPath()
                : context.getCacheDir().getPath();
        
        return StringUtils.isEmpty(dirName) ? cachePath : cachePath
                + File.separator + dirName;
    }
    
    //    public String getClassJson(Object o)
    //    {
    //        String str = "";
    //        try
    //        {
    //            StringBuffer sb = new StringBuffer("{");
    //            Field[] field = o.getClass().getDeclaredFields(); //获取实体类的所有属性，返回Field数组  
    //            for (int j = 0; j < field.length; j++)
    //            { //遍历所有属性
    //                String name = field[j].getName(); //获取属性的名字
    //                sb.append(name).append(":");
    //                System.out.println("attribute name:" + name);
    //                name = name.substring(0, 1).toUpperCase() + name.substring(1); //将属性的首字符大写，方便构造get，set方法
    //                //                String type = field[j].getGenericType().toString(); //获取属性的类型
    //                Method m = o.getClass().getMethod("get" + name);
    //                
    //                Object value = m.invoke(o);
    //                if (null != value)
    //                {
    //                    if (j == field.length - 1)
    //                    {
    //                        sb.append(String.valueOf(value)).append("}");
    //                    }
    //                    else
    //                    {
    //                        sb.append(String.valueOf(value)).append(",");
    //                    }
    //                }
    //                else
    //                {
    //                    if (j == field.length - 1)
    //                    {
    //                        sb.append("}");
    //                    }
    //                    
    //                }
    //                
    //            }
    //            if (null != sb)
    //            {
    //                Gson gson = new Gson();
    //                str = gson.toJson(sb.toString());
    //            }
    //            
    //        }
    //        catch (Exception e)
    //        {
    //            AppLog.out("CHUtils:getClassJson()",
    //                    e.getMessage(),
    //                    AppLog.LEVEL_ERROR);
    //        }
    //        return str;
    //    }
    
    public static String convertByteToMB(String str)
    {
        if (StringUtils.isEmpty(str))
        {
            return 0 + "MB";
        }
        DecimalFormat fnum = new DecimalFormat("##0.00");
        String dd = fnum.format(Float.valueOf(str) / (1024 * 1024));
        return dd + "MB";
    }
    
    /**
     * 
     * 清除缓存
     * [功能详细描述]
     * @param context
     */
    public static void clearCache(Context context)
    {
        
    }
    
    /**
     * 
     * 手机号码校验
     * [功能详细描述]
     * @param str
     * @return
     */
    public static boolean isOrNotTelNum(String str)
    {
        /* 
                            移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188 
                            联通：130、131、132、152、155、156、185、186 
                            电信：133、153、180、189、（1349卫通） 
                            总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9 
        */
        if(StringUtils.isEmpty(str))
        {
            return false;
        }
        
        String telRegex = "[1][358]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。  
        return str.matches(telRegex);
    }
}