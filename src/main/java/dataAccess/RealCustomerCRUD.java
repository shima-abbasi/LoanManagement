package dataAccess;

import dataAccess.entity.RealCustomer;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;

/**
 * Created by Dotin school 5 on 8/21/2016.
 */
public class RealCustomerCRUD {

    public static void createRealCustomer(RealCustomer customer) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(customer);
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully created " + customer.toString());
    }
    public static List<RealCustomer> searchCustomer(String customerNumber, String firstName, String lastName, String fatherName, String dateOfBirth, String internationalID) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql = generateHQL(customerNumber,firstName,lastName,fatherName,dateOfBirth,internationalID);
        List<RealCustomer> customers = session.createQuery(hql).list();
        session.close();
        return  customers;
    }

    public static void updateCustomer(int id, String firstName, String lastName, String fatherName, String dateOfBirth, String internationalID) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        RealCustomer realCustomer = (RealCustomer) session.get(RealCustomer.class, id);
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

public  static  void  deleteCustomer (int id)
{
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
        if (customerNumber!=""| firstName != "" | lastName != "" | fatherName != "" | dateOfBirth != "" | internationalID != "") {
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


}
