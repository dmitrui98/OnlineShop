package by.dmitrui98.service.dao.implementation;

import by.dmitrui98.dao.CategoryDao;
import by.dmitrui98.entity.Category;
import by.dmitrui98.service.dao.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Администратор on 29.04.2017.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;

    @Override
    public List<Category> getAll() {
        return categoryDao.findAll();
    }

    @Override
    public Category getById(Integer id) {
        return categoryDao.getById(id);
    }

    @Override
    public void save(Category category) {
        if (category.getCategoryId() == 0) {
            category.setCreatedAt(new Date());
            category.setUpdatedAt(new Date());
        } else
            category.setUpdatedAt(new Date());

        categoryDao.addOrUpdate(category);
    }

    @Override
    public void remove(Integer id) {
        categoryDao.delete(id);
    }
}
