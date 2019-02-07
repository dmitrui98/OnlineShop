package by.dmitrui98.service.dao.impl;

import by.dmitrui98.dao.ProductDao;
import by.dmitrui98.entity.Image;
import by.dmitrui98.entity.Material;
import by.dmitrui98.entity.Product;
import by.dmitrui98.entity.ProductMaterial;
import by.dmitrui98.service.dao.ImageService;
import by.dmitrui98.service.dao.MaterialService;
import by.dmitrui98.service.dao.ProductService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Администратор on 16.04.2017.
 */
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
    public Product save(Product product, String[] stringMaterialIds, String[] stringPercents) {
        if (product.getProductMaterials() == null)
            setProductMaterias(product, stringMaterialIds, stringPercents);
        return productDao.addOrUpdate(product);

    }

    @Override
    public Product save(Product product, String[] materialIds, String[] percents, String imageDirectory, Long imageId) {
        if ((product.getProductId() != null) && (imageDirectory != null)) {

            if (product.getImage() == null) {
                Image image = new Image(imageDirectory);
                image.setImageId(imageId);
                product.setImage(image);
            } else { // если изображение изменено, то удаляем старое изображение
                imageService.remove(imageDirectory);
            }
        }
        return save(product, materialIds, percents);
    }

    @Override
    public void setProductMaterias(Product product, String[] stringMaterialIds, String[] stringPercents) {
        if (stringMaterialIds != null && stringPercents != null) {
            Long[] materialIds = new Long[stringMaterialIds.length];
            double[] percents = new double[stringPercents.length];

            for (int i = 0; i < stringMaterialIds.length; i++) {
                materialIds[i] = Long.parseLong(stringMaterialIds[i]);
                percents[i] = Double.parseDouble(stringPercents[i]);
            }

            Set<ProductMaterial> productMaterials = new HashSet<>();
            for (int i = 0; i < materialIds.length; i++) {
                ProductMaterial productMaterial = new ProductMaterial();

                Material material = materialService.getById(materialIds[i]);

                productMaterial.setMaterial(material);
                productMaterial.setProduct(product);
                productMaterial.setPercentMaterial(percents[i]);

                productMaterials.add(productMaterial);
                material.setProductMaterials(productMaterials);
            }

            product.setProductMaterials(productMaterials);
        }
    }

    @Override
    public boolean remove(Long id) {
        Product product = productDao.getById(id);
        if (imageService.removeImage(product)) {
            log.info("Изображение: " + product.getImage().getImageDirectory() + " удалено успешно");
        } else {
            // TODO если изображение default, то не выводить сообщение
            log.error("Изображение: " + product.getImage().getImageDirectory() + " не удалено!!!!");
        }
        return productDao.delete(id);
    }

}
