package by.dmitrui98.service.dao;

import by.dmitrui98.entity.Material;

/**
 * Created by Администратор on 29.04.2017.
 */
public interface MaterialService extends BaseService<Material, Long> {
    void removeCascade(Long id);
    Material getByName(String name);
}
