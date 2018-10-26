package com.facedamon.smart.common.base;

import java.util.HashMap;

/**
 * @Description: 操作消息提醒
 * @Author: facedamon
 * @CreateDate: 2018/10/26 11:42
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/26 11:42
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class Response extends HashMap<String, Object> {

    /**
     * 初始化一个新创建的 Message 对象
     */
    public Response() {
    }

    /**
     * 返回错误消息
     *
     * @return 错误消息
     */
    public static Response error() {
        return error(1, "操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 内容
     * @return 错误消息
     */
    public static Response error(String msg) {
        return error(500, msg);
    }

    /**
     * 返回错误消息
     *
     * @param code 错误码
     * @param msg  内容
     * @return 错误消息
     */
    public static Response error(int code, String msg) {
        Response json = new Response();
        json.put("code", code);
        json.put("msg", msg);
        return json;
    }

    /**
     * 返回成功消息
     *
     * @param msg 内容
     * @return 成功消息
     */
    public static Response success(String msg) {
        Response json = new Response();
        json.put("msg", msg);
        json.put("code", 0);
        return json;
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static Response success() {
        return Response.success("操作成功");
    }

    /**
     * 返回成功消息
     *
     * @param key   键值
     * @param value 内容
     * @return 成功消息
     */
    @Override
    public Response put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
