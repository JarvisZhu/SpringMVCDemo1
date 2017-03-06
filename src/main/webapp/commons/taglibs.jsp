<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!-- 模板继承标签 -->
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%--<%@ taglib uri="/WEB-INF/rapid.tld" prefix="rapid" %>--%>

<!-- 分页页才用 -->
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable" %>

<c:set var="root" value="${pageContext.request.contextPath}"/>
<c:set var="ctx" value="${pageContext.request.contextPath}/manage"/>