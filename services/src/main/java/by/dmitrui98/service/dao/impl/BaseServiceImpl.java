package by.dmitrui98.service.dao.impl;

import by.dmitrui98.dao.BaseDao;
import by.dmitrui98.service.dao.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class BaseServiceImpl<T, T_ID> implements BaseService<T, T_ID> {

    @Autowired
    protected BaseDao<T, T_ID> baseDao;

    @Override
    public List<T> getAll() {
        return baseDao.findAll();
    }

    @Override
    public T getById(T_ID id) {
        return baseDao.getById(id);
    }

    @Override
    public T save(T entity) {
        return baseDao.addOrUpdate(entity);
    }

    @Override
    public boolean remove(T_ID id) {
        return baseDao.remove(id);
    }
}
