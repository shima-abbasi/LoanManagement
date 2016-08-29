package logic;

import dataAccess.LoanTypeCRUD;
import dataAccess.entity.GrantCondition;
import dataAccess.entity.LoanType;
import exceptions.OutOfRangeException;

import javax.transaction.NotSupportedException;
import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Dotin school 5 on 8/29/2016.
 */
public class GrantConditionLogic {
    public static void createLoanType(LoanType loanType, Set<GrantCondition> grantConditions) throws NotSupportedException, OutOfRangeException {
        validateGrantConditions(grantConditions);
        LoanTypeCRUD.createLoanType(loanType, grantConditions);
    }

    private static void validateGrantConditions(Set<GrantCondition> grantConditions)
            throws OutOfRangeException {

        for(GrantCondition grantCondition : grantConditions){
            if(grantCondition.getMinDuration()> grantCondition.getMaxDuration()){
                throw new OutOfRangeException("حداکثر مدت قرارداد باید بزرگتر از حداقل مدت قرارداد باشد.");
            }
            if(grantCondition.getMinAmount().compareTo(grantCondition.getMaxAmount())==1){
                throw new OutOfRangeException("حداکثر مبلغ قرارداد باید بزرگتر از حداقل مدت قرارداد باشد.");
            }
        }
    }
}
