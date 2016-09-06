package dataAccess;

import dataAccess.entity.LoanFile;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 * Created by Dotin school 5 on 9/3/2016.
 */
public class LoanFileCRUD {
    static Logger logger = Logger.getLogger(RealCustomerCRUD.class);

    public static void saveLoanFile(LoanFile loanFile) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.save(loanFile);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error(e.getMessage());
        } finally {
            session.close();
        }
    }


}
