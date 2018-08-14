package com.ibms.app.utils;

/**
 * @ClassName Globals
 * @PackageName com.cmos.ccp.util
 * @Description 全局变量
 * @Author daizong
 * @Date 2018/6/6 9:17
 * @Version 1.0
 **/
public class Globals {

    public static final int Log_Type_LOGIN = 1; //登录
    public static final int Log_Type_EXIT = 2;  //退出
    public static final int Log_Type_INSERT = 3; //插入
    public static final int Log_Type_DELETE = 4; //删除
    public static final int Log_Type_UPDATE = 5; //更新
    public static final int Log_Type_UPLOAD = 6; //上传
    public static final int Log_Type_LOOK = 7 ; //查看
    public static final int Log_Type_OTHER = 8 ; //其他

    public static int HTTP_CONTINUE = 100; //Continue
    public static int HTTP_OK = 200; //OK
    public static int HTTP_MULTIPLE_CHOICES = 300; //MULTIPLE_CHOICES
    public static int HTTP_BAD_REQUEST = 400; //BAD_REQUEST
    public static int HTTP_NOT_FOUND = 404; //NOT_FOUND
    public static int HTTP_METHOD_NOT_ALLOWED = 405; //METHOD_NOT_ALLOWED
    public static int HTTP_INTERNAL_SERVER_ERROR = 500; //INTERNAL_SERVER_ERROR
    public static int HTTP_TOKEN_INVALID = 3200; //TOKEN_INVALID
}
