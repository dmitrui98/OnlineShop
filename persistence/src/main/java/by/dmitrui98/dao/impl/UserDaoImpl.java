package by.dmitrui98.dao.impl;

import by.dmitrui98.dao.UserDao;
import by.dmitrui98.entity.User;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

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
        User user = session.createQuery("FROM User u where u.login=:name", User.class)
                .setParameter("name", username)
                .uniqueResult();
        sessionUtil.closeTransactionSession();
        return user;
    }

    @Override
    public User getByEmail(String email) {
        sessionUtil.openTransactionSession();
        Session session = sessionUtil.getSession();
        User user = session.createQuery("FROM User u where u.email=:email", User.class)
                .setParameter("email", email)
                .uniqueResult();
        sessionUtil.closeTransactionSession();
        return user;
    }
}
