package logic;

import dataAccess.GrantConditionCRUD;
import dataAccess.LoanTypeCRUD;
import dataAccess.entity.GrantCondition;
import dataAccess.entity.LoanType;
import exceptions.DataNotFoundException;
import exceptions.OutOfRangeException;
import org.apache.log4j.Logger;
import servlets.RealCustomerServlet;

import javax.transaction.NotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Dotin school 5 on 8/29/2016.
 */
public class GrantConditionLogic {
    static Logger logger = Logger.getLogger(RealCustomerServlet.class);
    public static void createLoanType(LoanType loanType, Set<GrantCondition> grantConditions) throws OutOfRangeException {
        validateGrantConditions(grantConditions);
        LoanTypeCRUD.createLoanType(loanType);
    }

    public static void validateGrantConditions(Set<GrantCondition> grantConditions)
            throws OutOfRangeException {
        for(GrantCondition grantCondition : grantConditions){
            if(grantCondition.getMinDuration()> grantCondition.getMaxDuration()){
                logger.error("Duration out of range exception occurred !");
                throw new OutOfRangeException("حداکثر مدت قرارداد باید بزرگتر از حداقل مدت قرارداد باشد.");
            }
            if(grantCondition.getMinAmount().compareTo(grantCondition.getMaxAmount())==1){
                logger.error("Amount out of range exception occurred!");
                throw new OutOfRangeException("حداکثر مبلغ قرارداد باید بزرگتر از حداقل مدت قرارداد باشد.");
            }
        }
    }
    public static List<GrantCondition> retrieveConditionsByLoanId(int loanTypeId) throws DataNotFoundException {

        return GrantConditionCRUD.retrieveLoanTypeConditions(loanTypeId);

    }
}
