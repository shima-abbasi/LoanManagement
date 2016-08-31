package logic;

import dataAccess.LoanTypeCRUD;
import dataAccess.entity.LoanType;
import exceptions.RequiredFieldException;

import java.util.ArrayList;

/**
 * Created by Dotin school 5 on 8/29/2016.
 */
public class LoanTypeLogic {
    public static LoanType createLoanType(String loanName, Float interestRate)
            throws RequiredFieldException {
        return validateLoanType(loanName, interestRate);
    }


    private static LoanType validateLoanType(String loanName, Float interestRate)
            throws RequiredFieldException {

        if (loanName.equals(null) || loanName.isEmpty()) {
            throw new RequiredFieldException("وارد کردن نام تسهیلات الزامی است.");
        }
        if (interestRate == null) {
            throw new RequiredFieldException("وارد کردن نرخ سود الزامی است.");
        }
        return new LoanType(loanName, interestRate);
    }

    public static ArrayList<LoanType> retrieveLoanTypes() {

        return LoanTypeCRUD.retrieveLoanTypes();
    }
}
