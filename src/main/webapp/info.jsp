<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang=fa>
<head>
    <meta charset=UTF-8>
    <link href=css/style.css rel=stylesheet>
    <title>پیام
    </title>
</head>
<body>
<div class=box-in>
    <br>
    <br>
    <h1><%=request.getAttribute("text")%>
    </h1>
    <br>
    <form action="<%=request.getAttribute("url")%>">
        <button class="button" type="submit"> بازگشت به صفحه قبل</button>
    </form>
</div>
</body>
</html>

