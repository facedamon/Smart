package com.facedamon.smart.framework.web.exception;

import com.facedamon.smart.common.base.Response;
import com.facedamon.smart.common.exception.TestModelException;
import com.facedamon.smart.framework.util.PermissionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Description:    自定义全局异常处理器
 * @Author:         facedamon
 * @CreateDate:     2018/10/29 15:41
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/10/29 15:41
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@RestControllerAdvice
@Slf4j
public class DefaultExceptionHandler {

    /**
     * 权限认证失败
     * @param e
     * @return
     */
    @ExceptionHandler(AuthorizationException.class)
    public Response handlerAuthorizationException(AuthorizationException e){
        log.error(e.getMessage(),e);
        return Response.error(PermissionUtils.getMsg(e.getMessage()));
    }

    /**
     * 请求方式不支持
     * @param e
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Response handlerException(HttpRequestMethodNotSupportedException e){
        log.error(e.getMessage(),e);
        return Response.error("不支持'" + e.getMethod() + "'");
    }

    /**
     * 运行时异常
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public Response runtimeException(RuntimeException e){
        log.error("运行时异常：",e);
        return Response.error("运行时异常：" + e.getMessage());
    }

    /**
     * 系统异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Response handlerException(Exception e){
        log.error(e.getMessage(),e);
        return Response.error("服务器错误，请联系管理员");
    }

    /**
     * 测试模式
     * @param e
     * @return
     */
    @ExceptionHandler(TestModelException.class)
    public Response testModelException(TestModelException e){
        return Response.error("测试模式，不允许操作");
    }
}
