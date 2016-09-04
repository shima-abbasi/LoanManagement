package logic;

import dataAccess.RealCustomerCRUD;
import dataAccess.entity.RealCustomer;
import exceptions.NoValidatedCustomerException;
import exceptions.RequiredFieldException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Dotin school 5 on 8/7/2016.
 */
public class CustomerLogic {

    static Logger logger = Logger.getLogger(CustomerLogic.class);

    public static RealCustomer setCustomerInfo(String firstName, String lastName, String fatherName, String dateOfBirth, String internationalID) throws SQLException, NoValidatedCustomerException, RequiredFieldException {

        RealCustomer realCustomer = new RealCustomer();

        if (!RealCustomerLogic.checkField(firstName, lastName, fatherName, dateOfBirth, internationalID)) {
            logger.info("Empty field");
            throw new RequiredFieldException("لطفا اطلاعات مشتری را بطور کامل وارد کنید!");
        }
        else if (!RealCustomerLogic.validateUniqueCustomer(internationalID))
            throw new NoValidatedCustomerException("مشتری با کد ملی وارد شده در سیستم موجود می باشد!");
        else {
            realCustomer.setCustomerNumber(generateCustomerNumber());
            realCustomer.setFirstName(firstName);
            realCustomer.setLastName(lastName);
            realCustomer.setFatherName(fatherName);
            realCustomer.setDateOfBirth(dateOfBirth);
            realCustomer.setInternationalID(internationalID);
            RealCustomerCRUD.createRealCustomer(realCustomer);
            return realCustomer;
        }
    }


    public static int generateCustomerNumber() throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "select max (rc.customerNumber) from RealCustomer as rc";
        Query query = session.createQuery(hql);
        int customerNumber;
        if (query.uniqueResult() != null) {
            customerNumber = (int) query.uniqueResult();
            return customerNumber + 1;
        } else {
            return 1000;
        }
    }
    public static List<RealCustomer> searchCustomer(String customerNumber, String firstName, String lastName, String fatherName, String dateOfBirth, String internationalID) throws SQLException {
        return RealCustomerCRUD.searchCustomer(customerNumber, firstName, lastName, fatherName, dateOfBirth, internationalID);


    }

    public static void updateCustomer(int id, String firstName, String lastName, String fatherName, String dateOfBirth, String internationalID) throws SQLException, RequiredFieldException, NoValidatedCustomerException {
        if (!RealCustomerLogic.checkField(firstName, lastName, fatherName, dateOfBirth, internationalID))
            throw new RequiredFieldException("لطفا فیلدهای ضروری را تکمیل کنید.");
        if (!RealCustomerLogic.validateUniqueCustomer(internationalID))
            throw new NoValidatedCustomerException();
        else
            RealCustomerCRUD.updateCustomer(id, firstName, lastName, fatherName, dateOfBirth, internationalID);
    }
}
