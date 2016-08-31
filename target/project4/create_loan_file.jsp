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
        <table>
            <tr>
                <td>شماره مشتری</td>
                <td><input type="text" name="customerId" value="<%=request.getAttribute("customerId")%>"></td>
                <td><input class="button" type="submit" value="بازیابی"></td>
                <td><a href="real_customer_management.jsp" class="form">بازگشت به صفحه قبل</a></td>
            </tr>
        </table>
    </form>
    <br>
    <hr>
    <br>
    <% int customerExists = (int) request.getAttribute("customerExists");%>
    <c:choose>
        <c:when test="<%=(customerExists==1)%>">
            <form action="LoanFileController">
                <input type="text" hidden name="action" value="create">
                <% RealCustomer realCustomerObject = (RealCustomer) request.getAttribute("realCustomerObject"); %>
                <input type="text" hidden name="confirmedCustomerId" value="<%=realCustomerObject.getCustomerId()%>">

                <table>
                    <tr>
                        <td> نام و نام خانوادگی مشتری :</td>
                        <td><%=realCustomerObject.getFirstName()%>
                        </td>
                    </tr>
                    <tr>
                        <td> نوع تسهیلات*</td>
                        <td>
                            <%boolean anyLoanTypeExists = (boolean) request.getAttribute("anyLoanTypeExists"); %>
                            <c:choose>
                                <c:when test="<%=anyLoanTypeExists%>">
                                    <% ArrayList<LoanType> loanTypeObjects = (ArrayList<LoanType>) request.getAttribute("loanTypeObjects"); %>
                                    <select class="my-dropdown" name="loanType">
                                        <% for (LoanType loanTypeObject : loanTypeObjects) { %>
                                        <option value="<%= loanTypeObject.getLoanTypeId()%>"><%= loanTypeObject.getLoanName()%>
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
                        <td> مدت قرارداد*</td>
                        <td><input type="text" name="duration"></td>
                    </tr>
                    <tr>
                        <td>مبلغ قرارداد*</td>
                        <td><input type="text" name="amount"></td>
                    </tr>
                </table>
                <input class="button" type="submit" value="ثبت">
            </form>
        </c:when>
        <c:when test="<%=(customerExists==0)%>">
            <h2>خطا</h2>
            <p>شماره مشتری یافت نشد</p>
        </c:when>
        <c:otherwise>
        </c:otherwise>
    </c:choose>
    <br>
</div>
</body>
</html>
