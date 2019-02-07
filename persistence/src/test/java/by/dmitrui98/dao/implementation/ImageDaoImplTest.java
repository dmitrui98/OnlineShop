package by.dmitrui98.dao.implementation;

import by.dmitrui98.dao.BaseDaoImplTest;
import by.dmitrui98.dao.ImageDao;
import by.dmitrui98.entity.Image;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Администратор on 30.05.2017.
 */
public class ImageDaoImplTest extends BaseDaoImplTest {

    @Autowired
    private ImageDao imageDao;

    @Test
    public void save() throws Exception {
        String testDirectory = "/testDirectory";
        Image image = imageDao.save(testDirectory);
        assertNotEquals(0L, (long) image.getImageId());
        assertEquals(testDirectory, image.getImageDirectory());
    }


    @Test
    public void addOrUpdate() throws Exception {
        Image image = createTestImage();
        Long expectedId = 1L;

        Image result = imageDao.addOrUpdate(image);

        assertEquals(expectedId, result.getImageId());
        assertEquals(image.getImageDirectory(), result.getImageDirectory());
    }

    @Test
    public void delete() throws Exception {
        Image image = createTestImage();
        Image result = imageDao.addOrUpdate(image);
        long id = result.getImageId();
        imageDao.delete(id);
        result = imageDao.getById(id);
        assertNull(result);
    }

    @Test
    public void findAll() throws Exception {
        Image image = createTestImage();
        Image image1 = new Image("/test1");
        imageDao.addOrUpdate(image);
        imageDao.addOrUpdate(image1);

        List<Image> resultList = imageDao.findAll();

        assertEquals(2, resultList.size());
    }

    private Image createTestImage() {
        Image image = new Image("/test");
        return image;
    }

    @Before
    public void setUp() throws Exception {
        System.out.println("**********BEFORE**********");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("**********AFTER**********");
        sessionUtil.openTransactionSession();
        Session session = sessionUtil.getSession();

        session.createNativeQuery("DELETE FROM image").executeUpdate();
        session.createNativeQuery("ALTER TABLE image ALTER COLUMN image_id RESTART WITH 1").executeUpdate();

        sessionUtil.closeTransactionSession();
    }

}