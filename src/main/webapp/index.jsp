<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fa">
<head>
    <meta charset="UTF-8">
    <link href="css/style.css" rel="stylesheet">
    <title>ورود به سامانه</title>
</head>
<body>
<p id="demo" align="left" > </p>
<script>
    document.getElementById("demo").innerHTML = Date();
</script>
<div id="LoginHeader"></div>
<div>
    <form action="select-task.jsp">
        <button id="Loginbutton"><span>ورود به سامانه </span></button>
    </form>
</div>
</body>
</html>