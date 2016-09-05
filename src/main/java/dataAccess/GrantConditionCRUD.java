package dataAccess;

import dataAccess.entity.GrantCondition;
import exceptions.DataNotFoundException;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import servlets.RealCustomerServlet;
import util.HibernateUtil;

import java.util.List;

/**
 * Created by Dotin school 5 on 9/3/2016.
 */
public class GrantConditionCRUD {
    static Logger logger = Logger.getLogger(RealCustomerServlet.class);

    public static List<GrantCondition> retrieveLoanTypeConditions(int loanTypeId) throws DataNotFoundException {

        List<GrantCondition> grantConditions=null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria criteria = session.createCriteria(GrantCondition.class);
            criteria.add(Restrictions.eq("loanType.loanTypeId", loanTypeId));
            grantConditions = criteria.list();
            if (grantConditions == null) {
                throw new DataNotFoundException("شروط اعطا یافت نشد!");

            }
        } catch (HibernateException e) {
            logger.error(e.getMessage());
        } finally {
            logger.info("session closed!");
            session.close();
            return grantConditions;
        }
    }
}
