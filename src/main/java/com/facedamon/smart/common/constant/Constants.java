package com.facedamon.smart.common.constant;

public enum Constants {
    UTF8("UTF-8"),

    SUCCESS("0"),
    ERROR("-1"),
    FIVE_ERROR("500"),
    LOGIN_SUCCESS("LOGIN_SUCCESS"),
    LOGOUT("LOGOUT"),
    LOGIN_FAIL("LOGIN_FAIL"),

    PAGE_NUM("pageNum"),
    PAGE_SIZE("pageSize"),
    ORDER_BY_COLUMN("orderByColumn"),
    IS_ASC("isAsc"),

    PREMISSION("[\"{0}\"]"),

    MENU_NAME_UNIQUE("0"),
    MENU_NAME_NOT_UNIQUE("1");

    private String value;

    public String getValue() {
        return value;
    }

    Constants(String value) {
        this.value = value;
    }
}
