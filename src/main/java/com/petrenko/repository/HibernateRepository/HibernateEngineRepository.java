package com.petrenko.repository.HibernateRepository;

import com.petrenko.config.HibernateFactoryUtil;
import com.petrenko.model.Engine;
import com.petrenko.repository.Crud;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class HibernateEngineRepository implements Crud<Engine> {
    private static HibernateEngineRepository instance;

    private HibernateEngineRepository() {
    }

    public static HibernateEngineRepository getInstance() {
        if (instance == null) {
            instance = new HibernateEngineRepository();
        }
        return instance;
    }
    @Override
    public void save(Engine engine) {
        final SessionFactory sessionFactory = HibernateFactoryUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(engine);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Engine> getAll() {
        final SessionFactory sessionFactory = HibernateFactoryUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Engine> engineList = session.createQuery("from Engine", Engine.class)
                .list();
        session.close();
        return engineList;
    }

    @Override
    public Optional<Engine> getByUuid(String id) {
        final SessionFactory sessionFactory = HibernateFactoryUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Engine engine = session.createQuery("from Engine as e where e.id = :name", Engine.class)
                .setParameter("name", id)
                .uniqueResult();
        session.close();
        return Optional.ofNullable(engine);
    }

    @Override
    public void deleteByUuid(String id) {
        getByUuid(id).ifPresent(o -> {
            final SessionFactory sessionFactory = HibernateFactoryUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.remove(o);
            session.getTransaction().commit();
            session.close();
        });
    }
}
