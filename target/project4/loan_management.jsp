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
    <div class="content">
        <div><h1> نوع عملیات را انتخاب کنید:</h1></div>

        <form name="submitForm" action="" method="get">
            <input class="button" type="button" onclick="doAction('create_loan_type.jsp');"
                   value="تعریف تسهیلات جدید"/>
            <input class="button" type="button" onclick="doAction('create_loan_file.jsp');"
                   value="تشکیل پرونده تسهیلات"/>

            <input class="button" type="button" onclick="doAction('select-task.jsp');"
                   value="بازگشت به صفحه قبل"/>
        </form>

    </div>
</div>
</body>
</html>