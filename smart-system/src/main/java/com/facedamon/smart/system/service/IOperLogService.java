package com.facedamon.smart.system.service;

import com.facedamon.smart.system.domain.OperLog;

import java.util.List;

/**
 * @Description:    操作日志 service
 * @Author:         facedamon
 * @CreateDate:     2018/10/29 10:55
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/10/29 10:55
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
public interface IOperLogService
{
    /**
     * 新增操作日志
     * 
     * @param operLog 操作日志对象
     */
    public void insertOperlog(OperLog operLog);

    /**
     * 查询系统操作日志集合
     * 
     * @param operLog 操作日志对象
     * @return 操作日志集合
     */
    public List<OperLog> selectOperLogList(OperLog operLog);

    /**
     * 批量删除系统操作日志
     * 
     * @param ids 需要删除的数据
     * @return 结果
     */
    public int deleteOperLogByIds(String ids);

    /**
     * 查询操作日志详细
     * 
     * @param operId 操作ID
     * @return 操作日志对象
     */
    public OperLog selectOperLogById(Long operId);

    /**
     * 清空操作日志
     */
    public void cleanOperLog();
}
