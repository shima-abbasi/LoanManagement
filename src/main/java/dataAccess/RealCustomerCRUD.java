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

    public static List<RealCustomer> searchCustomer(String customerNumber, String firstName, String lastName, String fatherName, String dateOfBirth, String internationalID) {
        List<RealCustomer> customers = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = generateHQL(customerNumber, firstName, lastName, fatherName, dateOfBirth, internationalID);
            customers = session.createQuery(hql).list();
        } catch (HibernateException e) {
            logger.error(e.getMessage());
        } finally {
            session.close();
            logger.info("session closed!");
            return customers;
        }
    }

    public static void updateCustomer(RealCustomer realCustomer) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            RealCustomer realCustomerObject = session.get(RealCustomer.class, realCustomer.getCustomerId());
            realCustomerObject.setFirstName(realCustomer.getFirstName());
            realCustomerObject.setLastName(realCustomer.getLastName());
            realCustomerObject.setFatherName(realCustomer.getFatherName());
            realCustomerObject.setDateOfBirth(realCustomer.getDateOfBirth());
            realCustomerObject.setInternationalID(realCustomer.getInternationalID());
            session.update(realCustomerObject);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error(e.getMessage());
            transaction.rollback();
        } finally {
            logger.info("session closed!");
            session.close();
        }
    }

    public static void deleteCustomer(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            RealCustomer realCustomer = session.get(RealCustomer.class, id);
            session.delete(realCustomer);
            logger.info("Customer deleted!");
            transaction.commit();
        } catch (HibernateException e) {
            logger.error(e.getMessage());
        } finally {
            session.close();
        }
    }

    public static String generateHQL(String customerNumber, String firstName, String lastName, String fatherName, String dateOfBirth, String internationalID) {
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
        RealCustomer realCustomer = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            realCustomer = session.get(RealCustomer.class, id);
        } catch (HibernateException e) {
            logger.error(e.getMessage());
        } finally {
            session.close();
            logger.info("session closed!");
            return realCustomer;
        }
    }

    public static RealCustomer retrieveCustomerByCustomerNumber(int customerNumber) throws SQLException, DataNotFoundException {
        RealCustomer realCustomer = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Object object = session.createQuery("from RealCustomer rc where rc.customerNumber=:cn").setParameter("cn", customerNumber).uniqueResult();
            if (object == null) {
                logger.error("customer not found!");
                throw new DataNotFoundException("مشتری مورد نظر یافت نشد!");
            } else {
                realCustomer = (RealCustomer) object;
            }

        } catch (HibernateException e) {
            logger.error(e.getMessage());
        } finally {
            logger.info("session closed!");
            session.close();
            return realCustomer;
        }
    }
}
