package com.springapp.mvc.controller;

import com.rapid.framework.web.scope.Flash;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2015/9/19.
 */
@Controller
@RequestMapping(value = "/flash")
public class FlashController {

    @RequestMapping(value = "/inter")
    public String inter(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        return "/flash/inter";
    }

    @RequestMapping(value = "/go", method = RequestMethod.POST)
    public String go(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        Flash.current().success("成功");
        return "redirect:/flash/inter";
    }

    @RequestMapping(value = "/go2", method = RequestMethod.POST)
    public String go2(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        Flash.current().error("失败");
        return "/flash/inter";
    }
}
