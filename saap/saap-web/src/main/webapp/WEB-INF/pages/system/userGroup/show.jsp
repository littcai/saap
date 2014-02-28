<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.inc"%>
<%@ include file="/common/taglibs.inc"%>
<html lang="en">
  <head>		
  </head>
  <body>   
  <form id="theform" action="show.do" method="post" class="form-horizontal">
  	<input type="hidden" name="id" value="${userGroup.id}" />
				<fieldset>
					<legend><s:message code="userGroup.ui.fieldset.base" /></legend>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="userGroup.id"><s:message code="userGroup.id" /></label>
								<div class="controls">
									<c:out value="${userGroup.id}" />									
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="userGroup.tenantId"><s:message code="userGroup.tenantId" /></label>
								<div class="controls">
									<c:out value="${userGroup.tenantId}" />									
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="userGroup.parentId"><s:message code="userGroup.parentId" /></label>
								<div class="controls">
									<c:out value="${userGroup.parentId}" />									
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="userGroup.code"><s:message code="userGroup.code" /></label>
								<div class="controls">
									<c:out value="${userGroup.code}" />									
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="userGroup.name"><s:message code="userGroup.name" /></label>
								<div class="controls">
									<c:out value="${userGroup.name}" />									
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="userGroup.type"><s:message code="userGroup.type" /></label>
								<div class="controls">
									<c:out value="${userGroup.type}" />									
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="userGroup.description"><s:message code="userGroup.description" /></label>
								<div class="controls">
									<c:out value="${userGroup.description}" />									
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="userGroup.status"><s:message code="userGroup.status" /></label>
								<div class="controls">
									<c:out value="${userGroup.status}" />									
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="userGroup.createUserId"><s:message code="userGroup.createUserId" /></label>
								<div class="controls">
									<c:out value="${userGroup.createUserId}" />									
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="userGroup.createDatetime"><s:message code="userGroup.createDatetime" /></label>
								<div class="controls">
									<c:out value="${userGroup.createDatetime}" />									
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="userGroup.updateUserId"><s:message code="userGroup.updateUserId" /></label>
								<div class="controls">
									<c:out value="${userGroup.updateUserId}" />									
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="userGroup.updateDatetime"><s:message code="userGroup.updateDatetime" /></label>
								<div class="controls">
									<c:out value="${userGroup.updateDatetime}" />									
								</div>
							</div>
						</div>								
					</div>
				</fieldset>					
						
				<div class="form-actions">					
					<button type="button" class="btn" onclick="history.back();"><s:message code="btn.cancel" /></button>
				</div>					
				
			</form>			
			  
  </body>	
</html>