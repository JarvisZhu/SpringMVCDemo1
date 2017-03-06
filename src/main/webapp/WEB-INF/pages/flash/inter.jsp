<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ include file="/commons/message.jsp" %>
<html>
<head>
    <title>消息提醒测试</title>
</head>
<body>
<form action="${root}/flash/go2" method="post">
    <input type="submit" value="提交" />
</form>
</body>
</html>