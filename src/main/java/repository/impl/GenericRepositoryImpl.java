package repository.impl;

import Connnection.SessionFactorySingleton;
import org.hibernate.Session;

import org.hibernate.SessionFactory;
import repository.GenericRepository;

import java.util.List;

public class GenericRepositoryImpl<T,ID> implements GenericRepository<T,ID> {
    protected SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    private Class<T> tClass;

    public GenericRepositoryImpl(Class<T> tClass) {
        this.tClass = tClass;
    }

    @Override
    public T save(T t) {
        Session session = sessionFactory.getCurrentSession();
        session.save(t);
        return t;
    }

    @Override
    public void update(T t) {
        Session session = sessionFactory.getCurrentSession();
        session.update(t);
    }

    @Override
    public void delete(T t) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(t);
    }

    @Override
    public void deleteById(ID id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(id);
    }

    @Override
    public List<T> findAll(ID id) {
        Session session = sessionFactory.openSession();
        String query = String.format("from %s", tClass.getSimpleName());
        return session.createQuery(query, tClass).list();
    }
}
