<%@ page import="dataAccess.entity.LoanType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <script type="text/javascript" src="table_script.js"></script>
    <title>تعریف شروط اعطا</title>
</head>
<body>
<div class=box-in>
    <div><h2>مشخصات تسهیلات ایجاد شده:</h2></div>
    <br>
    <table align="center">
        <%
            LoanType loanType = (LoanType) request.getAttribute("LoanType");
        %>
        <tr>
            <td>نام تسهیلات:</td>
            <td><%=loanType.getLoanName()%>
            </td>
        </tr>
        <tr>
            <td>نرخ سود تسهیلات:</td>
            <td><%=loanType.getInterestRate()%>
            </td>
        </tr>
    </table>
    <hr>
    <br>
    <h2>لطفا مشخصات شروط اعطای مورد نظر را وارد نمایید:</h2>
    <table align="center">
        <tr>
            <td> نام:</td>
            <td><input type="text" id="grantName"
                >
            </td>
        </tr>
        <tr>
            <td> حداقل مدت قرارداد:</td>
            <td><input type="text" id="minDuration"
            ></td>
        </tr>
        <tr>
            <td> حداکثر مدت قرارداد:</td>
            <td><input type="text" id="maxDuration"
            ></td>
        </tr>
        <tr>
            <td> حداقل مبلغ قرارداد:</td>
            <td><input type="text" id="minAmount"
            ></td>
        </tr>
        <tr>
            <td> حداکثر مبلغ قرارداد:</td>
            <td><input type="text" id="maxAmount"
            ></td>
        </tr>
    </table>
    <br>
    <input class="button" type="button" value="اضافه کردن شروط" onclick="addRow()">
    <br>
    <hr>

    <br>
    <form action="GrantConditionServlet" method="get">
        <input type="hidden" name="loanName" value="<%= request.getParameter("loanTypeName")%>">
        <input type="hidden" name="interestRate" value="<%= request.getParameter("interestRate")%>">
        <table class="result-table" id="GrantConditionShowTable"></table>
        <br>
    </form>
    <div>
        <form action="create_loan_type.jsp">
            <button class="button" type="submit">بازگشت به صفحه قبل</button>
        </form>
    </div>
</div>

</body>
</html>
