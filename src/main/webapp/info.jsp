<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang=fa>
<head>
    <meta charset=UTF-8>
    <link href=css/style.css rel=stylesheet>
    <title>پیام</title>
    <script type="text/javascript">
        function doAction(actionType) {
            document.forms['submitForm'].action = actionType;
            document.forms['submitForm'].submit();
        }
    </script>
</head>
<body>
<div class=box-in>
    <h1><%=request.getAttribute("text")%>
    </h1>
    <br>
    <form name="submitForm" action="">
        <input class="button" type="button" onclick="doAction('<%=request.getAttribute("url")%>')"
               value="بازگشت به صفحه قبل"/>
    </form>
</div>
</body>
</html>

