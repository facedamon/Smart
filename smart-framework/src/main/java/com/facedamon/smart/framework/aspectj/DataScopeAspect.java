package com.facedamon.smart.framework.aspectj;

import com.facedamon.smart.common.annotation.DataScope;
import com.facedamon.smart.common.base.BaseEntity;
import com.facedamon.smart.common.utils.StringUtils;
import com.facedamon.smart.framework.util.ShiroUtils;
import com.facedamon.smart.system.doamin.Role;
import com.facedamon.smart.system.doamin.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Description:    数据过滤，通过AOP拦截sql
 * @Author:         facedamon
 * @CreateDate:     2018/10/26 15:55
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/10/26 15:55
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Aspect
@Component
public class DataScopeAspect {

    /**
     * 全部数据权限
     */
    public static final String DATA_SCOPE_ALL = "1";

    /**
     * 自定义数据权限
     */
    public static final String DATA_SCOPE_CUSTOM = "2";

    /**
     * 数据权限过滤关键词
     */
    public static final String DATA_SCOPE = "dataScope";

    @Pointcut("@annotation(com.facedamon.smart.common.annotation.DataScope)")
    public void dataScopePointCut(){}

    @Before("dataScopePointCut()")
    public void doBefore(JoinPoint point){

    }

    protected void handleDataScope(final JoinPoint point){
        DataScope dataScope = getAnnotation(point);
        if (null == dataScope){
            return;
        }
        User user = ShiroUtils.getUser();
        if (null != user){
            if (!user.isAdmin()){
                dataScopeFilter(point,user,dataScope.tableAlias());
            }
        }
    }

    /**
     * 数据过滤范围
     * @param point
     * @param user 当前用户
     * @param alias DataScope tableAlias
     */
    public static void dataScopeFilter(JoinPoint point,User user,String alias){
        StringBuffer sql = new StringBuffer();
        for (Role role : user.getRoles()){
            String dataScope = role.getDataScope();
            if (DATA_SCOPE_ALL.equals(dataScope)){
                break;
            }else if (DATA_SCOPE_CUSTOM.equals(dataScope)){
               sql.append(StringUtils.format("OR {}.dept_id IN ( select dept_id from sys_role where role_id = {})"
                    ,alias,role.getRoleId()));
            }
        }

        if (StringUtils.isNotBlank(sql.toString())){
            BaseEntity baseEntity = (BaseEntity) point.getArgs()[0];
            baseEntity.getParams().put(DATA_SCOPE," AND (" + sql.substring(4) + ")");
        }
    }

    /**
     * 获取声明DataScope方法的注解
     * @param point
     * @return
     */
    private DataScope getAnnotation(JoinPoint point){
        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (null != method){
            return method.getAnnotation(DataScope.class);
        }
        return null;
    }
}
