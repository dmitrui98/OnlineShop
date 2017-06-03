package by.dmitrui98.dao.implementation;

import by.dmitrui98.dao.*;
import by.dmitrui98.entity.*;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by Администратор on 02.06.2017.
 */
public class ProductDaoImplTest extends BaseDaoImplTest{
    @Autowired
    private ProductDao productDao;

    @Autowired
    private AdminDao adminDao;
    @Autowired
    private ImageDao imageDao;
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private MaterialDao materialDao;

    @Test
    public void addOrUpdate() throws Exception {
        Product product = createTestProduct();
        long expectedId = 1;

        Product result = productDao.addOrUpdate(product);

        assertEquals(expectedId, result.getProductId());
        assertEquals(product.getName(), result.getName());
    }

    @Test
    public void delete() throws Exception {
        Product product = createTestProduct();
        Product result = productDao.addOrUpdate(product);
        long id = result.getProductId();
        productDao.delete(id);
        result = productDao.getById(id);
        assertNull(result);
    }

    @Test
    public void findAll() throws Exception {
        Product product = createTestProduct();
        Product product1 = createSecondTestProduct();

        productDao.addOrUpdate(product);
        productDao.addOrUpdate(product1);

        List<Product> resultList = productDao.findAll();

        assertEquals(2, resultList.size());
    }

    private int compositionSize = 4;
    private Product createTestProduct() {
        Product product = new Product("name", 10, "описание", new Date(), new Date(), image, category, admin);
        Material[] materials = new Material[compositionSize];
        double[] percents = new double[compositionSize];
        createComposition(materials, percents);
        setComposition(product, materials, percents);
        materialArray = materials;
        percentArray = percents;

        return product;
    }

    private Product createSecondTestProduct() {
        Product product = new Product("name2", 15, "описание11", new Date(), new Date(), image, category, admin);
        setComposition(product, materialArray, percentArray);
        return product;
    }

    private Admin admin;
    private Image image;
    private Category category;
    private Material[] materialArray;
    private double[] percentArray;
    @Before
    public void before() {
        System.out.println("**********BEFORE**********");
        admin = adminDao.addOrUpdate(new Admin("login", "email", "password", new Date(), new Date()));
        image = imageDao.addOrUpdate(new Image("/test"));
        category = categoryDao.addOrUpdate(new Category("категория", admin, new Date(), new Date()));
    }

    @After
    public void clearTable() {
        System.out.println("**********AFTER**********");

        sessionUtil.openTransactionSession();
        Session session = sessionUtil.getSession();

        session.createNativeQuery("DELETE FROM product_material").executeUpdate();

        session.createNativeQuery("DELETE FROM product").executeUpdate();
        session.createNativeQuery("ALTER TABLE product ALTER COLUMN product_id RESTART WITH 1").executeUpdate();

        session.createNativeQuery("DELETE FROM material").executeUpdate();
        session.createNativeQuery("ALTER TABLE material ALTER COLUMN material_id RESTART WITH 1").executeUpdate();

        session.createNativeQuery("DELETE FROM image").executeUpdate();
        session.createNativeQuery("ALTER TABLE image ALTER COLUMN image_id RESTART WITH 1").executeUpdate();

        session.createNativeQuery("DELETE FROM category").executeUpdate();
        session.createNativeQuery("ALTER TABLE category ALTER COLUMN category_id RESTART WITH 1").executeUpdate();

        session.createNativeQuery("DELETE FROM admin").executeUpdate();
        session.createNativeQuery("ALTER TABLE admin ALTER COLUMN admin_id RESTART WITH 1").executeUpdate();

        admin = null;
        image = null;
        category = null;
        materialArray = null;
        percentArray = null;

        sessionUtil.closeTransactionSession();
    }

    private void setComposition(Product product, Material[] materials, double[] percents) {

        Set<ProductMaterial> productMaterials = new HashSet<>();
        for (int i = 0; i < materials.length; i++) {
            ProductMaterial productMaterial = new ProductMaterial();

            Material material = materials[i];

            productMaterial.setMaterial(material);
            productMaterial.setProduct(product);
            productMaterial.setPercentMaterial(percents[i]);

            productMaterials.add(productMaterial);
        }

        product.setProductMaterials(productMaterials);
    }

    private double expectedPercent = 100;
    private void createComposition(Material[] materials, double[] percents) {
        double percent = expectedPercent/compositionSize;
        for (int i = 0; i<materials.length; i++) {
            materials[i] = new Material("material"+(i+1), admin, new Date(), new Date());
            materialDao.addOrUpdate(materials[i]);
            percents[i] = percent;
        }
    }


}