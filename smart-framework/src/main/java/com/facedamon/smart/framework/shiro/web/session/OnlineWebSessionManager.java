package com.facedamon.smart.framework.shiro.web.session;

import com.facedamon.smart.common.constant.ShiroConstants;
import com.facedamon.smart.common.utils.DateUtils;
import com.facedamon.smart.framework.shiro.session.OnlineSession;
import com.facedamon.smart.framework.util.ApplicationHolder;
import com.facedamon.smart.system.doamin.UserOnline;
import com.facedamon.smart.system.service.impl.UserOnlineServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.ExpiredSessionException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: session属性manager
 * 主要是在此如果会话的属性修改了 就标识下其修改了
 * 方便 OnlineSessionDao同步
 * @Author: facedamon
 * @CreateDate: 2018/10/6 下午6:55
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/6 下午6:55
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Slf4j
public class OnlineWebSessionManager extends DefaultWebSessionManager {

    @Override
    public void setAttribute(SessionKey sessionKey, Object attributeKey, Object value) throws InvalidSessionException {
        super.setAttribute(sessionKey, attributeKey, value);

        if (null != value && needMarkAttributeChanged(attributeKey)) {
            OnlineSession onlineSession = (OnlineSession)doGetSession(sessionKey);
            onlineSession.markAttributeChanged();
        }
    }

    @Override
    public Object removeAttribute(SessionKey sessionKey, Object attributeKey) throws InvalidSessionException {
        Object removed = super.removeAttribute(sessionKey, attributeKey);
        if (null != removed) {
            OnlineSession onlineSession = (OnlineSession) sessionKey;
            onlineSession.markAttributeChanged();
        }
        return removed;
    }

    /**
     * 删除过期的session，并同步到数据库
     */
    @Override
    public void validateSessions() {
        if (log.isInfoEnabled()) {
            log.info("invalidation sessions...");
        }
        /**
         * 删除过期sessin
         */
        int invalidCount = 0;

        int timeout = (int) this.getGlobalSessionTimeout();
        Date expiredDate = org.apache.commons.lang3.time.DateUtils.addMilliseconds(DateUtils.getNowDate(), 0 - timeout);
        UserOnlineServiceImpl userOnlineService = ApplicationHolder.getBean(UserOnlineServiceImpl.class);
        List<UserOnline> userOnlineList = userOnlineService.selectOnlineByExpired(expiredDate);
        List<String> needOfflineIdList = new ArrayList<>();

        for (UserOnline userOnline : userOnlineList) {
            try {
                SessionKey key = new DefaultSessionKey(userOnline.getSessionId());
                if (null != retrieveSession(key)) {
                    throw new InvalidSessionException();
                }
            } catch (InvalidSessionException e) {
                if (log.isDebugEnabled()) {
                    boolean expired = (e instanceof ExpiredSessionException);
                    String msg = "Invalidated session with id [" + userOnline.getSessionId() + "]"
                            + (expired ? " (expired)" : " (stopped)");
                    log.debug(msg);
                }
                invalidCount++;
                needOfflineIdList.add(userOnline.getSessionId());
            }

        }

        if (!needOfflineIdList.isEmpty()) {
            try {
                userOnlineService.batchDeleteOnline(needOfflineIdList);
            } catch (Exception e) {
                log.error("batch delete db session error.", e);
            }
        }

        if (log.isInfoEnabled()) {
            StringBuffer msg = new StringBuffer("Finished invalidation session.");
            if (invalidCount > 0) {
                msg.append(" [").append(invalidCount).append("] ")
                        .append("sessions were stopped.");
            } else {
                msg.append(" No session were stopped.");
            }
            log.info(msg.toString());
        }
    }

    /**
     * @param attributeKey
     * @return
     */
    private boolean needMarkAttributeChanged(Object attributeKey) {
        if (null == attributeKey) {
            return false;
        }
        String attributeKeyStr = attributeKey.toString();
        if (attributeKeyStr.startsWith("org.springframework") || attributeKeyStr.startsWith("javax.servlet")) {
            return false;
        }
        if (ShiroConstants.CURRENT_USER.getValue().equals(attributeKeyStr)) {
            return false;
        }

        return true;
    }
}