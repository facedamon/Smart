package com.facedamon.smart.system.service;

import com.facedamon.smart.system.doamin.UserOnline;

import java.util.Date;
import java.util.List;

/**
 * @Description: 在线用户 service
 * @Author: facedamon
 * @CreateDate: 2018/10/6 下午4:16
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/6 下午4:16
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public interface IUserOnlineService {

    /**
     * 通过会话序号查询信息
     *
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    UserOnline selectOnlineById(String sessionId);

    /**
     * 通过会话序号删除信息
     *
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    void deleteOnlineById(String sessionId);

    /**
     * 通过会话序号删除信息
     *
     * @param sessions 会话ID集合
     * @return 在线用户信息
     */
    void batchDeleteOnline(List<String> sessions);

    /**
     * 保存会话信息
     *
     * @param online 会话信息
     */
    void saveOnline(UserOnline online);

    /**
     * 查询会话集合
     *
     * @param userOnline 分页参数
     * @return 会话集合
     */
    List<UserOnline> selectUserOnlineList(UserOnline userOnline);

    /**
     * 强退用户
     *
     * @param sessionId 会话ID
     */
    void forceLogout(String sessionId);

    /**
     * 查询过期会话集合
     *
     * @param expiredDate 有效期
     * @return 会话集合
     */
    List<UserOnline> selectOnlineByExpired(Date expiredDate);
}