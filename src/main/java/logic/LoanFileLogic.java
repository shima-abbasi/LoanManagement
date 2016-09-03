package logic;

import dataAccess.LoanFileCRUD;
import dataAccess.entity.GrantCondition;
import dataAccess.entity.LoanFile;
import dataAccess.entity.LoanType;
import dataAccess.entity.RealCustomer;
import exceptions.DataNotFoundException;
import exceptions.OutOfRangeException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dotin school 5 on 8/31/2016.
 */
public class LoanFileLogic {
    public static void validateLoanFile(LoanFile loanFile, int loanTypeId) throws OutOfRangeException, DataNotFoundException {

        List<GrantCondition> grantConditions = GrantConditionLogic.retrieveConditionsByLoanId(loanTypeId);
        for(GrantCondition grantConditionObject : grantConditions){
            if( loanFile.getDuration() > grantConditionObject.getMaxDuration() || loanFile.getDuration() < grantConditionObject.getMinDuration()){
                throw new OutOfRangeException("مدت زمان وارد شده در محدوده مدت زمان های شرایط تسهیلات صدق نمی کند! لطفا دوباره تلاش کنید.");
            }
            if( loanFile.getAmount().compareTo(new BigDecimal(grantConditionObject.getMaxDuration()))==1  || loanFile.getAmount().compareTo(new BigDecimal(grantConditionObject.getMinDuration()))==-1 ){
                throw new OutOfRangeException("مبلغ وارد شده در محدوده مبلغ های شرایط تسهیلات صدق نمی کند! لطفا دوباره تلاش کنید.");
            }
        }
    }
//
//    public static RealCustomerObject retrieveCustomer(Integer customerId)
//            throws DataNotFoundException {
//
//        return RealCustomerLogic.retrieve(customerId);
//    }
//
//    public static ArrayList<LoanTypeObject> retrieveLoanTypes()
//            throws DataNotFoundException {
//
//        return LoanTypeLogic.retrieveAll();
//    }
//
    public static LoanType retrieveLoanType(int loanTypeId) {

        return LoanTypeLogic.retrieveLoanTypeById(loanTypeId);
    }

    public static void create(int customerNumber, int loanTypeId, LoanFile loanFile) {

        try {
            LoanType loanType = retrieveLoanType(loanTypeId);
            validateLoanFile(loanFile,loanTypeId);
            loanFile.setLoanType(loanType);
            RealCustomer realCustomer = retrieveCustomer(customerNumber);
            loanFile.setRealCustomer(realCustomer);
            LoanFileCRUD.saveLoanFile(loanFile.toLoanFile(), loanType.toLoanType(), realCustomer.toRealCustomer());
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
    }
}
