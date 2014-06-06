/*
 * 文件名: DateUtil.java
 * 版    权：Copyright Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描    述: 根据时间格式生成时间字符串
 * 创建人: 周雪松
 * 创建时间:2012-2-23
 * 
 * 修改人：
 * 修改时间:
 * 修改内容：[修改内容]
 */
package com.changhong.sdk.baseapi;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import android.content.Context;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;

import com.changhong.sdk.baseapi.StringUtils;

/**
 * [根据时间格式，获取时间字符串]<BR>
 * [功能详细描述]
 * @author 王磊
 * @version [ChangHong SmartHome V100R001C03, 2014-4-22] 
 */
public class DateUtil
{
    /**
     * TAG:用于打印日志的
     */
    public static final String TAG = "DateUtil";
    
    /**
     * 日期formatter
     */
    public static final SimpleDateFormat FRIEND_MANAGER_FORMATTER = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
    
    /**
     * 通话时间
     */
    public static final SimpleDateFormat VOIP_TALKING_TIME = new SimpleDateFormat(
            "mm:ss");
    
    /**
     * 时间戳格式
     */
    public static final SimpleDateFormat TIMESTAMP_DF = new SimpleDateFormat(
            "yyyyMMddHHmmss");
    
    /**
     * UTC-服务器下发时间格式
     */
    public static final SimpleDateFormat UTC_DF = new SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss");
    
    /**
     * UTC-同步截至时间
     */
    public static final SimpleDateFormat UTC_SYNC = new SimpleDateFormat(
            "yyyy'-'MM'-'dd'T00:00:00Z'");
    
    /**
     * 第三方离线消息时间格式（20121120T06:21:15）
     */
    public static final SimpleDateFormat THIRD_IM_OUTLINE_DF = new SimpleDateFormat(
            "yyyyMMdd'T'HH:mm:ss");
    
    /**
     * 时间戳格式
     */
    public static final SimpleDateFormat BACKUP_DF = new SimpleDateFormat(
            "dd/MM/yy HH:mm");
    
    /**
     * 时间戳格式
     */
    public static final SimpleDateFormat STANDARD_DF = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
    
    /**
     * 时间戳格式
     */
    public static final SimpleDateFormat STANDARD_NO_S_DF = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm");
    
    /**
     * 时间戳格式
     */
    public static final SimpleDateFormat STANDARD_NO_TIME_DF = new SimpleDateFormat(
            "yyyy-MM-dd ");
    
    /**
     * 时间戳格式
     */
    public static final SimpleDateFormat M_D_DF = new SimpleDateFormat(
            "MM-dd HH:mm");
    
    /**
     * 时间戳格式
     */
    public static final SimpleDateFormat M_D_ONLY_DF = new SimpleDateFormat(
            "MM-dd");
    
    /**
     * 时间戳格式
     */
    public static final SimpleDateFormat Y_DF = new SimpleDateFormat("yyyy");
    
    /**
     * 时间戳格式
     */
    public static final SimpleDateFormat M_DF = new SimpleDateFormat("MM");
    
    /**
     * 时间戳格式
     */
    public static final SimpleDateFormat D_DF = new SimpleDateFormat("dd");
    
    /**
     * 时间戳格式
     */
    public static final SimpleDateFormat TIME_H_M_DF = new SimpleDateFormat(
            "HH:mm");
    
    /**
     * 时间戳格式
     */
    public static final SimpleDateFormat H_M_DF = new SimpleDateFormat(
            "yyyy.MM.dd HH:mm");
    
    /**
     * 
     * [一句话功能简述]<BR>
     * 根据默认的格式获得当前时间字符串
     * 
     * @return 当前时间
     */
    public static String getCurrentDateString()
    {
        return TIMESTAMP_DF.format(new Date());
    }
    
