package by.dmitrui98.dao.implementation;

import by.dmitrui98.dao.UserDao;
import by.dmitrui98.entity.User;
import by.dmitrui98.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Администратор on 12.04.2017.
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    SessionUtil sessionUtil;


    @Override
    public void addOrUpdate(User user) {
        sessionUtil.openTransactionSession();

        Session session = sessionUtil.getSession();
        session.saveOrUpdate(user);

        sessionUtil.closeTransactionSession();

    }

    @Override
    public void delete(Long id) {
        sessionUtil.openTransactionSession();
        Session session = sessionUtil.getSession();
        session.delete(id);
        sessionUtil.closeTransactionSession();
    }

    @Override
    public List<User> findAll() {
        sessionUtil.openTransactionSession();
        Session session = sessionUtil.getSession();
        List<User> result = session.createQuery("from User").list();

        sessionUtil.closeTransactionSession();

        return result;
    }

    @Override
    public User getById(Long id) {
        sessionUtil.openTransactionSession();
        Session session = sessionUtil.getSession();
        User result = (User) session.get(User.class, id);
        sessionUtil.closeTransactionSession();
        return result;
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
}
