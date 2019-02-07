package by.dmitrui98.dao.implementation;

import by.dmitrui98.dao.BaseDao;
import by.dmitrui98.util.SessionUtil;
import lombok.extern.log4j.Log4j;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
@Log4j
public abstract class BaseDaoImpl<T, T_ID extends Serializable> implements BaseDao<T, T_ID> {
    @Autowired
    protected SessionUtil sessionUtil;

    private Class<T> clazz;

    @Override
    public T addOrUpdate(T entity) {
        sessionUtil.openTransactionSession();
        Session session = sessionUtil.getSession();
        session.saveOrUpdate(entity);
        sessionUtil.closeTransactionSession();
        return entity;
    }

    @Override
    public boolean delete(T_ID id) {
        try {
            sessionUtil.openTransactionSession();
            Session session = sessionUtil.getSession();
            T myObject = session.get(clazz, id);
            session.delete(myObject);
            sessionUtil.closeTransactionSession();
            return true;
        } catch (Exception ex) {
            log.error(String.format("Can not delete entity with id %s, class %s", id, clazz), ex);
            return false;
        }
    }

    @Override
    public List<T> findAll() {
        sessionUtil.openTransactionSession();
        Session session = sessionUtil.getSession();
        Criteria criteria = session.createCriteria(clazz).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return (List<T>) criteria.list();
    }

    @Override
    public T getById(T_ID id) {
        sessionUtil.openTransactionSession();
        Session session = sessionUtil.getSession();
        T result = session.get(clazz, id);
        sessionUtil.closeTransactionSession();
        return result;
    }

    protected void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }
}
