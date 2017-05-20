package by.dmitrui98.service.dao;

import by.dmitrui98.entity.Product;

/**
 * Created by Администратор on 16.04.2017.
 */
public interface ProductService extends BaseService<Product, Long> {
    public void save(Product product, String[] materialIds, String[] percents);
    public void save(Product product, String[] materialIds, String[] percents,
                     String imageDirectory, long imageId);
    void setProductMaterias(Product product, String[] materialIds, String[] percents);
}
