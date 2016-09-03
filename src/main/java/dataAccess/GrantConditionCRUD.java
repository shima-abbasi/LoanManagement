package dataAccess;

import dataAccess.entity.GrantCondition;
import exceptions.DataNotFoundException;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;

import java.util.List;

/**
 * Created by Dotin school 5 on 9/3/2016.
 */
public class GrantConditionCRUD {

    public static List<GrantCondition> retrieveLoanTypeConditions(int loanTypeId) throws  DataNotFoundException {

        List<GrantCondition> grantConditions;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Criteria criteria =session.createCriteria(GrantCondition.class);
            criteria.add(Restrictions.eq("loanType.loanTypeId", loanTypeId));
            grantConditions = criteria.list();
        } catch (RuntimeException e) {

            e.printStackTrace();
            throw new DataNotFoundException("خطا در بازیابی شروط اعطا!");
        } finally {
            session.close();
        }
        return grantConditions;
    }
}
