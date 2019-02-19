package by.dmitrui98.dao;

import by.dmitrui98.entity.Image;

/**
 * Created by Администратор on 09.05.2017.
 */
public interface ImageDao extends BaseDao<Image, Long>{
    Image save(String directory);

    Image getByPath(String path);
}
