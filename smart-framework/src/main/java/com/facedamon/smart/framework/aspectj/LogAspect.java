package com.facedamon.smart.framework.aspectj;

import com.alibaba.fastjson.JSON;
import com.facedamon.smart.common.annotation.Log;
import com.facedamon.smart.common.enums.BusinessStatus;
import com.facedamon.smart.common.utils.StringUtils;
import com.facedamon.smart.framework.syn.AsyncFactory;
import com.facedamon.smart.framework.util.ServletUtils;
import com.facedamon.smart.framework.util.ShiroUtils;
import com.facedamon.smart.system.doamin.OperLog;
import com.facedamon.smart.system.doamin.User;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @Description:    操作日志记录切面
 * @Author:         facedamon
 * @CreateDate:     2018/10/29 10:15
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/10/29 10:15
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Pointcut("@annotation(com.facedamon.smart.common.annotation.Log)")
    public void logPointCut(){}

    /**
     *
     * @param point
     */
    @AfterReturning(pointcut = "logPointCut()")
    public void afterReturning(JoinPoint point){
        handleLog(point, null);
    }

    /**
     * 异常拦截
     * @param poin
     * @param e
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void afterThrowing(JoinPoint poin, Exception e) {
        handleLog(poin, e);
    }

    protected void handleLog(final JoinPoint point,final Exception e){
        try {
            Log controllerLog = getAnnotation(point);
            if (null == controllerLog){
                return;
            }
            User user = ShiroUtils.getUser();

            OperLog.OperLogBuilder operLogBuilder = OperLog.builder()
                    .status(BusinessStatus.SUCCESS.ordinal())
                    .operIp(ShiroUtils.getIp())
                    .operUrl(ServletUtils.getRequest().getRequestURI());

            if (null != user){
                operLogBuilder.operName(user.getLoginName());
                if (null != user.getDept() && StringUtils.isNotBlank(user.getDept().getDeptName())){
                    operLogBuilder.deptName(user.getDept().getDeptName());
                }
            }

            if (null != e){
                operLogBuilder.status(BusinessStatus.FAIL.ordinal())
                        .errorMsg(StringUtils.substring(e.getMessage(),0,2000));
            }

            String className = point.getTarget().getClass().getName();
            String methodName = point.getSignature().getName();
            operLogBuilder.method(className + "." + methodName + "()");

            /**
             * 设置注解参数
             */
            controllerHandler(controllerLog,operLogBuilder);
            /**
             * 保存数据库
             */
            AsyncFactory.INSTANCE.recordOper(operLogBuilder.build());
        }catch (Exception ex){
            log.error("===前置通知异常===");
            log.error("异常信息{}",ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * 获取注解中对方法的描述信息，作用于controller层注解
     * @param llog
     * @param operLogBuilder
     * @throws Exception
     */
    protected void controllerHandler(Log llog,OperLog.OperLogBuilder operLogBuilder) throws Exception{
        operLogBuilder.businessType(llog.businessType().ordinal())
                .model(llog.model())
                .operatorType(llog.operatorType().ordinal());

        if (llog.isSaveRequestData()){
            saveRequestDate(operLogBuilder);
        }
    }

    /**
     * 获取请求的参数，放到Log中
     * @param operLogBuilder
     * @throws Exception
     */
    protected void saveRequestDate(OperLog.OperLogBuilder operLogBuilder) throws Exception{
        Map<String,String[]> map = ServletUtils.getRequest().getParameterMap();
        String params = JSON.toJSONString(map);
        operLogBuilder.operParam(StringUtils.substring(params,0,255));
    }

    /**
     * 获取Log注解
     * @param point
     * @return
     * @throws Exception
     */
    private Log getAnnotation(JoinPoint point) throws Exception{
        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (null != method){
            return method.getAnnotation(Log.class);
        }
        return null;
    }
}
