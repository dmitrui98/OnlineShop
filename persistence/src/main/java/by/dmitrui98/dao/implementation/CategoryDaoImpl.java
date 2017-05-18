package by.dmitrui98.dao.implementation;

import by.dmitrui98.dao.CategoryDao;
import by.dmitrui98.dao.ProductDao;
import by.dmitrui98.entity.Admin;
import by.dmitrui98.entity.Category;
import by.dmitrui98.entity.Product;
import by.dmitrui98.util.SessionUtil;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public void addOrUpdate(Category category) {
        sessionUtil.openTransactionSession();

        Session session = sessionUtil.getSession();
        session.saveOrUpdate(category);

        sessionUtil.closeTransactionSession();
    }

    @Override
    public void delete(Integer id) {
        sessionUtil.openTransactionSession();
        Session session = sessionUtil.getSession();
        Category category = (Category) session.get(Category.class,id);

        Iterator<Product> iterator = category.getProducts().iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            session.delete(product);
        }

        session.delete(category);
        sessionUtil.closeTransactionSession();
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
}
