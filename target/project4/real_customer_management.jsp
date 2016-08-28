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
        <form action="create_real_customer.jsp">
            <button class="button" type="submit">تعریف مشتری جدید</button>
        </form>
        <form action="search_real_customer.jsp">
            <button class="button" type="submit"> جستجوی مشتری</button>
        </form>
        <form action="select-task.jsp">
            <button class="button" type="submit"> بازگشت به صفحه قبل</button>
        </form>
    </div>
</div>
</body>
</html>