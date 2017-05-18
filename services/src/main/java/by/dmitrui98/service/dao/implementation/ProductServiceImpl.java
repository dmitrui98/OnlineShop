package by.dmitrui98.service.dao.implementation;

import by.dmitrui98.dao.ProductDao;
import by.dmitrui98.entity.Material;
import by.dmitrui98.entity.Product;
import by.dmitrui98.entity.ProductMaterial;
import by.dmitrui98.service.dao.ImageService;
import by.dmitrui98.service.dao.MaterialService;
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
    private ProductDao productDao;

    @Autowired
    private MaterialService materialService;

    @Autowired
    private ImageService imageService;

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
    public void save(Product product, int[] materialIds, double[] persents) {

        this.setDate(product);
        if (product.getImage() == null)
            product.setImage(imageService.getDefaultImage());

        Set<ProductMaterial> productMaterials = new HashSet<>();
        for (int i = 0; i < materialIds.length; i++) {
            ProductMaterial productMaterial = new ProductMaterial();

            Material material = materialService.getById(materialIds[i]);

            productMaterial.setMaterial(material);
            productMaterial.setProduct(product);
            productMaterial.setPersentMaterial(persents[i]);

            productMaterials.add(productMaterial);
        }

        product.setProductMaterials(productMaterials);
        productDao.addOrUpdate(product);

    }



    @Override
    public void remove(Long id) {
//        TODO удалить картинку, прикрепленную к товару
//        TODO удалить картинку, прикрепленную к товару
        productDao.delete(id);
    }

    private void setDate(Product product) {
        if (product.getProductId() == 0) {
            product.setCreatedAt(new Date());
            product.setUpdatedAt(new Date());
        } else
            product.setUpdatedAt(new Date());
    }

}
