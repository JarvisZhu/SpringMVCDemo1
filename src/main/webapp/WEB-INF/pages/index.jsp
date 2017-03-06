<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglibs.jsp" %>
<html>
<head>
    <title>测试引入taglibs文件</title>
    <script type="text/javascript" src="${root}/resources/script/jquery.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            alert(4);
        });
    </script>
</head>
<body>
<h1>1${ctx}</h1>
<h1>2${root}</h1>
<img src="${root}/resources/image/1.jpg" />
</body>
</html>