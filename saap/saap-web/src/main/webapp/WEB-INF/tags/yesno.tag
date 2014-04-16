<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ attribute name="value" required="true" rtexprvalue="true" %> 
<c:if test="${value }"><i class="icon-ok green"></i></c:if>
<c:if test="${!value }"><i class="icon-remove red"></i></c:if>