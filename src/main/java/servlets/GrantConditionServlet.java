package servlets;

import dataAccess.entity.GrantCondition;
import dataAccess.entity.LoanType;
import exceptions.OutOfRangeException;
import logic.GrantConditionLogic;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.NotSupportedException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dotin school 5 on 8/29/2016.
 */
public class GrantConditionServlet extends HttpServlet {
    static Logger logger = Logger.getLogger(RealCustomerServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");
        try {
            String loanName = request.getParameter("loanName");
            Float interestRate = Float.parseFloat(request.getParameter("interestRate"));
            int rowNumber = Integer.parseInt(request.getParameter("rowNumber"));
            Set<GrantCondition> grantConditions = new HashSet<GrantCondition>();
            for (int i = 1; i < rowNumber - 1; i++) {
                GrantCondition grantCondition = new GrantCondition();
                grantCondition.setGrantName(request.getParameter("grantName" + i));
                grantCondition.setMinDuration(Integer.parseInt(request.getParameter("minDuration" + i)));
                grantCondition.setMaxDuration(Integer.parseInt((request.getParameter("maxDuration" + i))));
                grantCondition.setMinAmount(new BigDecimal((request.getParameter("minAmount" + i))));
                grantCondition.setMaxAmount(new BigDecimal((request.getParameter("maxAmount" + i))));
                grantConditions.add(grantCondition);
            }

            GrantConditionLogic.createLoanType(new LoanType(loanName, interestRate), grantConditions);
            logger.info("Loan type created successfully!");
            request.setAttribute("text", "نوع تسهیلات جدید با موفقیت ثبت شد!");
            request.setAttribute("url", "/create_loan_type.jsp");
        } catch (NotSupportedException e) {
            request.setAttribute("header", "عملیات ناموفق");
            request.setAttribute("text", "خطا در ذخیره نوع تسهیلات. لطفا مجددا تلاش کنید!" + "\n" + e.getMessage());
            request.setAttribute("url", "/create_loan_type.jsp");
        } catch (OutOfRangeException e) {
            e.printStackTrace();
        }
        getServletConfig().getServletContext().getRequestDispatcher("/info.jsp").forward(request, response);
    }
}
