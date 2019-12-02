package com.facedamon.smart.common.enums;

/**
 * @Description: 用户状态
 * @Author: facedamon
 * @CreateDate: 2018/10/25 15:44
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/25 15:44
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public enum UserStatus {
    OK("0", "正常"), DISABLE("1", "停用");

    private final String code;
    private final String info;

    UserStatus(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}