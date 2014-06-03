package com.changhong.sdk.entity;

public class BaseAccountInfo
{
    //终端用户帐号
    private String accountId;
    
    //终端用户 Id
    private String userId;
    
    //小区编码
    private String communityCode;
    
    //终端标识1手机2 TV 3 PAD
    private int flag;
    
    //分别率
    private String resolution;
    
    //终端类型(HTC,Samsung,…) 规则可有终端定义刷新
    private String type;
    
    //经度
    private double longitude;
    
    //纬度
    private double latitude;
    
    //房间号
    private String houseId;

    
    public String getHouseId()
    {
        return houseId;
    }

    public void setHouseId(String houseId)
    {
        this.houseId = houseId;
    }

    public double getLongitude()
    {
        return longitude;
    }

    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }

    public double getLatitude()
    {
        return latitude;
    }

    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }

    public String getAccountId()
    {
        return accountId;
    }
    
    public void setAccountId(String accountId)
    {
        this.accountId = accountId;
    }
    
    public String getUserId()
    {
        return userId;
    }
    
    public void setUserId(String userId)
    {
        this.userId = userId;
    }
    
    public String getCommunityCode()
    {
        return communityCode;
    }
    
    public void setCommunityCode(String communityCode)
    {
        this.communityCode = communityCode;
    }
    
    public int getFlag()
    {
        return flag;
    }
    
    public void setFlag(int flag)
    {
        this.flag = flag;
    }
    
    public String getResolution()
    {
        return resolution;
    }
    
    public void setResolution(String resolution)
    {
        this.resolution = resolution;
    }
    
    public String getType()
    {
        return type;
    }
    
    public void setType(String type)
    {
        this.type = type;
    }
    
    //    /**
    //     * 
    //     * 获取json字符串
    //     * @return
    //     */
    //    public String getJsonStr()
    //    {
    //        Gson gson = new Gson();
    //        return gson.toJson(this);
    //    }
}
