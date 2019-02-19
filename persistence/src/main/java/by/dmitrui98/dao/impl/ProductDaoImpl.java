package by.dmitrui98.dao.impl;

import by.dmitrui98.dao.ProductDao;
import by.dmitrui98.entity.Product;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Администратор on 16.04.2017.
 */
@Repository("productDao")
@Log4j
public class ProductDaoImpl extends BaseDaoImpl<Product, Long> implements ProductDao {

    public ProductDaoImpl() {
        setClazz(Product.class);
    }

    @Override
    public boolean remove(Long id) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Product product = session.get(Product.class, id);

            session.delete(product);
            product.getCategory().getProducts().remove(product);
            product.removeAllMaterial();
            return true;
        } catch (Exception ex) {
            log.error("Can not remove product with id " + id, ex);
            return false;
        }
    }

    @Override
    public List<Product> getElements(int from, int count) {
        Session session = sessionFactory.getCurrentSession();
        List<Product> result = session.createQuery("from Product")
                .setFirstResult(from)
                .setMaxResults(count).list();
        return result;
    }

    @Override
    public Long getCount() {
        Session session = sessionFactory.getCurrentSession();
        Object o = session.createQuery("select count(*) from Product").uniqueResult();
        Long count = null;
        if (o != null) {
            count = (Long) o;
        }
        return count;
    }
}
