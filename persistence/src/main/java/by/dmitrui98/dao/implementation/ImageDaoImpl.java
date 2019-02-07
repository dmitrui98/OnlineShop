package by.dmitrui98.dao.implementation;

import by.dmitrui98.dao.ImageDao;
import by.dmitrui98.entity.Image;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        List<Image> images = session.createQuery("FROM Image image where image.imageDirectory=:path", Image.class)
                .setParameter("imageDirectory", path)
                .getResultList();
        sessionUtil.closeTransactionSession();
        Image image = null;
        if (images.size() > 0) {
            image = images.get(0);
        }
        return image;
    }
}
