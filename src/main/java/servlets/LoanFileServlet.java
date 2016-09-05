package servlets;

import dataAccess.entity.LoanFile;
import dataAccess.entity.LoanType;
import dataAccess.entity.RealCustomer;
import exceptions.DataNotFoundException;
import logic.LoanFileLogic;
import logic.LoanTypeLogic;
import logic.RealCustomerLogic;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Dotin school 5 on 8/31/2016.
 */
public class LoanFileServlet extends HttpServlet {
    static Logger logger = Logger.getLogger(RealCustomerServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        request.setCharacterEncoding("UTF-8");
        if ("retrieve-customer-and-loan-type".equalsIgnoreCase(action)) {
            retrieveCustomerLoanType(request, response);
        }
        if ("create".equalsIgnoreCase(action)) {
            createLoanFile(request, response);
        }
        if ("first-run".equalsIgnoreCase(action)) {
            firstRun(request, response);
        }
    }

    private void createLoanFile(HttpServletRequest request, HttpServletResponse response) {
            LoanFile loanFile = new LoanFile();
        try {
            int customerNumber = Integer.parseInt(request.getParameter("confirmedCustomerNumber"));
            int loanTypeId = Integer.parseInt(request.getParameter("loanType"));
            loanFile.setAmount(new BigDecimal(request.getParameter("amount")));
            loanFile.setDuration(Integer.parseInt(request.getParameter("duration")));
            LoanFileLogic.create(customerNumber, loanTypeId, loanFile);
            logger.info("loan type created successfully!");
            request.setAttribute("text", "پرونده تسهیلاتی با موفقیت ثبت شد.");
            request.setAttribute("url", "create_loan_file.jsp");

        } catch (Exception e) {
            request.setAttribute("text", e.getMessage());
        } finally {
            try {
                request.setAttribute("url", "LoanFileServlet?action=first-run");
                getServletConfig().getServletContext().getRequestDispatcher("/info.jsp").forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void retrieveCustomerLoanType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int customerNumber = Integer.parseInt(request.getParameter("customerNumber"));
            int customerExist = 0;
            boolean loanTypeExist = false;
        try {
            RealCustomer realCustomer = RealCustomerLogic.retrieveCustomerByCustomerNumber(customerNumber);
            if (realCustomer != null) {
                customerExist = 1;

                request.setAttribute("customerNumber", customerNumber);
                request.setAttribute("realCustomer", realCustomer);

                ArrayList<LoanType> loanTypes = LoanTypeLogic.retrieveLoanTypes();
                if (loanTypes != null) {
                    loanTypeExist = true;
                    request.setAttribute("loanTypes", loanTypes);
                }
            }
        } catch (DataNotFoundException e) {
            request.setAttribute("text", e.getMessage());
            request.setAttribute("url", "create_loan_file");

        } catch (SQLException e) {
            logger.error(e.getMessage());
            request.setAttribute("text", e.getMessage());
            request.setAttribute("url", "create_loan_file");
        }
        request.setAttribute("customerExist", customerExist);
        request.setAttribute("loanTypeExist", loanTypeExist);
        getServletConfig().getServletContext().getRequestDispatcher("/create_loan_file.jsp").forward(request, response);


    }

    private void firstRun(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("customerExist", -1);
            request.setAttribute("customerNumber", "");
            getServletConfig().getServletContext().getRequestDispatcher("/create_loan_file.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
