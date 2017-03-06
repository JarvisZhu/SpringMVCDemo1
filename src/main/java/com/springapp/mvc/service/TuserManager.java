package com.springapp.mvc.service;

import com.rapid.framework.page.Page;
import com.springapp.mvc.dao.TuserDao;
import com.springapp.mvc.model.Tuser;
import com.springapp.mvc.query.HelloQuery;
import javacommon.base.BaseManager;
import javacommon.base.EntityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class TuserManager extends BaseManager<Tuser, Integer> {
    @Autowired
    private TuserDao tuserDao;

    @Override
    protected EntityDao<Tuser, Integer> getEntityDao() {
        return this.tuserDao;
    }

    @Transactional(readOnly = true)
    public Page<Tuser> findPage(HelloQuery query) {
        return tuserDao.findPage(query);
    }

    @Transactional(readOnly = true)
    public Tuser findOne() {
        return tuserDao.findOne();
    }

    @Transactional(readOnly = true)
    public List<Map<String, Object>> findlist() {
        return tuserDao.findlist();
    }

    public int executeSQL() {
        return tuserDao.executeSQL();
    }

    public void dbInManager() {
        List<Tuser> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Tuser tuser = new Tuser();
            tuser.setUsername("字符的");
            list.add(tuser);
        }
        batchSave(list);

        List<Tuser> all = findAll();
        if (all != null && !all.isEmpty()) {
            for (Tuser tuser : all) {
                System.out.println(tuser.getUserid() + ":" + tuser.getUsername());
            }
        } else {
        }

        List<Tuser> allBy = findAllBy("username", "字符的");
        if (allBy != null && !allBy.isEmpty()) {
            for (Tuser tuser : allBy) {
                System.out.println(tuser.getUserid() + ":" + tuser.getUsername());
            }
        } else {
        }


        Tuser tuser = getById(1);
        System.out.println(tuser.getUserid() + ":" + tuser.getUsername());

        tuser.setUsername("更新数据");
        update(tuser);
    }

    public void addUsercount() {
        tuserDao.addUsercount();
    }

    public synchronized void synTest1() {
        Tuser tuser = getById(2);
        tuser.setUsercount(tuser.getUsercount() + 1);
        merge(tuser);
    }

    public void synTest3() {
        Tuser tuser = getById(2);
        tuser.setUsercount(tuser.getUsercount() + 1);
        merge(tuser);
    }
}
