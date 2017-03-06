<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglibs.jsp" %>
<html>
<head>
    <script type="text/javascript" src="${root}/resources/script/jquery.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            $("#artContent").click(function() {
//                $("#hiddenids").val("朱德方");
                createActivity_dialog.close();
            });
        });
    </script>
</head>
<body>
<input type="button" id="artContent" value="tijiao" />
<input type="text" id="hiddenids" value="" />
</body>
</html>
