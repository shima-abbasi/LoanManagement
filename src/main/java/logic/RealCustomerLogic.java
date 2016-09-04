package logic;

import dataAccess.RealCustomerCRUD;
import dataAccess.entity.RealCustomer;
import exceptions.DataNotFoundException;
import exceptions.NoValidatedCustomerException;
import exceptions.RequiredFieldException;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Dotin school 5 on 8/7/2016.
 */
public class RealCustomerLogic extends CustomerLogic {

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
            throw e ;
        } finally {
            session.close();
            return bool;
        }
    }

    public static boolean checkField(String firstName, String lastName, String fatherName, String dateOfBirth, String internationalID) throws RequiredFieldException, NoValidatedCustomerException, SQLException {
        if (firstName.trim().length() == 0 | lastName.trim().length() == 0 | fatherName.trim().length() == 0 | dateOfBirth.trim().length() == 0 | internationalID.trim().length() == 0)
            return false;
        return true;
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
