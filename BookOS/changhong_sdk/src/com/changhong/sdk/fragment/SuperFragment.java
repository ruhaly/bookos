package com.changhong.sdk.fragment;

import java.text.DecimalFormat;

import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.changhong.sdk.R;
import com.changhong.sdk.activity.SuperActivity;
import com.changhong.sdk.baseapi.StringUtils;
import com.changhong.sdk.baseapi.SuperMsgWhat;

public abstract class SuperFragment extends Fragment implements
        View.OnClickListener, SuperMsgWhat
{
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }
    
    public abstract void updateView(Message msg);
    
    public abstract void updateView(Intent intent);
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        initData();
        return initLayout(inflater, container, savedInstanceState);
    }
    
    public abstract void initData();
    
    public abstract View initLayout(LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState);
    
    public void reqeustDate(String keyword)
    {
        
    }
    
    /**
     * fHandler
     */
    public MewwHandler fHandler = new MewwHandler();
    
    public class MewwHandler extends Handler
    {
        /*
         * (non-Javadoc) (覆盖方法) 方法名称： handleMessage 作者： hanliangru 方法描述：
         * 
         * @see android.os.Handler#handleMessage(android.os.Message)
         */
        @Override
        public void handleMessage(Message msg)
        {
            handleMsg(msg);
        }
    }
    
    /**
     * 方法名称： handleMsg 作者： hanliangru 方法描述： 处理消息 输入参数： @param msg 返回类型： void
     */
    public void handleMsg(Message msg)
    {
        switch (msg.what)
        {
            case CONNECT_ERROR_MSGWHAT:
            {
                showToast(getString(R.string.error_net));
                break;
            }
            case DATA_FORMAT_ERROR_MSGWHAT:
            {
                showToast(getString(R.string.error_fwq));
                break;
            }
            default:
                break;
        }
        if( getActivity() instanceof SuperActivity){
            ((SuperActivity) getActivity()).dismissProgress();    
        }
        
    }
    
    public void showToast(String str)
    {
        if (str != null)
            if( getActivity() instanceof SuperActivity){
                ((SuperActivity) getActivity()).showToast(str);
            }
           
    }
    
    public void showProcessDialog(OnDismissListener dismiss)
    {
        if( getActivity() instanceof SuperActivity){
            ((SuperActivity) getActivity()).showProgressDialog("",
                    getString(R.string.loading),
                    true,
                    dismiss);
        }
        
       
    }
    
    public String convertByteToMB(String str)
    {
        if (StringUtils.isEmpty(str))
        {
            return 0 + "MB";
        }
        DecimalFormat fnum = new DecimalFormat("##0.00");
        String dd = fnum.format(Float.valueOf(str) / (1024 * 1024));
        return dd + "MB";
    }
    
    @Override
    public void onResume()
    {
        super.onResume();
    }
}
