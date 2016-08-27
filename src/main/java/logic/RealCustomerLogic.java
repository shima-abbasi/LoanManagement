package logic;
import dataAccess.RealCustomerCRUD;
import dataAccess.connectionutil.DBConnection;
import dataAccess.entity.RealCustomer;
import exceptions.NoValidatedCustomerException;
import exceptions.RequiredFieldException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Dotin school 5 on 8/7/2016.
 */
public class RealCustomerLogic extends CustomerLogic {
    static Connection connection = DBConnection.getDBConnection();

    public static boolean validateUniqueCustomer(String internationalID) throws SQLException {
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM real_customer WHERE international_id=" + internationalID);
        if (!resultSet.next()) {
            return false;
        }
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
