package by.dmitrui98.dao;

import by.dmitrui98.entity.Admin;

/**
 * Created by Администратор on 20.04.2017.
 */
public interface AdminDao extends BaseDao<Admin, Integer> {
    Admin getByName(String name);
    Admin getByEmail(String email);
}
