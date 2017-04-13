package by.dmitrui98.dao;


import javax.persistence.Entity;
import java.util.List;

/**
 * Created by Администратор on 12.04.2017.
 */
public interface BaseDao<T, T_ID> {
    public void addOrUpdate(T entity);
    public  void delete(T_ID id);
    public List<T> findAll();
    public T getById(T_ID id);
}
