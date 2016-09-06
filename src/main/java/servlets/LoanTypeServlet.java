package servlets;

import dataAccess.entity.LoanType;
import exceptions.RequiredFieldException;
import logic.LoanTypeLogic;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dotin school 5 on 8/29/2016.
 */
public class LoanTypeServlet extends HttpServlet {
    static Logger logger = Logger.getLogger(RealCustomerServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");
        String loanName = request.getParameter("loanName");
        Float interestRate = Float.parseFloat(request.getParameter("interestRate"));
        try {
            LoanType loanType = LoanTypeLogic.createLoanType(loanName, interestRate);
            logger.info("Loan type created, need conditions!");
            request.setAttribute("LoanType", loanType);
            RequestDispatcher dispatcher = getServletConfig().getServletContext().getRequestDispatcher("/create_grant_condition.jsp");
            dispatcher.forward(request, response);
        } catch (RequiredFieldException e) {
            request.setAttribute("text", "خطا در ثبت نوع تسهیلات ایجاد شده است.");
            request.setAttribute("url", "create_loan_type.jsp");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/info.jsp");
            dispatcher.forward(request, response);
        }
    }
}
