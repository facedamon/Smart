package com.facedamon.smart.project.system.user.domain;
/**
* @Description:    用户状态
* @Author:         facedamon
* @CreateDate:     2018/8/16 10:25
* @UpdateUser:     facedamon
* @UpdateDate:     2018/8/16 10:25
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
public enum UserStatus {
    OK("0","正常"),
    DISABLED("1","停用"),
    DELETED("2","删除");

    private final String code;
    private final String value;

    UserStatus(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
