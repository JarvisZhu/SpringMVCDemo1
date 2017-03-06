<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglibs.jsp" %>
<html>
<head>
    <title>提交表单测试</title>
</head>
<body>
<form action="${root}/insert" method="post">
    名字:<input type="text" name="username" maxlength="16" />
    usercount:<input type="text" name="usercount" maxlength="16" />
    createtime:<input type="text" name="createtimeString" value="2015-09-09 12:12:12"/>
    content:<input type="text" name="content" maxlength="16" />
    <input type="submit" value="提交" />
</form>
</body>
</html>