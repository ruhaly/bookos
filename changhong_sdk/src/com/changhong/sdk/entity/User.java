/**
 * User.java
 * com.pactera.ch.bedframe.entity
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2013-12-13 		b
 *
 * Copyright (c) 2013, TNT All Rights Reserved.
 */

package com.changhong.sdk.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * ClassName:User Function: TODO ADD FUNCTION
 * 
 * @author hanliangru
 * @version
 * @since Ver 1.1
 * @Date 2013-12-13 上午9:05:34
 */
public class User implements Parcelable
{
    //原子业务
    public List<BusinessInfo> list = new ArrayList<BusinessInfo>();
    
    //key:包名；value: 服务器的原子业务息
    public Map<String, BusinessInfo> map = new HashMap<String, BusinessInfo>();
    
    private int id;
    
    private String uid;
    
    private String nickName;
    
    private String account;
    
    private String pwd;
    
    private String reallyName;
    
    //角色用户类型
    //    0:租住
    //    1:业主
    //    2:住户
    private String role;
    
    //性别 1男 0 女
    private String sex;
    
    private String address;
    
    private String birth;
    
    //绑定手机号
    private String boundPhone;
    
    //登陆模式
    private String type;
    
    private String provinceId;
    
    private String provinceName;
    
    private String cityId;
    
    private String cityName;
    
    //小区ID 项目
    private String organId;
    
    private String organName;
    
    //所属社区ID 组团
    private String communtyId;
    
    private String communtyName;
    
    //楼栋
    private String buildingId;
    
    //楼栋名称
    private String buildingName;
    
    private String unitId;
    
    private String unitName;
    
    private String doorPlateId;
    
    private String doorPlateName;
    
    private String familyAccount;
    
    private String mobile;
    
    private String userType;
    
    private String headUrl;
    
    private String headStr;
    
    //家庭成员注册是否需要审核 0:是 1:否
    private String joinCheck;
    
    //是否允许别人加为好友 0:是 1:否
    private String joinFriend;
    
    //由谁审核的
    private String approveBy;
    
    //记录id 删除需要
    private String hrId;
    
    //是否有权限0:有权限，1：没有权限
    private String hasPermission;
    
    //是否可以授权0：可以授权，1：已授权，2：不能授权
    private String canPermit;
    
    private List<Room> roomList = new ArrayList<Room>();
    
    //状态0：待审批，1忽略，2：通过
    private String status;
    
    //0存在1不存在
    private String isExist;
    
    //小区地址
    private String communityAddress;
    
    private String ippAcount;
    
    private String ippPwd;
    
    public String getIppAcount()
    {
        return ippAcount;
    }
    
    public void setIppAcount(String ippAcount)
    {
        this.ippAcount = ippAcount;
    }
    
    public String getIppPwd()
    {
        return ippPwd;
    }
    
    public void setIppPwd(String ippPwd)
    {
        this.ippPwd = ippPwd;
    }
    
    public String getRoomListToString()
    {
        if (roomList == null || roomList.size() == 0)
        {
            return "";
        }
        String temp = "";
        for (int i = 0; i < roomList.size(); i++)
        {
            temp = temp + roomList.get(i).getHouseName() + ",";
        }
        temp = temp.substring(0, temp.lastIndexOf(","));
        return temp;
    }
    
    public String getCommunityAddress()
    {
        return communityAddress;
    }
    
    public void setCommunityAddress(String communityAddress)
    {
        this.communityAddress = communityAddress;
    }
    
    public String getIsExist()
    {
        return isExist;
    }
    
    public void setIsExist(String isExist)
    {
        this.isExist = isExist;
    }
    
    public int getId()
    {
        return id;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    public List<Room> getRoomList()
    {
        return roomList;
    }
    
    public void setRoomList(List<Room> roomList)
    {
        this.roomList = roomList;
    }
    
    public String getStatus()
    {
        return status;
    }
    
    public void setStatus(String status)
    {
        this.status = status;
    }
    
    public String getHasPermission()
    {
        return hasPermission;
    }
    
    public void setHasPermission(String hasPermission)
    {
        this.hasPermission = hasPermission;
    }
    
    public String getCanPermit()
    {
        return canPermit;
    }
    
    public void setCanPermit(String canPermit)
    {
        this.canPermit = canPermit;
    }
    
    public Map<String, BusinessInfo> getMap()
    {
        return map;
    }
    
    public void setMap(Map<String, BusinessInfo> map)
    {
        this.map = map;
    }
    
    public String getApproveBy()
    {
        return approveBy;
    }
    
    public void setApproveBy(String approveBy)
    {
        this.approveBy = approveBy;
    }
    
    public String getHrId()
    {
        return hrId;
    }
    
    public void setHrId(String hrId)
    {
        this.hrId = hrId;
    }
    
    public String getJoinFriend()
    {
        return joinFriend;
    }
    
    public void setJoinFriend(String joinFriend)
    {
        this.joinFriend = joinFriend;
    }
    
    public String getHeadStr()
    {
        return headStr;
    }
    
    public void setHeadStr(String headStr)
    {
        this.headStr = headStr;
    }
    
    public List<BusinessInfo> getList()
    {
        return list;
    }
    
    public void setList(List<BusinessInfo> list)
    {
        this.list = list;
    }
    
    public String getJoinCheck()
    {
        return joinCheck;
    }
    
    public void setJoinCheck(String joinCheck)
    {
        this.joinCheck = joinCheck;
    }
    
    public String getHeadUrl()
    {
        return headUrl;
    }
    
    public void setHeadUrl(String headUrl)
    {
        this.headUrl = headUrl;
    }
    
    public String getBuildingId()
    {
        return buildingId;
    }
    
    public void setBuildingId(String buildingId)
    {
        this.buildingId = buildingId;
    }
    
    public String getCommuntyId()
    {
        return communtyId;
    }
    
    public void setCommuntyId(String communtyId)
    {
        this.communtyId = communtyId;
    }
    
    public String getCommuntyName()
    {
        return communtyName;
    }
    
    public void setCommuntyName(String communtyName)
    {
        this.communtyName = communtyName;
    }
    
    public String getFamilyAccount()
    {
        return familyAccount;
    }
    
    public void setFamilyAccount(String familyAccount)
    {
        this.familyAccount = familyAccount;
    }
    
    public String getMobile()
    {
        return mobile;
    }
    
    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }
    
