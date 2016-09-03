package logic;

import dataAccess.LoanFileCRUD;
import dataAccess.entity.GrantCondition;
import dataAccess.entity.LoanFile;
import dataAccess.entity.LoanType;
import dataAccess.entity.RealCustomer;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by Dotin school 5 on 8/31/2016.
 */
public class LoanFileLogic {
    public static void validateLoanFile(LoanFile loanFileObject, Integer loanId)
            throws DataNotFoundException, InputNotInRangeException {

        ArrayList<GrantCondition> grantConditionObjects = GrantConditionLogic.retrieveConditionsByLoanId(loanId);
        for(GrantCondition grantConditionObject : grantConditionObjects){
            if( loanFileObject.getDuration() > grantConditionObject.getMaxDuration() || loanFileObject.getDuration() < grantConditionObject.getMinDuration()){
                throw new InputNotInRangeException("مدت زمان وارد شده در محدوده مدت زمان های شرایط تسهیلات صدق نمی کند! لطفا دوباره تلاش کنید.");
            }
            if( loanFileObject.getAmount().compareTo(new BigDecimal(grantConditionObject.getMaxDuration()))==1  || loanFileObject.getAmount().compareTo(new BigDecimal(grantConditionObject.getMinDuration()))==-1 ){
                throw new InputNotInRangeException("مبلغ وارد شده در محدوده مبلغ های شرایط تسهیلات صدق نمی کند! لطفا دوباره تلاش کنید.");
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
//    public static LoanTypeObject retrieveLoanType(Integer loanTypeId)
//            throws DataNotFoundException {
//
//        return LoanTypeLogic.retrieve(loanTypeId);
//    }

    public static void create(int customerNumber, int loanTypeId, LoanFile loanFile) throws InputNotInRangeException {

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