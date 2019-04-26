package persistence.dao;

import domain.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import persistence.utils.HibernateSessionFactory;

import java.util.ArrayList;

public class UserDAO implements DAO<User> {
    @Override
    public void create(User item) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(item);
        tx.commit();
        session.close();
    }

    @Override
    public void update(User item) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(item);
        tx.commit();
        session.close();
    }

    @Override
    public void delete(User item) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(item);
        tx.commit();
        session.close();
    }

    @Override
    public ArrayList<User> getAll() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        ArrayList<User> retUsers =
                (ArrayList<User>) session.createQuery("FROM User", User.class).getResultList();

        session.close();
        return retUsers;
    }
}
