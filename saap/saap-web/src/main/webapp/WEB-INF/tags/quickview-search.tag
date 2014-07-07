<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ attribute name="search" required="true" rtexprvalue="true" type="com.litt.saap.core.module.quickview.model.search.Search" %>
<table>
  <c:forEach items="${search.paramGroupList }" var="paramGroup">
    <tr>
      <c:forEach items="${paramGroup.fieldList }" var="field">
      <td><s:message code="${field.displayName }"></s:message></td>
      <td>
        <c:if test="${'text' eq field.input.type }">
          <input type="text" id="${field.key }" name="${field.key }" value="${param[field.key] }" />
        </c:if>
        <c:if test="${'datepicker' eq field.input.type }">
          <div class="input-append date datepicker" data-date-format="yyyy-mm-dd">
            <input type="text" id="${field.key }" name="${field.key }" value="${param[field.key] }"/>
            <span class="add-on"><i class="icon-calendar"></i></span>
          </div>
        </c:if>
        <c:if test="${'datetimepicker' eq field.input.type }">
          <div class="input-append date datetimepicker" data-date-format="yyyy-mm-dd hh:ii:ss">
            <input type="text" id="${field.key }" name="${field.key }" value="${param[field.key] }"/>
            <span class="add-on"><i class="icon-calendar"></i></span>
          </div>
        </c:if>
        <c:if test="${'textarea' eq field.input.type }">
          <textarea rows="8" cols="20" id="${field.key }" name="${field.key }" class="span4"><c:out value="${field.input.defaultValue }"></c:out></textarea>
        </c:if>
        <c:if test="${'radio' eq field.input.type }">
          <c:forEach items="${field.items.itemList }" var="item">
            <label class="radio inline"><input type="radio" name="${field.key }" value="${item.value }" ${item.value eq field.input.defaultValue?"checked='checked'":""  }  />&nbsp;<c:out value="${item.name }"/></label>&nbsp;
          </c:forEach>
        </c:if>
        <c:if test="${'select' eq field.input.type }">
          <select id="${field.key }" name="${field.key }">
            <option value=""><s:message code='common.ui.select.all' /></option>
            <c:forEach items="${field.items.itemList }" var="fieldItem">
              <option value="${fieldItem.value }" ${fieldItem.value eq param[field.key]?"selected='selected'":""  }><c:out value="${fieldItem.name }"/></option>
            </c:forEach>
          </select>
        </c:if>
        <c:if test="${'autocomplete' eq field.input.type }">
          <div class="input-append">
            <input type="text" id="${field.key }DisplayName" name="${field.key }DisplayName" autocomplete="off"   value='${param[field.displayKey] }' />
            <span class="add-on"><i class="icon-search"></i></span>
          </div>
          <input type="hidden" id="${field.key }" name="${field.key }" value="${param[field.key] }" />
        </c:if>
        <!-- scripts -->
        <c:if test="${not empty field.input.scripts }">
        <script type="text/javascript">
        ${field.input.scripts}
        </script>
        </c:if>
      </td> 
      </c:forEach>
    </tr>
  </c:forEach>
</table>
<div class="text-center">
  <button type="submit" class="btn btn-small"><i class="icon-search"></i>&nbsp;<s:message code='btn.query' /></button>
</div>
