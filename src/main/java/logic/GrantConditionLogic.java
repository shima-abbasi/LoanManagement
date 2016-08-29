package logic;

import dataAccess.entity.GrantCondition;
import dataAccess.entity.LoanType;

import javax.transaction.NotSupportedException;
import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Dotin school 5 on 8/29/2016.
 */
public class GrantConditionLogic {
    public static void createGrantCondition(LoanType loanType, Set<GrantCondition> grantConditions) throws NotSupportedException {
        validateGrantConditions(grantConditions);
        GrantConditionCRUD.saveLoanType(loanType, grantConditions);
    }

    private static void validateGrantConditions(ArrayList<GrantCondition> grantConditions)
            throws ConditionRangeException {

        for(GrantCondition grantCondition : grantConditions){
            if(grantCondition.getMinDuration() > grantCondition.getMaxDuration()){
                throw new ConditionRangeException("حداکثر مدت قرارداد باید بزرگتر از حداقل مدت قرارداد باشد.");
            }
            if(grantConditionObject.getMinAmount().compareTo(grantConditionObject.getMaxAmount())==1){
                throw new ConditionRangeException("حداکثر مبلغ قرارداد باید بزرگتر از حداقل مدت قرارداد باشد.");
            }
        }
    }
}
