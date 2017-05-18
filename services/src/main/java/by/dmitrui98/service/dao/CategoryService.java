package by.dmitrui98.service.dao;

import by.dmitrui98.entity.Category;

/**
 * Created by Администратор on 29.04.2017.
 */
public interface CategoryService extends BaseService<Category, Integer> {

    void removeCascade(Integer id);
}
