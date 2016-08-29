<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="fa">
<head>
    <meta charset="UTF-8">
    <link href="css/style.css" rel="stylesheet">
    <title>جستجوی مشتری</title>
</head>
<body>
<div class="box-in">

<br>
<h1>لطفا اطلاعات مشتری را وارد نمایید</h1>
<br>
<form action="SearchRealCustomerServlet" method="get">
    <table align="center">
        <tr>
            <td> شماره مشتری</td>
            <td><input type="text" name="customerNumber"></td>
        </tr>
        <tr>
            <td> نام</td>
            <td><input type="text" name="firstName"></td>
        </tr>
        <tr>
            <td> نام خانوادگی</td>
            <td><input type="text" name="lastName"></td>
        </tr>
        <tr>
            <td>نام پدر</td>
            <td><input type="text" name="fatherName"></td>
        </tr>
        <tr>
            <td>تاریخ تولد</td>
            <td><input type="text" name="dateOfBirth"></td>
        </tr>
        <tr>
            <td>کد ملی</td>
            <td><input type="text" name="internationalID"></td>
        </tr>
    </table>
    <input type="submit" class="button" value="جستجو">
</form>
<form action="real_customer_management.jsp">
    <button class="button" type="submit"> بازگشت به صفحه قبل</button>
</form>
</div>
</body>
</html>