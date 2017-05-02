package by.dmitrui98.dao.implementation;

import by.dmitrui98.dao.MateriaDao;
import by.dmitrui98.entity.Materia;
import by.dmitrui98.util.SessionUtil;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Администратор on 29.04.2017.
 */
@Repository
public class MateriaDaoImpl implements MateriaDao {

    @Autowired
    SessionUtil sessionUtil;

    @Override
    public void addOrUpdate(Materia materia) {
        sessionUtil.openTransactionSession();

        Session session = sessionUtil.getSession();
        session.saveOrUpdate(materia);

        sessionUtil.closeTransactionSession();
    }

    @Override
    public void delete(Integer id) {
        sessionUtil.openTransactionSession();
        Session session = sessionUtil.getSession();
        Materia myObject = (Materia) session.load(Materia.class,id);
        session.delete(myObject);
        sessionUtil.closeTransactionSession();
    }

    @Override
    public List<Materia> findAll() {
        sessionUtil.openTransactionSession();
        Session session = sessionUtil.getSession();
        List<Materia> result = session.createQuery("from Materia").list();

        sessionUtil.closeTransactionSession();

        return result;
    }

    @Override
    public Materia getById(Integer id) {
        sessionUtil.openTransactionSession();
        Session session = sessionUtil.getSession();
        Materia result = (Materia) session.get(Materia.class, id);
        sessionUtil.closeTransactionSession();
        return result;
    }
}
