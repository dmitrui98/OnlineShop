package by.dmitrui98.dao.implementation;

import by.dmitrui98.dao.ImageDao;
import by.dmitrui98.entity.Image;
import by.dmitrui98.util.SessionUtil;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Администратор on 08.05.2017.
 */
@Repository
public class ImageDaoImpl implements ImageDao {

    @Autowired
    private SessionUtil sessionUtil;

    @Override
    public Image save(String directory) {
        Image image = new Image(directory);
        sessionUtil.openTransactionSession();

        Session session = sessionUtil.getSession();
        session.saveOrUpdate(image);

        sessionUtil.closeTransactionSession();

        return image;
    }

    @Override
    public Image addOrUpdate(Image image) {
        sessionUtil.openTransactionSession();

        Session session = sessionUtil.getSession();
        session.saveOrUpdate(image);

        sessionUtil.closeTransactionSession();

        return image;

    }

    @Override
    public boolean delete(Long id) {
        try {
            sessionUtil.openTransactionSession();
            Session session = sessionUtil.getSession();
            Image image = (Image) session.get(Image.class, id);
            session.delete(image);
            sessionUtil.closeTransactionSession();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Image> findAll() {
        sessionUtil.openTransactionSession();
        Session session = sessionUtil.getSession();
        List<Image> result = session.createQuery("from Image").list();

        sessionUtil.closeTransactionSession();

        return result;
    }

    @Override
    public Image getById(Long id) {
        sessionUtil.openTransactionSession();
        Session session = sessionUtil.getSession();
        Image result = (Image) session.get(Image.class, id);
        sessionUtil.closeTransactionSession();
        return result;
    }

}
