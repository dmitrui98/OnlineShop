package by.dmitrui98.dao.implementation;

import by.dmitrui98.dao.AdminDao;
import by.dmitrui98.dao.BaseDaoImplTest;
import by.dmitrui98.entity.Admin;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Администратор on 30.05.2017.
 */
@Ignore
public class AdminDaoImplTest extends BaseDaoImplTest {

    @Autowired
    private AdminDao adminDao;

    @Test
    public void addOrUpdate() throws Exception {
        Admin admin = createTestAdmin();
        long expectedId = 1;

        long id = adminDao.addOrUpdate(admin);
        Admin result = adminDao.getById(id);

        assertEquals(expectedId, result.getAdminId());
        assertEquals(admin.getLogin(), result.getLogin());
        assertEquals(admin.getPassword(), result.getPassword());
        assertEquals(admin.getEmail(), result.getEmail());
    }

    @Test
    public void delete() throws Exception {
        Admin admin = createTestAdmin();
        long id = adminDao.addOrUpdate(admin);
        adminDao.delete(id);
        Admin result = adminDao.getById(id);
        assertNull(result);
    }

    @Test
    public void findAll() throws Exception {
        Admin admin = createTestAdmin();
        Admin admin1 = new Admin("login", "email", "password");
        admin1.setCreatedAt(new Date());
        admin1.setUpdatedAt(new Date());

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
        admin.setCreatedAt(new Date());
        admin.setUpdatedAt(new Date());

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
        session.createNativeQuery("ALTER TABLE admin AUTO_INCREMENT=1").executeUpdate();

        sessionUtil.closeTransactionSession();
    }

}