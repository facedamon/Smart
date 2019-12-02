package com.facedamon.smart.common.constant;

/**
 * @Description: 权限通用常量
 * @Author: facedamon
 * @CreateDate: 2018/10/29 15:37
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/29 15:37
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public enum PermissionConstants {

    /**
     * 新增权限
     */
    ADD_PERMISSION("add"),

    /**
     * 编辑权限
     */
    EDIT_PERMISSION("edit"),

    /**
     * 删除权限
     */
    REMOVE_PERMISSION("remove"),

    /**
     * 导出权限
     */
    EXPORT_PERMISSION("export"),

    /**
     * 视图
     */
    VIEW_PERMISSION("view"),

    /**
     * 列出权限
     */
    LIST_PERMISSION("list");

    private String value;

    public String getValue() {
        return value;
    }

    PermissionConstants(String value) {
        this.value = value;
    }
}
