package dataAccess;

import dataAccess.entity.LoanFile;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 * Created by Dotin school 5 on 9/3/2016.
 */
public class LoanFileCRUD {
    public static void saveLoanFile(LoanFile loanFile) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction tx = session.beginTransaction();
            session.save(loanFile);
            tx.commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


}
