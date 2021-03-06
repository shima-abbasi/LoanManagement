package servlets;

import dataAccess.entity.RealCustomer;
import exceptions.IncorrectFormatException;
import exceptions.NoValidatedCustomerException;
import exceptions.RequiredFieldException;
import logic.RealCustomerLogic;
import org.apache.log4j.Logger;
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
public class RealCustomerServlet extends HttpServlet {
    static Logger logger = Logger.getLogger(RealCustomerServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        if ("create".equalsIgnoreCase(action)) {
            createRealCustomer(request, response);
        }
        if ("search".equalsIgnoreCase(action)) {
            searchRealCustomer(request, response);
        }
        if ("delete".equalsIgnoreCase(action)) {
            deleteRealCustomer(request, response);
        }
        if ("retrieve".equalsIgnoreCase(action)) {
            retrieveRealCustomer(request, response);
        }
        if ("update".equalsIgnoreCase(action)) {
            updateRealCustomer(request, response);
        }
    }

    private void createRealCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RealCustomer realCustomer = new RealCustomer();
        realCustomer.setFirstName(request.getParameter("firstName"));
        realCustomer.setLastName(request.getParameter("lastName"));
        realCustomer.setFatherName(request.getParameter("fatherName"));
        realCustomer.setDateOfBirth(request.getParameter("dateOfBirth"));
        realCustomer.setInternationalID(request.getParameter("internationalID"));
        String output = "";

        try {
            RealCustomer realCustomerObject = RealCustomerLogic.setCustomerInfo(realCustomer);
            output = OutputGenerator.generateRealCustomer(realCustomerObject);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } catch (NoValidatedCustomerException | RequiredFieldException | IncorrectFormatException e) {
            output = OutputGenerator.generateMessage(e.getMessage(), "create_real_customer.jsp");
        }
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(output);
    }

    private void searchRealCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String customerNumber = request.getParameter("customerNumber");
        String firstName =request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String fatherName = request.getParameter("fatherName");
        String dateOfBirth =request.getParameter("dateOfBirth");
        String internationalID = request.getParameter("internationalID");
        String output = "";
        try {
            List<RealCustomer> realCustomerResult = RealCustomerLogic.searchCustomer(customerNumber,firstName, lastName, fatherName, dateOfBirth, internationalID);
            if (realCustomerResult.size() == 0) {
                logger.info("Not found any customer!");
                output = OutputGenerator.generateMessage("مشتری با اطلاعات وارد شده وجود ندارد.", "search_real_customer.jsp");
            } else {
                logger.info("Customer found!");
                output = OutputGenerator.generateRealCustomerResult(realCustomerResult);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(output);

    }

    private void deleteRealCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String output = "";
        try {
            RealCustomerLogic.deleteCustomer(id);
            output = OutputGenerator.generateMessage("مشتری مورد نظر حذف شد", "search_real_customer.jsp");

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(output);
    }

    private void updateRealCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RealCustomer realCustomer = new RealCustomer();
        realCustomer.setCustomerId(Integer.parseInt(request.getParameter("id")));
        realCustomer.setFirstName(request.getParameter("firstName"));
        realCustomer.setLastName(request.getParameter("lastName"));
        realCustomer.setFatherName(request.getParameter("fatherName"));
        realCustomer.setDateOfBirth(request.getParameter("dateOfBirth"));
        realCustomer.setInternationalID(request.getParameter("internationalID"));
        String output = "";
        try {
            RealCustomerLogic.updateCustomer(realCustomer);
            logger.info("Successfully updated ");
            output = OutputGenerator.generateMessage("اطلاعات مشتری با موفقیت اصلاح شد.", "search_real_customer.jsp");
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } catch (RequiredFieldException | IncorrectFormatException | NoValidatedCustomerException e) {
            output = OutputGenerator.generateMessage(e.getMessage(), "search_real_customer.jsp");
        }
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(output);
    }


    private void retrieveRealCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String output = "";
        try {
            RealCustomer realCustomer = RealCustomerLogic.retrieveCustomer(id);
            output = OutputGenerator.generateUpdatePage(realCustomer);

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(output);

    }
}

