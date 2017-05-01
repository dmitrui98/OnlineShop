package by.dmitrui98.service.dao.implementation;

import by.dmitrui98.dao.UserDao;
import by.dmitrui98.service.dao.UserService;
import by.dmitrui98.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Администратор on 13.04.2017.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

       // user.setConfirmPassword(null);

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

    @Override
    public User getByName(String username) {
        return userDao.getByName(username);
    }

    @Override
    public User getByEmail(String email) {
        return userDao.getByEmail(email);
    }
}
