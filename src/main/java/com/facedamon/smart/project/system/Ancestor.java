package com.facedamon.smart.project.system;

import com.facedamon.smart.common.utils.StringUtils;

/**
 * @Description:    机构数Ancestors计算逻辑
 * @Author:         facedamon
 * @CreateDate:     2018/10/9 16:52
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/10/9 16:52
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
public enum Ancestor {

    /**
     * 分隔符
     */
    SEPARATOR(","),

    /**
     * 根节点
     */
    ROOT("0");
    
    private String value;

    public String getValue() {
        return value;
    }

    Ancestor(String value) {
    
        this.value = value;
    }

    /**
     * 计算机构ancestor
     * @param parentAncestor
     * @param parentId
     * @return
     */
    public static String calculateAncestor(String parentAncestor,Long parentId){
        if (StringUtils.isBlank(parentAncestor)){
            return ROOT.getValue();
        }
        return StringUtils.join(parentAncestor,SEPARATOR.value,parentId);
    }
}
