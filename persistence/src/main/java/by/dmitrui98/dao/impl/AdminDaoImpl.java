package by.dmitrui98.dao.impl;

import by.dmitrui98.dao.AdminDao;
import by.dmitrui98.entity.Admin;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * Created by Администратор on 20.04.2017.
 */
@Repository
@Log4j
public class AdminDaoImpl extends BaseDaoImpl<Admin, Integer> implements AdminDao {

    public AdminDaoImpl() {
        setClazz(Admin.class);
    }

    @Override
    public Admin getByName(String adminName) {
        Session session = sessionFactory.getCurrentSession();
        Admin admin = session.createQuery("FROM Admin a where a.login=:name", Admin.class)
                .setParameter("name", adminName)
                .uniqueResult();
        return admin;
    }

    @Override
    public Admin getByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Admin admin = session.createQuery("FROM Admin a where a.email=:email", Admin.class)
                .setParameter("email", email)
                .uniqueResult();
        return admin;
    }
}
