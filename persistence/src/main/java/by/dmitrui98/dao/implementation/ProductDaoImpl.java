package by.dmitrui98.dao.implementation;

import by.dmitrui98.dao.ProductDao;
import by.dmitrui98.entity.Category;
import by.dmitrui98.entity.Product;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

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
    public Product addOrUpdate(Product product) {


        sessionUtil.openTransactionSession();

        Session session = sessionUtil.getSession();

        // если не удалить, то hibernate просто увеличит состав, не удалив старые записи
        // TODO убрать эту хрень
        if (product.getProductId() != null) {
            session.createNativeQuery("DELETE FROM product_material" +
                    " where product_id="+product.getProductId()).executeUpdate();
        }

        session.saveOrUpdate(product);
        sessionUtil.closeTransactionSession();

        return product;
    }


    @Override
    // TODO реализовать каскады
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
            log.error("Can not delete product with id " + id, ex);
            return false;
        }
    }

    private void removeAssosiations(Product product) {
        Category category = product.getCategory();
        Set<Product> products = category.getProducts();
        products.remove(product);
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
