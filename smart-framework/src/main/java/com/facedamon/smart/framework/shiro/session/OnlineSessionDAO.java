package com.facedamon.smart.framework.shiro.session;

import com.facedamon.smart.common.enums.OnlineStatus;
import com.facedamon.smart.framework.syn.AsyncFactory;
import com.facedamon.smart.system.doamin.UserOnline;
import com.facedamon.smart.system.service.IUserOnlineService;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 针对自定义的shiro session 的 DB 操作
 * @Author: facedamon
 * @CreateDate: 2018/10/6 下午4:46
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/6 下午4:46
 * @UpdateRemark: 修改内容
 * @Version: 1.0
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

    private static final String LAST_SYNC_DB_TIMESTAMP = OnlineSessionDAO.class.getName() + "LAST_SYNC_DB_TIMESTAMP";

    /**
     * 根据会话ID获取会话
     *
     * @param sessionId
     * @return
     */
    @Override
    protected Session doReadSession(Serializable sessionId) {
        UserOnline userOnline = userOnlineService.selectOnlineById(String.valueOf(sessionId));
        if (null == userOnline) {
            return null;
        }
        return super.doReadSession(sessionId);
    }

    /**
     * 当会话过期/停止（如用户退出时）属性等会调用
     *
     * @param session
     */
    @Override
    protected void doDelete(Session session) {
        OnlineSession onlineSession = (OnlineSession) session;
        if (null != onlineSession) {
            onlineSession.setStatus(OnlineStatus.OFF_LINE);
            userOnlineService.deleteOnlineById(String.valueOf(session.getId()));
        }
    }

    /**
     * 更新会话；如更新会话最后访问时间/停止会话/设置超时时间/设置移除属性等会调用
     *
     * @param onlineSession
     */
    public void syncToDb(OnlineSession onlineSession) {
        Date lastSyncTimestamp = (Date) onlineSession.getAttribute(LAST_SYNC_DB_TIMESTAMP);
        if (null != lastSyncTimestamp) {
            boolean needSync = true;
            long deltatime = onlineSession.getLastAccessTime().getTime() - lastSyncTimestamp.getTime();
            /**
             * 时间差不足，无需同步
             */
            if (deltatime < dbSyncPeriod * 60 * 1000) {
                needSync = false;
            }

            boolean isGuest = onlineSession.getUserId() == null || onlineSession.getUserId() == 0L;

            if (!isGuest && onlineSession.isAttributeChanged()) {
                needSync = true;
            }

            if (!needSync) {
                return;
            }
        }

        onlineSession.setAttribute(LAST_SYNC_DB_TIMESTAMP, onlineSession.getLastAccessTime());

        /**
         * 更新完毕，重置标识
         */
        if (onlineSession.isAttributeChanged()) {
            onlineSession.resetAttributeChanged();
        }
        AsyncFactory.INSTANCE.asyncSessionToDb(onlineSession);
    }

}