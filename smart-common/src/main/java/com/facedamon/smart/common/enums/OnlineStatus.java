package com.facedamon.smart.common.enums;

/**
 * @Description: 用户会话
 * @Author: facedamon
 * @CreateDate: 2018/10/25 15:45
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/25 15:45
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public enum OnlineStatus {
    /**
     * 用户状态
     */
    ON_LINE("在线"), OFF_LINE("离线");

    private final String info;

    OnlineStatus(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}