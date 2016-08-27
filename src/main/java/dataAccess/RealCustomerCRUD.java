package dataAccess;

import dataAccess.entity.Customer;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;

/**
 * Created by Dotin school 5 on 8/21/2016.
 */
public class RealCustomerCRUD {

    public static Integer create(Customer e) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        session.save(e);

        session.getTransaction().commit();

        session.close();

        System.out.println("Successfully created " + e.toString());

        return e.getCustomerId();

    }

    public static List<Customer> retrieve() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        @SuppressWarnings("unchecked")

        List<Customer> customers = session.createQuery("FROM Customer").list();

        session.close();

        System.out.println("Found " + customers.size() + " Customers");

        return customers;

    }

    public static void update(Customer e) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        session.update(e);

        session.getTransaction().commit();

        session.close();

        System.out.println("Successfully updated " + e.toString());

    }


}
