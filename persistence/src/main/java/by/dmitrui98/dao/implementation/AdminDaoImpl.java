package by.dmitrui98.dao.implementation;

import by.dmitrui98.dao.AdminDao;
import by.dmitrui98.entity.Admin;
import by.dmitrui98.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Администратор on 20.04.2017.
 */
@Repository
public class AdminDaoImpl implements AdminDao {
    @Autowired
    SessionUtil sessionUtil;


    @Override
    public void addOrUpdate(Admin admin) {
        sessionUtil.openTransactionSession();

        Session session = sessionUtil.getSession();
        session.saveOrUpdate(admin);

        sessionUtil.closeTransactionSession();

    }

    @Override
    public boolean delete(Long id) {

        try {
            sessionUtil.openTransactionSession();
            Session session = sessionUtil.getSession();
            Admin myObject = (Admin) session.get(Admin.class, id);
            session.delete(myObject);
            sessionUtil.closeTransactionSession();

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Admin> findAll() {
        sessionUtil.openTransactionSession();
        Session session = sessionUtil.getSession();
        List<Admin> result = session.createQuery("from Admin").list();

        sessionUtil.closeTransactionSession();

        return result;
    }

    @Override
    public Admin getById(Long id) {
        sessionUtil.openTransactionSession();
        Session session = sessionUtil.getSession();
        Admin result = (Admin) session.get(Admin.class, id);
        sessionUtil.closeTransactionSession();
        return result;
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