    public String getUnitId()
    {
        return unitId;
    }
    
    public void setUnitId(String unitId)
    {
        this.unitId = unitId;
    }
    
    public String getUserType()
    {
        return userType;
    }
    
    public void setUserType(String userType)
    {
        this.userType = userType;
    }
    
    public static Creator<User> getCreator()
    {
        return CREATOR;
    }
    
    public String getRole()
    {
        return role;
    }
    
    public void setRole(String role)
    {
        this.role = role;
    }
    
    public String getBoundPhone()
    {
        return boundPhone;
    }
    
    public void setBoundPhone(String boundPhone)
    {
        this.boundPhone = boundPhone;
    }
    
    public String getReallyName()
    {
        return reallyName;
    }
    
    public void setReallyName(String reallyName)
    {
        this.reallyName = reallyName;
    }
    
    public String getSex()
    {
        return sex;
    }
    
    public void setSex(String sex)
    {
        this.sex = sex;
    }
    
    public String getAddress()
    {
        return "";
    }
    
    public void setAddress(String address)
    {
        this.address = address;
    }
    
    public String getBirth()
    {
        return birth;
    }
    
    public void setBirth(String birth)
    {
        this.birth = birth;
    }
    
    public String getType()
    {
        return type;
    }
    
    public void setType(String type)
    {
        this.type = type;
    }
    
    public String getPwd()
    {
        return pwd;
    }
    
    public void setPwd(String pwd)
    {
        this.pwd = pwd;
    }
    
    public String getAccount()
    {
        return account;
    }
    
    public void setAccount(String account)
    {
        this.account = account;
    }
    
    public String getUid()
    {
        return uid;
    }
    
    public void setUid(String uid)
    {
        this.uid = uid;
    }
    
    public String getNickName()
    {
        return nickName;
    }
    
    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }
    
    @Override
    public int describeContents()
    {
        return 0;
    }
    
    public String getOrganId()
    {
        return organId;
    }
    
    public void setOrganId(String organId)
    {
        this.organId = organId;
    }
    
    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(uid);
        dest.writeString(nickName);
        dest.writeString(reallyName);
        dest.writeString(boundPhone);
        dest.writeString(sex);
        dest.writeString(birth);
        dest.writeString(address);
    }
    
    public static final Creator<User> CREATOR = new Creator<User>()
    {
        
        @Override
        public User createFromParcel(Parcel source)
        {
            User user = new User();
            user.uid = source.readString();
            user.nickName = source.readString();
            user.reallyName = source.readString();
            user.boundPhone = source.readString();
            user.sex = source.readString();
            user.birth = source.readString();
            user.address = source.readString();
            return user;
        }
        
        @Override
        public User[] newArray(int size)
        {
            return new User[size];
        }
    };
    
    public String getProvinceName()
    {
        return provinceName;
    }
    
    public void setProvinceName(String provinceName)
    {
        this.provinceName = provinceName;
    }
    
    public String getCityName()
    {
        return cityName;
    }
    
    public void setCityName(String cityName)
    {
        this.cityName = cityName;
    }
    
    public String getProvinceId()
    {
        return provinceId;
    }
    
    public void setProvinceId(String provinceId)
    {
        this.provinceId = provinceId;
    }
    
    public String getCityId()
    {
        return cityId;
    }
    
    public void setCityId(String cityId)
    {
        this.cityId = cityId;
    }
    
    public String getOrganName()
    {
        return organName;
    }
    
    public void setOrganName(String organName)
    {
        this.organName = organName;
    }
    
    public String getDoorPlateId()
    {
        return doorPlateId;
    }
    
    public void setDoorPlateId(String doorPlateId)
    {
        this.doorPlateId = doorPlateId;
    }
    
    public String getBuildingName()
    {
        return buildingName;
    }
    
    public void setBuildingName(String buildingName)
    {
        this.buildingName = buildingName;
    }
    
    public String getUnitName()
    {
        return unitName;
    }
    
    public void setUnitName(String unitName)
    {
        this.unitName = unitName;
    }
    
    public String getDoorPlateName()
    {
        return doorPlateName;
    }
    
    public void setDoorPlateName(String doorPlateName)
    {
        this.doorPlateName = doorPlateName;
    }
    
}
