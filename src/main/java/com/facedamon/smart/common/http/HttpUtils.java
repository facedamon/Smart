package com.facedamon.smart.common.http;

import com.facedamon.smart.common.constant.Constants;
import com.facedamon.smart.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
* @Description:    Http 工具类
* @Author:         facedamon
* @CreateDate:     2018/8/15 14:46
* @UpdateUser:     facedamon
* @UpdateDate:     2018/8/15 14:46
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
@Slf4j
public class HttpUtils {

    /**
     * 向客户端发送消息
     * @param response
     * @param msg
     */
    public static void renderString(HttpServletResponse response,String msg){
        response.setContentType(HttpConstants.CONTENT_TYPE.getValue());
        response.setCharacterEncoding(Constants.UTF8.getValue());
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.print(msg);
        } catch (IOException e) {
            log.error("renderString {} failed",msg,e);
        }finally {
            if (null != writer){
                writer.close();
            }
        }
    }

    public static boolean isAjaxRequest(HttpServletRequest request){
        String accept = request.getHeader(HttpConstants.HEADER_ACCEPT.getValue());
        if (StringUtils.indexOf(accept,HttpConstants.CONTENT_TYPE.getValue()) != -1){
            return true;
        }
        String xRequestedWith = request.getHeader(HttpConstants.HEADER_X_REQUEST.getValue());
        if (StringUtils.indexOf(xRequestedWith,HttpConstants.HEADER_XML_REQUEST.getValue()) != -1){
            return true;
        }
        String uri = request.getRequestURI();
        if (StringUtils.containsIgnoreCase(uri,Constants.JSON_SUFFIX.getValue()) ||
                StringUtils.containsIgnoreCase(uri,Constants.XML_SUFFIX.getValue())){
            return true;
        }
        String ajax = request.getParameter("__ajax");
        if (StringUtils.containsIgnoreCase(ajax,Constants.JSON.getValue()) ||
                StringUtils.containsIgnoreCase(ajax,Constants.XML.getValue())){
            return true;
        }
        return false;
    }
}
