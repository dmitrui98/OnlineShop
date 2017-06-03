package by.dmitrui98.dao.implementation;

import by.dmitrui98.dao.CategoryDao;
import by.dmitrui98.dao.ProductDao;
import by.dmitrui98.entity.Admin;
import by.dmitrui98.entity.Category;
import by.dmitrui98.entity.Product;
import by.dmitrui98.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Администратор on 29.04.2017.
 */
@Repository
public class CategoryDaoImpl implements CategoryDao {

    @Autowired
    SessionUtil sessionUtil;


    @Override
    public Category addOrUpdate(Category category) {
        sessionUtil.openTransactionSession();

        Session session = sessionUtil.getSession();
        session.saveOrUpdate(category);

        sessionUtil.closeTransactionSession();

        return category;
    }

    @Override
    public boolean delete(Integer id) {
        try {
            sessionUtil.openTransactionSession();
            Session session = sessionUtil.getSession();
            Category category = (Category) session.get(Category.class, id);

            Iterator<Product> iterator = category.getProducts().iterator();
            while (iterator.hasNext()) {
                Product product = iterator.next();
                session.delete(product);
            }

            session.delete(category);
            sessionUtil.closeTransactionSession();

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Category> findAll() {
        sessionUtil.openTransactionSession();
        Session session = sessionUtil.getSession();
        List<Category> result = session.createQuery("from Category").list();

        sessionUtil.closeTransactionSession();

        return result;
    }

    @Override
    public Category getById(Integer id) {
        sessionUtil.openTransactionSession();
        Session session = sessionUtil.getSession();
        Category result = (Category) session.get(Category.class, id);
        sessionUtil.closeTransactionSession();
        return result;
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
