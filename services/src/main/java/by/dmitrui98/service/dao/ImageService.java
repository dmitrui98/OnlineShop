package by.dmitrui98.service.dao;

import by.dmitrui98.entity.Image;

/**
 * Created by Администратор on 09.05.2017.
 */
public interface ImageService extends BaseService<Image, Long> {
    Image save(byte[] bytes);
    Image getDefaultImage();
}
