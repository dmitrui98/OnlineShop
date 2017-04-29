package by.dmitrui98.service.dao;

import by.dmitrui98.entity.Admin;

/**
 * Created by Администратор on 20.04.2017.
 */
public interface AdminService extends BaseService<Admin, Long> {
    Admin getByName(String name);

    Admin getByEmail(String email);

}
