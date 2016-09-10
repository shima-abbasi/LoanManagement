<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="fa">
<head>
    <meta charset="UTF-8">
    <link href="css/style.css" rel="stylesheet">
    <title>انتخاب نوع عملیات</title>
    <script type="text/javascript">
        function doAction(actionType) {
            document.forms['submitForm'].action = actionType;
            document.forms['submitForm'].submit();

        }
    </script>
</head>
<body>
<div class="box-in">
    <div><h1>لطفا پنل مدیریت مورد نظر را انتخاب کنید:</h1></div>
    <br>
    <form action="" name="submitForm">
        <input class="button" type="button" onclick="doAction('real_customer_management.jsp')" value="مدیریت مشتری"/>
        <input class="button" type="button" onclick="doAction('loan_management.jsp')" value="مدیریت تسهیلات"/>
    </form>
</div>
</body>
</html>