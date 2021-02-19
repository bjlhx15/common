package com.github.bjlhx15.common.springdemo.springbootjunit.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.AbstractMap;
import java.util.Map;

/**
 * @author lihongxu6
 * @version 1.0
 * @className KaptchaController
 * @description TODO
 * @date 2021-01-08 22:16
 */
@Controller
public class KaptchaController {
    /**
     * 验证码工具
     */
    @Autowired
    DefaultKaptcha defaultKaptcha;

    @RequestMapping("/get")
    public void defaultKaptcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        byte[] captcha = null;
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            // 将生成的验证码保存在session中
            String createText = defaultKaptcha.createText();
            request.getSession().setAttribute("rightCode", createText);
            BufferedImage bi = defaultKaptcha.createImage(createText);
            ImageIO.write(bi, "jpg", out);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        captcha = out.toByteArray();
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ServletOutputStream sout = response.getOutputStream();
        sout.write(captcha);
        sout.flush();
        sout.close();
    }

    /**
     * 校对验证码
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public ResponseEntity check(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        String rightCode = (String) request.getSession().getAttribute("rightCode");
        String tryCode = request.getParameter("tryCode");
        System.out.println("rightCode:" + rightCode + " ———— tryCode:" + tryCode);
        if (!rightCode.equals(tryCode)) {
            return ResponseEntity.ok(new AbstractMap.SimpleEntry(false, "验证码错误,请再输一次!"));
        } else {
            return ResponseEntity.ok(new AbstractMap.SimpleEntry(true, "ok"));
        }
    }
}
