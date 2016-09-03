package dataAccess;

import dataAccess.entity.LoanFile;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 * Created by Dotin school 5 on 9/3/2016.
 */
public class LoanFileCRUD {
    public static void createLoanFile(int customerNumber, int loanTypeId, LoanFile loanFile) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

    }
}
