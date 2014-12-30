<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.littcore.com/core" prefix="li" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ attribute name="table" required="true" rtexprvalue="true" type="com.litt.saap.core.module.quickview.model.table.Table" %>
<%@ attribute name="rsList" required="false" rtexprvalue="true" type="java.util.Collection" %>
<%@ attribute name="var" required="true" rtexprvalue="false" %>
<%@ variable name-from-attribute="var" alias="v" scope="NESTED" variable-class="java.lang.Object" %>
<table class="table table-striped table-bordered table-hover datatable">
<thead>
  <tr>
  <c:forEach items="${table.columnList }" var="column">
    <th class="${column.hide?'hide':'' } ${'checkbox' eq column.format?'checkCol':'' } column_${column.name }"  >
      <c:choose>  
        <c:when test="${'checkbox' eq column.format }">
          <input type="checkbox" id="checkAll" name="checkAll" />
        </c:when>
        <c:otherwise><s:message code="${column.title }" /></c:otherwise>
      </c:choose>
    </th> 
  </c:forEach>
  <th><s:message code="common.action" /></th>
  </tr>
</thead>
<tbody>
  <c:forEach items="${requestScope[table.dataset].rsList }" var="row" varStatus="rowStatus">
      <c:set var="v" value="${row }" />
                    <tr>
                      <c:forEach items="${table.columnList }" var="column">
                      <td class="${column.hide?'hide':'' } ${'checkbox' eq column.format?'checkCol':'' } column_${column.name }">
                        <c:choose>
                          <c:when test="${'checkbox' eq column.format }">
                            <input type="checkbox" class="checkItem" name="primaryIds" value="${row[column.name] }" />
                          </c:when>           
                          <c:when test="${'date' eq column.dataType}">
                            <fmt:formatDate value="${row[column.name]}" pattern="${column.format }"/>
                          </c:when>
                          <c:when test="${'decimal' eq column.dataType}">
                            <fmt:formatNumber value="${row[column.name]}" pattern="${column.format }"/>
                          </c:when>                                           
                          <c:otherwise><c:out value="${row[column.name]}"></c:out></c:otherwise>
                        </c:choose>   
                      </td>
                      </c:forEach>
                      <td>                      
                        <jsp:doBody></jsp:doBody>
                      </td>
                    </tr> 
  </c:forEach>
</tbody>
</table>