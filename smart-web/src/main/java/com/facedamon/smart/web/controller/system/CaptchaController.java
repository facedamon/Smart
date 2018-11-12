package com.facedamon.smart.web.controller.system;

import com.facedamon.smart.web.core.base.BaseController;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Description: 验证码controller
 * @Author: facedamon
 * @CreateDate: 2018/10/29 17:40
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/29 17:40
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Controller
@RequestMapping("/captcha")
public class CaptchaController extends BaseController {

    @Autowired
    private Producer captchaProducer;

    @Autowired
    private Producer captchaProducerMath;

    @GetMapping("/captchaImage")
    public void getKaptchaImage(HttpServletRequest request, HttpServletResponse response) {
        ServletOutputStream out = null;

        try {
            HttpSession session = request.getSession();
            response.setDateHeader("Expires", 0);
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            response.setHeader("Pragma", "no-cache");
            response.setContentType("image/jpeg");

            String type = request.getParameter("type");
            String capStr = "";
            String code = "";
            BufferedImage bi = null;

            if ("math".equals(type)) {
                String capText = captchaProducerMath.createText();
                capStr = capText.substring(0, capText.lastIndexOf("@"));
                code = capText.substring(capText.lastIndexOf("@") + 1);
                bi = captchaProducerMath.createImage(capStr);
            } else if ("char".equals(type)) {
                capStr = code = captchaProducer.createText();
                bi = captchaProducer.createImage(capStr);
            }
            session.setAttribute(Constants.KAPTCHA_SESSION_KEY, code);
            out = response.getOutputStream();
            ImageIO.write(bi, "jpg", out);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != out) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
