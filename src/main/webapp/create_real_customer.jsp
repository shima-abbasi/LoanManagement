<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html lang="fa">
<head>
    <meta charset="UTF-8">
    <link href="css/style.css" rel="stylesheet">
    <title>ثبت مشتری جدید</title>
</head>
<body>
<div class="box-in">
    <br>
    <div><h1>لطفا اطلاعات مشتری را وارد نمایید:</h1></div>
    <br>
    <form action="RealCustomerServlet" method="get">
        <input type="hidden" name="action" value="create">
        <table align="center">
            <tr>
                <td> نام</td>
                <td><input type="text" name="firstName" required="required"
                           oninvalid="alert('لطفا نام مشتری را وارد کنید!')"/></td>
            </tr>
            <tr>
                <td> نام خانوادگی</td>
                <td><input type="text" name="lastName" required="required"
                           oninvalid="alert('لطفا نام خانوادگی مشتری را وارد کنید!')"/></td>
            </tr>
            <tr>
                <td>نام پدر</td>
                <td><input type="text" name="fatherName" required="required"
                           oninvalid="alert('لطفا نام پدر را وارد کنید!')"/></td>
            </tr>
            <tr>
                <td>تاریخ تولد</td>
                <td><input type="text" name="dateOfBirth" required="required"
                           oninvalid="alert('لطفا تاریخ تولد را وارد کنید!')"/></td>
            </tr>
            <tr>
                <td>کد ملی</td>
                <td><input type="text" name="internationalID" required="required"
                           oninvalid="alert('لطفا کد ملی را وارد کنید!')"/></td>
            </tr>
        </table>
        <input type="submit" class="button" value="ثبت اطلاعات">
    </form>
    <div>
        <form action="real_customer_management.jsp">
            <button class="button" type="submit"> بازگشت به صفحه قبل</button>
        </form>
    </div>
</div>
</body>
</html>