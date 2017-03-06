package com.springapp.mvc.dao;

import com.rapid.framework.page.Page;
import com.springapp.mvc.model.Tshop;
import com.springapp.mvc.query.TshopQuery;
import javacommon.base.BaseHibernateDao;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2015/9/20.
 */
@Repository
public class TshopDao extends BaseHibernateDao<Tshop, Integer> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Class<Tshop> getEntityClass() {
        return Tshop.class;
    }

    public Page<Tshop> findPage(TshopQuery query) {
        String sql = "select t from Tshop t left join t.tuser s where 1=1 "
                + "/~ and t.shopid = {shopid} ~/"
                + "/~ and t.shopname like '%[shopname]%' ~/"
                + "/~ and t.userid = {userid} ~/"
                + "/~ and s.username like '%[username]%' ~/"
                + "/~ and s.usercount = {usercount} ~/"
                + "/~ order by [sortColumns] ~/";
        return pageQuery(sql,query);
    }

    public Page find(TshopQuery query) {
        String sql = "select t.shopid, t.shopname, t.userid, u.username, u.usercount from t_shop t left join t_user u on t.userid = u.userid"
                + " where 1 = 1 "
                + "/~ and t.shopid = {shopid} ~/"
                + "/~ and t.shopname like '%[shopname]%' ~/"
                + "/~ and t.userid = {userid} ~/"
                + "/~ and u.username like '%[username]%' ~/"
                + "/~ and u.usercount = {usercount} ~/"
                + "/~ order by [sortColumns] ~/";

        return pageSQLQuery(sql, query);
//        sql = new XsqlBuilder().generateHql1(sql, query).getXsql();
//        SQLQuery selectQuery = sessionFactory.getCurrentSession().createSQLQuery(sql);
//        selectQuery.setProperties(query);
//
//        // 获取查询总数对象
//        String sumSQL = "select count(*) " + SqlRemoveUtils.removeSelect(SqlRemoveUtils.removeOrders(sql));
//        sumSQL = new XsqlBuilder().generateHql1(sumSQL, query).getXsql();
//        SQLQuery countQuery = sessionFactory.getCurrentSession().createSQLQuery(sumSQL);
//        countQuery.setProperties(query);
//
//        // 分页
//        int start_row = query.getPageNumber() > 0 ? (query.getPageNumber() - 1) * query.getPageSize() : 0;
//        selectQuery.setFirstResult(start_row);
//        selectQuery.setMaxResults(query.getPageSize());
//
//        // 总数
//        int totalCount = ((Number) countQuery.uniqueResult()).intValue();
//
//        // 创建Page对象
//        Page<Map<String, Object>> page = new Page<Map<String, Object>>(query.getPageNumber(), query.getPageSize(), totalCount);
//        page.setResult(selectQuery.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list());
//
//        return page;
    }

    /**
     * 下面这种写法就很臃肿了，分页的话，两个Query都要一一判断和赋值，很麻烦的，而且在给like参数赋值的时候还要手动加上%，
     * 所以一般只有在查询整个List的时候才这么写，不用分页，直接setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
     * 如果是分页的话，还是建议使用上面的方式。当然，在包含有子查询的时候，是没办法用XsqlBuilder从sql语句得到count语句的，这时候就要自行构造count sql，然后使用
     * public Page pageSQLQuery(final String sql, final String count, PageRequest pageRequest)
     * 方法来分页查询，见find3;
     */
    public Page find2(TshopQuery query) {
        String sql = "select t.shopid, t.shopname, t.userid, u.username, u.usercount from t_shop t left join t_user u on t.userid = u.userid where 1 = 1";
        if (query.getShopid() != null) {
            sql += " and t.shopid = :shopid";
        }
        if (StringUtils.isNotBlank(query.getShopname())) {
            sql += " and t.shopname like :shopname";
        }
        if (query.getUsercount() != null) {
            sql += " and u.usercount = :usercount";
        }
        if (StringUtils.isNotBlank(query.getUsername())) {
            sql += " and u.username like :username";
        }
        if (StringUtils.isNotBlank(query.getSortColumns())) {
            sql += " order by " + query.getSortColumns();
        }

        Query selectQuery = sessionFactory.getCurrentSession().createSQLQuery(sql);
        if (query.getShopid() != null) {
            selectQuery.setParameter("shopid", query.getShopid());
        }
        if (StringUtils.isNotBlank(query.getShopname())) {
            selectQuery.setParameter("shopname", replaceWildcard(query.getShopname()));
        }
        if (query.getUsercount() != null) {
            selectQuery.setParameter("usercount", query.getUsercount());
        }
        if (StringUtils.isNotBlank(query.getUsername())) {
            selectQuery.setParameter("username", replaceWildcard(query.getUsername()));
        }

        String count = "select count(0) from t_shop t left join t_user u on t.userid = u.userid where 1 = 1";
        if (query.getShopid() != null) {
            count += " and t.shopid = :shopid";
        }
        if (StringUtils.isNotBlank(query.getShopname())) {
            count += " and t.shopname like :shopname";
        }
        if (query.getUsercount() != null) {
            count += " and u.usercount = :usercount";
        }
        if (StringUtils.isNotBlank(query.getUsername())) {
            count += " and u.username like :username";
        }
        Query countQuery = sessionFactory.getCurrentSession().createSQLQuery(count);
        if (query.getShopid() != null) {
            countQuery.setParameter("shopid", query.getShopid());
        }
        if (StringUtils.isNotBlank(query.getShopname())) {
            countQuery.setParameter("shopname", replaceWildcard(query.getShopname()));
        }
        if (query.getUsercount() != null) {
            countQuery.setParameter("usercount", query.getUsercount());
        }
        if (StringUtils.isNotBlank(query.getUsername())) {
            countQuery.setParameter("username", replaceWildcard(query.getUsername()));
        }

        return pageSQLQuery(selectQuery, countQuery, query);
    }

    public Page find3(TshopQuery query) {
        String sql = "select t.shopid, t.shopname, t.userid, u.username, u.usercount from t_shop t left join t_user u on t.userid = u.userid"
                + " where 1 = 1 "
                + "/~ and t.shopid = {shopid} ~/"
                + "/~ and t.shopname like '%[shopname]%' ~/"
                + "/~ and t.userid = {userid} ~/"
                + "/~ and u.username like '%[username]%' ~/"
                + "/~ and u.usercount = {usercount} ~/"
                + "/~ order by [sortColumns] ~/";
        String count = "select count(0) from t_shop t left join t_user u on t.userid = u.userid"
                + " where 1 = 1 "
                + "/~ and t.shopid = {shopid} ~/"
                + "/~ and t.shopname like '%[shopname]%' ~/"
                + "/~ and t.userid = {userid} ~/"
                + "/~ and u.username like '%[username]%' ~/"
                + "/~ and u.usercount = {usercount} ~/"
                + "/~ order by [sortColumns] ~/";
        return pageSQLQuery(sql, count, query);
    }
}
