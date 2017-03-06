package com.springapp.mvc.manager;

import com.springapp.mvc.service.TuserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2015/9/18.
 */
@Service
public class UserManager {
    @Autowired
    private TuserManager tuserManager;

    public void syn() {
        synchronized (this) {
            tuserManager.synTest3();
        }
    }
}
