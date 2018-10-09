package com.facedamon.smart.core.shiro.session;

import com.facedamon.smart.project.monitor.online.domain.OnlineSession;
import com.facedamon.smart.project.monitor.online.domain.UserOnline;
import com.facedamon.smart.project.monitor.online.service.IUserOnlineService;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;

/**
 * @Description:    针对自定义的shiro session 的 DB 操作
 * @Author:         facedamon
 * @CreateDate:     2018/10/6 下午4:46
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/10/6 下午4:46
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
public class OnlineSessionDAO extends EnterpriseCacheSessionDAO {

    /**
     * 同步session到数据库的周期，单位min
     */
    @Value("${shiro.session.dbSyncPeriod}")
    private int dbSyncPeriod;

    @Autowired
    private IUserOnlineService userOnlineService;

    /**
     * 会话工厂，主要用来创建Session
     */
    @Autowired
    private OnlineSessionFactory onlineSessionFactory;

    /**
     * 根据会话ID获取会话
     * @param sessionId
     * @return
     */
    @Override
    protected Session doReadSession(Serializable sessionId) {
        UserOnline userOnline = userOnlineService.selectOnlineById(String.valueOf(sessionId));
        if (null == userOnline){
            return null;
        }
        return onlineSessionFactory.createSession(userOnline);
    }

    /**
     * 当会话过期/停止（如用户退出时）属性等会调用
     * @param session
     */
    @Override
    protected void doDelete(Session session) {
        OnlineSession onlineSession = (OnlineSession) session;
        if (null != onlineSession){
            onlineSession.setStatus(OnlineSession.OnlineStatus.OFF_LINE);
            userOnlineService.deleteOnlineById(String.valueOf(session.getId()));
        }
    }

    /**
     * 更新会话；如更新会话最后访问时间/停止会话/设置超时时间/设置移除属性等会调用
     * @param onlineSession
     */
    public void syncToDb(OnlineSession onlineSession){
        //TODO
    }
}
