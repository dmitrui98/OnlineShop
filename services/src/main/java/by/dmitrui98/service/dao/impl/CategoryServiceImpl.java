package by.dmitrui98.service.dao.impl;

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
public class CategoryServiceImpl extends BaseServiceImpl<Category, Integer> implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ImageService imageService;

    @Override
    public List<Category> getAll() {
        List<Category> categories = categoryDao.findAll();
        Collections.sort(categories);
        return categories;
    }


    @Override
    public Category save(Category category) {
        category.setName(category.getName().toLowerCase());
        return categoryDao.addOrUpdate(category);
    }

    /**
     * Не удаляет категорию, если имеются продукты данной категории
     *
     * @param id id категории
     * @return boolean удалилась ли категория
     */
    @Override
    public boolean remove(Integer id) {
        Category category = categoryDao.getById(id);
        return (category.getProducts().size() == 0) && categoryDao.delete(id);
    }

    /**
     * удаляет продукты вместе с категориями каскадно,
     * удаляет картинки, связанные с продуктами данной категории
     * @param id id категории
     */
    @Override
    public void removeCascade(Integer id) {
        Category category = categoryDao.getById(id);
        for (Product product : category.getProducts()) {
            imageService.removeImage(product);
        }

        categoryDao.delete(id);
    }

    @Override
    public Category getByName(String name) {
        return categoryDao.getByName(name);
    }
}
