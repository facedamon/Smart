package com.facedamon.smart.core.shiro.web.session;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

/**
 * @Description:    session属性manager
 *                  主要是在此如果会话的属性修改了 就标识下其修改了
 *                  方便 OnlineSessionDao同步
 * @Author:         facedamon
 * @CreateDate:     2018/10/6 下午6:55
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/10/6 下午6:55
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Slf4j
public class OnlineWebSessionManager extends DefaultWebSessionManager {

    @Override
    public void setAttribute(SessionKey sessionKey, Object attributeKey, Object value) throws InvalidSessionException {
        super.setAttribute(sessionKey, attributeKey, value);
    }

    @Override
    public Object removeAttribute(SessionKey sessionKey, Object attributeKey) throws InvalidSessionException {
        return super.removeAttribute(sessionKey, attributeKey);
    }

    @Override
    public void validateSessions() {
        super.validateSessions();
    }
}
