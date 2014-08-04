<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.jspf"%>
<%@ include file="/common/taglibs.jspf"%>
<html lang="en">
  <head>		
  </head>
  <body>   
  <form id="theform" action="show.do" method="post" class="form-horizontal">
    <input type="hidden" name="id" value="${userGroup.id}" />
	<fieldset>
		<legend><s:message code="common.ui.fieldset.base" /></legend>
		<div class="row-fluid">
			<div class="control-group">
				<label class="control-label" for="userGroup.code"><s:message code="userGroup.code" /></label>
				<div class="controls">
					<input id="userGroup.code" name="code" type="text" value="<c:out value='${userGroup.code }'/>" readonly="readonly"/>
				</div>
			</div>						
		</div>
		<div class="row-fluid">
			<div class="control-group">
				<label class="control-label" for="userGroup.name"><s:message code="userGroup.name" /></label>
				<div class="controls">
					<input id="userGroup.name" name="name" type="text" value="<c:out value='${userGroup.name }'/>" readonly="readonly"/>
				</div>
			</div>									
		</div>
		<div class="row-fluid">
			<div class="control-group">
				<label class="control-label" for="description"><s:message code="common.field.description" /></label>
				<div class="controls">
					<textarea rows="6" cols="8" id="description" name="description" class="input-block-level limited" readonly="readonly"><c:out value='${userGroup.description }'/></textarea>
				</div>
			</div>								
		</div>
	</fieldset>	
	<div class="form-actions">
		<button type="button" class="btn" onclick="history.back();"><s:message code="btn.back" /></button>
	</div>
  </form>	
  </body>	
</html>