<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html lang="fa">
<head>
    <meta charset="UTF-8">
    <link href="css/style.css" rel="stylesheet">
    <title>ثبت مشتری جدید</title>
    <script type="text/javascript">
        function check() {
            var firstName = document.getElementsByName("firstName");
            var lastName = document.getElementsByName("lastName");
            var fatherName = document.getElementsByName("fatherName");
            if (firstName.value()==""||lastName.value()==""||fatherName.value()=="") {
                alert('لطفا فیلدهای ضروری را تکمیل کنید!');
            }
        }
    </script>

</head>
<body>
<div class="box-in">
    <div><h1>لطفا اطلاعات مشتری را وارد نمایید:</h1></div>
    <br>
    <form action="RealCustomerServlet" method="get" onsubmit="check()">
        <input type="hidden" name="action" value="create">
        <table align="center">
            <tr>
                <td> نام:</td>
                <td><input type="text" name="firstName"  /></td>
            </tr>
            <tr>
                <td> نام خانوادگی:</td>
                <td><input type="text" name="lastName"/></td>
            </tr>
            <tr>
                <td>نام پدر:</td>
                <td><input type="text" name="fatherName"/></td>
            </tr>
            <tr>
                <td>تاریخ تولد:</td>
                <td><input type="text" name="dateOfBirth"/></td>
            </tr>
            <tr>
                <td>کد ملی:</td>
                <td><input type="text" name="internationalID" /></td>
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