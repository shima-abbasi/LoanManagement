package servlets;

import exceptions.NoValidatedCustomerException;
import exceptions.RequiredFieldException;
import logic.CustomerLogic;
import output.OutputGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Created by Dotin school 5 on 8/15/2016.
 */
public class UpdateRealCustomerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String fatherName = request.getParameter("fatherName");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String internationalID = request.getParameter("internationalID");
        String output="";
        try {
            CustomerLogic.updateCustomer(id ,firstName, lastName, fatherName , dateOfBirth , internationalID);
            output = OutputGenerator.generateMessage("اطلاعات مشتری با موفقیت اصلاح شد.","search_real_customer.html");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (RequiredFieldException e) {
            output = OutputGenerator.generateMessage("لطفا اطلاعات ضروری را تکمیل کنید","search_real_customer.html");
        } catch (NoValidatedCustomerException e) {
            output = OutputGenerator.generateMessage("کد ملی تکراری است","search_real_customer.html");

        }
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(output);

    }
}
