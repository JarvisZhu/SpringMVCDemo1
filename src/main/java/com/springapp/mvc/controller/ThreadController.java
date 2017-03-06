package com.springapp.mvc.controller;

import com.springapp.mvc.manager.UserManager;
import com.springapp.mvc.service.TuserManager;
import com.springapp.mvc.syntest.SynThread1;
import com.springapp.mvc.syntest.SynThread2;
import com.springapp.mvc.syntest.SynThread3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 测试结果与 templateTest 中的测试结果相符，更多的测试参见 templateTest;
 */
@Controller
@RequestMapping(value = "/thread")
public class ThreadController {
    @Autowired
    private TuserManager tuserManager;
    @Autowired
    private UserManager userManager;

    /**  */

    /** 在事务注解Manager中同步，无效(不管synchronized关键在加在方法上or方法内部) */
    @RequestMapping(value = "/test1")
    public void test1(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        for(int i = 0; i < 100; i++) {
            new Thread(new SynThread1(tuserManager)).start();
        }
    }


    /** 虽然是在事务注解Manager中同步，但由于并不是将对象查出来更新，而是直接用SQL更新，所以有效 */
    @RequestMapping(value = "/test2")
    public void test2(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        for(int i = 0; i < 100; i++) {
            new Thread(new SynThread2(tuserManager)).start();
        }
    }

    /** 在非事务注解Manager中同步(在该非事务注解Manager中调用带有事务注解的Manager)，有效 */
    @RequestMapping(value = "/test3")
    public void test3(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        for(int i = 0; i < 100; i++) {
            new Thread(new SynThread3(userManager)).start();
        }
    }
}
