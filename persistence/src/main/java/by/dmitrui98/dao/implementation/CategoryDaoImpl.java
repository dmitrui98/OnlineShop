package by.dmitrui98.dao.implementation;

import by.dmitrui98.dao.CategoryDao;
import by.dmitrui98.entity.Category;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        sessionUtil.openTransactionSession();
        Session session = sessionUtil.getSession();
        Query query = session.createQuery("FROM Category c where c.name=:name", Category.class);
        query.setParameter("name", name);
        List<Category> categories = ((List<Category>) query.getResultList());
        sessionUtil.closeTransactionSession();

        Category category = null;
        if (categories.size() > 0) {
            category = categories.get(0);
        }

        return category;
    }
}
