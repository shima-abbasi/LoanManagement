<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="fa">
<head>
    <meta charset="UTF-8">
    <link href="css/style.css" rel="stylesheet">
    <title>انتخاب نوع مشتری</title>
</head>
<body>
<div class="box-in">
    <div class="content">
        <div><h1> نوع عملیات را انتخاب کنید</h1></div>
        <form action="create_loan_type.jsp">
            <button class="button" type="submit">تعریف تسهیلات جدید</button>
        </form>
        <form action="/LoanFileServlet">
            <input name="action" value="first-run" hidden>
            <button class="button" type="submit">تشکیل پرونده تسهیلاتی</button>
        </form>
        <form action="select-task.jsp">
            <button class="button" type="submit"> بازگشت به صفحه قبل</button>
        </form>
    </div>
</div>
</body>
</html>