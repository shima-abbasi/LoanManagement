package dataAccess;

import dataAccess.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Created by Dotin school 5 on 8/21/2016.
 */
public class RealCustomerCRUD {

    public static SessionFactory getSessionFactory() {

        Configuration configuration = new Configuration().configure();

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());

        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());

        return sessionFactory;

    }


    public static Integer create(Customer e) {

        Session session = getSessionFactory().openSession();

        session.beginTransaction();

        session.save(e);

        session.getTransaction().commit();

        session.close();

        System.out.println("Successfully created " + e.toString());

        return e.getId();

    }

    public static List<Customer> read() {

        Session session = getSessionFactory().openSession();

        @SuppressWarnings("unchecked")

        List<Customer> customers = session.createQuery("FROM Customer").list();

        session.close();

        System.out.println("Found " + customers.size() + " Customers");

        return customers;

    }

    public static void update(Customer e) {

        Session session = getSessionFactory().openSession();

        session.beginTransaction();

        Customer customer = (Customer) session.load(Customer.class, e.getId());

        customer.setName(e.getName());

        customer.setAge(e.getAge());

        session.getTransaction().commit();

        session.close();

        System.out.println("Successfully updated " + e.toString());



    }




}
