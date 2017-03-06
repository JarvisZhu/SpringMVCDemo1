<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglibs.jsp" %>
<html>
<head>
    <title>数据库测试</title>
</head>
<body>
userid = ${tuser.userid}, username = ${tuser.username}
<br/>
<ul>
    <c:forEach items="${tuserlist}" var="item">
        <li>id = ${item.userid}, name = ${item.username}</li>
    </c:forEach>
</ul>
<br/>
<ul>
    <c:forEach items="${list}" var="item">
        <li>id = ${item.userid}, name = ${item.username}</li>
    </c:forEach>
</ul>
<br/>
影响结果条数 = ${result}
</body>
</html>