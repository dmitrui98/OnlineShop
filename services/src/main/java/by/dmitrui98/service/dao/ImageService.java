package by.dmitrui98.service.dao;

import by.dmitrui98.entity.Image;
import by.dmitrui98.entity.Product;

/**
 * Created by Администратор on 09.05.2017.
 */
public interface ImageService {
    Image write(byte[] bytes);

    byte[] read(String imageName);

    boolean remove(String serverDirectory);

    /**
     * получает изображение по умолчанию из базы. Если такого нет, создает transient объект
     *
     * @return default image
     */
    Image getDefaultImage();

    boolean removeImage(Product product);

    Image getImageByPath(String defaultImagePath);
}
