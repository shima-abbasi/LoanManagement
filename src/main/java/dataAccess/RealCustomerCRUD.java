package dataAccess;

import dataAccess.entity.RealCustomer;
import exceptions.DataNotFoundException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Dotin school 5 on 8/21/2016.
 */
public class RealCustomerCRUD {
    static Logger logger = Logger.getLogger(RealCustomerCRUD.class);

    public static void createRealCustomer(RealCustomer customer) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(customer);
            transaction.commit();
            logger.info("Successfully created customer: " + customer.toString());
        } catch (HibernateException e) {
            logger.warn(e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
            logger.info("session closed!");
        }
    }

    public static List<RealCustomer> searchCustomer(String customerNumber, String firstName, String
            lastName, String fatherName, String dateOfBirth, String internationalID) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql = generateHQL(customerNumber, firstName, lastName, fatherName, dateOfBirth, internationalID);
        List<RealCustomer> customers = session.createQuery(hql).list();
        session.close();
        return customers;
    }

    public static void updateCustomer(int id, String firstName, String lastName, String fatherName, String dateOfBirth, String internationalID) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        RealCustomer realCustomer = session.get(RealCustomer.class, id);
        realCustomer.setFirstName(firstName);
        realCustomer.setLastName(lastName);
        realCustomer.setFatherName(fatherName);
        realCustomer.setDateOfBirth(dateOfBirth);
        realCustomer.setInternationalID(internationalID);
        session.update(realCustomer);
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully updated " + realCustomer.toString());
    }

    public static void deleteCustomer(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        RealCustomer realCustomer = session.get(RealCustomer.class, id);
        session.delete(realCustomer);
        System.out.println("Deleted Successfully");
        session.getTransaction().commit();
        session.close();
    }

    public static String generateHQL(String customerNumber, String firstName, String
            lastName, String fatherName, String dateOfBirth, String internationalID) {
        StringBuilder stringBuilder = new StringBuilder();
        if (customerNumber != "" | firstName != "" | lastName != "" | fatherName != "" | dateOfBirth != "" | internationalID != "") {
            stringBuilder.append("FROM RealCustomer rc where");
            int count = 0;
            if (customerNumber != "") {
                stringBuilder.append(" rc.customerNumber ='" + customerNumber + "'");
                count++;
            }
            if (firstName != "") {
                if (count == 1)
                    stringBuilder.append(" AND rc.firstName ='" + firstName + "'");
                else stringBuilder.append(" rc.firstName ='" + firstName + "'");
                count++;
            }
            if (lastName != "") {
                if (count == 2)
                    stringBuilder.append(" AND rc.lastName ='" + lastName + "'");
                else stringBuilder.append(" rc.lastName ='" + lastName + "'");
                count++;
            }
            if (fatherName != "") {
                if (count == 3)
                    stringBuilder.append(" AND rc.fatherName ='" + fatherName + "'");
                else stringBuilder.append(" rc.fatherName ='" + fatherName + "'");
                count++;
            }
            if (dateOfBirth != "") {
                if (count == 4)
                    stringBuilder.append(" AND rc.dateOfBirth ='" + dateOfBirth + "'");
                else stringBuilder.append(" rc.dateOfBirth ='" + dateOfBirth + "'");

                count++;
            }
            if (internationalID != "") {
                if (count == 5)
                    stringBuilder.append("AND rc.internationalID ='" + internationalID + "'");
                else stringBuilder.append(" rc.internationalID ='" + internationalID + "'");

            }
        } else
            stringBuilder.append("FROM RealCustomer");
        return stringBuilder.toString();
    }

    public static RealCustomer retrieveCustomerById(int id) throws SQLException {
        RealCustomer realCustomer;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        realCustomer = session.get(RealCustomer.class, id);
        return realCustomer;
    }

    public static RealCustomer retrieveCustomerByCustomerNumber(int customerNumber) throws SQLException, DataNotFoundException {
        RealCustomer realCustomer;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from RealCustomer rc where rc.customerNumber=:cn");
            query.setParameter("cn", customerNumber);
            realCustomer = (RealCustomer) query.uniqueResult();
            if (query.uniqueResult() == null) {
                throw new DataNotFoundException("مشتری مورد نظر یافت نشد");
            } else {
                realCustomer = (RealCustomer) query.uniqueResult();
            }

        } finally {
            session.close();
        }
        return realCustomer;
    }
}
