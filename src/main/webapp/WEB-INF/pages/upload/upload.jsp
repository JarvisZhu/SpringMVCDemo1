<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglibs.jsp" %>
<html>
<head>
    <script type="text/javascript" src="${root}/resources/script/jquery.js"></script>
    <script type="text/javascript" src="${root}/resources/script/jquery.form.js"></script>
    <title>文件上传测试</title>
    <script type="text/javascript">
        $(document).ready(function(){
            $("#submit").click(function(){
                $("#form").ajaxSubmit({
                    url:"${root}/file/upload",
                    dataType: "json",
                    success:function(data){
                        alert(data.flag);
                        alert(data.msg);
                    }
                });
            });
        });
    </script>
</head>
<body>
<form id="form" action="${root}/file/upload" method="post" enctype="multipart/form-data">
    <input type="file" name="file" />
</form>
<input type="button" id="submit" value="提交" />
</body>
</html>