package com.springapp.mvc.controller;

import com.springapp.mvc.model.Tuser;
import com.springapp.mvc.service.TuserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Jarvis on 2015/9/19.
 */
@Controller
@RequestMapping(value = "/excel")
public class ExcelController {
    @Autowired
    private TuserManager tuserManager;

    @RequestMapping(value = "/inter")
    public String inter(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        return "/excel/inter";
    }

    @RequestMapping(value = "/downjxl")
    public String downjxl(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        List<Tuser> list = tuserManager.findAll();
        model.addAttribute("list", list);
        return "ExportExcelJxl";
    }

    @RequestMapping(value = "/downpoi")
    public String downpoi(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        List<Tuser> list = tuserManager.findAll();
        model.addAttribute("list", list);
        return "ExportExcelPoi";
    }
}
