package com.facedamon.smart.common.constant;

/**
 * @Description:    常量枚举
 * @Author:         facedamon
 * @CreateDate:     2018/9/30 下午8:48
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/9/30 下午8:48
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
public enum Constants {

    /**
     * 编码
     */
    UTF8("UTF-8"),

    /**
     * 返回成功状态
     */
    SUCCESS("0"),

    /**
     * 返回异常状态
     */
    ERROR("-1"),

    /**
     * TODO
     */
    FIVE_ERROR("500"),

    /**
     * 登陆成功标识
     */
    LOGIN_SUCCESS("LOGIN_SUCCESS"),

    /**
     * 登出标识
     */
    LOGOUT("LOGOUT"),

    /**
     * 登陆失败标识
     */
    LOGIN_FAIL("LOGIN_FAIL"),

    /**
     * 分页参数，当前页码
     */
    PAGE_NUM("pageNum"),

    /**
     * 分页参数，每页数量
     */
    PAGE_SIZE("pageSize"),

    /**
     * 排序参数
     */
    ORDER_BY_COLUMN("orderByColumn"),

    /**
     * 排序是否升序
     */
    IS_ASC("isAsc"),

    /**
     * 权限正则表达式
     */
    PREMISSION("[\"{0}\"]"),

    /**
     * 菜单名称唯一
     */
    MENU_NAME_UNIQUE("0"),

    /**
     * 菜单名称不唯一
     */
    MENU_NAME_NOT_UNIQUE("1"),

    /**
     * json
     */
    JSON("json"),

    /**
     * xml
     */
    XML("xml"),

    JSON_SUFFIX(".json"),

    XML_SUFFIX(".xml");

    private String value;

    public String getValue() {
        return value;
    }

    Constants(String value) {
        this.value = value;
    }
}
