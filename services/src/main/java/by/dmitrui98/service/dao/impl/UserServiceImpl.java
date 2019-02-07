package by.dmitrui98.service.dao.impl;

import by.dmitrui98.dao.UserDao;
import by.dmitrui98.entity.User;
import by.dmitrui98.service.dao.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by Администратор on 13.04.2017.
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User save(User user) {

        user.setFname(user.getFname().trim());
        user.setLname(user.getLname().trim());
        user.setSurname(user.getSurname().trim());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        return userDao.addOrUpdate(user);
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
