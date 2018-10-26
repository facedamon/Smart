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
public enum UserConstants {

    NORMAL("0");

    public String value;

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
        USERNAME_MIN_LENGTH(5),
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