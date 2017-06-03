package by.dmitrui98.dao.implementation;

import by.dmitrui98.dao.AdminDao;
import by.dmitrui98.dao.BaseDaoImplTest;
import by.dmitrui98.dao.CategoryDao;
import by.dmitrui98.entity.Admin;
import by.dmitrui98.entity.Category;
import org.hibernate.Session;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Администратор on 30.05.2017.
 */
public class CategoryDaoImplTest extends BaseDaoImplTest {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private AdminDao adminDao;

    @Test
    public void addOrUpdate() throws Exception {
        Category category = createTestCategory();
        long expectedId = 1;

        Category result = categoryDao.addOrUpdate(category);

        assertEquals(expectedId, result.getCategoryId());
        assertEquals(category.getName(), result.getName());
    }

    @Test
    public void delete() throws Exception {
        Category category = createTestCategory();
        Category result = categoryDao.addOrUpdate(category);
        int id = result.getCategoryId();
        categoryDao.delete(id);
        result = categoryDao.getById(id);
        assertNull(result);
    }

    @Test
    public void findAll() throws Exception {
        Category category = createTestCategory();
        Category category1 = new Category("name", admin, new Date(), new Date());

        categoryDao.addOrUpdate(category);
        categoryDao.addOrUpdate(category1);

        List<Category> resultList = categoryDao.findAll();

        assertEquals(2, resultList.size());
    }



    @Test
    public void getByName() throws Exception {
        Category category = createTestCategory();
        categoryDao.addOrUpdate(category);
        Category result = categoryDao.getByName(category.getName());
        assertEquals(category.getName(), result.getName());
    }

    private Category createTestCategory() {
        Category category = new Category("testName", admin, new Date(), new Date());
        return category;
    }

    private Admin admin;
    @Before
    public void before() {
        System.out.println("**********BEFORE**********");
        admin = adminDao.addOrUpdate(new Admin("login", "email", "password", new Date(), new Date()));
    }

    @After
    public void clearTable() {
        System.out.println("**********AFTER**********");

        sessionUtil.openTransactionSession();
        Session session = sessionUtil.getSession();

        session.createNativeQuery("DELETE FROM category").executeUpdate();
        session.createNativeQuery("ALTER TABLE category ALTER COLUMN category_id RESTART WITH 1").executeUpdate();

        session.createNativeQuery("DELETE FROM admin").executeUpdate();
        session.createNativeQuery("ALTER TABLE admin ALTER COLUMN admin_id RESTART WITH 1").executeUpdate();
        admin = null;

        sessionUtil.closeTransactionSession();
    }

}