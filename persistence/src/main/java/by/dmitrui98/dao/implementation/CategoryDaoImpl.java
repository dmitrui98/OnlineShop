package by.dmitrui98.dao.implementation;

import by.dmitrui98.dao.CategoryDao;
import by.dmitrui98.entity.Category;
import by.dmitrui98.util.SessionUtil;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
        Category myObject = (Category) session.get(Category.class,id);
        session.delete(myObject);
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
