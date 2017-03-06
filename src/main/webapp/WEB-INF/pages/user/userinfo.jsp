<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglibs.jsp" %>
<html>
<head>
    <title>IDEA SpringMVC测试</title>
</head>
<body>
<h1>${userid}</h1>
<c:forEach items="${list}" var="str">
    ${str}
</c:forEach>
</body>
</html>