    /**
     * 
     * 转换日期格式<BR>
     * 
     * @param time
     *            TIMESTAMP_DF格式的日期
     * @return BACKUP_DF格式的日期
     */
    public static String getBackupDateString(String time)
    {
        try
        {
            return BACKUP_DF.format(TIMESTAMP_DF.parse(time));
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 转化短信备份日期格式 <BR>
     * 
     * @param time
     *            短息long类型日期
     * @return 短信备份日期格式
     */
    public static String getSmsBackupDateString(String time)
    {
        long dateTime = Long.parseLong(time);
        Date date = new Date(dateTime);
        return TIMESTAMP_DF.format(date);
    }
    
    /**
     * 将短信备份的日期转化成数据库需要插入的日期 <BR>
     * 
     * @param time
     * @return
     */
    public static long getSmsBackupDateLong(String time)
    {
        Date date;
        try
        {
            date = TIMESTAMP_DF.parse(time);
            if (date != null)
            {
                return date.getTime();
            }
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return 0L;
    }
    
    /**
     * 
     * 转换日期格式<BR>
     * 
     * @param time
     *            TIMESTAMP_DF格式的日期
     * @return STANDARD_DF格式的日期
     */
    public static String getStandardDateString(String time)
    {
        try
        {
            return STANDARD_DF.format(TIMESTAMP_DF.parse(time));
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 转化格式化的日期字符串<BR>
     * 
     * @param date
     *            日期
     * @return 格式化的日期字符串
     */
    public static String getDateString(Date date)
    {
        if (null == date)
        {
            return getCurrentDateString();
        }
        return STANDARD_NO_TIME_DF.format(date);
    }
    
    /**
     * 
     * 设置通话时长格式 xx : xx: xx
     * 
     * @param diffTime
     *            long
     * @return 通话时长格式
     */
    public static String getDiffTime2(long diffTime)
    {
        // 小时常数
        long hourMarker = 60 * 60;
        
        // 分钟常数
        long minuteMarker = 60;
        
        // 秒常数
        long secondMarker = 1;
        
        // 小时
        long hour = diffTime / hourMarker;
        // 分钟
        long minute = (diffTime - hour * hourMarker) / minuteMarker;
        // 秒
        long second = (diffTime - hour * hourMarker - minute * minuteMarker)
                / secondMarker;
        DecimalFormat decfmt = new DecimalFormat();
        if (hour == 0)
        {
            return decfmt.format(minute) + ":" + decfmt.format(second);
        }
        
        return decfmt.format(hour) + ":" + decfmt.format(minute) + ":"
                + decfmt.format(second);
        
    }
    
    /**
     * SNS获取制定格式时间 当天取时间(时分)HH:mm 昨天返回Yesterday 昨天之前的 返回yyyy-MM-dd
     * 
     * @param time
     *            当天取时间(时分)HH:mm
     * @param yesterday
     *            昨天返回Yesterday
     * @return 昨天返回Yesterday,昨天之前的 返回yyyy-MM-dd
     */
    public static String getComparedTime(String yesterday, String time)
    {
        
        if (StringUtils.isNullOrEmpty(time))
        {
            return null;
        }
        Date date;
        try
        {
            SimpleDateFormat format = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
            format.setTimeZone(TimeZone.getTimeZone("GMT+0"));
            date = format.parse(time);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
            return null;
        }
        
        // 获取当前时间
        Date nowDate = new Date();
        String formatTime = null;
        // 再把时间的年月日取出来
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");
        String lastTimeDate = df.format(date);
        
        // 先判断是否是今天
        if (df.format(nowDate).equals(lastTimeDate))
        {
            df = new SimpleDateFormat("HH:mm", Locale.getDefault());
            formatTime = df.format(date);
        }
        else if (date.getTime() >= getYestadayStart()
                && date.getTime() <= getYestadayEnd())
        {
            formatTime = yesterday;
        }
        else
        {
            formatTime = lastTimeDate;
        }
        return formatTime;
    }
    
    /**
     * 获取昨天开始的毫秒数2011-11-20 00:00:00
     * 
     * @return 获取昨天开始的毫秒数
     */
    private static long getYestadayStart()
    {
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DAY_OF_YEAR, -1);
        Date tadayDate = ca.getTime();
        return setStartDate(tadayDate);
    }
    
    /**
     * 获取昨天结束的毫秒数2011-11-20 23:59:59
     * 
     * @return 获取昨天结束的毫秒数
     */
    private static long getYestadayEnd()
    {
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DAY_OF_YEAR, -1);
        Date tadayDate = ca.getTime();
        return setEndDate(tadayDate);
    }
    
    /**
     * 设置日期的时分秒为00:00:00格式
     * 
     * @return 设置日期的时分秒
     */
    private static long setStartDate(Date date)
    {
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        return date.getTime();
    }
    
    /**
     * 设置日期的时分秒为23:59:59格式
     * 
     * @return 设置日期的时分秒
     */
    private static long setEndDate(Date date)
    {
        date.setHours(23);
        date.setMinutes(59);
        date.setSeconds(59);
        return date.getTime();
    }
    
    /**
     * 通话 话时长 设置通话时长格式 xx:xx
     * 
     * @param diffTime
     *            long
     * @return 通话时长格式
     */
    public static String getDiffTime(long diffTime)
    {
        // 分钟常数
        long minuteMarker = 60;
        
        // 秒常数
        long secondMarker = 1;
        // 分钟
        long minute = diffTime / minuteMarker;
        // 秒
        long second = (diffTime - minute * minuteMarker) / secondMarker;
        
        String strMin = String.valueOf(minute);
        String strSec = String.valueOf(second);
        if (minute < 10)
        {
            strMin = "0" + strMin;
        }
        if (second < 10)
        {
            strSec = "0" + strSec;
        }
        return strMin + ":" + strSec;
    }
    
    /**
     * 
     * [一句话功能简述]<BR>
     * 根据默认的格式获得生日时间字符串
     * 
     * @param date
     *            Date
     * @return 生日格式时间
     */
    public static String getBirthdayDateString(Date date)
    {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }
    
    /**
     * 找朋友小助手时间转换<BR>
     * 
     * @param date
     *            date
     * @return 日期的时间串
     */
    public static String getFormatTimeStringForFriendManager(Date date)
    {
        return null == date ? FRIEND_MANAGER_FORMATTER.format(new Date())
                : FRIEND_MANAGER_FORMATTER.format(date);
    }
    
    /**
     * 找朋友小助手时间串转Date<BR>
     * 
     * @param friendManagerTimeString
     *            找朋友小助手时间串
     * @return 日期对象
     * 
     * @throws ParseException
     *             解析发生异常
     */
    public static Date getDateFromFriendManageTimeString(
            String friendManagerTimeString) throws ParseException
    {
        return FRIEND_MANAGER_FORMATTER.parse(friendManagerTimeString);
    }
    
    /**
     * 
     * [一句话功能简述]<BR>
     * [功能详细描述]
     * 
     * @param context
     *            Context
     * @param lastTime
     *            String
     * @return String
     */
    public static String getFormatTimeString(Context context, String lastTime)
    {
        if (lastTime == null)
        {
            return null;
        }
        Date lastDate = null;
        try
        {
            lastDate = TIMESTAMP_DF.parse(lastTime);
        }
        catch (ParseException e)
        {
            Log.e(TAG, "getFormatTimeString ParseException: " + e.toString());
        }
        if (lastDate == null)
        {
            return null;
        }
        return getFormatTimeByDate(context, lastDate);
    }
    
    /**
     * 
     * 通话记录详情年-月-日
     * 
     * @param context
     *            Context
     * @param lastDate
     *            Date
     * @return 通话记录详情年-月-日
     */
    public static String getCommunicationLogDetailTimeByDate(Context context,
            Date lastDate)
    {
        // 取出具体时间
        SimpleDateFormat df = new SimpleDateFormat("HH:mm", Locale.getDefault());
        
        String time = df.format(lastDate);
        if (time.charAt(0) == '0')
        {
            time = time.substring(1);
        }
        // 再把时间的年月日取出来
        df = new SimpleDateFormat("yyyy-MM-dd");
        String lastTimeDate = df.format(lastDate);
        return lastTimeDate;
    }
    
    /**
     * 
     * 聊天界面时间格式转化<BR>
     * 气泡中的时间显示格式 HH：mm
     * 
     * @param context
     *            Context
     * @param lastDate
     *            Date
     * @return 时间
     */
    public static String getFormatTimeByDate(Context context, Date lastDate)
    {
        String formatTime = null;
        
        SimpleDateFormat hourDf = new SimpleDateFormat("HH:mm",
                Locale.getDefault());
        formatTime = hourDf.format(lastDate);
        return formatTime;
    }
    
    /**
     * 
     * 获取指定格式的时间字符串<BR>
     * 当天按： 0:00 24小时制 昨天按：昨天 昨天以前按：年-月-日 例：2012-5-29
     * 
     * @param yesterday
     *            昨天
     * @param date
     *            需要转换的date
     * @return 转化好的字符串
     */
    public static String getComparedTimeByDate(String yesterday, Date date)
    {
        if (date == null)
        {
            return "";
        }
        // 将Date转化为Calendar
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        // Calendar 转为 Date:
        Calendar c = Calendar.getInstance();
        Date nowDate = c.getTime();
        String formatTime = null;
        // 再把时间的年月日取出来
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String lastTimeDate = df.format(date);
        
        // 先判断是否是今天
        if (df.format(nowDate).equals(lastTimeDate))
        {
            df = new SimpleDateFormat("HH:mm", Locale.getDefault());
            formatTime = df.format(date);
        }
        else
        {
            // 昨天
            c.set(Calendar.DATE, c.get(Calendar.DATE) - 1);
            if (df.format(c.getTime()).equals(lastTimeDate))
            {
                formatTime = yesterday;
            }
            else
            {
                formatTime = lastTimeDate;
            }
        }
        
        return formatTime;
    }
    
    /**
     * 
     * 获取指定格式的时间字符串<BR>
     * 当天按： 0:00 24小时制 昨天按：昨天 昨天以前按：年-月-日 例：2012-5-29
     * 
     * @param yesterday
     *            昨天
     * @param time
     *            需要转换的time
     * @return 转化好的字符串
     */
    public static String getComparedTimeByTime(String time, String yesterday)
    {
        Date lastDate = changeUTCtoLocalTime(time);
        if (lastDate == null)
        {
            return null;
        }
        return getComparedTimeByDate(yesterday, lastDate);
    }
    
    /**
     * voip通话历史界面时间显示：（当天-- 时：分 ；其它天-- 日期：时：分）
     * 
     * @param context
     *            context
     * @param callTime
     *            String
     * @return voip通话历史界面时间显示
     */
    public static String getVoipCallRecordTime(Context context, String callTime)
    {
        if (callTime == null)
        {
            return null;
        }
        Date lastDate = null;
        try
        {
            lastDate = TIMESTAMP_DF.parse(callTime);
        }
        catch (ParseException e)
        {
            Log.e(TAG, "getVoipCallRecordTime ParseException: " + e.toString());
        }
        if (null == lastDate)
        {
            return null;
        }
        // 当前年份
        Calendar c = Calendar.getInstance();
        // Calendar 转为 Date:
        Date nowDate = c.getTime();
        String formatTime = null;
        // 取出 时间的年月日
        SimpleDateFormat df = new SimpleDateFormat("dd/MM", Locale.getDefault());
        // 取出具体时间
        SimpleDateFormat df2 = new SimpleDateFormat("HH:mm",
                Locale.getDefault());
        String time = df2.format(lastDate);
        // if (time.charAt(0) == '0')
        // {
        // time = time.substring(1);
        // }
        String lastTimeDate = df.format(lastDate);
        // 判断是否是今天
        if (df.format(nowDate).equals(lastTimeDate))
        {
            formatTime = time;
        }
        else
        {
            // 处理 日期格式
            if (lastTimeDate.charAt(0) == '0')
            {
                // 将开头的0去掉 d/MM/yyyy
                lastTimeDate = lastTimeDate.substring(1);
                if (lastTimeDate.charAt(2) == '0')
                {
                    // 将 d/MM/yyyy 第3位为0时 去掉0
                    lastTimeDate = lastTimeDate.substring(0, 2)
                            + lastTimeDate.substring(3, lastTimeDate.length());
                }
            }
            else
            {
                // 将dd/MM/yyyy 第1位不为0且第4位为0时 去掉0
                if (lastTimeDate.charAt(3) == '0')
                {
                    lastTimeDate = lastTimeDate.substring(0, 3)
                            + lastTimeDate.substring(4, lastTimeDate.length());
                }
            }
            // 取出日期：时：分
            formatTime = lastTimeDate + "  " + time;
        }
        return formatTime;
    }
    
    /**
     * 
     * 获取指定格式的时间字符串<BR>
     * 当天按： 0:00 24小时制 昨天或以前按：日/月/年 例：29/05/2012
     * 
     * @param context
     *            context
     * @param lastTime
     *            需要转换的time
     * @return 转化好的字符串
     */
    public static String getSysmessageTime(Context context, String lastTime)
    {
        Date lastDate = null;
        try
        {
            lastDate = TIMESTAMP_DF.parse(lastTime);
        }
        catch (ParseException e)
        {
            Log.e(TAG, "getSysmessageTime ParseException:" + e.toString());
        }
        if (null == lastDate)
        {
            return "";
        }
        // 将Date转化为Calendar
        Calendar cal = new GregorianCalendar();
        cal.setTime(lastDate);
        // Calendar 转为 Date:
        Calendar c = Calendar.getInstance();
        Date nowDate = c.getTime();
        String formatTime = null;
        // 再把时间的年月日取出来
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String lastTimeDate = df.format(lastDate);
        
        // 先判断是否是今天
        if (df.format(nowDate).equals(lastTimeDate))
        {
            df = new SimpleDateFormat("HH:mm", Locale.getDefault());
            formatTime = df.format(lastDate);
        }
        else
        {
            formatTime = lastTimeDate;
        }
        return formatTime;
    }
    
    /**
     * 根据UTC时间获得展示的时间String
     * 
     * @param timeString
     *            标准UTC时间String
     * @return "yyyy'-'MM'-'dd  kk':'mm':'ss'"格式的时间String
     */
    public static String getTimeShowFromUtc(String timeString)
    {
        String timeShowString = "";
        if (TextUtils.isEmpty(timeString))
        {
            return timeShowString;
        }
        timeShowString = DateFormat.format("dd'/'MM'/'yyyy kk':'mm'",
                DateUtil.changeUTCtoLocalTime(timeString)).toString();
        return timeShowString;
    }
    
    /**
     * [UTC时间，转换为本地时间]<BR>
     * [功能详细描述]
     * @param dateStr  String 解析前的字符串时间对象，为0时区的时间
     * @return Date 经过解析后的时间对象
     */
    public static Date changeUTCtoLocalTime(String dateStr)
    {
        if (StringUtils.isNullOrEmpty(dateStr))
        {
            return null;
        }
        SimpleDateFormat imDelayFormatter = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss");
        TimeZone timeZone = TimeZone.getTimeZone("GMT+0");
        imDelayFormatter.setTimeZone(timeZone);
        
        Date date = null;
        try
        {
            date = imDelayFormatter.parse(dateStr);
        }
        catch (ParseException e)
        {
            Log.e(TAG, "[changeUTCtoLocalTime]ParseException", e);
        }
        return date;
    }
    
    /**
     * 将UTC时间转换为long型时间
     * 
     * @param strDate
     *            UTC时间
     * @return long型时间
     */
    public static long parseUTCToLong(String strDate)
    {
        if (StringUtils.isNullOrEmpty(strDate))
        {
            Log.w(TAG, "[parseUTC]strDate is null.");
            return 0;
        }
        long time = 0;
        try
        {
            Log.d(TAG, "strDate:[" + strDate + "]");
            Date date = UTC_DF.parse(strDate);
            time = date.getTime();
            Log.d(TAG, "time:[" + time + "]");
        }
        catch (ParseException e)
        {
            Log.e(TAG, "[parseUTCToLong]ParseException", e);
        }
        return time;
        
    }
    
    /**
     * 将UTC时间转换为Date类型时间
     * 
     * @param strDate
     *            UTC时间
     * @return Date类型时间
     */
    public static Date parseUTCToDate(String strDate)
    {
        if (StringUtils.isNullOrEmpty(strDate))
        {
            Log.w(TAG, "[parseUTC]strDate is null.");
            return null;
        }
        Log.d(TAG, "strDate[" + strDate + "]");
        try
        {
            return UTC_DF.parse(strDate);
        }
        catch (ParseException e)
        {
            Log.e(TAG, "[parseUTC]ParseException", e);
        }
        return null;
    }
    
    /**
     * 将SyncUTC时间转换为Date类型时间
     * 
     * @param strDate
     *            SyncUTC时间
     * @return Date类型时间
     */
    public static Date parseSyncUTCToDate(String strDate)
    {
        if (StringUtils.isNullOrEmpty(strDate))
        {
            Log.w(TAG, "[parseUTC]strDate is null.");
            return null;
        }
        Log.d(TAG, "strDate[" + strDate + "]");
        try
        {
            return UTC_SYNC.parse(strDate);
        }
        catch (ParseException e)
        {
            Log.e(TAG, "[parseUTC]ParseException", e);
        }
        return null;
    }
    
    
    /**
     * [email下发的时间转换成UTC时间]<BR>
     * [功能详细描述]
     * @param dateStr String email下发的字符串
     * @return String 经过解析后的utc时间
     * @throws ParseException 解析错误
     */
    public static String changeEmailTimeToUtc(String dateStr)
            throws ParseException
    {
        if (null == dateStr
                || dateStr.length() < "yyyy-MM-ddTHH:mm:ss".length())
        {
            return dateStr;
        }
        
        String time = dateStr.substring(0, "yyyy-MM-ddTHH:mm:ss".length());
        String zoneStr = dateStr.substring("yyyy-MM-ddTHH:mm:ss".length());
        
        TimeZone emailTimeZone = TimeZone.getTimeZone("GMT+0");
        
        if (zoneStr != null && (zoneStr.contains("+") || zoneStr.contains("-")))
        {
            
            emailTimeZone = TimeZone.getTimeZone("GMT" + zoneStr);
        }
        else
        {
            emailTimeZone = TimeZone.getTimeZone("GMT+0");
        }
        
        SimpleDateFormat localFormatter = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss");
        localFormatter.setTimeZone(emailTimeZone);
        // 获取本地时间
        Date localDate = localFormatter.parse(time);
        
        // 设置存储时区为utc时区
        localFormatter.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        
        return localFormatter.format(localDate);
    }
    
    /**
     * 获得当前UTC格式的时间字符串
     * 
     * @return 当前UTC格式的时间字符串
     */
    public static String getUTCTime()
    {
        // TODO 获取当前时区
        Calendar cal = Calendar.getInstance(Locale.getDefault());
        int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET);
        int dstOffset = cal.get(java.util.Calendar.DST_OFFSET);
        cal.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        return DateFormat.format("yyyy'-'MM'-'dd'T'kk':'mm':'ss'Z'", cal)
                .toString();
    }
    
    /**
     * 
     * 获取时间字符串精确到毫秒<BR>
     * 获取形式如：HHmmssSSS
     * 
     * @return String 时分秒毫秒
     */
    public static String getMillisecond()
    {
        SimpleDateFormat timeStampMilliSecondDF = new SimpleDateFormat(
                "HHmmssSSS");
        return timeStampMilliSecondDF.format(new Date());
    }
    
    /**
     * 获得几天前的UTC格式的时间字符串
     * 
     * @param days
     *            天数
     * @return 几天前的UTC格式的时间字符串
     */
    public static String getUTCDateBeforeToday(int days)
    {
        Calendar cal = Calendar.getInstance(Locale.getDefault());
        cal.add(Calendar.DAY_OF_YEAR, -days);
        // TimeZone tz = cal.getTimeZone();
        // String timeZoneString = getTimeZoneString(tz.getRawOffset());
        SimpleDateFormat formatter = new SimpleDateFormat(
                "yyyy'-'MM'-'dd'T00:00:00Z'");
        String time = formatter.format(cal.getTime());
        // if (timeZoneString != null)
        // {
        // time += timeZoneString;
        // }
        return time;
    }
    
    /**
     * 
     * 根据偏移量获取时区<BR>
     * 
     * @param offset
     *            偏移量
     * @return 时区
     */
    public static String getTimeZoneString(long offset)
    {
        // 是否是正数
        boolean isPositive = true;
        if (offset < 0)
        {
            offset = -offset;
            isPositive = false;
        }
        long hour = offset / (60 * 60 * 1000);
        long minute = offset % (60 * 60 * 1000) / (60 * 1000);
        String flag = isPositive ? "+" : "-";
        String hours = hour < 10 ? "0" + String.valueOf(hour)
                : String.valueOf(hour);
        String minutes = minute < 10 ? "0" + String.valueOf(minute)
                : String.valueOf(minute);
        
        return flag + hours + minutes;
    }
    
    /**
     * 转化分钟为毫秒数<BR>
     * 
     * @param min
     *            分钟数
     * @author liying 00124251
     * @return 毫秒数
     */
    public static Long changeMinToMillSecond(String min)
    {
        return Long.parseLong(min) * 60 * 1000;
    }
    
    /**
     * 转化秒为毫秒
     * 
     * @param second
     *            秒
     * @return 毫秒
     */
    public static Long changeSecondToMillSecond(String second)
    {
        return Long.parseLong(second) * 1000;
    }
    
    public static String getDateFromLong(long l)
    {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(new Date(l));
    }
    
    public static boolean DateCompare(String s1, String s2) throws Exception
    {
        //设定时间的模板
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        //得到指定模范的时间
        Date d1 = sdf.parse(s1);
        Date d2 = sdf.parse(s2);
        if (d1.getTime() - d2.getTime() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
        //比较
        //        if (Math.abs(((d1.getTime() - d2.getTime()) / (24 * 3600 * 1000))) >= 3)
        //        {
        //            System.out.println("大于三天");
        //        }
        //        else
        //        {
        //            System.out.println("小于三天");
        //        }
        
    }
}
