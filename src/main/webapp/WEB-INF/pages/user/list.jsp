<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglibs.jsp" %>
<html>
<head>
    <title>测试分页</title>
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
<form id="queryForm" name="queryForm" method="get" action="${root}/page">
    <input type="text" name="username" value="${query.username}"/>
    <input type="submit" value="提交" onclick="getReferenceForm(this).action='${root}/page'"/>

    <input name="batchDel" onclick="javascript:doRestBatchDelete('${root}/batchDelete','items',document.forms.queryForm);" type="button" value="批量删除" />

    <table id="gl_main_menu_title" class="gl_main_menu_title" style="width: 100%;margin: 0 auto;" border="0" cellpadding="0" cellspacing="1" bgcolor="#e4e4e4">
        <tr>
            <th width="7%" height="45" align="center" bgcolor="#f7f9fb">
                <input id="check1" type="checkbox" onclick="setAllCheckboxState('items',this.checked);"/>&nbsp;&nbsp;
            </th>
            <th sortColumn="USERID" width="20%" height="45" align="center" bgcolor="#f7f9fb"><strong>userid</strong></th>
            <th sortColumn="USERNAME" width="25%" height="45" align="center" bgcolor="#f7f9fb"><strong>username</strong></th>
            <th sortColumn="CREATETIME" width="25%" height="45" align="center" bgcolor="#f7f9fb"><strong>createtime</strong></th>
            <th sortColumn="CONTENT" width="25%" height="45" align="center" bgcolor="#f7f9fb"><strong>content</strong></th>
            <th width="25%" height="45" align="center" bgcolor="#f7f9fb"><strong>operation</strong></th>
        </tr>
        <c:forEach items="${page.result}" var="item" varStatus="status">
            <tr>
                <td width="7%" height="30" align="center" bgcolor="#FFFFFF"><input type="checkbox" name="items" value="${item.userid}"></td>
                <td width="20%" height="30" align="center" bgcolor="#FFFFFF"><c:out value='${item.userid}'/>&nbsp;</td>
                <td width="25%" height="30" align="center" bgcolor="#FFFFFF">
                    <c:out value='${item.username}'/><br />
                    <c:if test="${!empty item.tshopset}">
                        <c:forEach items="${item.tshopset}" var="tshop">
                            ${tshop.shopid},${tshop.shopname}<br />
                        </c:forEach>
                    </c:if>
                </td>
                <td width="20%" height="30" align="center" bgcolor="#FFFFFF"><c:out value='${item.createtimeString}'/>&nbsp;</td>
                <td width="20%" height="30" align="center" bgcolor="#FFFFFF"><c:out value='${item.content}'/>&nbsp;</td>
                <td width="25%" height="30" align="center" bgcolor="#FFFFFF">
                    <a name="del"  href="${root}/${item.userid}" onclick="doRestDelete(this,'您确定要删除吗？');return false;">删除</a>
                </td>
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