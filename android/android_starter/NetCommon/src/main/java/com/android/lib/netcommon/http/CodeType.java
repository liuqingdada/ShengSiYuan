package com.android.lib.netcommon.http;

/**
 * Created by cooper
 * 2021/5/26.
 * Email: 1239604859@qq.com
 * <p>
 * 200 - 成功
 * 400 - 参数错误
 * 500 - 服务器内部错误
 * <p>
 * 100 - 登录token过期
 * 101 - 登录校验失败
 * 102 - 没有权限
 * 103 - 电视token过期
 * 104 - 电视不存在
 * 105 - com不存在
 */
public interface CodeType {
    int OK = 200;
    int ERROR_PARAM = 400;
    int ERROR_SERVER = 500;

    int ERROR_SIGNIN_TOKEN_EXPIRE = 100;
    int ERROR_SIGNIN_CHECK = 101;
    int ERROR_NO_PERMISSION = 102;
    int ERROR_TV_TOKEN_EXPIRE = 103;
    int ERROR_TV_NOT_EXIST = 104;
    int ERROR_COM_NOT_EXIST = 105;

    /**
     * 进场小米电视 http 服务
     * 返回数据是空
     */
    int CODE_EMPTY_BODY = -1;

    /**
     * 进场小米电视 http 服务
     * 返回数据没有 code 字段
     */
    int CODE_NULL = -2;

    /**
     * 进场小米电视 http 服务
     * 执行 http 请求出错
     */
    int CODE_RESPONSE_ERROR_FORMAT = -3;

    /**
     * 进场小米电视 http 服务
     * 返回数据不是 json
     */
    int CODE_NOT_JSON = -4;

    /**
     * 进场小米电视 http 服务
     * 返回数据解析 json 失败
     */
    int CODE_PARSE_JSON_ERROR = -5;
}
