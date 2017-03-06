package com.springapp.mvc.controller;

import com.springapp.mvc.mailer.SendMailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/9/21.
 */
@Controller
@RequestMapping(value = "/mail")
public class MailController {
    @Autowired
    private SendMailer sendMailer;

    @RequestMapping(value = "/send")
    public void send(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        Map param = new HashMap();
        param.put("contents", "朱德方测试邮件");
        param.put("timestamp", Calendar.getInstance().getTime().toString());
        System.out.println(sendMailer.sendConfirmMail("524261314@163.com", param, "标题"));
    }
}
