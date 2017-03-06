package com.springapp.mvc.dao;

import com.rapid.framework.page.Page;
import com.springapp.mvc.model.Tuser;
import com.springapp.mvc.query.HelloQuery;
import javacommon.base.BaseHibernateDao;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class TuserDao extends BaseHibernateDao<Tuser, Integer> {
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 就因为在配置文件里少写了一个<mvc:annotation-driven />，导致无法初始化HibernateDaoSupport里的sessionFactory, 所以手动加了下面这个方法；
     * 而且导致在Controller里调用BaseManager里的部分方法无效，这里所说的部分方法是指BaseManager里那些没有手动开启和提交事务的方法，比如：
     * update, delete等等，而batchSave等方法手动开启了事务就可以。
     * java.lang.IllegalArgumentException: 'sessionFactory' or 'hibernateTemplate' is required
     */
//    @Resource(name = "sessionFactory")
//    private void setHibernateSessionFactory(SessionFactory sessionFactory) {
//        super.setSessionFactory(sessionFactory);
//    }

    public Page<Tuser> findPage(HelloQuery query) {
        String sql = "select t from Tuser t where 1=1 "
                + "/~ and t.userid = {userid} ~/"
                + "/~ and t.username like '%[username]%' ~/"
                + "/~ and t.usercount = {usercount} ~/"
                + "/~ order by [sortColumns] ~/";
        return pageQuery(sql,query);
    }

    @Override
    public Class<Tuser> getEntityClass() {
        return Tuser.class;
    }

    public Tuser findOne() {
        String hql = "select t from Tuser t order by t.userid desc";
        Query Q = sessionFactory.getCurrentSession().createQuery(hql);
        Q.setMaxResults(1);

        List<Tuser> list = Q.list();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }


    public List<Map<String, Object>> findlist() {
        String sql = "select t.userid, t.username from t_user t";
        Query Q = sessionFactory.getCurrentSession().createSQLQuery(sql);
        return Q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
    }

    public int executeSQL() {
        String sql = "update t_user set username = :username";
        Query Q = sessionFactory.getCurrentSession().createSQLQuery(sql);
        Q.setParameter("username", "孙中山");
        return Q.executeUpdate();
    }

    public void addUsercount() {
        String sql = "update t_user set usercount = usercount + 1";
        Query Q = sessionFactory.getCurrentSession().createSQLQuery(sql);
        Q.executeUpdate();
    }
}
