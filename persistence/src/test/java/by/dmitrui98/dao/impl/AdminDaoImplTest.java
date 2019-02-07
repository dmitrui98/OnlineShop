package by.dmitrui98.dao.impl;

import by.dmitrui98.dao.AdminDao;
import by.dmitrui98.dao.BaseDaoImplTest;
import by.dmitrui98.entity.Admin;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by Администратор on 30.05.2017.
 */
public class AdminDaoImplTest extends BaseDaoImplTest {

    @Autowired
    private AdminDao adminDao;

    @Test
    public void addOrUpdate() throws Exception {
        Admin admin = createTestAdmin();
        Integer expectedId = 1;

        Admin result = adminDao.addOrUpdate(admin);

        assertEquals(expectedId, result.getAdminId());
        assertEquals(admin.getLogin(), result.getLogin());
        assertEquals(admin.getPassword(), result.getPassword());
        assertEquals(admin.getEmail(), result.getEmail());
    }

    @Test
    public void delete() throws Exception {
        Admin admin = createTestAdmin();
        Admin result = adminDao.addOrUpdate(admin);
        Integer id = result.getAdminId();
        adminDao.delete(id);
        result = adminDao.getById(id);
        assertNull(result);
    }

    @Test
    public void findAll() {
        Admin admin = createTestAdmin();
        Admin admin1 = new Admin("login", "email", "password");

        adminDao.addOrUpdate(admin);
        adminDao.addOrUpdate(admin1);

        List<Admin> resultList = adminDao.findAll();

        assertEquals(2, resultList.size());
    }

    @Test
    public void getByName() throws Exception {
        Admin admin = createTestAdmin();
        adminDao.addOrUpdate(admin);
        Admin result = adminDao.getByName(admin.getLogin());
        assertEquals(admin.getLogin(), result.getLogin());
        assertEquals(admin.getEmail(), result.getEmail());
    }

    @Test
    public void getByEmail() throws Exception {
        Admin admin = createTestAdmin();
        adminDao.addOrUpdate(admin);
        Admin result = adminDao.getByEmail(admin.getEmail());
        assertEquals(admin.getLogin(), result.getLogin());
        assertEquals(admin.getEmail(), result.getEmail());
    }

    private Admin createTestAdmin() {
        Admin admin = new Admin();
        admin.setLogin("testAdminLogin");
        admin.setPassword("testAdminPassword");
        admin.setEmail("testAdminEmail");

        return admin;
    }

    @Before
    public void before() {
        System.out.println("**********BEFORE**********");
    }

    @After
    public void clearTable() {
        System.out.println("**********AFTER**********");
        sessionUtil.openTransactionSession();
        Session session = sessionUtil.getSession();

        session.createNativeQuery("DELETE FROM admin").executeUpdate();
        session.createNativeQuery("ALTER TABLE admin ALTER COLUMN admin_id RESTART WITH 1").executeUpdate();

        sessionUtil.closeTransactionSession();
    }

}