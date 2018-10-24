package com.facedamon.smart.project.monitor.logininfor.mapper;

import com.facedamon.smart.project.monitor.logininfor.domain.Logininfor;

import java.util.List;

/**
 * @Description:    系统访问日志情况信息
 * @Author:         facedamon
 * @CreateDate:     2018/10/16 下午4:04
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/10/16 下午4:04
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
public interface LogininforMapper {


    /**
     * 新增系统登录日志
     * @param logininfor 访问日志对象
     */
    void insertLogininfor(Logininfor logininfor);

    /**
     * 查询系统登录日志集合
     * @param logininfor 访问日志对象
     * @return 登录记录集合
     */
    List<Logininfor> selectLogininforList(Logininfor logininfor);

    /**
     * 批量删除系统登录日志
     * @param ids 需要删除的数据
     * @return
     */
    int deleteLogininforByIds(String[] ids);

}
