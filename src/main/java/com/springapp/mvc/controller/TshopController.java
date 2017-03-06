package com.springapp.mvc.controller;

import com.rapid.framework.page.Page;
import com.springapp.mvc.model.Tshop;
import com.springapp.mvc.query.TshopQuery;
import com.springapp.mvc.service.TshopManager;
import javacommon.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2015/9/20.
 */
@Controller
@RequestMapping(value = "/tshop")
public class TshopController extends BaseController<Tshop, Integer>{
    @Autowired
    private TshopManager tshopManager;

    @RequestMapping(value = "/page")
    public String page(ModelMap model, TshopQuery query, HttpServletRequest request, HttpServletResponse response) {
        Page<Tshop> page = tshopManager.findPage(query);
        model.addAllAttributes(toModelMap(page, query));
        return "/tshop/list";
    }

    @RequestMapping(value = "/sqlpage")
    public String sqlpage(ModelMap model, TshopQuery query, HttpServletRequest request, HttpServletResponse response) {
        Page page = tshopManager.find(query);
        model.addAllAttributes(toModelMap(page, query));
        return "/tshop/sqllist";
    }

    @RequestMapping(value = "/sqlpage2")
    public String sqlpage2(ModelMap model, TshopQuery query, HttpServletRequest request, HttpServletResponse response) {
        Page page = tshopManager.find2(query);
        model.addAllAttributes(toModelMap(page, query));
        return "/tshop/sqllist2";
    }

    @RequestMapping(value = "/sqlpage3")
    public String sqlpage3(ModelMap model, TshopQuery query, HttpServletRequest request, HttpServletResponse response) {
        Page page = tshopManager.find3(query);
        model.addAllAttributes(toModelMap(page, query));
        return "/tshop/sqllist3";
    }
}
