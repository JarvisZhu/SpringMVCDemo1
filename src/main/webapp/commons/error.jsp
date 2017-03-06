<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
<head>
    <title>ERROR</title>
</head>
<body>
<h1>ERROR</h1>
<%=exception.getMessage()%>
<br />
<%=exception.printStackTrace()%>
</body>
</html>