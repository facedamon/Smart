package com.facedamon.smart.system.mapper;

import com.facedamon.smart.system.domain.UserOnline;

import java.util.List;

/**
 * @Description: 在线用户 mapper
 * @Author: facedamon
 * @CreateDate: 2018/10/6 下午4:09
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/6 下午4:09
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public interface UserOnlineMapper {

    /**
     * 通过会话ID查询在线信息
     *
     * @param sessionId
     * @return
     */
    UserOnline selectOnlineById(String sessionId);

    /**
     * 通过会话ID删除在线信息
     *
     * @param sessionId
     * @return
     */
    int deleteOnlineById(String sessionId);

    /**
     * 保存用户在线信息
     *
     * @param userOnline
     * @return
     */
    int saveOnline(UserOnline userOnline);

    /**
     * 条件匹配在线信息
     *
     * @param userOnline
     * @return
     */
    List<UserOnline> selectUserOnlineList(UserOnline userOnline);

    /**
     * 查询过期会话
     *
     * @param lastAccessTime
     * @return
     */
    List<UserOnline> selectOnlineByExpired(String lastAccessTime);
}