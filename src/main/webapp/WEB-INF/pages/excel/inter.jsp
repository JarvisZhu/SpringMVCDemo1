<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglibs.jsp" %>
<html>
<head>
    <script type="text/javascript" src="${root}/resources/script/jquery.js"></script>
    <script type="text/javascript" src="${root}/resources/script/application.js"></script>
    <title>导出Excel</title>
</head>
<body>
<form id="queryForm" name="queryForm" method="get" style="display: inline;">
</form>
<input type="button" onclick="javascript:outExcel('${root}/excel/downjxl.xls',window.event);" value="导出Excel" />
</body>
</html>