package by.dmitrui98.dao.implementation;

import by.dmitrui98.dao.ImageDao;
import by.dmitrui98.entity.Image;
import by.dmitrui98.util.SessionUtil;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public void addOrUpdate(Image entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Long aLong) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Image> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Image getById(Long aLong) {
        throw new UnsupportedOperationException();
    }
}
