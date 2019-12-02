package com.facedamon.smart.framework.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.facedamon.smart.framework.shiro.realm.UserRealm;
import com.facedamon.smart.framework.shiro.session.OnlineSessionDAO;
import com.facedamon.smart.framework.shiro.session.OnlineSessionFactory;
import com.facedamon.smart.framework.shiro.web.LogoutFilter;
import com.facedamon.smart.framework.shiro.web.filter.captcha.CaptchaValidateFilter;
import com.facedamon.smart.framework.shiro.web.filter.online.OnlineSessionFilter;
import com.facedamon.smart.framework.shiro.web.filter.online.SyncOnlineSessionFilter;
import com.facedamon.smart.framework.shiro.web.session.OnlineWebSessionManager;
import com.facedamon.smart.framework.shiro.web.session.SpringSessionValidationScheduler;
import net.sf.ehcache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description: shiro config
 * @Author: facedamon
 * @CreateDate: 2018/10/6 下午2:46
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/6 下午2:46
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Configuration
public class ShiroConfig {

    /**
     * session过期时间，单位分钟，默认30min
     */
    @Value("${shiro.session.expireTime}")
    private int expireTime;

    /**
     * 间隔多久检查一次session有效性，单位分钟，默认10min
     */
    @Value("${shiro.session.validationInterval}")
    private int validationInterval;

    /**
     * 验证码开关
     */
    @Value("${shiro.user.captchaEnabled}")
    private boolean captchaEnabled;

    @Value("${shiro.user.captchaType}")
    private String captchaType;

    /**
     * 设置cookie域名
     */
    @Value("${shiro.cookie.domain}")
    private String domain;

    /**
     * 设置cookie有效访问路径
     */
    @Value("${shiro.cookie.path}")
    private String path;

    /**
     * 设置httpOnly属性
     */
    @Value("${shiro.cookie.httpOnly}")
    private boolean httpOnly;

    /**
     * cookie过期时间，单位天
     */
    @Value("${shiro.cookie.maxAge}")
    private int maxAge;

    /**
     * 登录地址
     */
    @Value("${shiro.user.loginUrl}")
    private String loginUrl;

    /**
     * 权限认证失败地址
     */
    @Value("${shiro.user.unauthorizedUrl}")
    private String unauthorizedUrl;

    /**
     * 缓存管理器
     *
     * @return
     */
    @Bean
    public EhCacheManager getEhCacheManager() {
        CacheManager cacheManager = CacheManager.getCacheManager("smart");
        EhCacheManager ehCacheManager = new EhCacheManager();

        if (null == cacheManager) {
            ehCacheManager.setCacheManagerConfigFile("classpath:ehcache/ehcache-shiro.xml");
            return ehCacheManager;
        } else {
            ehCacheManager.setCacheManager(cacheManager);
            return ehCacheManager;
        }
    }

    /**
     * 自定义用户realm
     *
     * @param manager
     * @return
     */
    @Bean
    public UserRealm userRealm(EhCacheManager manager) {
        UserRealm userRealm = new UserRealm();
        userRealm.setCacheManager(manager);
        return userRealm;
    }

    /**
     * OnlineSessionDAO config
     *
     * @return
     */
    @Bean
    public OnlineSessionDAO onlineSessionDAO() {
        return new OnlineSessionDAO();
    }

    /**
     * 自定义sessionfactory会话
     *
     * @return
     */
    @Bean
    public OnlineSessionFactory onlineSessionFactory() {
        return new OnlineSessionFactory();
    }

    /**
     * 自定义sessionFactory调度器
     *
     * @return
     */
    @Bean
    public SpringSessionValidationScheduler sessionValidationScheduler() {
        SpringSessionValidationScheduler sessionValidationScheduler = new SpringSessionValidationScheduler();
        /**
         * 相隔多久检查一次session的有效性，单位毫秒
         */
        sessionValidationScheduler.setSessionValidationInterval(validationInterval * 60 * 1000);

        /**
         * 设置会话验证调度器
         */
        sessionValidationScheduler.setValidatingSessionManager(sessionValidationManager());
        return sessionValidationScheduler;
    }

    /**
     * 安全管理器
     *
     * @return
     */
    @Bean
    public SecurityManager securityManager(UserRealm realm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(realm);
        manager.setCacheManager(getEhCacheManager());
        manager.setRememberMeManager(rememberMeManager());
        manager.setSessionManager(sessionValidationManager());

        return manager;
    }

