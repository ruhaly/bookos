package com.changhong.sdk.entity;

import android.content.Intent;
import android.graphics.drawable.Drawable;

public class BusinessInfo
{
    //包名
    private String packageName;
    
    //apk主界面全路径
    private String mainActivityPath;
    
    //后台传的id
    private String apkId;
    
    //原子业务编码
    private String businessCode;
    
    //业务类型名字apk名字
    private String businessName;
    
    //apk大小
    private String size;
    
    //原子业务类型
    private String businessType;
    
    //权限
    //0:已购买
    //1:未购买
    //2:免费使用
    private String isAuthority;
    
    //序列号
    private String serialNumber;
    
    //安装包地址
    private String sourceUrl;
    
    //所属SP
    private String spId;
    
    //最新版本号
    private String versionNo;
    
    private String installState;
    
    private String icon;
    
    //是否有快捷方式
    private String hasShortCut;
    
    //版本比较用这个
    private String versionName;
    
    private Drawable shorcut;
    
    private Intent intent;
    
    private int iconResId;
    
    //推荐度级别星星的个数
    private String starNum;
    
    public String getStarNum()
    {
        return starNum;
    }
    
    public void setStarNum(String starNum)
    {
        this.starNum = starNum;
    }
    
    public int getIconResId()
    {
        return iconResId;
    }
    
    public void setIconResId(int iconResId)
    {
        this.iconResId = iconResId;
    }
    
    public String getHasShortCut()
    {
        return hasShortCut;
    }
    
    public void setHasShortCut(String hasShortCut)
    {
        this.hasShortCut = hasShortCut;
    }
    
    public String getVersionName()
    {
        return versionName;
    }
    
    public void setVersionName(String versionName)
    {
        this.versionName = versionName;
    }
    
    public Drawable getShorcut()
    {
        return shorcut;
    }
    
    public void setShorcut(Drawable shorcut)
    {
        this.shorcut = shorcut;
    }
    
    public Intent getIntent()
    {
        return intent;
    }
    
    public void setIntent(Intent intent)
    {
        this.intent = intent;
    }
    
    public String getIcon()
    {
        return icon;
    }
    
    public void setIcon(String icon)
    {
        this.icon = icon;
    }
    
    public String getInstallState()
    {
        return installState;
    }
    
    public void setInstallState(String installState)
    {
        this.installState = installState;
    }
    
    public String getPackageName()
    {
        return null == packageName ? "" : packageName;
    }
    
    public void setPackageName(String packageName)
    {
        this.packageName = packageName;
    }
    
    public String getMainActivityPath()
    {
        return mainActivityPath;
    }
    
    public void setMainActivityPath(String mainActivityPath)
    {
        this.mainActivityPath = mainActivityPath;
    }
    
    public String getSize()
    {
        return size;
    }
    
    public void setSize(String size)
    {
        this.size = size;
    }
    
    public String getApkId()
    {
        return apkId;
    }
    
    public void setApkId(String apkId)
    {
        this.apkId = apkId;
    }
    
    public String getBusinessCode()
    {
        return businessCode;
    }
    
    public void setBusinessCode(String businessCode)
    {
        this.businessCode = businessCode;
    }
    
    public String getBusinessName()
    {
        return businessName;
    }
    
    public void setBusinessName(String businessName)
    {
        this.businessName = businessName;
    }
    
    public String getBusinessType()
    {
        return businessType;
    }
    
    public void setBusinessType(String businessType)
    {
        this.businessType = businessType;
    }
    
    public String getIsAuthority()
    {
        return isAuthority;
    }
    
    public void setIsAuthority(String isAuthority)
    {
        this.isAuthority = isAuthority;
    }
    
    public String getSerialNumber()
    {
        return serialNumber;
    }
    
    public void setSerialNumber(String serialNumber)
    {
        this.serialNumber = serialNumber;
    }
    
    public String getSourceUrl()
    {
        return sourceUrl;
    }
    
    public void setSourceUrl(String sourceUrl)
    {
        this.sourceUrl = sourceUrl;
    }
    
    public String getSpId()
    {
        return spId;
    }
    
    public void setSpId(String spId)
    {
        this.spId = spId;
    }
    
    public String getVersionNo()
    {
        return versionNo;
    }
    
    public void setVersionNo(String versionNo)
    {
        this.versionNo = versionNo;
    }
    
}
