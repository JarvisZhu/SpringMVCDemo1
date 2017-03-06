<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglibs.jsp" %>
<html>
<head>
    <title>测试HQL级联</title>
    <link type="text/css" rel="stylesheet" href="${root}/resources/script/simpletable/simpletable.css">

    <script type="text/javascript" src="${root}/resources/script/jquery.js"></script>
    <script type="text/javascript" src="${root}/resources/script/rest.js"></script>
    <script type="text/javascript" src="${root}/resources/script/application.js"></script>
    <script type="text/javascript" src="${root}/resources/script/simpletable/simpletable.js"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            // 分页需要依赖的初始化动作
            window.simpleTable = new SimpleTable('queryForm', ${page.thisPageNumber}, ${page.pageSize}, '${pageRequest.sortColumns}');
        });
    </script>
</head>
<body>
<form id="queryForm" name="queryForm" method="get" action="${root}/tshop/sqlpage">
    shopname:<input type="text" name="shopname" value="${query.shopname}"/><br />
    username:<input type="text" name="username" value="${query.username}"/><br />
    userid:<input type="text" name="userid" value="${query.userid}"/><br />
    usercount:<input type="text"name="usercount"value="${query.usercount}"/><br />
    <input type="submit" value="提交" onclick="getReferenceForm(this).action='${root}/tshop/sqlpage'"/>


    <table id="gl_main_menu_title" class="gl_main_menu_title" style="width: 100%;margin: 0 auto;" border="0" cellpadding="0" cellspacing="1" bgcolor="#e4e4e4">
        <tr>
            <th width="7%" height="45" align="center" bgcolor="#f7f9fb">
                <input id="check1" type="checkbox" onclick="setAllCheckboxState('items',this.checked);"/>&nbsp;&nbsp;
            </th>
            <th sortColumn="SHOPID" width="20%" height="45" align="center" bgcolor="#f7f9fb"><strong>shopid</strong></th>
            <th sortColumn="SHOPNAME" width="25%" height="45" align="center" bgcolor="#f7f9fb"><strong>shopname</strong></th>
            <th sortColumn="t.userid" width="25%" height="45" align="center" bgcolor="#f7f9fb"><strong>userid</strong></th>
            <th sortColumn="u.username" width="25%" height="45" align="center" bgcolor="#f7f9fb"><strong>username</strong></th>
            <th sortColumn="u.usercount" width="25%" height="45" align="center" bgcolor="#f7f9fb"><strong>usercount</strong></th>
            <!-- 上面排序字段值的写法，由于是sql查询，所以要写字段名，表名前缀也要和后台SQL语句中的一致。 -->
        </tr>
        <c:forEach items="${page.result}" var="item" varStatus="status">
            <tr>
                <td width="7%" height="30" align="center" bgcolor="#FFFFFF"><input type="checkbox" name="items" value="${item.shopid}"></td>
                <td width="20%" height="30" align="center" bgcolor="#FFFFFF"><c:out value='${item.shopid}'/>&nbsp;</td>
                <td width="25%" height="30" align="center" bgcolor="#FFFFFF"><c:out value='${item.shopname}'/>&nbsp;</td>
                <td width="25%" height="30" align="center" bgcolor="#FFFFFF"><c:out value='${item.userid}'/>&nbsp;</td>
                <td width="25%" height="30" align="center" bgcolor="#FFFFFF"><c:out value='${item.username}'/>&nbsp;</td>
                <td width="25%" height="30" align="center" bgcolor="#FFFFFF"><c:out value='${item.usercount}'/>&nbsp;</td>
            </tr>
        </c:forEach>
    </table>

    <table id="page-table" cellspacing="0" width="100%">
        <tbody>
        <tr>
            <td align="right" nowrap="true">
                <div id="turn-page">
                    <simpletable:pageToolbar page="${page}"/>
                </div>
            </td>
        </tr>
        </tbody>
    </table>
</form>
</body>
</html>