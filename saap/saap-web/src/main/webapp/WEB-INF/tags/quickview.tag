<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ attribute name="moduleCode" required="true" rtexprvalue="true" %>
<%@ attribute name="defaultUrl" required="false" rtexprvalue="true" %>
<div class="quickview">
  <ul>
    <li><span class="brand"><i class="icon-tags"></i></span></li>
    <li <c:if test="${empty param.quickView }">class="active"</c:if>><a href="${empty defaultUrl?'index.do':defaultUrl }">
      <s:message code="quickView.all" /></a></li>
  </ul>
</div>
