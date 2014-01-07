<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.inc"%>
<%@ include file="/common/taglibs.inc"%>
<html lang="en">
  <head>		
  </head>
  <body>   
  <form id="theform" action="show.do" method="post" class="form-horizontal">
  	<input type="hidden" name="id" value="${knowledgeSpace.id}" />
				<fieldset>
					<legend><s:message code="knowledgeSpace.ui.fieldset.base" /></legend>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="knowledgeSpace.id"><s:message code="knowledgeSpace.id" /></label>
								<div class="controls">
									<c:out value="${knowledgeSpace.id}" />									
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="knowledgeSpace.tenantId"><s:message code="knowledgeSpace.tenantId" /></label>
								<div class="controls">
									<c:out value="${knowledgeSpace.tenantId}" />									
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="knowledgeSpace.code"><s:message code="knowledgeSpace.code" /></label>
								<div class="controls">
									<c:out value="${knowledgeSpace.code}" />									
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="knowledgeSpace.name"><s:message code="knowledgeSpace.name" /></label>
								<div class="controls">
									<c:out value="${knowledgeSpace.name}" />									
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="knowledgeSpace.descr"><s:message code="knowledgeSpace.descr" /></label>
								<div class="controls">
									<c:out value="${knowledgeSpace.descr}" />									
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="knowledgeSpace.createUserId"><s:message code="knowledgeSpace.createUserId" /></label>
								<div class="controls">
									<c:out value="${knowledgeSpace.createUserId}" />									
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="knowledgeSpace.createDatetime"><s:message code="knowledgeSpace.createDatetime" /></label>
								<div class="controls">
									<c:out value="${knowledgeSpace.createDatetime}" />									
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="knowledgeSpace.updateUserId"><s:message code="knowledgeSpace.updateUserId" /></label>
								<div class="controls">
									<c:out value="${knowledgeSpace.updateUserId}" />									
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="knowledgeSpace.updateDatetime"><s:message code="knowledgeSpace.updateDatetime" /></label>
								<div class="controls">
									<c:out value="${knowledgeSpace.updateDatetime}" />									
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