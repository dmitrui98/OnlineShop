package by.dmitrui98.service;

import by.dmitrui98.entity.Admin;

/**
 * Created by Администратор on 20.04.2017.
 */
public interface AdminService extends BaseService<Admin, Long> {
    Admin getByName(String name);

}
