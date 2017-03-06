<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglibs.jsp" %>
<html>
<head>
    <title>Ajax测试</title>
    <script type="text/javascript" src="${root}/resources/script/jquery.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            $.get("${root}/ajax", {username:"更新-1978909194", timestamp:(new Date()).getTime()}, function(result){
                alert(result.code);
                var items = result.list;
                for(var i = 0; i < items.length; i++) {
                    alert(items[i].userid + ", " + items[i].username);
                }
                alert("Over");
            });
        });
    </script>
</head>
<body>
<img src="${root}/resources/image/1.jpg" />
</body>
</html>