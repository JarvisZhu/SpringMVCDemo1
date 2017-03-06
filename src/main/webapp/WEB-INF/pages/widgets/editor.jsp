<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglibs.jsp" %>
<rapid:override name="head">
    <title>测试富文本</title>
    <script type="text/javascript" charset="utf-8">
        window.UEDITOR_HOME_URL = "${root}/resources/script/ueditor/";  //UEDITOR_HOME_URL、config、all这三个顺序不能改变
    </script>
    <script type="text/javascript" charset="utf-8" src="${root}/resources/script/ueditor/editor_config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${root}/resources/script/ueditor/editor_all.js"></script>
    <script type="text/javascript" charset="utf-8" src="${root}/resources/script/ueditor/_examples/editor_api.js"></script>
</rapid:override>
<rapid:override name="main">
    <form action="${root}/widgets/post" method="post">
        <div style="margin-left: 500px; margin-top: 300px;">
            <input type="submit" value="提交" /><br />
            <hr />
            <textarea id="content" name="content" style="width: 300px;height: 200px;"></textarea>
        </div>
    </form>
<script>
    var ME = UE.getEditor('content',
    {
//        initialFrameWidth: 400,
//        initialFrameHeight: 500,
        toolbars:[
            ['fullscreen', 'undo', 'redo', '|',
                'bold', 'underline', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch','autotypeset', '|', 'forecolor', 'backcolor', 'cleardoc', '|',
                'rowspacingtop', 'rowspacingbottom','lineheight','|',
                'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|',
                'imagenone', 'imageleft', 'imageright','imagecenter', '|',
                'customstyle', 'paragraph', 'fontsize', '|',
                'emotion','date', 'time',
                'spechars', '|',
                'preview', 'searchreplace', 'insertimage', 'link']
        ]});
</script>
</rapid:override>
<%@ include file="base.jsp" %>