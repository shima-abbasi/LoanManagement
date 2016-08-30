package dataAccess;

import dataAccess.entity.GrantCondition;
import dataAccess.entity.LoanType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Dotin school 5 on 8/29/2016.
 */
public class LoanTypeCRUD {
    public static void createLoanType(LoanType loanType, Set<GrantCondition> grantConditions) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(loanType);
            for(GrantCondition grantCondition : grantConditions){
                grantCondition.setLoanType(loanType);
                session.save(grantCondition);
            }
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}
