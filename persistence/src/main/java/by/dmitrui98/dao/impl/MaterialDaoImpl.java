package by.dmitrui98.dao.impl;

import by.dmitrui98.dao.MaterialDao;
import by.dmitrui98.entity.Material;
import lombok.extern.log4j.Log4j;
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
    public boolean remove(Long id) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Material material = session.get(Material.class, id);
            material.removeAllProductCascade(session);
            session.delete(material);
            return true;
        } catch (Exception ex) {
            log.error("Can not remove material with id " + id, ex);
            return false;
        }
    }


    @Override
    public Material getByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Material material = session.createQuery("FROM Material m where m.name=:name", Material.class)
                .setParameter("name", name)
                .uniqueResult();
        return material;
    }
}
