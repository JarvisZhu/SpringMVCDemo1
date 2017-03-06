package com.springapp.mvc.controller;

import com.springapp.mvc.service.TuserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Jarvis on 2015/9/19.
 */
@Controller
@RequestMapping(value = "/rapidtag")
public class RapidTagController {

    @Autowired
    private TuserManager tuserManager;


    @RequestMapping(value = "/inter")
    public String inter(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        return "/rapidtag/index";
    }
}
