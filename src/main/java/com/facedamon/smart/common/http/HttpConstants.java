package com.facedamon.smart.common.http;

/**
 * @Description:    http常量
 * @Author:         facedamon
 * @CreateDate:     2018/9/30 下午8:51
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/9/30 下午8:51
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
public enum HttpConstants {

    /**
     * http json 请求头
     */
    CONTENT_TYPE("application/json"),

    /**
     * http accept head
     */
    HEADER_ACCEPT("accept"),

    /**
     * http head
     */
    HEADER_X_REQUEST("X-Requested-With"),

    /**
     * http xml head
     */
    HEADER_XML_REQUEST("XMLHttpRequest");

    private String value;

    public String getValue() {
        return value;
    }

    HttpConstants(String value) {
        this.value = value;
    }
}
