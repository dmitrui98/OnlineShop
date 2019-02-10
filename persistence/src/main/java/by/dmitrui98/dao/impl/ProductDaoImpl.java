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
    public boolean delete(Long id) {
        try {
            sessionUtil.openTransactionSession();
            Session session = sessionUtil.getSession();
            Product product = session.get(Product.class, id);

            session.delete(product);
            removeAssociations(product);
            sessionUtil.closeTransactionSession();
            return true;
        } catch (Exception ex) {
            log.error("Can not delete product with id " + id, ex);
            return false;
        }
    }

    private void removeAssociations(Product product) {
        product.getCategory().getProducts().remove(product);

        if (product.getProductMaterials() != null) {
            product.getProductMaterials()
                    .removeIf(productMaterial -> {
                        return productMaterial.getProduct().equals(product);
                    });
        }
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
    public Long getCount() {
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
}
