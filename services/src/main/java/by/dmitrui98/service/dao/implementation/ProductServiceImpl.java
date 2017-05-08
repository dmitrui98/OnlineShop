package by.dmitrui98.service.dao.implementation;

import by.dmitrui98.dao.ProductDao;
import by.dmitrui98.entity.Materia;
import by.dmitrui98.entity.Product;
import by.dmitrui98.entity.ProductMateria;
import by.dmitrui98.service.dao.MateriaService;
import by.dmitrui98.service.dao.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Администратор on 16.04.2017.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;

    @Autowired
    MateriaService materiaService;

    @Override
    public List<Product> getAll() {
        return productDao.findAll();
    }

    @Override
    public Product getById(Long id) {
        return productDao.getById(id);
    }


    @Override
    public void save(Product product) {

        if (product.getProductId() == 0) {
            product.setCreatedAt(new Date());
            product.setUpdatedAt(new Date());
        } else
            product.setUpdatedAt(new Date());

        productDao.addOrUpdate(product);
    }

    @Override
    public void save(Product product, int[] materiaIds, double[] persants) {
        if (product.getProductId() == 0) {
            product.setCreatedAt(new Date());
            product.setUpdatedAt(new Date());
        } else
            product.setUpdatedAt(new Date());

        Set<ProductMateria> productMaterias = new HashSet<>();
        for (int i = 0; i < materiaIds.length; i++) {
            ProductMateria productMateria = new ProductMateria();

            Materia materia = materiaService.getById(materiaIds[i]);

            productMateria.setMateria(materia);
            productMateria.setProduct(product);
            productMateria.setPersantMateria(persants[i]);

            productMaterias.add(productMateria);
        }

        product.setProductMaterias(productMaterias);
        productDao.addOrUpdate(product);

    }

    @Override
    public void remove(Long id) {
        productDao.delete(id);
    }
}
