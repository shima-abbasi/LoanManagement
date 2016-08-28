package servlets;

import dataAccess.entity.RealCustomer;
import logic.CustomerLogic;
import output.OutputGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Dotin school 5 on 8/6/2016.
 */
public class SearchRealCustomerServlet extends HttpServlet {
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            request.setCharacterEncoding("UTF-8");
            String customerNumber = request.getParameter("customerNumber");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String fatherName = request.getParameter("fatherName");
            String dateOfBirth = request.getParameter("dateOfBirth");
            String internationalID = request.getParameter("internationalID");
            String output="";
            try {
                List<RealCustomer> realCustomerResult = CustomerLogic.searchCustomer(customerNumber,firstName,lastName,fatherName,dateOfBirth , internationalID);
                if(realCustomerResult.size() == 0){
                    output = OutputGenerator.generateMessage("مشتری با اطلاعات وارد شده وجود ندارد."  , "search_real_customer.jsp");
                }else {
                    output = OutputGenerator.generateRealCustomerResult(realCustomerResult);
                }
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }

            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println(output);

        }
}
