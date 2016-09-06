<%@ page import="dataAccess.entity.RealCustomer" %>
<%@ page import="dataAccess.entity.LoanType" %>
<%@ page import="java.util.ArrayList" %>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    RealCustomer realCustomer = (RealCustomer) request.getAttribute("realCustomer");
    String customerName = realCustomer == null ? "" : realCustomer.getFirstName() + " " + realCustomer.getLastName();
    Object objectBoolean = request.getAttribute("loanTypeExist");
    boolean loanTypeExist = objectBoolean == null ? false : true ;
    Object objectInt = request.getAttribute("customerExist");
    int customerExist = objectInt==null ? -1 : 1 ;
%>


<html>
<head>
    <meta charset="UTF-8">
    <link href="css/style.css" rel="stylesheet">
    <title>پرونده تسهیلاتی</title>
</head>
<body>
<div class="box-in">
    <div><h1>لطفا شماره مشتری مورد نظر را وارد کنید:</h1></div>
    <br>
    <form action="LoanFileServlet">
        <input type="text" name="action" value="retrieve-customer-and-loan-type" hidden>
        <table align="center">
            <tr>
                <td>شماره مشتری</td>
                <td><input type="text" name="customerNumber" value="<%=request.getAttribute("customerNumber")%>"></td>
                <td><input class="button" type="submit" value="بازیابی مشتری"></td>
            </tr>
        </table>
    </form>
    <br>
    <br>

    <c:choose>
        <c:when test="<%=(customerExist==1)%>">
            <form action="LoanFileServlet">
                <input type="text" name="action" value="create" hidden>
                <input type="text" name="confirmedCustomerNumber" value="<%=realCustomer.getCustomerNumber()%>" hidden>

                <table align="center">
                    <tr>
                        <td> نام و نام خانوادگی مشتری :</td>
                        <td><%=customerName%>
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
                                    <p>داده ای وجود ندارد!</p>
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
                <div>
                    <input class="button" type="submit" value="ثبت تسهیلات">
                </div>
            </form>
        </c:when>
        <c:when test="<%=(customerExist==0)%>">
            <h3>شماره مشتری یافت نشد!</h3>
        </c:when>
    </c:choose>
    <br>
    <div>
        <form action="loan_management.jsp">
            <button class="button" type="submit">بازگشت به صفحه قبل</button>
        </form>
    </div>
</div>
</body>
</html>
