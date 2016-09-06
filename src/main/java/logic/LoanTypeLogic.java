package logic;

import dataAccess.LoanTypeCRUD;
import dataAccess.entity.LoanFile;
import dataAccess.entity.LoanType;
import exceptions.DataNotFoundException;
import exceptions.OutOfRangeException;
import exceptions.RequiredFieldException;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import servlets.RealCustomerServlet;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 * Created by Dotin school 5 on 8/29/2016.
 */
public class LoanTypeLogic {
    static Logger logger = Logger.getLogger(RealCustomerServlet.class);

    public static LoanType createLoanType(String loanName, Float interestRate)
            throws RequiredFieldException {
        validateLoanType(loanName, interestRate);
        return new LoanType(loanName, interestRate);
    }


    private static void validateLoanType(String loanName, Float interestRate)
            throws RequiredFieldException {

        if (loanName.equals(null) || loanName.isEmpty()) {
            logger.error("Required loan Type name field!");
            throw new RequiredFieldException("وارد کردن نام تسهیلات الزامی است.");
        }
        if (interestRate == null) {
            logger.error("Required interest rate field!");
            throw new RequiredFieldException("وارد کردن نرخ سود الزامی است.");
        }
    }

    public static ArrayList<LoanType> retrieveLoanTypes() throws DataNotFoundException {

        return (ArrayList<LoanType>) LoanTypeCRUD.retrieveLoanTypes();
    }

    public static LoanType retrieveLoanTypeById(int loanTypeId) throws DataNotFoundException {
        return LoanTypeCRUD.retrieveLoanTypeById(loanTypeId);
    }

    public static void finalizeLoanType(LoanType loanType) throws OutOfRangeException {
        GrantConditionLogic.validateGrantConditions(loanType.getGrantConditions());
        LoanTypeCRUD.createLoanType(loanType);
    }
}
