package com.facedamon.smart.core.web.domain;

import com.facedamon.smart.common.constant.Constants;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.HashMap;

/**
 * @Description: 统一数据返回
 * @Author: facedamon
 * @CreateDate: 2018/8/15 12:41
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/8/15 12:41
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@NoArgsConstructor
@Builder
public class Response extends HashMap<String, Object> {

    public static Response error() {
        return error(Integer.valueOf(Constants.ERROR.getValue()), "操作失败");
    }

    public static Response error(String msg) {
        return error(Integer.valueOf(Constants.FIVE_ERROR.getValue()), msg);
    }

    public static Response error(int code, String msg) {
        Response response = Response.builder().build();
        response.put("code", code);
        response.put("msg", msg);
        return response;
    }

    public static Response success() {
        return success("SUCCESS");
    }

    public static Response success(String msg) {
        Response response = Response.builder().build();
        response.put("code", Integer.valueOf(Constants.SUCCESS.getValue()));
        response.put("msg", msg);
        return response;
    }

    @Override
    public Response put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
