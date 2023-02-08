package com.petrenko.repository.HibernateRepository;

import com.petrenko.config.HibernateFactoryUtil;
import com.petrenko.model.Order;
import com.petrenko.repository.Crud;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class HibernateOrderRepository implements Crud<Order> {
    private static HibernateOrderRepository instance;

    private HibernateOrderRepository() {
    }

    public static HibernateOrderRepository getInstance() {
        if (instance == null) {
            instance = new HibernateOrderRepository();
        }
        return instance;
    }

    @Override
    public void save(Order order) {
        final SessionFactory sessionFactory = HibernateFactoryUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(order);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Order> getAll() {
        final SessionFactory sessionFactory = HibernateFactoryUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Order> orderList = session.createQuery("from Order", Order.class)
                .list();
        session.close();
        return orderList;
    }

    @Override
    public Optional<Order> getByUuid(String id) {
        final SessionFactory sessionFactory = HibernateFactoryUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Order order = session.createQuery("from Order as o where o.id = :name", Order.class)
                .setParameter("name", id)
                .uniqueResult();
        session.close();
        return Optional.ofNullable(order);
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
