package com.facedamon.smart.framework.shiro.session;

import com.facedamon.smart.common.enums.OnlineStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.shiro.session.mgt.SimpleSession;

import java.util.Map;

/**
 * @Description: 在线用户会话属性
 * @Author: facedamon
 * @CreateDate: 2018/10/6 下午3:51
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/6 下午3:51
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@ToString
public class OnlineSession extends SimpleSession {

    /**
     * 用户ID
     */
    @Setter
    @Getter
    private Long userId;

    /**
     * 登录用户名
     */
    @Setter
    @Getter
    private String loginName;

    /**
     * 所属机构名称
     */
    @Setter
    @Getter
    private String deptName;

    /**
     * 登录IP
     */
    private String host;

    /**
     * 浏览器类型
     */
    @Getter
    @Setter
    private String browser;

    /**
     * 操作系统
     */
    @Getter
    @Setter
    private String os;

    /**
     * 属性是否改变 优化session数据同步
     */
    private transient boolean attributeChanged = false;

    /**
     * 在线状态
     */
    private OnlineStatus status = OnlineStatus.ON_LINE;

    public OnlineStatus getStatus() {
        return status;
    }

    public void setStatus(OnlineStatus status) {
        this.status = status;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public Map<Object, Object> getAttributes() {
        return super.getAttributes();
    }

    @Override
    public void setAttributes(Map<Object, Object> attributes) {
        super.setAttributes(attributes);
    }

    public void markAttributeChanged() {
        this.attributeChanged = true;
    }

    public void resetAttributeChanged() {
        this.attributeChanged = false;
    }

    public boolean isAttributeChanged() {
        return attributeChanged;
    }
}