package by.dmitrui98.dao;


import java.util.List;

/**
 * Created by Администратор on 12.04.2017.
 */
public interface BaseDao<T, T_ID> {
    T addOrUpdate(T entity);

    boolean remove(T_ID id);

    List<T> findAll();

    T getById(T_ID id);
}
