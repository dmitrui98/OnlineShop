package by.dmitrui98.dao.implementation;

import by.dmitrui98.dao.UserDao;
import by.dmitrui98.entity.User;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Администратор on 12.04.2017.
 */
@Repository
@Log4j
public class UserDaoImpl extends BaseDaoImpl<User, Long> implements UserDao {

    public UserDaoImpl() {
        setClazz(User.class);
    }

    @Override
    public User getByName(String username) {
        sessionUtil.openTransactionSession();
        Session session = sessionUtil.getSession();
        Query query = session.createQuery("FROM User u where u.login=:name", User.class);
        query.setParameter("name", username);
        List<User> users = ((List<User>) query.getResultList());
        sessionUtil.closeTransactionSession();

        User user = null;
        if (users.size() > 0) {
            user = users.get(0);
        }

        return user;
    }

    @Override
    public User getByEmail(String email) {
        sessionUtil.openTransactionSession();
        Session session = sessionUtil.getSession();
        Query query = session.createQuery("FROM User u where u.email=:email", User.class);
        query.setParameter("email", email);
        List<User> users = ((List<User>) query.getResultList());
        sessionUtil.closeTransactionSession();

        User user = null;
        if (users.size() > 0) {
            user = users.get(0);
        }

        return user;
    }
}
