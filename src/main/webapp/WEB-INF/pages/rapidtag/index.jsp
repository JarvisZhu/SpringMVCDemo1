<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--
rapid标签是在taglibs文件里定义的，我以为由于一步一步的在page下的base.jsp里已经引入了，所以这里就不用再引入了，
但是总是报错：
org.apache.catalina.core.StandardWrapperValve.invoke Servlet.service() for servlet [mvc-dispatcher] in context with path [] threw exception [/WEB-INF/pages/rapidtag/index.jsp (line: 21, column: 0) /WEB-INF/pages/rapidtag/../base.jsp (line: 3, column: 0) /commons/taglibs.jsp (line: 10, column: 51) The prefix rapid specified in this tag directive has been previously used by an action in file /WEB-INF/pages/rapidtag/index.jsp line 18.] with root cause
org.apache.jasper.JasperException: /WEB-INF/pages/rapidtag/index.jsp (line: 21, column: 0) /WEB-INF/pages/rapidtag/../base.jsp (line: 3, column: 0) /commons/taglibs.jsp (line: 10, column: 51) The prefix rapid specified in this tag directive has been previously used by an action in file /WEB-INF/pages/rapidtag/index.jsp line 18.
意思好像是标签在引入前就已经被使用了。我晕死。
现在才明白为什么HY的项目里为什么几乎每个页面都引入taglibs文件了，它不怕重复引入，但就怕用到的页面没被引入，
不要以为使用了rapid继承标签就省事了，用到的页面还是自己要引入。
meta.jsp里引入的是公共的JS、CSS等静态资源文件，而tag、tld文件由于在JSP页面被编译成class文件时就要用到，所以跟静态资源的引入还不一样；
-->
<%@ include file="/commons/taglibs.jsp" %>
<rapid:override name="head">
    <title>测试rapid继承标签</title>
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
</rapid:override>
<rapid:override name="main">
    <h1>内容</h1>
</rapid:override>
<%@ include file="base.jsp" %>