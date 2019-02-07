package by.dmitrui98.dao;

import by.dmitrui98.entity.Material;

/**
 * Created by Администратор on 29.04.2017.
 */
public interface MaterialDao extends BaseDao<Material, Long> {
    Material getByName(String name);
}
