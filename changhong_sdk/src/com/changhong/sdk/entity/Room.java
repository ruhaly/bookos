package com.changhong.sdk.entity;

import java.util.ArrayList;
import java.util.List;

import com.changhong.sdk.entity.User;

public class Room
{
    //房间id
    private String id;
    
    private String buildingName;
    
    private String groupName;
    
    private String houseName;
    
    private String unitName;
    
    //根据房间号分组
    public List<User> userList = new ArrayList<User>();
    
    public String getBuildingName()
    {
        return buildingName;
    }
    
    public void setBuildingName(String buildingName)
    {
        this.buildingName = buildingName;
    }
    
    public String getGroupName()
    {
        return groupName;
    }
    
    public void setGroupName(String groupName)
    {
        this.groupName = groupName;
    }
    
    public String getHouseName()
    {
        return houseName;
    }
    
    public void setHouseName(String houseName)
    {
        this.houseName = houseName;
    }
    
    public String getUnitName()
    {
        return unitName;
    }
    
    public void setUnitName(String unitName)
    {
        this.unitName = unitName;
    }
    
    public String getId()
    {
        return id;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }
    
    public List<User> getUserList()
    {
        return userList;
    }
    
    public void setUserList(List<User> userList)
    {
        this.userList = userList;
    }
    
}
