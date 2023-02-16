package com.petrenko.repository.HibernateRepository;

import com.petrenko.config.HibernateFactoryUtil;
import com.petrenko.model.Car;
import com.petrenko.repository.Crud;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class HibernateCarRepository implements Crud<Car> {
    private static HibernateCarRepository instance;

    private HibernateCarRepository() {
    }

    public static HibernateCarRepository getInstance() {
        if (instance == null) {
            instance = new HibernateCarRepository();
        }
        return instance;
    }
    @Override
    public void save(Car car) {
        final SessionFactory sessionFactory = HibernateFactoryUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(car);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Car> getAll() {
        final SessionFactory sessionFactory = HibernateFactoryUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Car> carList = session.createQuery("from Car", Car.class)
                .list();
        session.close();
        return carList;
    }

    @Override
    public Optional<Car> getByUuid(String id) {
        final SessionFactory sessionFactory = HibernateFactoryUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Car car = session.createQuery("from Car as p where p.id = :name", Car.class)
                .setParameter("name", id)
                .uniqueResult();
        session.close();
        return Optional.ofNullable(car);
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
