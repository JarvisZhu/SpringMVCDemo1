package com.springapp.mvc.auto;

import com.springapp.mvc.manager.UserManager;
import com.springapp.mvc.service.TuserManager;
import com.springapp.mvc.task.pool.TuserThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * Created by Administrator on 2015/9/10.
 */
public class ThreadStartServlet extends HttpServlet{
    private Logger log = LoggerFactory.getLogger(getClass());
    private static WebApplicationContext wac;

    @Override
    public void init() throws ServletException {
        super.init();
        wac = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());


        /** 两种方式都OK */
//        TuserManager tuserManager = wac.getBean(TuserManager.class);
//        UserManager userManager = wac.getBean(UserManager.class);

//        TuserManager tuserManager = (TuserManager)wac.getBean("tuserManager");
//        UserManager userManager = (UserManager)wac.getBean("userManager");

//        new TuserThread(tuserManager, userManager).start();
    }

    public static WebApplicationContext getWac(){
        return wac;
    }
}
