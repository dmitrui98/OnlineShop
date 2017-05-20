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

    Image getDefaultImage();

    boolean removeImage(Product product);
}
