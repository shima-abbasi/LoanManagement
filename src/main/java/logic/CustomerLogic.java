package logic;

import dataAccess.LegalCustomerCRUD;
import dataAccess.RealCustomerCRUD;
import dataAccess.connectionutil.DBConnection;
import dataAccess.entity.LegalCustomer;
import dataAccess.entity.RealCustomer;
import exceptions.NoValidatedCustomerException;
import exceptions.RequiredFieldException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Dotin school 5 on 8/7/2016.
 */
public class CustomerLogic {

    public static LegalCustomer setCustomerInfo(String companyName, String registrationDate, String economicID) throws SQLException, RequiredFieldException, NoValidatedCustomerException {

        LegalCustomer legalCustomer = new LegalCustomer();
        if (!LegalCustomerLogic.checkField(companyName, registrationDate, economicID))
            throw new RequiredFieldException();
        else if (!LegalCustomerLogic.validateUniqueCustomer(economicID))
            throw new NoValidatedCustomerException();
        else {
            legalCustomer.setCustomerNumber(generateCustomerNumber());
            legalCustomer.setCompanyName(companyName);
            legalCustomer.setRegistrationDate(registrationDate);
            legalCustomer.setEconomicID(economicID);
            LegalCustomerCRUD.createLegalCustomer(legalCustomer);
            return legalCustomer;
        }
    }

    public static RealCustomer setCustomerInfo(String firstName, String lastName, String fatherName, String dateOfBirth, String internationalID) throws SQLException, NoValidatedCustomerException, RequiredFieldException {
        RealCustomer realCustomer = new RealCustomer();
        if (!RealCustomerLogic.checkField(firstName, lastName, fatherName, dateOfBirth, internationalID))
            throw new RequiredFieldException();
        else if (RealCustomerLogic.validateUniqueCustomer(internationalID))
            throw new NoValidatedCustomerException();
        else {
            realCustomer.getCustomerId(generateCustomerNumber());
            realCustomer.setFirstName(firstName);
            realCustomer.setLastName(lastName);
            realCustomer.setFatherName(fatherName);
            realCustomer.setDateOfBirth(dateOfBirth);
            realCustomer.setInternationalID(internationalID);
            RealCustomerCRUD.createRealCustomer(realCustomer);
            return realCustomer;
        }
    }


    public static String generateCustomerNumber() throws SQLException {
        String customerNumber = "";
        ResultSet resultSet = DBConnection.getDBConnection().createStatement().executeQuery("SELECT customer_number FROM customer");
        while (resultSet.next()) {
            customerNumber = resultSet.getString(1);
        }
        if (customerNumber == "") {
            System.out.println("The first customer number created");
            return "1000";
        } else {
            System.out.println("customer number created");
            return String.valueOf((Integer.parseInt(customerNumber) + 1));
        }
    }

    public static ArrayList<LegalCustomer> searchCustomer(String customerNumber, String companyName, String registrationDate, String economicID) throws SQLException {
        return LegalCustomerCRUD.searchCustomer(customerNumber, companyName, registrationDate, economicID);

    }

    public static ArrayList<RealCustomer> searchCustomer(String customerNumber, String firstName, String lastName, String fatherName, String dateOfBirth, String internationalID) throws SQLException {
        return RealCustomerCRUD.searchCustomer(customerNumber, firstName, lastName, fatherName, dateOfBirth, internationalID);

    }

    public static void updateCustomer(int id, String companyName, String registrationDate, String economicID) throws SQLException, RequiredFieldException, NoValidatedCustomerException {
        if (!LegalCustomerLogic.checkField(companyName, registrationDate, economicID))
            throw new RequiredFieldException();
        if (!LegalCustomerLogic.validateUniqueCustomer(economicID)) {
            throw new NoValidatedCustomerException();
        }
        else
            LegalCustomerCRUD.updateCustomer(id, companyName, registrationDate, economicID);
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
