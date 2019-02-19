package by.dmitrui98.service.dao.impl;

import by.dmitrui98.dao.ProductDao;
import by.dmitrui98.entity.Image;
import by.dmitrui98.entity.Material;
import by.dmitrui98.entity.Product;
import by.dmitrui98.service.dao.ImageService;
import by.dmitrui98.service.dao.MaterialService;
import by.dmitrui98.service.dao.ProductService;
import lombok.extern.log4j.Log4j;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Администратор on 16.04.2017.
 */
@Transactional
@Service
@Log4j
public class ProductServiceImpl extends BaseServiceImpl<Product, Long> implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private MaterialService materialService;

    @Autowired
    private ImageService imageService;

    @Override
    public List<Product> getElements(int from, int count) {
        return productDao.getElements(from, count);
    }

    @Override
    public long getCount() {
        return productDao.getCount();
    }

    @Override
    public Product save(Product product) {
        return productDao.addOrUpdate(product);

    }

    @Override
    public Product save(Product product, String imageDirectory, Long imageId) {
        if ((product.getProductId() != null) && (imageDirectory != null)) {

            if (product.getImage() == null) {
                Image image = new Image(imageDirectory);
                image.setImageId(imageId);
                product.setImage(image);
            } else { // если изображение изменено, то удаляем старое изображение
                imageService.remove(imageDirectory);
            }
        }
        return save(product);
    }

    @Override
    public void setProductMaterials(Product product, String[] stringMaterialIds, String[] stringPercents) {
        // удаляем весь состав
        product.removeAllMaterial();

        // добавляем в состав переданные материалы и процент материала
        if (stringMaterialIds != null && stringPercents != null) {
            Long[] materialIds = new Long[stringMaterialIds.length];
            double[] percent = new double[stringPercents.length];

            for (int i = 0; i < materialIds.length; i++) {
                materialIds[i] = Long.parseLong(stringMaterialIds[i]);
                percent[i] = Double.parseDouble(stringPercents[i]);

                Material material = materialService.getById(materialIds[i]);
                product.addMaterial(material, percent[i]);
            }
        }
    }

    @Override
    public boolean remove(Long id) {
        Product product = productDao.getById(id);
        imageService.removeImage(product);
        return productDao.remove(id);
    }

    @Override
    public Product getById(Long id) {
        Product product = baseDao.getById(id);
        Hibernate.initialize(product.getProductMaterials());
        return product;
    }
}
