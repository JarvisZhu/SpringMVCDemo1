<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglibs.jsp" %>
<rapid:override name="head">
    <title>测试校验框架</title>
    <script type="text/javascript">
        $(document).ready(function(){
            /**
            * 也可以在这里添加表单提交的自定义验证，这里要早于Validation验证框架的验证，
             * 这里return true;才会执行Validation验证;
             * 当然，也可以在下面Validation中执行自定义验证;
            */
//            $("#submitButton").click(function(){
//                alert("5");
//                return false;
//            });
        });
    </script>
</rapid:override>
<rapid:override name="main">
    <form method="post" action="${root}/validation/save">
        <input value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="starttimeString" name="starttimeString"  readonly class="required" />
        <input value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="endtimeString" name="endtimeString"  readonly class="required great-than-starttimeString" />
        <input class="required" name="username" maxlength="10"/>
        <input class="required validate-number min-value-1 max-value-100" name="usercount" />
        <input type="submit" id="submitButton" value="提交" />
    </form>
    <script>
        new Validation(document.forms[0],{onSubmit:true, onFormValidate: function(result, form) {
            var finalResult = result;

            //在这里添加自定义验证
            return disableSubmit(finalResult,'submitButton');
        }});
    </script>
</rapid:override>
<%@ include file="base.jsp" %>