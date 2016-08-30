package servlets;

import dataAccess.entity.RealCustomer;
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
 * Created by Dotin school 5 on 8/6/2016.
 */
public class CreateRealCustomerServlet extends HttpServlet {

    protected void doGet(HttpServletRequest theRequest, HttpServletResponse response)
            throws ServletException, IOException {
        theRequest.setCharacterEncoding("UTF-8");
        String firstName = theRequest.getParameter("firstName");
        String lastName = theRequest.getParameter("lastName");
        String fatherName = theRequest.getParameter("fatherName");
        String dateOfBirth = theRequest.getParameter("dateOfBirth");
        String internationalID = theRequest.getParameter("internationalID");
        String output = "";

        try {
            RealCustomer realCustomer =CustomerLogic.setCustomerInfo(firstName, lastName, fatherName, dateOfBirth, internationalID);
            output = OutputGenerator.generateRealCustomer(realCustomer);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoValidatedCustomerException noValidatedCustomer) {
            output = OutputGenerator.generateMessage("مشتری با کد ملی وارد شده در سیستم موجود می باشد" , "create_real_customer.jsp");
        } catch (RequiredFieldException e) {
            output = OutputGenerator.generateMessage("لطفا اطلاعات ضروری را تکمیل کنید","create_real_customer.jsp");
        }
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(output);
    }
}