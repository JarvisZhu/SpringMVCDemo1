package com.springapp.mvc.controller;

import com.springapp.mvc.model.Tuser;
import com.springapp.mvc.service.TuserManager;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "/validation")
public class ValidationController {
    @Autowired
    private TuserManager tuserManager;

    @RequestMapping(value = "/inter")
    public String inter(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        return "/validation/index";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(ModelMap model, Tuser tuser, HttpServletRequest request, HttpServletResponse response) {
        tuserManager.save(tuser);
        return "redirect:/page";
    }
}
