package by.dmitrui98.service.dao;

import by.dmitrui98.entity.User;

/**
 * Created by Администратор on 13.04.2017.
 */
public interface UserService extends BaseService<User, Long> {
    User getByName(String name);
    User getByEmail(String name);

}
