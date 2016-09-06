package logic;

import dataAccess.LoanFileCRUD;
import dataAccess.entity.GrantCondition;
import dataAccess.entity.LoanFile;
import dataAccess.entity.LoanType;
import dataAccess.entity.RealCustomer;
import exceptions.DataNotFoundException;
import exceptions.OutOfRangeException;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dotin school 5 on 8/31/2016.
 */
public class LoanFileLogic {
    public static GrantCondition validateLoanFile(LoanFile loanFile, int loanTypeId) throws OutOfRangeException, DataNotFoundException {

        List<GrantCondition> grantConditions = GrantConditionLogic.retrieveConditionsByLoanId(loanTypeId);
        for (GrantCondition grantCondition : grantConditions) {
            if (loanFile.getDuration() <= grantCondition.getMaxDuration() && loanFile.getDuration() >= grantCondition.getMinDuration() && loanFile.getAmount().compareTo(grantCondition.getMaxAmount()) == -1 && loanFile.getAmount().compareTo(grantCondition.getMinAmount()) == 1) {
                return grantCondition;
            }
        }
        throw new OutOfRangeException("تسهیلاتی با محدوده زمان و مبلغ وارد شده موجود نمی باشد!");
    }

    public static RealCustomer retrieveCustomer(int customerNumber)
            throws DataNotFoundException, SQLException {

        return RealCustomerLogic.retrieveCustomerByCustomerNumber(customerNumber);
    }

    public static LoanType retrieveLoanType(int loanTypeId) throws DataNotFoundException {

        return LoanTypeLogic.retrieveLoanTypeById(loanTypeId);
    }

    public static void create(int customerNumber, int loanTypeId, LoanFile loanFile) throws DataNotFoundException, OutOfRangeException, SQLException {

        LoanType loanType = retrieveLoanType(loanTypeId);
        GrantCondition grantCondition = validateLoanFile(loanFile, loanTypeId);
        loanType.setGrantConditions(grantCondition);
        loanFile.setLoanType(loanType);
        RealCustomer realCustomer = retrieveCustomer(customerNumber);
        loanFile.setRealCustomer(realCustomer);
        LoanFileCRUD.saveLoanFile(loanFile);
    }
}
