package servlets;

import dataAccess.entity.RealCustomer;
import logic.RealCustomerLogic;
import output.OutputGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Created by Dotin school 5 on 8/6/2016.
 */
public class RetrieveRealCustomerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String output="";
        try {
            RealCustomer realCustomer = RealCustomerLogic.retrieveCustomer(id);
            output = OutputGenerator.generateUpdatePage(realCustomer);

        } catch (SQLException e) {
            output = OutputGenerator.generateMessage("Problem in connection to the database", "search_legal_customer.html");
        }

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(output);

    }
}
