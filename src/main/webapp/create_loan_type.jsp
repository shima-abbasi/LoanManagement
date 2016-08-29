<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>تعریف نوع تسهیلات</title>
</head>
<body>
<div class=box-in>
    <br>
    <h1>لطفا تسهیلات مورد نظر خود را تعریف کنید</h1>
    <br>
    <form action="/createLoanTypeServlet" method="post">
        <table align="center">
            <tr>
                <td> نام نوع تسهیلات </td>
                <td> <input type="text" name="loanName" required="required" oninvalid="alert('لطفا نام تسهیلات را وارد کنید')"> </td>
            </tr>
            <tr>
                <td> نرخ سود </td>
                <td><input type="text" name="interestRate" required="required" oninvalid="alert('لطفا نرخ سود را وارد کنید')"/></td>
            </tr>
        </table>
        <br>
        <br>
        <input class="button" type="submit" value="اضافه کردن شروط اعطا">
    </form>
    <form action="loan_management.jsp">
        <button class="button" type="submit"> بازگشت به صفحه قبل</button>
    </form>
</div>
</body>
</html>
