package servlets;

import dataAccess.entity.GrantCondition;
import dataAccess.entity.LoanType;
import logic.GrantConditionLogic;

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
public class CreateGrantConditionServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF8");

            String loanName = request.getParameter("loanName");
            Float interestRate = Float.parseFloat(request.getParameter("interestRate"));

            int rowNumber = Integer.parseInt(request.getParameter("rowNumber"));
            Set<GrantCondition> grantConditions = new HashSet<GrantCondition>();

            for (int i = 1; i < rowNumber - 1; i++) {
                GrantCondition grantConditionObject = new GrantCondition();
                grantConditionObject.setGrantName(request.getParameter("grantName" + i));
                grantConditionObject.setMinDuration(Integer.parseInt(request.getParameter("minDuration" + i)));
                grantConditionObject.setMaxDuration(Integer.parseInt((request.getParameter("maxDuration" + i))));
                grantConditionObject.setMinAmount(new BigDecimal((request.getParameter("minAmount" + i))));
                grantConditionObject.setMaxAmount(new BigDecimal((request.getParameter("maxAmount" + i))));
                grantConditions.add(grantConditionObject);
            }

            GrantConditionLogic.createGrantCondition( new LoanType(loanName, interestRate), grantConditions);

            request.setAttribute("header", "عملیات موفق");
            request.setAttribute("text","نوع تسهیلات جدید با موفقیت ثبت شد!");
            request.setAttribute("url", "createLoanType.jsp");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/info.jsp");
            dispatcher.forward(request , response);
        } catch (NotSupportedException e) {
            request.setAttribute("header","عملیات ناموفق");
            request.setAttribute("text","خطا در ذخیره نوع تسهیلات. لطفا مجددا تلاش کنید!" + "\n" + e.getMessage());
            request.setAttribute("url", "createLoanType.jsp");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/info.jsp");
            dispatcher.forward(request , response);
        }


    }
}
