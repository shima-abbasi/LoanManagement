<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang=fa>
<head>
    <meta charset=UTF-8>
    <link href=css/style.css rel=stylesheet>
    <title><%=request.getAttribute("header")%>
    </title>
</head>
<body>
<div class=box-in>
    <br>
    <h2><%=request.getAttribute("header")%>
    </h2>
    <br>
    <p><%=request.getAttribute("text")%>
    </p>
    <br>
    <form action="<%=request.getAttribute("url")%>">
        <button class="button" type="submit"> بازگشت به صفحه قبل</button>
    </form>
</div>
</body>
</html>

