package by.dmitrui98.dao.impl;

import by.dmitrui98.dao.ImageDao;
import by.dmitrui98.entity.Image;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * Created by Администратор on 08.05.2017.
 */
@Repository
@Log4j
public class ImageDaoImpl extends BaseDaoImpl<Image, Long> implements ImageDao {

    public ImageDaoImpl() {
        setClazz(Image.class);
    }

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
    public Image getByPath(String path) {
        sessionUtil.openTransactionSession();
        Session session = sessionUtil.getSession();
        Image image = session.createQuery("FROM Image image where image.imageDirectory=:path", Image.class)
                .setParameter("path", path)
                .uniqueResult();
        sessionUtil.closeTransactionSession();
        return image;
    }
}
