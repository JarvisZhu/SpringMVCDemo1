package com.springapp.mvc.controller;

import com.rapid.framework.page.Page;
import com.springapp.mvc.model.Tuser;
import com.springapp.mvc.query.HelloQuery;
import com.springapp.mvc.service.TuserManager;
import javacommon.base.BaseController;
import javacommon.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/")
public class HelloController extends BaseController<Tuser,Integer> {
    private Logger log = LoggerFactory.getLogger(HelloController.class);

    /**
     * 如果页面传时间类型参数到某个方法并且想要自动封装为对象的Date类型的属性值，那么就需要添加如下代码，否则在将时间字符串转为Date类型时报错。
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
    }

    @Autowired
    private TuserManager tuserManager;


    /**
     * 测试多线程下查出来的同一条记录在内存中是否是同一个对象.
     * 1.测试时需要将Tuser中重写了的hashcode、equals方法注释掉。
     * 2.每次输出的几乎都不一样，则说明同一条记录在多线程环境下，在内存中不是同一个对象。
     */
    @RequestMapping(value = "/multi")
    public void multi(ModelMap model) {
        try {
            System.out.println(Objects.hashCode(tuserManager.getById(1)));

//            for (int i = 0; i < 10; i++) {
//                new Thread(() -> {
//                    System.out.println(Objects.hashCode(tuserManager.getById(1)));
//                }).start();
//            }
        }catch (Exception e) {

        }
    }

    /** 删除 */
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public String delete(ModelMap model,@PathVariable Integer id) {
        tuserManager.deleteById(id);
        return "redirect:/page";
    }

    /** 批量删除 */
    @RequestMapping(value = "/batchDelete", method=RequestMethod.DELETE)
    public String batchDelete(ModelMap model,@RequestParam("items") java.lang.Integer[] items) {
        for (int i = 0; i < items.length; i++) {
            tuserManager.deleteById(items[i]);
        }
        return "redirect:/page";
    }

    /** 测试分页 */
    @RequestMapping(value = "/page")
    public String page(ModelMap model, HelloQuery query, HttpServletRequest request, HttpServletResponse response) {
        Page<Tuser> page = tuserManager.findPage(query);
        model.addAllAttributes(toModelMap(page, query));
        return "/user/list";
    }

    /** 测试request参数传递 */
    @RequestMapping(value = "/requestParam/{param1}")
    public void param(ModelMap model, @PathVariable String param1, HttpServletRequest request, HttpServletResponse response) {
        log.info("param1 = " + param1);
        Enumeration<String> params = request.getParameterNames();
        while (params.hasMoreElements()) {
            String param = params.nextElement();
            log.info(param + " = " + request.getParameter(param));
        }
    }

    /** 测试Ajax调用 */
    @RequestMapping(value = "/intoAjax")
    public String intoAjax(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        return "/user/ajax";
    }
    @RequestMapping(value = "/ajax")
    @ResponseBody
    public Map<String, Object> ajax(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        Map<String, Object> returnM = new HashMap<>();
        returnM.put("code", 0);
        returnM.put("list", tuserManager.findAllBy("username", username));
        return returnM;
    }

    /** 测试在Manager中进行db封装操作 */
    @RequestMapping(value = "/dbInManager")
    public void dbInManager(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        tuserManager.dbInManager();
    }

    /**
     * 测试常用DB方法封装
     */
    @RequestMapping(value = "/db2")
    public void db2(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        List<Tuser> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Tuser tuser = new Tuser();
            tuser.setUsername("字符的");
            tuser.setUsercount(i);
            list.add(tuser);
        }
        tuserManager.batchSave(list);


        List<Tuser> all = tuserManager.findAll();
        if (all != null && !all.isEmpty()) {
            log.info("all size = " + all.size());
            for (Tuser tuser : all) {
                System.out.println(tuser.getUserid() + ":" + tuser.getUsername());
            }
        } else {
            log.info("all size is empty .");
        }

        List<Tuser> allBy = tuserManager.findAllBy("username", "字符的");
        if (allBy != null && !allBy.isEmpty()) {
            log.info("allby size = " + allBy.size());
            for (Tuser tuser : allBy) {
                System.out.println(tuser.getUserid() + ":" + tuser.getUsername());
            }
        } else {
            log.info("allBy size is empty .");
        }


        Tuser tuser = tuserManager.getById(2);
        System.out.println(tuser.getUserid() + ":" + tuser.getUsername());

        tuser.setUsername("更新数据");
        tuserManager.update(tuser);

        try {
            Thread.sleep(10000);
        } catch(Exception e) {
            e.printStackTrace();
        }

        List<Tuser> alllist = tuserManager.findAll();
        for(Tuser t : alllist) {
            t.setUsername("更新" + new Random().nextInt());
        }
        tuserManager.batchUpdate(alllist);

        tuser.setUsername("上岛咖啡");
        tuserManager.merge(tuser);
//        tuserManager.delete(tuser);
    }


    /**
     * 测试数据库
     */
    @RequestMapping(value = "/db")
    public String db(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        Tuser tuser = tuserManager.findOne();
        model.addAttribute("tuser", tuser);

        List<Tuser> tuserlist = tuserManager.findAll();
        model.addAttribute("tuserlist", tuserlist);

        List<Map<String, Object>> list = tuserManager.findlist();
        model.addAttribute("list", list);

        int result = tuserManager.executeSQL();
        model.addAttribute("result", result);

        return "/user/db";
    }


    /**
     * 测试读取配置文件
     */
    @RequestMapping(value = "/config")
    public void config(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        log.info("url = " + Constant.url);
    }

    /**
     * i18n测试
     */
    @RequestMapping(value = "/i18n")
    public String i18n(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        return "/user/i18n";
    }

    /**
     * 测试重定向
     */
    @RequestMapping(value = "/redirect")
    public String redirect(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        return "redirect:/log";
    }

    /**
     * 测试参数自动注入
     */
    @RequestMapping(value = "/param")
    @ResponseBody
    public void param(ModelMap model, HelloQuery query, HttpServletRequest request, HttpServletResponse response) {
        log.info(query.toString());
    }

    /**
     * 测试日志
     */
    @RequestMapping(value = "/log", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        log.info("method = {}, id = {}, fuck = {}", "index", 1, 3);

        model.addAttribute("message", "Hello world!朱德方");
        return "hello";
    }

    /**
     * 测试路径，测试JSTL
     */
    @RequestMapping(value = "/getInfo/{userid}")
    public String getInfo(ModelMap model, @PathVariable int userid, HttpServletRequest request, HttpServletResponse response) {
        model.addAttribute("userid", userid);

        List<String> list = new ArrayList<>();
        list.add("蒋一杰");
        list.add("张佳");
        model.addAttribute("list", list);

        return "/user/userinfo";
    }

    /**
     * 测试引入taglib
     */
    @RequestMapping(value = "/taglib")
    public String taglib(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        return "/index";
    }



    /**
     * 测试表单提交
     */
    @RequestMapping(value = "/into")
    public String into(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        return "/user/insert";
    }
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public void insert(ModelMap model, Tuser tuser, HttpServletRequest request, HttpServletResponse response) {
        tuserManager.save(tuser);
        log.info("insert .....");
    }
}