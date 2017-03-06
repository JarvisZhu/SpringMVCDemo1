package javacommon.base;

import com.rapid.framework.page.Page;
import com.rapid.framework.page.PageRequest;
import com.rapid.framework.xsqlbuilder.SqlRemoveUtils;
//import javacommon.base.page.Page;
//import javacommon.base.page.PageRequest;
import com.rapid.framework.xsqlbuilder.SafeSqlProcesser;
import com.rapid.framework.xsqlbuilder.SafeSqlProcesserFactory;
import com.rapid.framework.xsqlbuilder.XsqlBuilder;
import com.rapid.framework.xsqlbuilder.safesql.DirectReturnSafeSqlProcesser;
import org.hibernate.*;
import org.hibernate.criterion.Expression;
import org.hibernate.dialect.Dialect;
import org.hibernate.impl.SessionFactoryImpl;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.orm.hibernate3.HibernateCallback;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.*;

import static com.rapid.framework.xsqlbuilder.SqlRemoveUtils.removeOrders;


/**
 * Created by Jarvis on 2015/9/12.
 */
public abstract class BaseHibernateDao<E, PK extends Serializable> extends HibernateDaoSupport implements EntityDao<E, PK> {

    public abstract Class<E> getEntityClass();

    @Override
    public void flush() {
        getHibernateTemplate().flush();
    }

    @Override
    public void clearSession() {
        Session session = this.getSession();
        if(session != null){
            session.flush();
            session.clear();
        }
    }

    @Override
    public E getById(PK id) {
        return getHibernateTemplate().get(getEntityClass(), id);
    }

    @Override
    public void deleteById(PK id) {
        E entity = getById(id);
        if (entity == null) {
            return;
        }
        getHibernateTemplate().delete(entity);
    }

    @Override
    public void delete(E entity) {
        getHibernateTemplate().delete(entity);
    }

