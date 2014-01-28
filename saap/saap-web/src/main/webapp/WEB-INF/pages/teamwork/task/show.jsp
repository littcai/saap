<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.inc"%>
<%@ include file="/common/taglibs.inc"%>
<html lang="en">
  <head>		
  </head>
  <body>   
  <form id="theform" action="show.do" method="post" class="form-horizontal">
  	<input type="hidden" name="id" value="${task.id}" />
				<fieldset>
					<legend><s:message code="task.ui.fieldset.base" /></legend>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="task.id"><s:message code="task.id" /></label>
								<div class="controls">
									<c:out value="${task.id}" />									
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="task.tenantId"><s:message code="task.tenantId" /></label>
								<div class="controls">
									<c:out value="${task.tenantId}" />									
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="task.projectId"><s:message code="task.projectId" /></label>
								<div class="controls">
									<c:out value="${task.projectId}" />									
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="task.title"><s:message code="task.title" /></label>
								<div class="controls">
									<c:out value="${task.title}" />									
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="task.content"><s:message code="task.content" /></label>
								<div class="controls">
									<c:out value="${task.content}" />									
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="task.priority"><s:message code="task.priority" /></label>
								<div class="controls">
									<c:out value="${task.priority}" />									
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="task.status"><s:message code="task.status" /></label>
								<div class="controls">
									<c:out value="${task.status}" />									
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="task.assignee"><s:message code="task.assignee" /></label>
								<div class="controls">
									<c:out value="${task.assignee}" />									
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="task.tags"><s:message code="task.tags" /></label>
								<div class="controls">
									<c:out value="${task.tags}" />									
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="task.createUserId"><s:message code="task.createUserId" /></label>
								<div class="controls">
									<c:out value="${task.createUserId}" />									
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="task.createDatetime"><s:message code="task.createDatetime" /></label>
								<div class="controls">
									<c:out value="${task.createDatetime}" />									
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="task.updateUserId"><s:message code="task.updateUserId" /></label>
								<div class="controls">
									<c:out value="${task.updateUserId}" />									
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="task.updateDatetime"><s:message code="task.updateDatetime" /></label>
								<div class="controls">
									<c:out value="${task.updateDatetime}" />									
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