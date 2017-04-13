package by.dmitrui98.service.implementation;

import by.dmitrui98.service.UserService;
import by.dmitrui98.dao.BaseDao;
import by.dmitrui98.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Администратор on 13.04.2017.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    //@Qualifier("by.dmitrui98.dao.implementation.UserDaoImpl")
    BaseDao<User, Long> userDao;

    @Override
    public void save(User user) {
        userDao.addOrUpdate(user);
    }

    @Override
    public void remove(Long id) {
        userDao.delete(id);
    }

    @Override
    public List<User> getAll() {
        return userDao.findAll();
    }

    @Override
    public User getById(Long aLong) {
        return userDao.getById(aLong);
    }
}
