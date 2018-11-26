package com.facedamon.smart.system.service.impl;

import com.facedamon.smart.common.support.Convert;
import com.facedamon.smart.system.domain.Logininfor;
import com.facedamon.smart.system.mapper.LogininforMapper;
import com.facedamon.smart.system.service.ILogininforService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author:         facedamon
 * @CreateDate:     2018/10/16 下午4:15
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/10/16 下午4:15
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Service
public class LogininforServiceImpl implements ILogininforService {

    @Autowired
    private LogininforMapper logininforMapper;

    /**
     * 新增系统登录日志
     * @param logininfor 访问日志对象
     */
    @Override
    public void insertLogininfor(Logininfor logininfor) {
        logininforMapper.insertLogininfor(logininfor);
    }

    /**
     * 查询系统登录日志集合
     * @param logininfor 访问日志对象
     * @return 登录记录集合
     */
    @Override
    public List<Logininfor> selectLogininforList(Logininfor logininfor) {
        return logininforMapper.selectLogininforList(logininfor);
    }

    /**
     * 批量删除系统登录日志
     * @param ids 需要删除的数据
     * @return
     */
    @Override
    public int deleteLogininforByIds(String ids) {
        return logininforMapper.deleteLogininforByIds(Convert.toStrArray(ids));
    }

    /**
     * 清空系统登录日志
     * @return
     */
    @Override
    public int cleanLogininfo() {
        return logininforMapper.cleanLogininfo();
    }
}