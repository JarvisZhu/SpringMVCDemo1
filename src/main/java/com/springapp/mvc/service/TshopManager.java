package com.springapp.mvc.service;

import com.rapid.framework.page.Page;
import com.springapp.mvc.dao.TshopDao;
import com.springapp.mvc.model.Tshop;
import com.springapp.mvc.query.TshopQuery;
import javacommon.base.BaseManager;
import javacommon.base.EntityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TshopManager extends BaseManager<Tshop, Integer> {
    @Autowired
    private TshopDao tshopDao;

    @Override
    protected EntityDao<Tshop, Integer> getEntityDao() {
        return this.tshopDao;
    }

    @Transactional(readOnly = true)
    public Page<Tshop> findPage(TshopQuery query) {
        return tshopDao.findPage(query);
    }

    @Transactional(readOnly = true)
    public Page find(TshopQuery query) {
        return tshopDao.find(query);
    }

    @Transactional(readOnly = true)
    public Page find2(TshopQuery query) {
        return tshopDao.find2(query);
    }

    @Transactional(readOnly = true)
    public Page find3(TshopQuery query) {
        return tshopDao.find3(query);
    }
}
