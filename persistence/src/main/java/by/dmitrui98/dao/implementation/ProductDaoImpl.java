package by.dmitrui98.dao.implementation;

import by.dmitrui98.dao.MaterialDao;
import by.dmitrui98.dao.ProductDao;
import by.dmitrui98.entity.Category;
import by.dmitrui98.entity.Material;
import by.dmitrui98.entity.Product;
import by.dmitrui98.entity.ProductMaterial;
import by.dmitrui98.util.SessionUtil;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Администратор on 16.04.2017.
 */
@Repository("productDao")
public class ProductDaoImpl implements ProductDao {

    private static final Logger logger = Logger.getLogger(ProductDaoImpl.class);

    @Autowired
    private SessionUtil sessionUtil;

    @Autowired
    @Qualifier("materialDao")
    private MaterialDao materialDao;


    @Override
    public Product addOrUpdate(Product product) {


        sessionUtil.openTransactionSession();

        Session session = sessionUtil.getSession();

        // если не удалить, то hibernate просто увеличит состав, не удалив старые записи
        if (product.getProductId() != 0) {
            session.createNativeQuery("DELETE FROM product_material" +
                    " where product_id="+product.getProductId()).executeUpdate();
        }


        session.saveOrUpdate(product);
        sessionUtil.closeTransactionSession();

        return product;
    }


    @Override
    public boolean delete(Long id) {
        try {
            sessionUtil.openTransactionSession();
            Session session = sessionUtil.getSession();
            Product product = (Product) session.get(Product.class, id);

            session.delete(product);

            this.removeAssosiations(product);

            sessionUtil.closeTransactionSession();
            return true;
        } catch (Exception ex) {
            logger.error("Can not delete product with id " + id, ex);
            return false;
        }
    }

    private void removeAssosiations(Product product) {
        Category category = product.getCategory();
        Set<Product> products = category.getProducts();
        products.remove(product);
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
    public List<Product> getElements(int from, int count) {
        sessionUtil.openTransactionSession();
        Session session = sessionUtil.getSession();
        List<Product> result = session.createQuery("from Product")
                .setFirstResult(from)
                .setMaxResults(count).list();
        sessionUtil.closeTransactionSession();
        return result;
    }

    @Override
    public long getCount() {
        sessionUtil.openTransactionSession();
        Session session = sessionUtil.getSession();
        Object o = session.createQuery("select count(*) from Product").uniqueResult();
        Long count = null;
        if (o != null) {
            count = (Long) o;
        }
        sessionUtil.closeTransactionSession();
        return count;
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
