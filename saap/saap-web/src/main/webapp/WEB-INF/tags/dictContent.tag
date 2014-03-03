<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ attribute name="dictType" required="true" rtexprvalue="true" %>  
<%@ attribute name="dictValue" required="true" rtexprvalue="true" %> 
<s:message code="dictparam.${dictType }.${dictValue }" />