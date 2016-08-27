package output;

import dataAccess.entity.LegalCustomer;
import dataAccess.entity.RealCustomer;

import java.util.ArrayList;

/**
 * Created by Dotin school 5 on 8/13/2016.
 */
public class OutputGenerator {


    public static String generateRealCustomer(RealCustomer realCustomer) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<!DOCTYPE html>");
        stringBuilder.append("<html lang=fa>");
        stringBuilder.append("<head>");
        stringBuilder.append("    <meta charset=UTF-8>");
        stringBuilder.append("    <link href=css/style.css rel=stylesheet>");
        stringBuilder.append("    <title>اطلاعات مشتری جدید</title>");
        stringBuilder.append("</head>");
        stringBuilder.append("<body>");
        stringBuilder.append("<div class=box-in>");
        stringBuilder.append("  <br>");
        stringBuilder.append("    <h1>اطلاعات زیر برای مشتری جدید ثبت شد</h1>");
        stringBuilder.append("  </br>");
        stringBuilder.append("<table align=\"center\">");
        stringBuilder.append("<tr>");
        stringBuilder.append("<td> نام و نام خوانوادگی مشتری </td>");
        stringBuilder.append("<td>" + realCustomer.getFirstName() + realCustomer.getLastName() + "</td>");
        stringBuilder.append("     </tr>");
        stringBuilder.append("     <tr>");
        stringBuilder.append("      <td>نام پدر</td>");
        stringBuilder.append("          <td>" + realCustomer.getFatherName() + "</td>");
        stringBuilder.append("     </tr>");
        stringBuilder.append("     <tr>");
        stringBuilder.append("         <td>تاریخ تولد</td>");
        stringBuilder.append("          <td>" + realCustomer.getDateOfBirth() + "</td>");
        stringBuilder.append("      </tr>");
        stringBuilder.append("     <tr>");
        stringBuilder.append("         <td>کد ملی</td>");
        stringBuilder.append("          <td>" + realCustomer.getInternationalID() + "</td>");
        stringBuilder.append("      </tr>");
        stringBuilder.append("      <tr>");
        stringBuilder.append("            <td>شماره مشتری</td>");
        stringBuilder.append("            <td>" + realCustomer.getCustomerNumber() + "</td>");
        stringBuilder.append("       </tr>");
        stringBuilder.append("</table>");
        stringBuilder.append("    <form action=\"real_customer_management.html\">");
        stringBuilder.append("         <button class=\"button\" type=\"submit\"> بازگشت به صفحه قبل</button>");
        stringBuilder.append("    </form>");
        stringBuilder.append("</div>");
        stringBuilder.append("</body>");
        stringBuilder.append("</html>");

