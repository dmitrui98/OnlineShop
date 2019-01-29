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
    private ThreadLocal<Session> session = new ThreadLocal<>();
    private ThreadLocal<Transaction> transaction = new ThreadLocal<>();

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return session.get();
    }

    public Transaction getTransaction() {
        return transaction.get();
    }

    public Session openSession() {
        return sessionFactory.openSession();
    }

    public Session openTransactionSession() {
        Session session = openSession();
        this.session.set(session);
        transaction.set(session.beginTransaction());
        return session;
    }

    public void closeSession() {
        session.get().close();
        session.set(null);
    }

    public void closeTransactionSession() {
        if (transaction.get().getStatus().equals(TransactionStatus.ACTIVE))
            transaction.get().commit();
        closeSession();
    }
}