    @Override
    public void save(E entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    public void update(E entity) {
        getHibernateTemplate().update(entity);
    }

    @Override
    public void saveOrUpdate(E entity) {
        getHibernateTemplate().saveOrUpdate(entity);
    }

    @Override
    public void merge(E entity) {
        getHibernateTemplate().merge(entity);
    }

    @Override
    public List<E> findAll() {
        return getHibernateTemplate().loadAll(getEntityClass());
    }

    @Override
    public List<E> findAllBy(String propertyName, Object value) {
        return (List<E>) getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                return session.createCriteria(getEntityClass()).add(Expression.eq(propertyName, value)).list();
            }
        });
    }

    @Override
    public E findByProperty(String propertyName, Object value) {
        return (E) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                return session.createCriteria(getEntityClass()).add(Expression.eq(propertyName, value)).uniqueResult();
            }
        });
    }

    @Override
    public void batchSave(List<E> list) {
        StatelessSession session = getSessionFactory().openStatelessSession();
        Transaction tx = session.beginTransaction();
        tx.begin();

        for (E e : list) {
            session.insert(e);
        }

        tx.commit();
        session.close();
    }

    @Override
    public void batchUpdate(List<E> list) {
        StatelessSession session = getSessionFactory().openStatelessSession();
        Transaction tx = session.beginTransaction();
        tx.begin();

        for (E e : list) {
            session.update(e);
        }

        tx.commit();
        session.close();
    }

    @Override
    public void deleteAll(Collection<E> entities) {
        getHibernateTemplate().deleteAll(entities);
    }

    /** SQL分页，自行构建select Query和 count Query对象 */
    public Page pageSQLQuery(final Query selectQuery, final Query countQuery, PageRequest pageRequest) {
        // 分页
        int start_row = pageRequest.getPageNumber() > 0 ? (pageRequest.getPageNumber() - 1) * pageRequest.getPageSize() : 0;
        selectQuery.setFirstResult(start_row);
        selectQuery.setMaxResults(pageRequest.getPageSize());

        // 总数
        int totalCount = ((Number) countQuery.uniqueResult()).intValue();

        // 创建Page对象
        Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageRequest.getPageNumber(), pageRequest.getPageSize(), totalCount);
        page.setResult(selectQuery.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list());

        return page;
    }
    /** SQL分页，且自行构造select和count语句，但使用同一个query对象来赋值 */
    public Page pageSQLQuery(final String sql, final String count, PageRequest pageRequest) {
        String selectSQL = new XsqlBuilder().generateHql(sql, pageRequest).getXsql();
        SQLQuery selectQuery = getSessionFactory().getCurrentSession().createSQLQuery(selectSQL);
        selectQuery.setProperties(pageRequest);

        String sumSQL = new XsqlBuilder().generateHql(count, pageRequest).getXsql();
        SQLQuery countQuery = getSessionFactory().getCurrentSession().createSQLQuery(sumSQL);
        countQuery.setProperties(pageRequest);

        // 分页
        int start_row = pageRequest.getPageNumber() > 0 ? (pageRequest.getPageNumber() - 1) * pageRequest.getPageSize() : 0;
        selectQuery.setFirstResult(start_row);
        selectQuery.setMaxResults(pageRequest.getPageSize());

        // 总数
        int totalCount = ((Number) countQuery.uniqueResult()).intValue();

        // 创建Page对象
        Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageRequest.getPageNumber(), pageRequest.getPageSize(), totalCount);
        page.setResult(selectQuery.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list());

        return page;
    }
    /** SQL分页，只需自行构造sql语句，然后这里使用XsqlBuilder来构建count语句，且select Query和count Query对象都使用同一个query对象来赋值 */
    public Page pageSQLQuery(final String sql, final PageRequest pageRequest) {
        String selectSQL = new XsqlBuilder().generateHql(sql, pageRequest).getXsql();
        SQLQuery selectQuery = getSessionFactory().getCurrentSession().createSQLQuery(selectSQL);
        selectQuery.setProperties(pageRequest);

        String sumSQL = "select count(*) " + SqlRemoveUtils.removeSelect(SqlRemoveUtils.removeOrders(selectSQL));
        sumSQL = new XsqlBuilder().generateHql(sumSQL, pageRequest).getXsql();
        SQLQuery countQuery = getSessionFactory().getCurrentSession().createSQLQuery(sumSQL);
        countQuery.setProperties(pageRequest);

        // 分页
        int start_row = pageRequest.getPageNumber() > 0 ? (pageRequest.getPageNumber() - 1) * pageRequest.getPageSize() : 0;
        selectQuery.setFirstResult(start_row);
        selectQuery.setMaxResults(pageRequest.getPageSize());

        // 总数
        int totalCount = ((Number) countQuery.uniqueResult()).intValue();

        // 创建Page对象
        Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageRequest.getPageNumber(), pageRequest.getPageSize(), totalCount);
        page.setResult(selectQuery.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list());

        return page;
    }
    /** 替换字符串中的MySQL通配符，并在字符串首尾加上%通配符以set模糊查询参数 */
    protected static String replaceWildcard(String param) {
        StringBuilder builder = new StringBuilder("%");
        int length = param.length();
        for (int i = 0; i < length; i++) {
            char c = param.charAt(i);
            if (c == '_') {
                builder.append("\\_");
            } else if (c == '%') {
                builder.append("\\%");
            } else {
                builder.append(c);
            }
        }
        builder.append("%");

        return builder.toString();
    }

    /** 以下为HQL分页相关 */
    public Page<E> pageQuery(final String sql, final PageRequest pageRequest) {
        final String countQuery = "select count(*) " + SqlRemoveUtils.removeSelect(SqlRemoveUtils.removeFetchKeyword((sql)));
        return pageQuery(sql, countQuery, pageRequest);
    }

    public Page<E> pageQuery(final String sql, String countQuery, final PageRequest pageRequest) {
        Map otherFilters = new HashMap(1);
        otherFilters.put("sortColumns", pageRequest.getSortColumns());

        XsqlBuilder builder = getXsqlBuilder();

        // 混合使用otherFilters与pageRequest为一个filters使用
        XsqlBuilder.XsqlFilterResult queryXsqlResult = builder.generateHql(sql, otherFilters, pageRequest);
        XsqlBuilder.XsqlFilterResult countQueryXsqlResult = builder.generateHql(countQuery, otherFilters, pageRequest);

        return PageQueryUtils.pageQuery(getHibernateTemplate(), pageRequest, queryXsqlResult, countQueryXsqlResult);
    }

    protected XsqlBuilder getXsqlBuilder() {
        SessionFactoryImpl sf = (SessionFactoryImpl) (getSessionFactory());
        Dialect dialect = sf.getDialect();

        // or SafeSqlProcesserFactory.getMysql();
        SafeSqlProcesser safeSqlProcesser = SafeSqlProcesserFactory.getFromCacheByHibernateDialect(dialect);
        XsqlBuilder builder = new XsqlBuilder(safeSqlProcesser);

        if (builder.getSafeSqlProcesser().getClass() == DirectReturnSafeSqlProcesser.class) {
            System.err.println(BaseHibernateDao.class.getSimpleName()
                    + ".getXsqlBuilder(): 故意报错,你未开启Sql安全过滤,单引号等转义字符在拼接sql时需要转义,不然会导致Sql注入攻击的安全问题，请修改源码使用new XsqlBuilder(SafeSqlProcesserFactory.getDataBaseName())开启安全过滤");
        }
        return builder;
    }

    static class PageQueryUtils {
        private static Page pageQuery(HibernateTemplate template, final PageRequest pageRequest, final XsqlBuilder.XsqlFilterResult queryXsqlResult, final XsqlBuilder.XsqlFilterResult countQueryXsqlResult) {
            return (Page) template.execute(new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException, SQLException {

                    Query query = setQueryParameters(session.createQuery(queryXsqlResult.getXsql()), pageRequest);
                    Query countQuery = setQueryParameters(session.createQuery(removeOrders(countQueryXsqlResult.getXsql())), pageRequest);

                    return executeQueryForPage(pageRequest, query, countQuery);
                }
            });
        }

        private static Object executeQueryForPage(final PageRequest pageRequest, Query query, Query countQuery) {
            Page page = new Page(pageRequest, ((Number) countQuery.uniqueResult()).intValue());
            if (page.getTotalCount() <= 0) {
                page.setResult(new ArrayList(0));
            } else {
                page.setResult(query.setFirstResult(page.getFirstResult()).setMaxResults(page.getPageSize()).list());
            }
            return page;
        }

        public static Query setQueryParameters(Query q, Object params) {
            q.setProperties(params);
            return q;
        }

        public static Query setQueryParameters(Query q, Map params) {
            q.setProperties(params);
            return q;
        }
    }
}
