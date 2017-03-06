<%@ tag pageEncoding="UTF-8"%>
<%@ attribute name="value" required="true" type="java.lang.String" description="value" %>
<%@ attribute name="isOmit" type="java.lang.Boolean" required="false"  %>
<%@ attribute name="omitLength" type="java.lang.Integer" required="false"  %>
<%@ attribute name="startOmit" type="java.lang.Integer" required="false"  %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
	if(jspContext.getAttribute("isOmit") == null) {
		jspContext.setAttribute("isOmit",true); 
	}
	Integer omitLength = (Integer)jspContext.getAttribute("omitLength");
	if(omitLength == null) {
		omitLength = 6;
		jspContext.setAttribute("omitLength",omitLength); 
	} 
	if(jspContext.getAttribute("startOmit") == null) {
		jspContext.setAttribute("startOmit",4); 
	} 
	StringBuffer omitStr = new StringBuffer();
	int len = omitLength;
	for(int i = 0;i < omitLength; i++){
		omitStr.append("*");
	}
	jspContext.setAttribute("omitStr",omitStr); 
%>
<c:choose><c:when test="${isOmit}">${fn:replace(value,fn:substring(value,startOmit,omitLength+startOmit),omitStr)}</c:when><c:when test="${!isOmit}">${value}</c:when></c:choose>

