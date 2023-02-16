package com.petrenko.repository.HibernateRepository;

import com.petrenko.config.HibernateFactoryUtil;
import com.petrenko.model.PassengerCar;
import com.petrenko.repository.Crud;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class HibernatePassengerCarRepository implements Crud<PassengerCar> {
    private static HibernatePassengerCarRepository instance;

    private HibernatePassengerCarRepository() {
    }

    public static HibernatePassengerCarRepository getInstance() {
        if (instance == null) {
            instance = new HibernatePassengerCarRepository();
        }
        return instance;
    }
    @Override
    public void save(PassengerCar passengerCar) {
        final SessionFactory sessionFactory = HibernateFactoryUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(passengerCar);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<PassengerCar> getAll() {
        final SessionFactory sessionFactory = HibernateFactoryUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<PassengerCar> passengerCarList = session.createQuery("from PassengerCar", PassengerCar.class)
                .list();
        session.close();
        return passengerCarList;
    }

    @Override
    public Optional<PassengerCar> getByUuid(String id) {
        final SessionFactory sessionFactory = HibernateFactoryUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        PassengerCar passengerCar = session.createQuery("from PassengerCar as p where p.id = :name",
                        PassengerCar.class)
                .setParameter("name", id)
                .uniqueResult();
        session.close();
        return Optional.ofNullable(passengerCar);
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
