package by.dmitrui98.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;
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
        transaction = session.beginTransaction();
        return session;
    }

    public void closeSession() {
        session.close();
    }

    public void closeTransactionSession() {
        if (transaction.getStatus().equals(TransactionStatus.ACTIVE))
            transaction.commit();
        closeSession();
    }
}
