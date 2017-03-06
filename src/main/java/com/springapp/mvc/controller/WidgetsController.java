package com.springapp.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2015/9/20.
 */
@Controller
@RequestMapping(value = "/widgets")
public class WidgetsController {
    private Logger log = LoggerFactory.getLogger(WidgetsController.class);

    @RequestMapping(value = "/interArt")
    public String interArt(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        return "/widgets/artdialog";
    }

    @RequestMapping(value = "/artContent")
    public String artContent(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        return "/widgets/artContent";
    }

    @RequestMapping(value = "/interEditor")
    public String interEditor(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        return "/widgets/editor";
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public String post(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        model.addAttribute("content", request.getParameter("content"));
        return "/widgets/showEditor";
    }
}
