package com.facedamon.smart.common.utils;

import com.facedamon.smart.common.constant.Constants;
import com.facedamon.smart.common.constant.HttpConstants;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

/**
 * @Description: Http 工具类
 * @Author: facedamon
 * @CreateDate: 2018/8/15 14:46
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/8/15 14:46
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Slf4j
public class HttpUtils {

    /**
     * 向客户端发送消息
     *
     * @param response
     * @param msg
     */
    public static void renderString(HttpServletResponse response, String msg) {
        response.setContentType(HttpConstants.CONTENT_TYPE.getValue());
        response.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.print(msg);
        } catch (IOException e) {
            log.error("renderString {} failed", msg, e);
        } finally {
            if (null != writer) {
                writer.close();
            }
        }
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {
        String accept = request.getHeader(HttpConstants.HEADER_ACCEPT.getValue());
        if (StringUtils.indexOf(accept, HttpConstants.CONTENT_TYPE.getValue()) != -1) {
            return true;
        }
        String xRequestedWith = request.getHeader(HttpConstants.HEADER_X_REQUEST.getValue());
        if (StringUtils.indexOf(xRequestedWith, HttpConstants.HEADER_XML_REQUEST.getValue()) != -1) {
            return true;
        }
        String uri = request.getRequestURI();
        if (StringUtils.containsIgnoreCase(uri, Constants.JSON_SUFFIX.getValue()) ||
                StringUtils.containsIgnoreCase(uri, Constants.XML_SUFFIX.getValue())) {
            return true;
        }
        String ajax = request.getParameter("__ajax");
        if (StringUtils.containsIgnoreCase(ajax, Constants.JSON.getValue()) ||
                StringUtils.containsIgnoreCase(ajax, Constants.XML.getValue())) {
            return true;
        }
        return false;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            String urlNameString = url + "?" + param;
            log.info("sendPost - {}", urlNameString);
            URL realUrl = new URL(urlNameString);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("contentType", "utf-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            out.print(param);
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            log.info("recv - {}", result);
        } catch (ConnectException e) {
            log.error("调用HttpUtils.sendPost ConnectException, url=" + url + ",param=" + param, e);
        } catch (SocketTimeoutException e) {
            log.error("调用HttpUtils.sendPost SocketTimeoutException, url=" + url + ",param=" + param, e);
        } catch (IOException e) {
            log.error("调用HttpUtils.sendPost IOException, url=" + url + ",param=" + param, e);
        } catch (Exception e) {
            log.error("调用HttpsUtil.sendPost Exception, url=" + url + ",param=" + param, e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                log.error("调用in.close Exception, url=" + url + ",param=" + param, ex);
            }
        }
        return result.toString();
    }
}