package by.dmitrui98.dao.impl;

import by.dmitrui98.dao.MaterialDao;
import by.dmitrui98.entity.Material;
import lombok.extern.log4j.Log4j;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * Created by Администратор on 29.04.2017.
 */
// TODO установить имя бина по умолчанию
@Repository("materialDao")
@Log4j
public class MaterialDaoImpl extends BaseDaoImpl<Material, Long> implements MaterialDao {

    public MaterialDaoImpl() {
        setClazz(Material.class);
    }

    @Override
    // TODO разобраться с каскадами
    public boolean delete(Long id) {

        try {
            sessionUtil.openTransactionSession();
            Session session = sessionUtil.getSession();
            Material material = session.get(Material.class, id);

//            if (material.getProductMaterials() != null) {
//                Iterator<ProductMaterial> iterator = material.getProductMaterials().iterator();
//                while (iterator.hasNext()) {
//                    Product product = iterator.next().getProduct();
//                    session.delete(product);
//                    removeAssosiations(product);
//                }
//            }

            session.delete(material);
            sessionUtil.closeTransactionSession();

            return true;
        } catch (Exception ex) {
            log.error("Can not delete material with id " + id, ex);
            return false;
        }
    }

//    private void removeAssosiations(Product product) {
//        Category category = product.getCategory();
//        Set<Product> products = category.getProducts();
//        products.remove(product);
//    }



    @Override
    // TODO разобраться с ленивой инициализацией
    public Material getById(Long id) {
        sessionUtil.openTransactionSession();
        Session session = sessionUtil.getSession();
        Material material = session.get(Material.class, id);

        if (material != null)
            Hibernate.initialize(material.getProductMaterials());

        sessionUtil.closeTransactionSession();

        return material;
    }

    @Override
    public Material getByName(String name) {
        sessionUtil.openTransactionSession();
        Session session = sessionUtil.getSession();
        Material material = session.createQuery("FROM Material m where m.name=:name", Material.class)
                .setParameter("name", name)
                .uniqueResult();
        sessionUtil.closeTransactionSession();
        return material;
    }
}
