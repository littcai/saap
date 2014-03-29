<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="value" required="false" rtexprvalue="true" %>
<c:set var="_returnUrl" value="${header.referer}" />
<c:if test="${not empty param.returnUrl }">
	<c:set var="_returnUrl" value="${param.returnUrl}" />	
</c:if>
<c:if test="${empty _returnUrl }">
	<c:set var="_returnUrl" value="${value}" />
</c:if>
"${_returnUrl }"