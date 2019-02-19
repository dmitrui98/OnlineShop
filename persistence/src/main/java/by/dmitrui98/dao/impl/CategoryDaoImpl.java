package by.dmitrui98.dao.impl;

import by.dmitrui98.dao.CategoryDao;
import by.dmitrui98.entity.Category;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * Created by Администратор on 29.04.2017.
 */
@Repository
public class CategoryDaoImpl extends BaseDaoImpl<Category, Integer> implements CategoryDao {

    public CategoryDaoImpl() {
        setClazz(Category.class);
    }

    @Override
    public Category getByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Category category = session.createQuery("FROM Category c where c.name=:name", Category.class)
                .setParameter("name", name)
                .uniqueResult();
        return category;
    }
}
