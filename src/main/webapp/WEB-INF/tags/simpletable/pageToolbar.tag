<%@ tag pageEncoding="UTF-8"%>
<%@ attribute name="page" required="true" type="com.rapid.framework.page.Page" description="Page.java" %>
<%@ attribute name="pageSizeSelectList" type="java.lang.Number[]" required="false"  %>
<%@ attribute name="isShowPageSizeList" type="java.lang.Boolean" required="false"  %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	// set default values
	Integer[] defaultPageSizes = new Integer[]{10,50,100};
	if(jspContext.getAttribute("pageSizeSelectList") == null) {
		jspContext.setAttribute("pageSizeSelectList",defaultPageSizes); 
	}
	
	if(jspContext.getAttribute("isShowPageSizeList") == null) {
		jspContext.setAttribute("isShowPageSizeList",true); 
	} 
%>
<style>
<!--
.ui-tx-page{TEXT-ALIGN: center; letter-spacing:-1px;padding:10px 0; COLOR: #999999; FONT-SIZE: 12px; width:100%;}
.ui-tx-page SPAN{PADDING-BOTTOM: 6px; PADDING-LEFT: 12px; WIDTH:auto; border:1px solid #ff5500; PADDING-RIGHT: 12px; BACKGROUND: #ffeee5; COLOR: #ff5500; MARGIN-LEFT:0px; PADDING-TOP: 6px}
.ui-tx-page A{PADDING-BOTTOM: 6px;border:1px solid #cccccc; PADDING-LEFT: 12px; WIDTH:auto; PADDING-RIGHT: 12px; COLOR: #0063dc; MARGIN-LEFT: 0px;  PADDING-TOP: 6px; }
.ui-tx-page A:hover{BACKGROUND: #ffeee5; COLOR: #ff5500; TEXT-DECORATION: none; border:1px solid #ff5500;padding-bottom: 6px;padding-left: 12px;padding-right: 12px;padding-top: 6px;}
.ui_input{width:30px; height:18px; line-height:18px; text-align:center; border:1px solid #999999;}

.ui-tx-page .u_no{PADDING-BOTTOM: 6px; border:1px solid #cccccc; PADDING-LEFT: 12px; WIDTH: 5px; PADDING-RIGHT: 12px; COLOR: #cccccc; MARGIN-LEFT: 0px;  PADDING-TOP: 6px; overflow:hidden;}
.ui-tx-page .u_no:hover{PADDING-BOTTOM: 6px; border:1px solid #cccccc; PADDING-LEFT: 12px; WIDTH: 5px; PADDING-RIGHT: 12px; COLOR: #cccccc; MARGIN-LEFT: 0px;  PADDING-TOP: 6px; overflow:hidden; background:#ffffff;}

-->
</style>
<script type="text/javascript">
<!--
function calMaxPageNum(recordCount, pagesize, obj){
    var inputObj = obj;
    if(null == obj || "undefined" == obj || "undefined" == obj.value || "" == obj.value){
        return;
    }else if("udnefined" == recordCount || "undefined" == pagesize || recordCount < 0 || pagesize <= 0){
        return;
    }
    
    var inputval = obj.value;
    var maxNum = recordCount / pagesize;
    maxNum += (recordCount % pagesize > 0 ? 1 : 0);
    
    if(parseInt(inputval) > maxNum){
        obj.value = parseInt(maxNum);
    }
}
//-->
</script>
<table width="100%"  border="0" cellspacing="0" class="gridToolbar">
  <tr>
	<td>
		<div class="box" align="center">
		
			<div  class="leftControls" >
				<jsp:doBody/>
			</div>
			
			<div class="ui-tx-page" style="border:0px solid red;">
				 
				<!--<c:choose>
				  <c:when test="${page.firstPage}">
				   <a style="color:#cccccc">
				    <img src="<c:url value='/resources/script/simpletable/images/bt11.gif'/>" style="border:0" />&nbsp;第一页
				   </a>
				  </c:when>
				  <c:otherwise>
				    <a href="javascript:simpleTable.togglePage(1);">
				      <img src="<c:url value='/resources/script/simpletable/images/bt1.gif'/>" style="border:0" >&nbsp;第一页
				    </a>
				  </c:otherwise>
				</c:choose>-->
				
				<c:choose>
				 <c:when test="${page.hasPreviousPage}">
				  <a href="javascript:simpleTable.togglePage(${page.previousPageNumber});" style="margin-right:4px;">
				   <img src="<c:url value='/resources/script/simpletable/images/bt1.gif'/>" style="border:0" >&nbsp;上一页
				  </a>
				 </c:when>
				<c:otherwise>
				  <a style="color:#cccccc;margin-right:4px;">
				    <img src="<c:url value='/resources/script/simpletable/images/bt11.gif'/>" style="border:0" >&nbsp;上一页
				  </a>
				</c:otherwise>
				</c:choose>
				
				<c:forEach var="item" items="${page.linkPageNumbers}">
					<c:choose>
					    <c:when test="${item - page.thisPageNumber == (-3)}">&nbsp;.&nbsp;.&nbsp;.&nbsp;</c:when>
					    <c:when test="${item - page.thisPageNumber == (-2) or item - page.thisPageNumber == (-1)}"><a href="javascript:simpleTable.togglePage(${item});">${item}</a></c:when>
						<c:when test="${item == page.thisPageNumber}"><span>${item}</span></c:when>
						<c:when test="${item - page.thisPageNumber == 1 or item - page.thisPageNumber == 2}"><a href="javascript:simpleTable.togglePage(${item});">${item}</a></c:when>
						<c:when test="${item - page.thisPageNumber == (3)}">&nbsp;.&nbsp;.&nbsp;.&nbsp;</c:when>
						<c:otherwise></c:otherwise>
					</c:choose>
				</c:forEach>
				
				<c:choose>
				<c:when test="${page.hasNextPage}">
				 <a href="javascript:simpleTable.togglePage(${page.nextPageNumber});">下一页&nbsp;<img src="<c:url value='/resources/script/simpletable/images/bt2.gif'/>" style="border:0" ></a></c:when>
				<c:otherwise>
				<a style="color:#cccccc">下一页&nbsp;<img src="<c:url value='/resources/script/simpletable/images/bt12.gif'/>" style="border:0" ></a>
				</c:otherwise>
				</c:choose>
				<!-- 
				<c:choose>
				<c:when test="${page.lastPage}">
				  <a style="color:#cccccc">最后一页&nbsp;
				  <img src="<c:url value='/resources/script/simpletable/images/bt12.gif'/>" style="border:0" ></a>
				 </c:when>
				<c:otherwise>
				<a href="javascript:simpleTable.togglePage(${page.lastPageNumber});">
				       最后一页&nbsp;<img src="<c:url value='/resources/script/simpletable/images/bt2.gif'/>" style="border:0" >
				</a>
				</c:otherwise>
				</c:choose> -->
				<!--<a>${page.thisPageFirstElementNumber} 到 ${page.thisPageLastElementNumber}条  共 ${page.totalCount}条</a>-->
				&nbsp;<a>共 ${page.totalCount}条</a>
				第&nbsp;<input id="gotobtn" class="ui_input validate-integer" maxlength="9" value="${page.thisPageNumber}" onblur="calMaxPageNum(${page.totalCount},${page.pageSize},this);" name="gotobtn" onkeyup="value=value.replace(/[^\d]/g,'')" type="text" />&nbsp;页
				<input value="跳转" name="" style="width:50px;height:25px;cursor: pointer;" onclick="javascript:simpleTable.togglePage(document.getElementById('gotobtn').value == '' ? 1:document.getElementById('gotobtn').value);" type="button" />
				
			</div>
			<!--<div style="height:26px;line-height:26px;padding-bottom:5px;">
				<c:if test="${isShowPageSizeList}">
				<select onChange="simpleTable.togglePageSize(this.value)">
					<c:forEach var="item" items="${pageSizeSelectList}">
						<option value="${item}" ${page.pageSize == item ? 'selected' : '' }>${item}</option>
					</c:forEach> 
				</select>
				</c:if>
			</div>-->
		</div>
	</td>
  </tr>
</table>