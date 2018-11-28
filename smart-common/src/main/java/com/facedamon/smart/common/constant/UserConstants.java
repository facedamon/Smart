package com.facedamon.smart.common.constant;

/**
 * @Description: 用户常量
 * @Author: facedamon
 * @CreateDate: 2018/10/15 下午5:23
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/15 下午5:23
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public enum
UserConstants {

    /**
     *
     */
    NORMAL("0"),

    /**
     * 参数键唯一
     */
    CONFIG_KEY_UNIQUE("0"),

    /**
     * 参数键不唯一
     */
    CONFIG_KEY_NOT_UNIQUE("1"),

    /**
     * 字典类型不唯一
     */
    DICT_TYPE_NOT_UNIQUE("1"),

    /**
     * 字典类型唯一
     */
    DICT_TYPE_UNIQUE("0"),

    POST_CODE_NOT_UNIQUE("1"),
    POST_CODE_UNIQUE("0"),
    POST_NAME_NOT_UNIQUE("1"),
    POST_NAME_UNIQUE("0");

    private String value;

    UserConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    /**
     * 用户名常量
     */
    public enum UserName {
        /**
         * 用户名最小长度
         */
        USERNAME_MIN_LENGTH(5),

        /**
         * 用户名最大长度
         */
        USERNAME_MAX_LENGTH(20);

        private int value;

        UserName(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

}