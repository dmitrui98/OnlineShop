package by.dmitrui98.dao.implementation;

import by.dmitrui98.dao.MaterialDao;
import by.dmitrui98.dao.ProductDao;
import by.dmitrui98.entity.Material;
import by.dmitrui98.entity.Product;
import by.dmitrui98.entity.ProductMaterial;
import by.dmitrui98.util.SessionUtil;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Администратор on 29.04.2017.
 */
@Repository
public class MaterialDaoImpl implements MaterialDao {

    @Autowired
    SessionUtil sessionUtil;

    @Override
    public void addOrUpdate(Material material) {
        sessionUtil.openTransactionSession();

        Session session = sessionUtil.getSession();
        session.saveOrUpdate(material);

        sessionUtil.closeTransactionSession();
    }

    @Override
    public void delete(Integer id) {
        sessionUtil.openTransactionSession();
        Session session = sessionUtil.getSession();
        Material material = (Material) session.get(Material.class,id);

        Iterator<ProductMaterial> iterator = material.getProductMaterials().iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next().getProduct();
            session.delete(product.getProductId());
        }

        session.delete(material);
        sessionUtil.closeTransactionSession();
    }

    @Override
    public List<Material> findAll() {
        sessionUtil.openTransactionSession();
        Session session = sessionUtil.getSession();
        List<Material> result = session.createQuery("from Material").list();

        sessionUtil.closeTransactionSession();

        return result;
    }

    @Override
    public Material getById(Integer id) {
        sessionUtil.openTransactionSession();
        Session session = sessionUtil.getSession();
        Material result = (Material) session.get(Material.class, id);
        sessionUtil.closeTransactionSession();
        return result;
    }
}
