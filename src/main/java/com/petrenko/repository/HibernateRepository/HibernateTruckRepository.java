package com.petrenko.repository.HibernateRepository;

import com.petrenko.config.HibernateFactoryUtil;
import com.petrenko.model.Truck;
import com.petrenko.repository.Crud;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class HibernateTruckRepository implements Crud<Truck> {
    private static HibernateTruckRepository instance;

    private HibernateTruckRepository() {
    }

    public static HibernateTruckRepository getInstance() {
        if (instance == null) {
            instance = new HibernateTruckRepository();
        }
        return instance;
    }
    @Override
    public void save(Truck truck) {
        final SessionFactory sessionFactory = HibernateFactoryUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(truck);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Truck> getAll() {
        final SessionFactory sessionFactory = HibernateFactoryUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Truck> truckList = session.createQuery("from Truck", Truck.class)
                .list();
        session.close();
        return truckList;
    }

    @Override
    public Optional<Truck> getByUuid(String id) {
        final SessionFactory sessionFactory = HibernateFactoryUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Truck truck = session.createQuery("from Truck as t where t.id = :name", Truck.class)
                .setParameter("name", id)
                .uniqueResult();
        session.close();
        return Optional.ofNullable(truck);
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
