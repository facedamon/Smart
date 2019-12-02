package com.facedamon.smart.common.enums;

/**
 * @Description: 自定义业务操作类型
 * @Author: facedamon
 * @CreateDate: 2018/10/29 10:12
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/29 10:12
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public enum BusinessType {
    /**
     * 其它
     */
    OTHER,
    /**
     * 新增
     */
    INSERT,
    /**
     * 更新
     */
    UPDATE,
    /**
     * 删除
     */
    DELETE,
    /**
     * 授权
     */
    GRANT,
    /**
     * 导出
     */
    EXPORT,
    /**
     * 导入
     */
    IMPORT,
    /**
     * 强退
     */
    FORCE,
    /**
     * 生成代码
     */
    GENCODE,
    /**
     * 清空
     */
    CLEAN
}