    /**
     * session validate manager
     *
     * @return
     */
    @Bean
    public OnlineWebSessionManager sessionValidationManager() {
        OnlineWebSessionManager onlineWebSessionManager = new OnlineWebSessionManager();
        onlineWebSessionManager.setCacheManager(getEhCacheManager());
        /**
         * 删除过期的会话
         */
        onlineWebSessionManager.setDeleteInvalidSessions(true);

        /**
         * 设置全局session超时时间
         */
        onlineWebSessionManager.setGlobalSessionTimeout(expireTime * 60 * 1000);

        /**
         * 设置session定时检查
         */
        onlineWebSessionManager.setSessionValidationSchedulerEnabled(true);

        /**
         * 去掉 JSESSIONID
         */
        onlineWebSessionManager.setSessionIdUrlRewritingEnabled(false);

        onlineWebSessionManager.setSessionDAO(onlineSessionDAO());
        onlineWebSessionManager.setSessionFactory(onlineSessionFactory());

        return onlineWebSessionManager;
    }

    /**
     * 设置记住我管理器
     *
     * @return
     */
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        /**
         * rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
         */
        cookieRememberMeManager.setCipherKey(Base64.decode("3AvVhmFLUs0KTA3Kprsdag=="));

        return cookieRememberMeManager;
    }

    /**
     * cookie setting
     *
     * @return
     */
    public SimpleCookie rememberMeCookie() {
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        cookie.setDomain(domain);
        cookie.setPath(path);
        cookie.setHttpOnly(httpOnly);
        cookie.setMaxAge(maxAge * 24 * 60 * 60);

        return cookie;
    }

    /**
     * thymeleaf 模板引擎和shiro框架整合
     *
     * @return
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        /**
         * shiro核心安全接口
         */
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        /**
         * 身份认证失败跳转到登录页面
         */
        shiroFilterFactoryBean.setLoginUrl(loginUrl);

        /**
         * 权限认证失败跳转到制定页面
         */
        shiroFilterFactoryBean.setUnauthorizedUrl(unauthorizedUrl);

        /**
         * 自定义过滤链
         */
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>(14);
        /**
         * 静态资源设置匿名访问
         */
        filterChainDefinitionMap.put("/favicon.ico**", "anon");
        filterChainDefinitionMap.put("/huoguo.jpg**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/docs/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/ajax/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/smart/**", "anon");
        filterChainDefinitionMap.put("/druid/**", "anon");
        filterChainDefinitionMap.put("/captcha/captchaImage**", "anon");

        /**
         * 退出 logout地址，shiro去清除session
         */
        filterChainDefinitionMap.put("/logout", "logout");
        /**
         * 不需要拦截的访问
         */
        filterChainDefinitionMap.put("/login", "anon,captchaValidate");

        /**
         * 添加自定义过滤器
         */
        Map<String, Filter> filters = new LinkedHashMap<>();
        filters.put("onlineSession", onlineSessionFilter());
        filters.put("syncOnlineSession", syncOnlineSessionFilter());
        filters.put("captchaValidate", captchaValidateFilter());

        filters.put("logout", logoutFilter());
        shiroFilterFactoryBean.setFilters(filters);

        filterChainDefinitionMap.put("/**", "user,onlineSession,syncOnlineSession");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 退出过滤器
     */
    public LogoutFilter logoutFilter() {
        LogoutFilter logoutFilter = new LogoutFilter();
        logoutFilter.setLoginUrl(loginUrl);
        return logoutFilter;
    }

    /**
     * 自定义在线用户过滤器
     *
     * @return
     */
    @Bean
    public OnlineSessionFilter onlineSessionFilter() {
        OnlineSessionFilter sessionFilter = new OnlineSessionFilter();
        sessionFilter.setLoginUrl(loginUrl);
        return sessionFilter;
    }

    /**
     * 自定义在线用户同步过滤器
     *
     * @return
     */
    @Bean
    public SyncOnlineSessionFilter syncOnlineSessionFilter() {
        return new SyncOnlineSessionFilter();
    }

    /**
     * 验证码
     *
     * @return
     */
    @Bean
    public CaptchaValidateFilter captchaValidateFilter() {
        CaptchaValidateFilter captchaValidateFilter = new CaptchaValidateFilter();
        captchaValidateFilter.setCaptchaEnabled(captchaEnabled);
        captchaValidateFilter.setCaptchaType(captchaType);
        return captchaValidateFilter;
    }

    /**
     * 开启注解通知
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager")
                                                                                           SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}