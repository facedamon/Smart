package com.facedamon.smart.framework.util;

import com.facedamon.smart.common.constant.PermissionConstants;
import com.facedamon.smart.common.utils.StringUtils;

/**
 * @Description: 权限工具类
 * @Author: facedamon
 * @CreateDate: 2018/10/29 15:40
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/29 15:40
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class PermissionUtils {
    /**
     * 权限错误消息提醒
     *
     * @param permissionsStr 错误信息
     * @return
     */
    public static String getMsg(String permissionsStr) {
        String permission = StringUtils.substringBetween(permissionsStr, "[", "]");
        String msg = MessageUtils.message("no.view.permission", permission);
        if (StringUtils.endsWithIgnoreCase(permission, PermissionConstants.ADD_PERMISSION.getValue())) {
            msg = MessageUtils.message("no.create.permission", permission);
        } else if (StringUtils.endsWithIgnoreCase(permission, PermissionConstants.EDIT_PERMISSION.getValue())) {
            msg = MessageUtils.message("no.update.permission", permission);
        } else if (StringUtils.endsWithIgnoreCase(permission, PermissionConstants.REMOVE_PERMISSION.getValue())) {
            msg = MessageUtils.message("no.delete.permission", permission);
        } else if (StringUtils.endsWithIgnoreCase(permission, PermissionConstants.EXPORT_PERMISSION.getValue())) {
            msg = MessageUtils.message("no.export.permission", permission);
        } else if (StringUtils.endsWithAny(permission,
                new String[]{PermissionConstants.VIEW_PERMISSION.getValue(), PermissionConstants.LIST_PERMISSION.getValue()})) {
            msg = MessageUtils.message("no.view.permission", permission);
        }
        return msg;
    }
}