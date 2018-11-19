package by.dmitrui98.dao;


import java.util.List;

/**
 * Created by Администратор on 12.04.2017.
 */
public interface BaseDao<T, T_ID> {
    public T addOrUpdate(T entity);
    public  boolean delete(T_ID id);
    public List<T> findAll();
    public T getById(T_ID id);
}
