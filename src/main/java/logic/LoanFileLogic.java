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
    public static void validateLoanFile(LoanFile loanFile, int loanTypeId) throws OutOfRangeException, DataNotFoundException {

        List<GrantCondition> grantConditions = GrantConditionLogic.retrieveConditionsByLoanId(loanTypeId);
        for(GrantCondition grantCondition : grantConditions){
            if( loanFile.getDuration() > grantCondition.getMaxDuration() || loanFile.getDuration() < grantCondition.getMinDuration()){
                throw new OutOfRangeException("مدت زمان وارد شده در محدوده مدت زمان های شرایط تسهیلات صدق نمی کند! لطفا دوباره تلاش کنید.");
            }
            if( loanFile.getAmount().compareTo(new BigDecimal(grantCondition.getMaxDuration()))==1  || loanFile.getAmount().compareTo(new BigDecimal(grantCondition.getMinDuration()))==-1 ){
                throw new OutOfRangeException("مبلغ وارد شده در محدوده مبلغ های شرایط تسهیلات صدق نمی کند! لطفا دوباره تلاش کنید.");
            }
        }
    }
//
    public static RealCustomer retrieveCustomer(int customerNumber)
            throws DataNotFoundException, SQLException {

        return RealCustomerLogic.retrieveCustomerByCustomerNumber(customerNumber);
    }
//
//    public static ArrayList<LoanTypeObject> retrieveLoanTypes()
//            throws DataNotFoundException {
//
//        return LoanTypeLogic.retrieveAll();
//    }
//
    public static LoanType retrieveLoanType(int loanTypeId) throws DataNotFoundException {

        return LoanTypeLogic.retrieveLoanTypeById(loanTypeId);
    }

    public static void create(int customerNumber, int loanTypeId, LoanFile loanFile) {

        try {
            LoanType loanType = retrieveLoanType(loanTypeId);
            validateLoanFile(loanFile,loanTypeId);
            loanFile.setLoanType(loanType);
            RealCustomer realCustomer = retrieveCustomer(customerNumber);
            loanFile.setRealCustomer(realCustomer);
            LoanFileCRUD.saveLoanFile(loanFile , loanType, realCustomer);
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        } catch (OutOfRangeException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
