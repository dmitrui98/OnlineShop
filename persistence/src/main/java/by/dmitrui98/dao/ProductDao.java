package by.dmitrui98.dao;

import by.dmitrui98.entity.Product;

import java.util.List;

/**
 * Created by Администратор on 16.04.2017.
 */
public interface ProductDao extends BaseDao<Product, Long> {
    /*
        Возвращает элементы с from элемента количеством count
     */
    List<Product> getElements(int from, int count);

    Long getCount();
}