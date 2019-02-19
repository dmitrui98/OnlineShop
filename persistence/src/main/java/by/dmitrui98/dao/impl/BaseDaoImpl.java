package by.dmitrui98.dao.impl;

import by.dmitrui98.dao.BaseDao;
import lombok.extern.log4j.Log4j;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
@Log4j
public abstract class BaseDaoImpl<T, T_ID extends Serializable> implements BaseDao<T, T_ID> {

    @Autowired
    protected SessionFactory sessionFactory;

    private Class<T> clazz;

    @Override
    public T addOrUpdate(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(entity);
        return entity;
    }

    @Override
    public boolean remove(T_ID id) {
        try {
            Session session = sessionFactory.getCurrentSession();
            T myObject = session.get(clazz, id);
            session.delete(myObject);
            return true;
        } catch (Exception ex) {
            log.error(String.format("Can not remove entity with id %s, class %s", id, clazz), ex);
            return false;
        }
    }

    @Override
    public List<T> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(clazz).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return (List<T>) criteria.list();
    }

    @Override
    public T getById(T_ID id) {
        Session session = sessionFactory.getCurrentSession();
        T result = session.get(clazz, id);
        return result;
    }

    protected void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }
}
