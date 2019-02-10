package by.dmitrui98.service.dao.implementation;

import by.dmitrui98.dao.CategoryDao;
import by.dmitrui98.entity.Category;
import by.dmitrui98.entity.Product;
import by.dmitrui98.service.dao.CategoryService;
import by.dmitrui98.service.dao.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created by Администратор on 29.04.2017.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    private ImageService imageService;

    @Override
    public List<Category> getAll() {
        List<Category> categories = categoryDao.findAll();
        Collections.sort(categories);
        return categories;
    }

    @Override
    public Category getById(Integer id) {
        return categoryDao.getById(id);
    }

    @Override
    public void save(Category category) {
        category.setName(category.getName().toLowerCase());
        categoryDao.addOrUpdate(category);
    }

    @Override
    public boolean remove(Integer id)
    {
        Category category = categoryDao.getById(id);

        if ((category.getProducts().size() == 0) && categoryDao.delete(id))
            return true;
        else
            return false;
    }

    @Override
    public void removeCascade(Integer id) {
        Category category = categoryDao.getById(id);
        for (Product product : category.getProducts())
            imageService.removeImage(product);

        categoryDao.delete(id);
    }

    @Override
    public Category getByName(String name) {
        return categoryDao.getByName(name);
    }
}
