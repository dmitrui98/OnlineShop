package by.dmitrui98.dao.implementation;

import by.dmitrui98.dao.AdminDao;
import by.dmitrui98.entity.Admin;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        sessionUtil.openTransactionSession();
        Session session = sessionUtil.getSession();
        Query query = session.createQuery("FROM Admin a where a.login=:name", Admin.class);
        query.setParameter("name", adminName);
        List<Admin> admins = ((List<Admin>) query.getResultList());
        sessionUtil.closeTransactionSession();

        Admin admin = null;
        if (admins.size() > 0) {
            admin = admins.get(0);
        }

        return admin;
    }

    @Override
    public Admin getByEmail(String email) {
        sessionUtil.openTransactionSession();
        Session session = sessionUtil.getSession();
        Query query = session.createQuery("FROM Admin a where a.email=:email", Admin.class);
        query.setParameter("email", email);
        List<Admin> admins = ((List<Admin>) query.getResultList());
        sessionUtil.closeTransactionSession();

        Admin admin = null;
        if (admins.size() > 0) {
            admin = admins.get(0);
        }

        return admin;
    }
}
