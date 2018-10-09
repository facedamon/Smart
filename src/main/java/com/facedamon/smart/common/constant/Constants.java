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
     * 角色名称唯一
     */
    ROLE_NAME_UNIQUE("0"),

    /**
     * 角色名称不唯一
     */
    ROLE_NAME_NOT_UNIQUE("1"),

    /**
     * 角色权限唯一
     */
    ROLE_KEY_UNIQUE("0"),

    /**
     * 角色权限不唯一
     */
    ROLE_KEY_NOT_UNIQUE("1"),

    /**
     * 用户名唯一
     */
    USER_NAME_UNIQUE("0"),

    /**
     * 用户名不唯一
     */
    USER_NAME_NOT_UNIQUE("1"),

    /**
     * 用户电话唯一
     */
    USER_PHONE_UNIQUE("0"),

    /**
     * 用户电话不唯一
     */
    USER_PHONE_NOT_UNIQUE("1"),

    /**
     * 用户email唯一
     */
    USER_EMAIl_UNIQUE("0"),

    /**
     * 用户email不唯一
     */
    USER_EMAIL_NOT_UNIQUE("1"),

    /**
     * 手机号码格式限制
     */
    MOBILE_PHONE_NUMBER_PATTERN("^0{0,1}(13[0-9]|15[0-9]|14[0-9]|18[0-9])[0-9]{8}$"),

    /**
     * 邮箱格式限制
     */
    EMAIL_PATTERN("^((([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+(\\.([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+)*)|((\\x22)((((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(([\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x7f]|\\x21|[\\x23-\\x5b]|[\\x5d-\\x7e]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(\\\\([\\x01-\\x09\\x0b\\x0c\\x0d-\\x7f]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF]))))*(((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(\\x22)))@((([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.)+(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.?"),

    /**
     * 部门状态
     */
    DEPT_NORMAL("0"),

    /**
     * 部门名称不唯一
     */
    DEPT_NAME_NOT_UNIQUE("1"),

    /**
     * 部门名称唯一
     */
    DEPT_NAME_UNIQUE("0"),

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
