<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.inc"%>
<%@ include file="/common/taglibs.inc"%>
<html lang="en">
  <head>		
  </head>
  <body>   
  <form id="theform" action="show.do" method="post" class="form-horizontal">
  	<input type="hidden" name="id" value="${knowledgePage.id}" />
				<fieldset>
					<legend><s:message code="knowledgePage.ui.fieldset.base" /></legend>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="knowledgePage.id"><s:message code="knowledgePage.id" /></label>
								<div class="controls">
									<c:out value="${knowledgePage.id}" />									
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="knowledgePage.tenantId"><s:message code="knowledgePage.tenantId" /></label>
								<div class="controls">
									<c:out value="${knowledgePage.tenantId}" />									
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="knowledgePage.spaceId"><s:message code="knowledgePage.spaceId" /></label>
								<div class="controls">
									<c:out value="${knowledgePage.spaceId}" />									
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="knowledgePage.parentId"><s:message code="knowledgePage.parentId" /></label>
								<div class="controls">
									<c:out value="${knowledgePage.parentId}" />									
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="knowledgePage.subject"><s:message code="knowledgePage.subject" /></label>
								<div class="controls">
									<c:out value="${knowledgePage.subject}" />									
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="knowledgePage.brief"><s:message code="knowledgePage.brief" /></label>
								<div class="controls">
									<c:out value="${knowledgePage.brief}" />									
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="knowledgePage.keywords"><s:message code="knowledgePage.keywords" /></label>
								<div class="controls">
									<c:out value="${knowledgePage.keywords}" />									
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="knowledgePage.content"><s:message code="knowledgePage.content" /></label>
								<div class="controls">
									<c:out value="${knowledgePage.content}" />									
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="knowledgePage.createUserId"><s:message code="knowledgePage.createUserId" /></label>
								<div class="controls">
									<c:out value="${knowledgePage.createUserId}" />									
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="knowledgePage.createDatetime"><s:message code="knowledgePage.createDatetime" /></label>
								<div class="controls">
									<c:out value="${knowledgePage.createDatetime}" />									
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="knowledgePage.updateUserId"><s:message code="knowledgePage.updateUserId" /></label>
								<div class="controls">
									<c:out value="${knowledgePage.updateUserId}" />									
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="knowledgePage.updateDatetime"><s:message code="knowledgePage.updateDatetime" /></label>
								<div class="controls">
									<c:out value="${knowledgePage.updateDatetime}" />									
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