package by.dmitrui98.dao.implementation;

import by.dmitrui98.entity.Image;
import by.dmitrui98.util.SessionUtil;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Администратор on 08.05.2017.
 */
@Repository
public class ImageDao {

    @Autowired
    private SessionUtil sessionUtil;

    public Image save(String directory) {
        Image image = new Image(directory);
        sessionUtil.openTransactionSession();

        Session session = sessionUtil.getSession();
        session.saveOrUpdate(image);

        sessionUtil.closeTransactionSession();

        return image;
    }
}
