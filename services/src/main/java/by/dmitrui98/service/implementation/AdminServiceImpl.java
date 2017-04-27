package by.dmitrui98.service.implementation;

import by.dmitrui98.dao.AdminDao;
import by.dmitrui98.entity.Admin;
import by.dmitrui98.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Администратор on 20.04.2017.
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminDao adminDao;

    @Override
    public List<Admin> getAll() {
        return adminDao.findAll();
    }

    @Override
    public Admin getById(Long id) {
        return adminDao.getById(id);
    }

    @Override
    public Admin getByName(String name) {
        return adminDao.getByName(name);
    }

    @Override
    public Admin getByEmail(String email) {
        return adminDao.getByEmail(email);
    }

    @Override
    public void save(Admin entity) {
        adminDao.addOrUpdate(entity);
    }

    @Override
    public void remove(Long id) {
        adminDao.delete(id);
    }
}
