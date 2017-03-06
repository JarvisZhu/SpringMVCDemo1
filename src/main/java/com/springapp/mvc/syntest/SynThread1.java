package com.springapp.mvc.syntest;

import com.springapp.mvc.service.TuserManager;

/**
 * Created by Administrator on 2015/9/18.
 */
public class SynThread1 implements Runnable {
    private TuserManager tuserManager;

    public SynThread1(TuserManager tuserManager) {
        this.tuserManager = tuserManager;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            tuserManager.synTest1();
        }
    }
}
