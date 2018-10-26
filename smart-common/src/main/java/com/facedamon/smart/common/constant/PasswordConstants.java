package com.facedamon.smart.common.constant;

/**
 * @Description: 密码常量
 * @Author: facedamon
 * @CreateDate: 2018/10/15 下午5:21
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/15 下午5:21
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public enum PasswordConstants {

    PASSWORD_MIN_LENGTH(5),
    PASSWORD_MAX_LENGTH(20);

    private int value;

    PasswordConstants(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}