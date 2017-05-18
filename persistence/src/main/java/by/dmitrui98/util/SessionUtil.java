package by.dmitrui98.util;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Администратор on 13.04.2017.
 */
public class SessionUtil {
    private Session session;
    private Transaction transaction;

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return session;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public Session openSession() {
        return sessionFactory.openSession();
    }

    public Session openTransactionSession() {
        session = openSession();
//        session.setFlushMode(FlushMode.MANUAL);
        transaction = session.beginTransaction();
        return session;
    }

    public void closeSession() {
        session.close();
    }

    public void closeTransactionSession() {
//        session.flush();
        transaction.commit();
        closeSession();
    }
}
