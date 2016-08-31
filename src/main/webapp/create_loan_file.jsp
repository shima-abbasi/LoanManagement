<%@ page import="dataAccess.entity.LoanType" %>
<%@ page import="dataAccess.entity.RealCustomer" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link href="css/style.css" rel="stylesheet">
    <title>پرونده تسهیلاتی</title>
</head>
<body>
<div class=box-in>
    <br>
    <br>
    <form action="LoanFileServlet">
        <input type="text" name="action" value="retrieveCustomerLoanType" hidden>
        <table>
            <tr>
                <td>شماره مشتری</td>
                <td><input type="text" name="customerId" required="required" oninvalid="alert('وارد کردن شماره مشتری الزامی است')"></td>
                <td><input class="button" type="submit" value="بازیابی"></td>
                <td><a href="realCustomerManagement.html" class="form">بازگشت به صفحه قبل</a></td>
            </tr>
        </table>
    </form>
    <br>
    <br>
    <br>


    <form action="LoanFileServlet">
        <% int customerExistence = (int) request.getAttribute("customerExistence");%>
        <% if (customerExistence == 1) {%>
        <input type="text" name="action" value="create" hidden>
        <% RealCustomer realCustomerObject = (RealCustomer) request.getAttribute("realCustomerObject"); %>
        <input type="text" name="RequestedCustomerId" value="<%=realCustomerObject.getCustomerId()%>" hidden>
        <table>
            <tr>
                <td> نام و نام خانوادگی مشتری</td>
                <td><%=realCustomerObject.getFirstName()%>
                </td>
            </tr>
            <tr>
                <td> نوع تسهیلات</td>
                <td>
                    <% boolean loanTypeExistence = (boolean) request.getAttribute("loanTypeExistence");%>
                    <% if (loanTypeExistence) {%>
                    <% ArrayList<LoanType> loanTypeObjects = (ArrayList<LoanType>) request.getAttribute("loanTypeObjects"); %>
                    <select class="my-dropdown" name="loanId">
                        <% for (LoanType loanTypeObject : loanTypeObjects) { %>
                        <option value="<%= loanTypeObject.getLoanTypeId()%>"><%= loanTypeObject.getLoanName()%>
                        </option>
                        <%}%>
                    </select>
                    <%} else {%>
                    <p> نوع تسهیلاتی برای ارائه موجود نیست.</p>
                    <%}%>
                </td>
            </tr>
            <tr>
                <td> مدت قرارداد</td>
                <td><input type="text" name="duration" required="required" oninvalid="alert('وارد کردن مدت قرارداد الزامی است')"></td>
            </tr>
            <tr>
                <td> مبلغ قرارداد</td>
                <td><input type="text" name="amount" required="required" oninvalid="alert('وارد کردن  مبلغ قرارداد الزامی است')"></td>
            </tr>
        </table>
        <input class="button" type="submit" value="ثبت">
    </form>
    <%
        }
        if (customerExistence == -1) {
    %>
    <p> شماره مشتری را وارد نمایید</p>
    <%
        }
        if (customerExistence == 0) {
    %>
    <h2>خطا</h2>
    <p>شماره مشتری یافت نشد</p>
    <%}%>
</div>
</body>
</html>
