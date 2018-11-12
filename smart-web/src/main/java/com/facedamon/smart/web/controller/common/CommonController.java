package com.facedamon.smart.web.controller.common;

import com.facedamon.smart.common.config.SmartConfig;
import com.facedamon.smart.common.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @Description:    通用controller
 * @Author:         facedamon
 * @CreateDate:     2018/11/8 16:08
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/11/8 16:08
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@RequestMapping("/common")
@Controller
@Slf4j
public class CommonController {

    @RequestMapping("/download")
    public void download(String fileName, Boolean delete,
                         HttpServletRequest request, HttpServletResponse response){
        try {
            String filePath = SmartConfig.INSTANCE.getDownloadPath() + fileName;
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition","attachment;fileName="+setFileDownloadHeader(request,fileName));

            FileUtils.writeBytes(filePath,response.getOutputStream());

            if (delete){
                FileUtils.deleteFile(filePath);
            }
        }catch (Exception e){
            log.error("下载文件失败,{}",e.getMessage());
        }
    }

    public String setFileDownloadHeader(HttpServletRequest request, String fileName) throws UnsupportedEncodingException {
        final String agent = request.getHeader("USER-AGENT");
        String filename = fileName;
        if (agent.contains("MSIE")) {
            // IE浏览器
            filename = URLEncoder.encode(filename, "utf-8");
            filename = filename.replace("+", " ");
        } else if (agent.contains("Firefox")) {
            // 火狐浏览器
            filename = new String(fileName.getBytes(), "ISO8859-1");
        } else if (agent.contains("Chrome")) {
            // google浏览器
            filename = URLEncoder.encode(filename, "utf-8");
        } else {
            // 其它浏览器
            filename = URLEncoder.encode(filename, "utf-8");
        }
        return filename;
    }
}
