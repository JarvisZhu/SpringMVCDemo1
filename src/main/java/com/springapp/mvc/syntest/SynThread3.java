package com.springapp.mvc.syntest;

import com.springapp.mvc.manager.UserManager;
import com.springapp.mvc.service.TuserManager;

/**
 * Created by Administrator on 2015/9/18.
 */
public class SynThread3 implements Runnable {
    private UserManager userManager;

    public SynThread3(UserManager userManager) {
        this.userManager = userManager;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            userManager.syn();
        }
    }
}
