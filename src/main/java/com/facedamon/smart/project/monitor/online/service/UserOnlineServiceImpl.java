package com.facedamon.smart.project.monitor.online.service;

import com.facedamon.smart.common.utils.DateUtils;
import com.facedamon.smart.project.monitor.online.domain.UserOnline;
import com.facedamon.smart.project.monitor.online.mapper.UserOnlineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description:    在线用户 service
 * @Author:         facedamon
 * @CreateDate:     2018/10/6 下午4:18
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/10/6 下午4:18
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Service
public class UserOnlineServiceImpl implements IUserOnlineService {

    @Autowired
    private UserOnlineMapper userOnlineMapper;

    /**
     * 通过会话ID查询用户在线信息
     * @param sessionId 会话ID
     * @return
     */
    @Override
    public UserOnline selectOnlineById(String sessionId) {
        return userOnlineMapper.selectOnlineById(sessionId);
    }

    /**
     * 通过会话ID删除用户在线信息
     * @param sessionId 会话ID
     */
    @Override
    public void deleteOnlineById(String sessionId) {
        UserOnline userOnline = selectOnlineById(sessionId);
        if (null != userOnline){
            userOnlineMapper.deleteOnlineById(sessionId);
        }
    }

    /**
     * 批量删除用户在线信息
     * @param sessions 会话ID集合
     */
    @Override
    public void batchDeleteOnline(List<String> sessions) {
        for (String sessionId : sessions){
            UserOnline userOnline = selectOnlineById(sessionId);
            if (null != userOnline){
                userOnlineMapper.deleteOnlineById(sessionId);
            }
        }
    }

    /**
     * 保存用户在线信息
     * @param online 会话信息
     */
    @Override
    public void saveOnline(UserOnline online) {
        userOnlineMapper.saveOnline(online);
    }

    /**
     * 分页查询在线用户信息
     * @param userOnline 分页参数
     * @return
     */
    @Override
    public List<UserOnline> selectUserOnlineList(UserOnline userOnline) {
        return userOnlineMapper.selectUserOnlineList(userOnline);
    }

    /**
     * 强制退出
     * @param sessionId 会话ID
     */
    @Override
    public void forceLogout(String sessionId) {
        //TODO
    }

    /**
     * 查询过期用户信息
     * @param expiredDate 有效期
     * @return
     */
    @Override
    public List<UserOnline> selectOnlineByExpired(Date expiredDate) {
        String lastAccessTime = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS,expiredDate);
        // TODO
        return null;
    }
}
