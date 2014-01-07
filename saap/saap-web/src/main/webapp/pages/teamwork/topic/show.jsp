<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.inc"%>
<%@ include file="/common/taglibs.inc"%>
<html lang="en">
  <head>		
  </head>
  <body>   
  <form id="theform" action="show.do" method="post" class="form-horizontal">
  	<input type="hidden" name="id" value="${topic.id}" />
				<fieldset>
					<legend><s:message code="topic.ui.fieldset.base" /></legend>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="topic.id"><s:message code="topic.id" /></label>
								<div class="controls">
									<c:out value="${topic.id}" />									
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="topic.tenantId"><s:message code="topic.tenantId" /></label>
								<div class="controls">
									<c:out value="${topic.tenantId}" />									
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="topic.projectId"><s:message code="topic.projectId" /></label>
								<div class="controls">
									<c:out value="${topic.projectId}" />									
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="topic.title"><s:message code="topic.title" /></label>
								<div class="controls">
									<c:out value="${topic.title}" />									
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="topic.content"><s:message code="topic.content" /></label>
								<div class="controls">
									<c:out value="${topic.content}" />									
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="topic.createUserId"><s:message code="topic.createUserId" /></label>
								<div class="controls">
									<c:out value="${topic.createUserId}" />									
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="topic.createDatetime"><s:message code="topic.createDatetime" /></label>
								<div class="controls">
									<c:out value="${topic.createDatetime}" />									
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="topic.updateUserId"><s:message code="topic.updateUserId" /></label>
								<div class="controls">
									<c:out value="${topic.updateUserId}" />									
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="topic.updateDatetime"><s:message code="topic.updateDatetime" /></label>
								<div class="controls">
									<c:out value="${topic.updateDatetime}" />									
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