        return stringBuilder.toString();
    }


    public static String generateRealCustomerResult(ArrayList<RealCustomer> realCustomerResult) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<!DOCTYPE html>");
        stringBuilder.append("<html lang=fa>");
        stringBuilder.append("<head>");
        stringBuilder.append("    <meta charset=UTF-8>");
        stringBuilder.append("    <link href=css/style.css rel=stylesheet>");
        stringBuilder.append("    <title>جستجوی مشتری</title>");
        stringBuilder.append("</head>");
        stringBuilder.append("<body>");
        stringBuilder.append("            <div class=box-in>");
        stringBuilder.append("                <br>");
        stringBuilder.append("                <h1>نتایج جستجو به شرح زیر است</h1>");
        stringBuilder.append("                <br>");

        stringBuilder.append("                    <table class=result-table>");
        stringBuilder.append("                        <tr>");
        stringBuilder.append("                            <td> نام </td>");
        stringBuilder.append("                            <td> نام خانوادگی</td>");
        stringBuilder.append("                            <td>نام پدر</td>");
        stringBuilder.append("                            <td>تاریخ تولد</td>");
        stringBuilder.append("                            <td>کد ملی</td>");
        stringBuilder.append("                            <td>شماره مشتری</td>");
        stringBuilder.append("                            <td>انجام عملیات</td>");
        stringBuilder.append("                        </tr>");
        for (RealCustomer realCustomer : realCustomerResult) {
            stringBuilder.append("                        <tr>");
            stringBuilder.append("                            <td>" + realCustomer.getFirstName() + "</td>");
            stringBuilder.append("                            <td>" + realCustomer.getLastName() + "</td>");
            stringBuilder.append("                            <td>" + realCustomer.getFatherName() + "</td>");
            stringBuilder.append("                            <td>" + realCustomer.getDateOfBirth() + "</td>");
            stringBuilder.append("                            <td>" + realCustomer.getInternationalID() + "</td>");
            stringBuilder.append("                            <td>" + realCustomer.getCustomerNumber() + "</td>");
            stringBuilder.append("                            <td><a href=DeleteRealCustomerServlet?id=" + realCustomer.getId() + " class=form>حذف</a>");
            stringBuilder.append("                            <a href=RetrieveRealCustomerServlet?id=" + realCustomer.getId() + " class=form>اصلاح</a></td>");
        }
        stringBuilder.append("                    </table>");
        stringBuilder.append("    <form action=\"search_real_customer.html\">");
        stringBuilder.append("         <button class=\"button\" type=\"submit\"> بازگشت به صفحه قبل</button>");
        stringBuilder.append("    </form>");
        stringBuilder.append("            </div>");
        stringBuilder.append("</body>");
        stringBuilder.append("</html>");

        return stringBuilder.toString();
    }


    public static String generateMessage(String message, String path) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<!DOCTYPE html>");
        stringBuilder.append("<html lang=fa>");
        stringBuilder.append("<head>");
        stringBuilder.append("    <meta charset=UTF-8>");
        stringBuilder.append("    <link href=css/style.css rel=stylesheet>");
        stringBuilder.append("    <title>ییام سیستم</title>");
        stringBuilder.append("</head>");
        stringBuilder.append("<body>");
        stringBuilder.append("<div class=box-in>");
        stringBuilder.append("  <br>");
        stringBuilder.append("    <h1>" + message + "</h1>");
        stringBuilder.append("  </br>");
        stringBuilder.append("    <form action=" + path + ">");
        stringBuilder.append("         <button class=\"button\" type=\"submit\"> بازگشت به صفحه قبل</button>");
        stringBuilder.append("    </form>");
        stringBuilder.append("</div>");
        stringBuilder.append("</body>");
        stringBuilder.append("</html>");

        return stringBuilder.toString();
    }

    public static String generateUpdatePage(RealCustomer realCustomer) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<!DOCTYPE html>");
        stringBuilder.append("<html lang=fa>");
        stringBuilder.append("<head>");
        stringBuilder.append("    <meta charset=UTF-8>");
        stringBuilder.append("    <link href=css/style.css rel=stylesheet>");
        stringBuilder.append("    <title>اصلاح اطلاعات مشتری</title>");
        stringBuilder.append("</head>");
        stringBuilder.append("<body>");
        stringBuilder.append("            <div class=box-in>");
        stringBuilder.append("                <br>");
        stringBuilder.append("                 <form action='UpdateRealCustomerServlet' method='get'>");
        stringBuilder.append("                <h1>اطلاعات مشتری حقوقی :</h1>");
        stringBuilder.append("                <br>");
        stringBuilder.append("<input type='hidden' name='id' value='" + realCustomer.getId() + "'>");
        stringBuilder.append("                    <table align='center'>");
        stringBuilder.append("                        <tr>");
        stringBuilder.append("                            <td>شماره مشتری</td>");
        stringBuilder.append("                            <td><input type='text' name='customerNumber' value='" + realCustomer.getCustomerNumber() + "' readonly ></td>");
        stringBuilder.append("                        </tr>");
        stringBuilder.append("                        <tr>");
        stringBuilder.append("                            <td> نام  </td>");
        stringBuilder.append("                            <td><input type='text' name='firstName' value='" + realCustomer.getFirstName() + "'></td>");
        stringBuilder.append("                        </tr>");
        stringBuilder.append("                            <td> نام خانوادگی  </td>");
        stringBuilder.append("                            <td><input type='text' name='lastName' value='" + realCustomer.getLastName() + "'></td>");
        stringBuilder.append("                        </tr>");
        stringBuilder.append("                            <td> نام پدر  </td>");
        stringBuilder.append("                            <td><input type='text' name='fatherName' value='" + realCustomer.getFatherName() + "'></td>");
        stringBuilder.append("                        </tr>");
        stringBuilder.append("                        <tr>");
        stringBuilder.append("                            <td> تاریخ تولد</td>");
        stringBuilder.append("                            <td><input type='text' name='dateOfBirth' value='" + realCustomer.getDateOfBirth() + "'></td>");
        stringBuilder.append("                        </tr>");
        stringBuilder.append("                        <tr>");
        stringBuilder.append("                            <td>کد ملی</td>");
        stringBuilder.append("                            <td><input type='text' name='internationalID' value='" + realCustomer.getInternationalID() + "'></td>");
        stringBuilder.append("                        </tr>");
        stringBuilder.append("                    </table>");
        stringBuilder.append("                    <input type='submit' class='button' value='ذخیره تغییرات'>");
        stringBuilder.append("                    </form>");
        stringBuilder.append("    <form action=\"real_customer_management.html\">");
        stringBuilder.append("         <button class=\"button\" type=\"submit\"> بازگشت به صفحه قبل</button>");
        stringBuilder.append("    </form>");
        stringBuilder.append("            </div>");
        stringBuilder.append("</body>");
        stringBuilder.append("</html>");

        return stringBuilder.toString();
    }
}



