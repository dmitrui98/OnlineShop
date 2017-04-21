package by.dmitrui98.dao.implementation;

import by.dmitrui98.dao.ProductDao;
import by.dmitrui98.entity.Product;
import by.dmitrui98.util.SessionUtil;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Администратор on 16.04.2017.
 */
@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    SessionUtil sessionUtil;


    @Override
    public void addOrUpdate(Product product) {
        sessionUtil.openTransactionSession();

        Session session = sessionUtil.getSession();
        session.saveOrUpdate(product);

        sessionUtil.closeTransactionSession();

    }

    @Override
    public void delete(Long id) {
        sessionUtil.openTransactionSession();
        Session session = sessionUtil.getSession();
        session.delete(id);
        sessionUtil.closeTransactionSession();
    }

    @Override
    public List<Product> findAll() {
        sessionUtil.openTransactionSession();
        Session session = sessionUtil.getSession();
        List<Product> result = session.createQuery("from Product").list();

        sessionUtil.closeTransactionSession();

        return result;
    }

    @Override
    public Product getById(Long id) {
        sessionUtil.openTransactionSession();
        Session session = sessionUtil.getSession();
        Product result = (Product) session.get(Product.class, id);
        sessionUtil.closeTransactionSession();
        return result;
    }
}
