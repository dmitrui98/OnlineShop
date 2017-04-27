package by.dmitrui98.dao;

import by.dmitrui98.entity.User;


/**
 * Created by Администратор on 12.04.2017.
 */
public interface UserDao extends BaseDao<User,Long> {
    User getByName(String name);
    User getByEmail(String email);
}
