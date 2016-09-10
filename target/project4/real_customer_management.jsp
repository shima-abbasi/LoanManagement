<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="fa">
<head>
    <meta charset="UTF-8">
    <link href="css/style.css" rel="stylesheet">
    <title>انتخاب نوع مشتری</title>
    <script type="text/javascript">
        function doAction(actionType) {
            document.forms['submitForm'].action = actionType;
            document.forms['submitForm'].submit();
        }
    </script>
</head>
<body>
<div class="box-in">
    <div><h1>لطفا نوع عملیات مورد نظر را انتخاب کنید:</h1></div>
    <form name="submitForm" action="">
        <input class="button" type="button" onclick="doAction('create_real_customer.jsp')" value="تعریف مشتری جدید"/>
        <input class="button" type="button" onclick="doAction('search_real_customer.jsp')" value="جستجوی مشتری"/>
        <input class="button" type="button" onclick="doAction('select-task.jsp')" value=" بازگشت به صفحه قبل"/>
    </form>
</div>
</body>
</html>