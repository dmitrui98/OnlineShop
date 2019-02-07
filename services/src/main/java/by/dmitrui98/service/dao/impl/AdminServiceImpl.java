package by.dmitrui98.service.dao.impl;

import by.dmitrui98.dao.AdminDao;
import by.dmitrui98.entity.Admin;
import by.dmitrui98.service.dao.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Администратор on 20.04.2017.
 */
@Service
public class AdminServiceImpl extends BaseServiceImpl<Admin, Integer> implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Override
    public Admin getByName(String name) {
        return adminDao.getByName(name);
    }

    @Override
    public Admin getByEmail(String email) {
        return adminDao.getByEmail(email);
    }

}
