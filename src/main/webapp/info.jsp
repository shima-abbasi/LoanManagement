<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang=fa>
<head>
    <meta charset=UTF-8>
    <link href=css/Style.css rel=stylesheet>
    <title><%=request.getAttribute("header")%>
    </title>
</head>
<body>
<div class=title>
    <h1><%=request.getAttribute("header")%>
    </h1>
</div>

<div class=main-box>
    <br>
    <h2><%=request.getAttribute("header")%>
    </h2>
    <br>
    <p><%=request.getAttribute("text")%>
    </p>
    <br>
    <a href="<%=request.getAttribute("url")%>" class="form">بازگشت </a>
</div>

</body>
</html>

