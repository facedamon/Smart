package com.facedamon.smart.core.shiro.web.filter.online;

import com.facedamon.smart.common.constant.ShiroConstants;
import com.facedamon.smart.core.shiro.session.OnlineSessionDAO;
import com.facedamon.smart.project.monitor.online.domain.OnlineSession;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @Description: 同步session数据到数据库
 * @Author: facedamon
 * @CreateDate: 2018/10/6 下午6:47
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/6 下午6:47
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class SyncOnlineSessionFilter extends PathMatchingFilter {

    @Autowired
    private OnlineSessionDAO onlineSessionDAO;

    /**
     * 同步会话数据到DB 一次请求最多同步一次 防止过多处理 需要放到Shiro过滤器之前
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        OnlineSession onlineSession = (OnlineSession) request.getAttribute(ShiroConstants.ONLINE_SESSION.getValue());
        /**
         * 如果stoptimestamp不为null则说明已经停止了
         */
        if (null != onlineSession && onlineSession.getUserId() != null && onlineSession.getStopTimestamp() == null) {
            onlineSessionDAO.syncToDb(onlineSession);
        }
        return true;
    }
}
