package dataAccess;

import dataAccess.entity.GrantCondition;
import dataAccess.entity.LoanType;
import exceptions.DataNotFoundException;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.omg.SendingContext.RunTime;
import servlets.RealCustomerServlet;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Dotin school 5 on 8/29/2016.
 */
public class LoanTypeCRUD {
    static Logger logger = Logger.getLogger(RealCustomerServlet.class);

    public static void createLoanType(LoanType loanType) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.save(loanType);
            for (GrantCondition grantCondition : loanType.getGrantConditions()) {
                grantCondition.setLoanType(loanType);
                session.save(grantCondition);
            }
            transaction.commit();
        } catch (HibernateException e) {
            logger.error(e.getMessage());
//            transaction.rollback();
        } finally {
            session.close();
        }
    }

    public static List<LoanType> retrieveLoanTypes() throws DataNotFoundException {
        List loanTypes;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            loanTypes = session.createQuery("from LoanType").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new DataNotFoundException("هیچ نوع تسهیلاتی ثبت نشده است!");
        }
        return loanTypes;
    }

    public static LoanType retrieveLoanTypeById(int loanTypeId) throws DataNotFoundException {
        LoanType loanType = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            loanType = session.get(LoanType.class, loanTypeId);
            if (loanType == null) {
                throw new DataNotFoundException("تسهیلات یافت نشد!");
            }
        } catch (HibernateException e) {
            logger.error(e.getMessage());
        } finally {
            logger.info("session closed!");
            session.close();
            return loanType;
        }
    }
}
