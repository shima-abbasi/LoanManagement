<%@ page import="dataAccess.entity.RealCustomer" %>
<%@ page import="dataAccess.entity.LoanType" %>
<%@ page import="java.util.ArrayList" %>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <link href="css/style.css" rel="stylesheet">
    <title>پرونده تسهیلاتی</title>
</head>
<body>
<div class="box-in">
    <br>
    <form action="LoanFileServlet">
        <input type="text" name="action" value="retrieve-customer-and-loan-type" hidden>
        <table align="center">
            <tr>
                <td>شماره مشتری</td>
                <td><input type="text" name="customerNumber" value="<%=request.getAttribute("customerNumber")%>"></td>
                <td><input class="button" type="submit" value="بازیابی مشتری"></td>
                <td><a href="real_customer_management.jsp" class="button">بازگشت به صفحه قبل</a></td>
            </tr>
        </table>
    </form>
    <br>
    <hr>
    <br>
    <% int customerExist = (int) request.getAttribute("customerExist");%>
    <%boolean loanTypeExist = (boolean) request.getAttribute("loanTypeExist"); %>

    <c:choose>
        <c:when test="<%=(customerExist==1)%>">
            <form action="LoanFileServlet">
                <input type="text" name="action" value="create" hidden>
                <% RealCustomer realCustomer = (RealCustomer) request.getAttribute("realCustomer"); %>
                <input type="text" name="confirmedCustomerNumber" value="<%=realCustomer.getCustomerNumber()%>" hidden>

                <table>
                    <tr>
                        <td> نام و نام خانوادگی مشتری :</td>
                        <td><%=realCustomer.getFirstName()%><%=realCustomer.getLastName()%>
                        </td>
                    </tr>
                    <tr>
                        <td> نوع تسهیلات :</td>
                        <td>
                            <c:choose>
                                <c:when test="<%=loanTypeExist%>">
                                    <% ArrayList<LoanType> loanTypes = (ArrayList<LoanType>) request.getAttribute("loanTypes"); %>
                                    <select class="my-dropdown" name="loanType">
                                        <% for (LoanType loanType : loanTypes) { %>
                                        <option value="<%= loanType.getLoanTypeId()%>"><%= loanType.getLoanName()%>
                                        </option>
                                        <%}%>
                                    </select>
                                </c:when>
                                <c:otherwise>
                                    <p>داده ای وجود ندارد</p>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <tr>
                        <td> مدت قرارداد :</td>
                        <td><input type="text" name="duration"></td>
                    </tr>
                    <tr>
                        <td>مبلغ قرارداد :</td>
                        <td><input type="text" name="amount"></td>
                    </tr>
                </table>
                <input class="button" type="submit" value="ثبت">
            </form>
        </c:when>
        <c:when test="<%=(customerExist==0)%>">
            <h2>خطا</h2>
            <p>شماره مشتری یافت نشد</p>
        </c:when>
    </c:choose>
    <br>
</div>
</body>
</html>
