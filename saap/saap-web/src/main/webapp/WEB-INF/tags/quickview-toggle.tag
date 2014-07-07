<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ attribute name="table" required="true" rtexprvalue="true" type="com.litt.saap.core.module.quickview.model.table.Table" %>
<div class="btn-group dropdown-checkbox">
  <a class="btn dropdown-toggle btn-small" data-toggle="dropdown" href="#"> <i class="icon-list-alt"></i> 
  <s:message code="quickview.func.columnToggle" /> <span class="caret"></span>
  </a>
  <ul class="dropdown-menu dropdown-checkbox-menu">
    <!-- dropdown menu links -->
    <c:forEach items="${table.columnList }" var="column"  varStatus="_status">
      <li><label data-stopPropagation="true"><input type="checkbox" value="${column.name }" data-column="${_status.index }" class="quickview-column-toggle" ${column.hide?"":"checked=\"checked\"" } />&nbsp;<s:message code="${column.title }"/></label></li>
    </c:forEach>
  </ul>
</div>
<script type="text/javascript">
$(document).ready(function(){
  $(".quickview-column-toggle").click(function(){
    var columnName = $(this).val();
    $(".column_"+columnName).toggle();
  });
  
});
</script>