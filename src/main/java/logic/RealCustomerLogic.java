package logic;
import dataAccess.RealCustomerCRUD;
import dataAccess.connectionutil.DBConnection;
import dataAccess.entity.RealCustomer;
import exceptions.NoValidatedCustomerException;
import exceptions.RequiredFieldException;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Dotin school 5 on 8/7/2016.
 */
public class RealCustomerLogic extends CustomerLogic {

    public static boolean validateUniqueCustomer(String internationalID) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "FROM real_customer where international_id=";
        Query query = session.createQuery(hql+internationalID);
        List results = query.list();
        session.beginTransaction().commit();
        if (results.size()==1) {
            session.close();
            return false;
        }
        session.close();
        return true;
    }
    public  static boolean checkField( String firstName, String lastName, String fatherName, String dateOfBirth, String internationalID) throws RequiredFieldException, NoValidatedCustomerException, SQLException {
        if (firstName.trim().length() == 0 | lastName.trim().length() == 0 | fatherName.trim().length() == 0 | dateOfBirth.trim().length() == 0 | internationalID.trim().length() == 0)
            throw new RequiredFieldException();
        return true;
    }
    public static RealCustomer retrieveCustomer(int id) throws SQLException {
        return RealCustomerCRUD.retrieveCustomer(id);
    }
    public static void deleteCustomer(int id) throws SQLException {
        RealCustomerCRUD.deleteCustomer(id);
    }

}
