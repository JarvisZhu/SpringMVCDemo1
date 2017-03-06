<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglibs.jsp" %>
<rapid:override name="head">
    <title>测试校验框架</title>
    <script type="text/javascript">
        var createActivity_dialog = null;

        /** 待弹出框关闭后获取弹出框里的内容，artDialog的一点好处就是父页和弹出框可以相互调用函数 */
        function getValueFromArtDialog(){
            alert($("#hiddenids").val());
        }

        $(document).ready(function(){
            $("#art").click(function() {
                $.get("${root}/widgets/artContent", {ts: (new Date()).getTime()}, function(data){
                    art.dialog({
                        lock:true,
                        opacity:0.3,
                        title: '编辑活动',
                        height: 'auto',
                        left: '50%',
                        top: '50%',
                        content: data,
                        esc: true,
                        init: function(){
                            createActivity_dialog = this;
                        },
                        close: function(){
                            createActivity_dialog = null;
                            getValueFromArtDialog();
                        }
                    });
                });
            });
        });
    </script>
</rapid:override>
<rapid:override name="main">
<input type="button" id="art" value="tijiao" />
</rapid:override>
<%@ include file="base.jsp" %>