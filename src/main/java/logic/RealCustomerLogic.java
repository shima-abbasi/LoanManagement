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
public class RealCustomerLogic extends CustomerLogic {

    public static boolean validateUniqueCustomer(String internationalID) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select realCustomer.internationalID from RealCustomer as realCustomer where realCustomer.internationalID=:int_id ");
       query.setParameter("int_id", internationalID);
        List results = query.list();
        if (results.size()>0) {
            session.close();
            return false;
        }
        session.close();
        return true;
    }
    public  static boolean checkField( String firstName, String lastName, String fatherName, String dateOfBirth, String internationalID) throws RequiredFieldException, NoValidatedCustomerException, SQLException {
        if (firstName.trim().length() == 0 | lastName.trim().length() == 0 | fatherName.trim().length() == 0 | dateOfBirth.trim().length() == 0 | internationalID.trim().length() == 0)
            throw new RequiredFieldException("وارد کردن نام تسهیلات الزامی است.");
        return true;
    }
    public static RealCustomer retrieveCustomer(int id) throws SQLException {
        return RealCustomerCRUD.retrieveCustomer(id);
    }
    public static void deleteCustomer(int id) throws SQLException {
        RealCustomerCRUD.deleteCustomer(id);
    }

}
