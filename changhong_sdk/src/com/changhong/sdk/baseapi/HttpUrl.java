package com.changhong.sdk.baseapi;

public class HttpUrl
{
    
    //张琪荣  http://172.17.184.36:8080
    //杨帆 http://172.17.184.41:8081/OSS
    //邓金祥 http://172.17.184.141:25535/OSS
    //OOS开发服务器http://172.17.184.94:6666
    //外网服务器http://221.226.107.6:8503
    //内网服务器------http://172.17.184.244:9999
    //内网服务器 -----http://172.17.184.213:8888
    //内网服务器 -----http://172.17.184.244:9999
    public static String OSS = "http://172.17.184.244:9999" + "/OSS";//"http://172.17.184.212:9999/OSS";//
    
    public static String CBS = "http://172.17.184.213:8888" + "/cbs";
    
    public static String SNS = "http://172.17.184.244:9999" + "/SNS";
    
    public static String OSS_PIC = OSS.replace("OSS", "");
    
    public static String URL_OSS = OSS + "/service";
    
    public static String URL_CBS = CBS + "/msg"; // "http://192.168.115.89:8089/cbs/msg";
    
    public static String URL_SNS = SNS + "/service";
    
}
