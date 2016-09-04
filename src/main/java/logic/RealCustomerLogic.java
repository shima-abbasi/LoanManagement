package logic;

import dataAccess.RealCustomerCRUD;
import dataAccess.entity.RealCustomer;
import exceptions.DataNotFoundException;
import exceptions.IncorrectFormatException;
import exceptions.NoValidatedCustomerException;
import exceptions.RequiredFieldException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Dotin school 5 on 8/7/2016.
 */
public class RealCustomerLogic {

    static int firstCustomerNumber = 1000;

    static Logger logger = Logger.getLogger(RealCustomerLogic.class);

    public static RealCustomer setCustomerInfo(RealCustomer realCustomer) throws SQLException, NoValidatedCustomerException, RequiredFieldException, IncorrectFormatException {

        validateCustomerInformation(realCustomer);
        validateUniqueCustomer(realCustomer.getInternationalID());
        realCustomer.setCustomerNumber(generateCustomerNumber());
        RealCustomerCRUD.createRealCustomer(realCustomer);
        return realCustomer;
    }


    public static int generateCustomerNumber() throws SQLException {

        Session session = HibernateUtil.getSessionFactory().openSession();
        int customerNumber;
        try {
            Query query = session.createQuery("select max (rc.customerNumber) from RealCustomer as rc");
            if (query.uniqueResult() != null) {
                customerNumber = (int) query.uniqueResult() + 1;
                logger.info("Customer number created: " + customerNumber);
                return customerNumber;
            } else {
                logger.info("The first customer number created: " + firstCustomerNumber);
                return firstCustomerNumber;
            }
        } catch (HibernateException e) {
            logger.warn(e.getMessage());
        } finally {
            session.close();
            logger.info("session closed!");
        }
    }

    public static List<RealCustomer> searchCustomer(RealCustomer realCustomer) throws SQLException {
        return RealCustomerCRUD.searchCustomer(realCustomer);


    }

    public static void updateCustomer(RealCustomer realCustomer) throws SQLException, RequiredFieldException, NoValidatedCustomerException, IncorrectFormatException {
        validateCustomerInformation(realCustomer);
        RealCustomerCRUD.updateCustomer(realCustomer);
    }

    public static boolean validateUniqueCustomer(String internationalID) throws SQLException {
        Boolean bool = true;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            List results = session.createQuery("select rc.internationalID from RealCustomer as rc where rc.internationalID=:int_id ").setParameter("int_id", internationalID).list();
            if (results.size() > 0) {
                bool = false;
            }
        } catch (HibernateException e) {
            logger.warn(e.getMessage());
        } finally {
            session.close();
            logger.info("session closed!");
            return bool;
        }
    }

    private static void validateCustomerInformation(RealCustomer realCustomer)
            throws RequiredFieldException, IncorrectFormatException {

        if (realCustomer.getFirstName().equals(null) || realCustomer.getFirstName().isEmpty()) {
            logger.error("Required first name field");
            throw new RequiredFieldException("وارد کردن نام الزامی است.");
        }
        if (realCustomer.getLastName().equals(null) || realCustomer.getLastName().isEmpty()) {
            logger.error("Required last name field");
            throw new RequiredFieldException("وارد کردن نام خانوادگی الزامی است.");
        }
        if (realCustomer.getFatherName().equals(null) || realCustomer.getFatherName().isEmpty()) {
            logger.error("Required father name field");
            throw new RequiredFieldException("وارد کردن نام پدر الزامی است.");
        }
        if (realCustomer.getDateOfBirth().equals(null) || realCustomer.getDateOfBirth().isEmpty()) {
            logger.error("Required date of birth field");
            throw new RequiredFieldException("وارد کردن تاریخ تولد الزامی است.");
        }
        if (realCustomer.getInternationalID().equals(null) || realCustomer.getInternationalID().isEmpty()) {
            logger.error("Required international ID field");
            throw new RequiredFieldException("وارد کردن کد ملی الزامی است.");
        } else {
            if (realCustomer.getInternationalID().trim().length() != 10) {
                throw new IncorrectFormatException("کد ملی باید دقیقا ده رقم باشد");
            } else {
                String code = realCustomer.getInternationalID().trim();
                int sum = 0;
                for (int i = 10; i > 1; i--) {
                    sum += Integer.parseInt(code.substring(10 - i, 11 - i)) * i;
                }
                int rightMostNumber = Integer.parseInt(code.substring(9));
                int reminder = sum % 11;
                if (!(reminder == rightMostNumber) && !(reminder == 11 - rightMostNumber)) {
                    logger.error("International ID is incorrect");
                    throw new IncorrectFormatException("کد ملی وارد شده صحیح نیست");
                }
            }
        }
    }


    public static RealCustomer retrieveCustomer(int id) throws SQLException {
        return RealCustomerCRUD.retrieveCustomerById(id);
    }

    public static RealCustomer retrieveCustomerByCustomerNumber(int customerNumber) throws SQLException, DataNotFoundException {
        return RealCustomerCRUD.retrieveCustomerByCustomerNumber(customerNumber);
    }

    public static void deleteCustomer(int id) throws SQLException {
        RealCustomerCRUD.deleteCustomer(id);
    }

}
