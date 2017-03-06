package com.springapp.mvc.task.pool;

import com.springapp.mvc.manager.UserManager;
import com.springapp.mvc.service.TuserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2015/9/18.
 */
public class TuserThread extends Thread {
    private Logger log = LoggerFactory.getLogger(TuserThread.class);
    private TuserManager tuserManager;
    private UserManager userManager;

    public TuserThread(TuserManager tuserManager, UserManager userManager) {
        this.tuserManager = tuserManager;
        this.userManager = userManager;
    }

    public void run() {
        log.info("线程开始");

        try {
            tuserManager.addUsercount();
            userManager.syn();
        } catch (Exception e) {

        }

        log.info("线程结束");
    }
}
