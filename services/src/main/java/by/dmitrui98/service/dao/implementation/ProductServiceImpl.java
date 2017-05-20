package by.dmitrui98.service.dao.implementation;

import by.dmitrui98.dao.ProductDao;
import by.dmitrui98.entity.Image;
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
    public void save(Product product, String[] stringMaterialIds, String[] stringPercents) {

        if (product.getProductMaterials() == null)
            setProductMaterias(product, stringMaterialIds, stringPercents);

        this.setDate(product);
        if (product.getImage() == null)
            product.setImage(imageService.getDefaultImage());

        productDao.addOrUpdate(product);

    }

    @Override
    public void save(Product product, String[] materialIds, String[] percents, String imageDirectory, long imageId) {
        if ((product.getProductId() != 0) && (imageDirectory != null)) {

            if (product.getImage() == null) {
                Image image = new Image(imageDirectory);
                image.setImageId(imageId);
                product.setImage(image);
            } else { // если изображение изменено, то удаляем старое изображение
                imageService.remove(imageDirectory);
            }
        }
        save(product, materialIds, percents);
    }

    @Override
    public void setProductMaterias(Product product, String[] stringMaterialIds, String[] stringPercents) {
        if (stringMaterialIds != null && stringPercents != null) {
            int[] materialIds = new int[stringMaterialIds.length];
            double[] percents = new double[stringPercents.length];

            for (int i = 0; i < stringMaterialIds.length; i++) {
                materialIds[i] = Integer.parseInt(stringMaterialIds[i]);
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
            }

            product.setProductMaterials(productMaterials);
        }
    }

    @Override
    public boolean remove(Long id) {
        Product product = productDao.getById(id);

        if (imageService.removeImage(product))
            System.out.println("Изображение: " + product.getImage().getImageDirectory() + " удалено успешно");
        else
            System.err.println("Изображение: " + product.getImage().getImageDirectory() + " не удалено!!!!");

        if (productDao.delete(id))
            return true;
        else
            return false;
    }

    private void setDate(Product product) {
        if (product.getProductId() == 0) {
            product.setCreatedAt(new Date());
            product.setUpdatedAt(new Date());
        } else
            product.setUpdatedAt(new Date());
    }

}
