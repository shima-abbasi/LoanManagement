package servlets;

import dataAccess.entity.LoanFile;
import dataAccess.entity.LoanType;
import dataAccess.entity.RealCustomer;
import logic.LoanFileLogic;
import logic.LoanTypeLogic;
import logic.RealCustomerLogic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by Dotin school 5 on 8/31/2016.
 */
public class LoanFileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        request.setCharacterEncoding("UTF-8");
        if ("retrieve-customer-and-loan-type".equalsIgnoreCase(action)){
            retrieveCustomerLoanType(request, response);
        }
//        if ("first-run".equalsIgnoreCase(action)){
//            firstRun(request, response);
//        }
        if ("create".equalsIgnoreCase(action)){
            createLoanFile(request, response);
        }
    }

    private void createLoanFile(HttpServletRequest request, HttpServletResponse response) {

        try {
            LoanFile loanFile = new LoanFile();
            int customerNumber = Integer.parseInt(request.getParameter("confirmedCustomerNumber"));
            int loanTypeId = Integer.parseInt(request.getParameter("loanType"));
            loanFile.setAmount(new BigDecimal(request.getParameter("amount")));
            loanFile.setDuration(Integer.parseInt(request.getParameter("duration")));
            LoanFileLogic.create(customerNumber,loanTypeId,loanFile);

            request.setAttribute("header","عملیات موفق");
            request.setAttribute("text","پرونده تسهیلاتی با موفقیت ثبت شد.");
        } catch (Exception e) {
            request.setAttribute("header","عملیات ناموفق");

            request.setAttribute("text","خطا در ثبت پرونده تسهیلاتی جدیدایجاد شده است." + "\n" + e.getMessage());
        } finally {
            try {
                request.setAttribute("url","LoanFileController?action=first-run");
                getServletConfig().getServletContext().getRequestDispatcher("/info-page.jsp").forward(request,response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }

//    private void firstRun(HttpServletRequest request, HttpServletResponse response) {
//        try {
//            request.setAttribute("customerExists",-1);
//            request.setAttribute("customerId","");
//            getServletConfig().getServletContext().getRequestDispatcher("/create-loan-file.jsp").forward(request,response);
//        } catch (ServletException | IOException e) {
//            e.printStackTrace();
//        }
//    }

    private void retrieveCustomerLoanType(HttpServletRequest request, HttpServletResponse response) {

        int customerNumber = Integer.parseInt(request.getParameter("customerId"));
        int customerExist = 0;
        boolean loanTypeExist = false;
        try {
            RealCustomer realCustomer = RealCustomerLogic.retrieveCustomerByCustomerNumber(customerNumber);
            customerExist = 1;
            request.setAttribute("realCustomer", realCustomer);
            request.setAttribute("customerNumber", customerNumber);

            ArrayList<LoanType> loanTypes = LoanTypeLogic.retrieveLoanTypes();
            loanTypeExist = true;
            request.setAttribute("loanTypes", loanTypes);
            request.setAttribute("customerExist", customerExist);
            request.setAttribute("loanTypeExist", loanTypeExist);
            getServletConfig().getServletContext().getRequestDispatcher("/create-loan-file.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
//    }
}
