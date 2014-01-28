<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.inc"%>
<%@ include file="/common/taglibs.inc"%>
<html lang="en">
  <head>		
  </head>
  <body>   
  <form id="theform" action="show.do" method="post" class="form-horizontal">
  	<input type="hidden" name="id" value="${project.id}" />
				<fieldset>
					<legend><s:message code="project.ui.fieldset.base" /></legend>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="project.id"><s:message code="project.id" /></label>
								<div class="controls">
									<c:out value="${project.id}" />									
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="project.tenantId"><s:message code="project.tenantId" /></label>
								<div class="controls">
									<c:out value="${project.tenantId}" />									
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="project.name"><s:message code="project.name" /></label>
								<div class="controls">
									<c:out value="${project.name}" />									
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="project.descr"><s:message code="project.descr" /></label>
								<div class="controls">
									<c:out value="${project.descr}" />									
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="project.status"><s:message code="project.status" /></label>
								<div class="controls">
									<c:out value="${project.status}" />									
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="project.tags"><s:message code="project.tags" /></label>
								<div class="controls">
									<c:out value="${project.tags}" />									
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="project.createUserId"><s:message code="project.createUserId" /></label>
								<div class="controls">
									<c:out value="${project.createUserId}" />									
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="project.createDatetime"><s:message code="project.createDatetime" /></label>
								<div class="controls">
									<c:out value="${project.createDatetime}" />									
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="project.updateUserId"><s:message code="project.updateUserId" /></label>
								<div class="controls">
									<c:out value="${project.updateUserId}" />									
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="project.updateDatetime"><s:message code="project.updateDatetime" /></label>
								<div class="controls">
									<c:out value="${project.updateDatetime}" />									
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