package by.dmitrui98.service.implementation;

import by.dmitrui98.dao.BaseDao;
import by.dmitrui98.entity.Product;
import by.dmitrui98.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Администратор on 16.04.2017.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    BaseDao<Product, Long> productDao;
    @Override
    public List<Product> getAll() {
        return productDao.findAll();
    }

    @Override
    public Product getById(Long id) {
        return productDao.getById(id);
    }

    @Override
    public void save(Product entity) {
        productDao.addOrUpdate(entity);
    }

    @Override
    public void remove(Long id) {
        productDao.delete(id);
    }
}
