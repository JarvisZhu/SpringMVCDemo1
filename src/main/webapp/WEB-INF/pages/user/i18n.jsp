<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglibs.jsp" %>
<html>
<head>
    <title>i18n测试</title>
    <script>
    </script>
</head>
<body>
<spring:message code="test.key" />
<spring:message code="pageKey" />
<spring:message code="notKey" />
<fmt:message key="test.key" />
<fmt:message key="pageKey" />
<fmt:message key="notKey" />
</body>
</html>