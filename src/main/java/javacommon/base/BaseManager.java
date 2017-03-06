package javacommon.base;

import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Transactional
public abstract class BaseManager<E, PK extends Serializable> {
    protected abstract EntityDao<E, PK> getEntityDao();

    public void flush() {
        getEntityDao().flush();
    }

    @Transactional(readOnly = true)
    public void clearSession() {
        getEntityDao().clearSession();
    }

    @Transactional(readOnly = true)
    public E getById(PK id) {
        return getEntityDao().getById(id);
    }

    public void deleteById(PK id) {
        getEntityDao().deleteById(id);
    }

    public void delete(E entity) {
        getEntityDao().delete(entity);
    }

    public void save(E entity) {
        getEntityDao().save(entity);
    }

    public void update(E entity) {
        getEntityDao().update(entity);
    }

    public void saveOrUpdate(E entity) {
        getEntityDao().saveOrUpdate(entity);
    }

    public void merge(E entity) {
        getEntityDao().merge(entity);
    }

    @Transactional(readOnly = true)
    public List<E> findAll() {
        return getEntityDao().findAll();
    }

    @Transactional(readOnly = true)
    public List<E> findAllBy(String propertyName, Object value) {
        return getEntityDao().findAllBy(propertyName, value);
    }

    @Transactional(readOnly = true)
    public E findByProperty(String propertyName, Object value) {
        return getEntityDao().findByProperty(propertyName, value);
    }

    public void batchSave(List<E> list) {
        getEntityDao().batchSave(list);
    }

    public void batchUpdate(List<E> list) {
        getEntityDao().batchUpdate(list);
    }

    public void deleteAll(Collection<E> entities) {
        getEntityDao().deleteAll(entities);
    }
}
