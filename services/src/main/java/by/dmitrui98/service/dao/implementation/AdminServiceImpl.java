package by.dmitrui98.service.dao.implementation;

import by.dmitrui98.dao.AdminDao;
import by.dmitrui98.entity.Admin;
import by.dmitrui98.service.dao.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Администратор on 20.04.2017.
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Override
    public List<Admin> getAll() {
        return adminDao.findAll();
    }

    @Override
    public Admin getById(Integer id) {
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
    public void save(Admin admin) {
        adminDao.addOrUpdate(admin);
    }

    @Override
    public boolean remove(Integer id) {
        return adminDao.delete(id);
    }
}
