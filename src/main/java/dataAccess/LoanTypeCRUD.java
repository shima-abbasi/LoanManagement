package dataAccess;

import dataAccess.entity.GrantCondition;
import dataAccess.entity.LoanType;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Dotin school 5 on 8/29/2016.
 */
public class LoanTypeCRUD {
    public static void createLoanType(LoanType loanType, ArrayList<GrantCondition> grantConditions) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction tx = session.beginTransaction();
            loanType.setGrantConditions(grantConditions);
            session.save(loanType);
            tx.commit();
        } finally {
            session.close();
        }
    }
}
