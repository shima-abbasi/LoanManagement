package logic;
import dataAccess.RealCustomerCRUD;
import dataAccess.entity.RealCustomer;
import exceptions.NoValidatedCustomerException;
import exceptions.RequiredFieldException;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Dotin school 5 on 8/7/2016.
 */
public class CustomerLogic {
    public static RealCustomer setCustomerInfo(String firstName, String lastName, String fatherName, String dateOfBirth, String internationalID) throws SQLException, NoValidatedCustomerException, RequiredFieldException {
        RealCustomer realCustomer = new RealCustomer();
        if (!RealCustomerLogic.checkField(firstName, lastName, fatherName, dateOfBirth, internationalID))
            throw new RequiredFieldException();
        else if (RealCustomerLogic.validateUniqueCustomer(internationalID))
            throw new NoValidatedCustomerException();
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
        String hql = "select max (real_customer.customer_number) from real_customer";
        Query query = session.createQuery(hql);
        int customerNumber = 0 ;
        customerNumber = (int) query.uniqueResult();
        session.beginTransaction().commit();
        if (customerNumber==0) {
            System.out.println("The first customer number created");
            return 1000;
        } else {
            System.out.println("customer number created");
            return customerNumber + 1;
        }
    }


    public static List<RealCustomer> searchCustomer(String customerNumber, String firstName, String lastName, String fatherName, String dateOfBirth, String internationalID) throws SQLException {
        return RealCustomerCRUD.searchCustomer(customerNumber, firstName, lastName, fatherName, dateOfBirth, internationalID);

    }

    public static void updateCustomer(int id, String firstName, String lastName, String fatherName, String dateOfBirth, String internationalID) throws SQLException, RequiredFieldException, NoValidatedCustomerException {
        if (!RealCustomerLogic.checkField(firstName, lastName, fatherName, dateOfBirth, internationalID))
            throw new RequiredFieldException();
        if(!RealCustomerLogic.validateUniqueCustomer(internationalID))
            throw  new NoValidatedCustomerException();
        else
            RealCustomerCRUD.updateCustomer(id, firstName, lastName, fatherName, dateOfBirth, internationalID);
    }
}
