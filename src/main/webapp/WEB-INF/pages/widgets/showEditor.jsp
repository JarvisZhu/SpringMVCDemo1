<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglibs.jsp" %>
<rapid:override name="head">
    <title>展示富文本</title>
</rapid:override>
<rapid:override name="main">
    ${content}
</rapid:override>
<%@ include file="base.jsp" %>