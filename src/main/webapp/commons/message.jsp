<%@ include file="/commons/taglibs.jsp" %>

<%-- Error Messages --%>
<c:if test="${flash.success != null}">
	<div class="flash_success" style="width: 100%;height: 40px;background-color: yellow">
		${flash.success}<br/>
	</div>    
</c:if>

<%-- Info Messages --%>
<c:if test="${flash.error != null}">
	<div class="flash_error" style="width: 100%;height: 40px;background-color: red">
		${flash.error}<br/>
	</div> 
</c:if>
