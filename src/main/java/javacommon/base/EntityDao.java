package javacommon.base;

import org.springframework.dao.DataAccessException;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created by Jarvis on 2015/9/12.
 */
public interface EntityDao<E, PK extends Serializable> {
    /**
     * 用于hibernate.flush(), 有些dao实现不需要实现此类
     */
    public void flush();

    /**
     * 清除缓存
     */
    public void clearSession();

    public E getById(PK id);

    public void deleteById(PK id);

    public void delete(E entity);

    public void save(E entity);

    public void update(E entity);

    public void saveOrUpdate(E entity);

    public void merge(E entity);

    public List<E> findAll();

    public List<E> findAllBy(String propertyName, Object value);

    public E findByProperty(String propertyName, Object value);

    public void batchSave(List<E> list);

    public void batchUpdate(List<E> list);

    public void deleteAll(Collection<E> entities);
}
