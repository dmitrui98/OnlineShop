package by.dmitrui98.service;

import java.util.List;

/**
 * Created by Администратор on 13.04.2017.
 */
public interface BaseService<T, T_ID> {
    List<T> getAll();
    T getById(T_ID id);
    T getByName(String name);
    void save(T entity);
    void remove(T_ID entity);

}
