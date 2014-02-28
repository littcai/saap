<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.inc"%>
<%@ include file="/common/taglibs.inc"%>
<html lang="en">
  <head>		
  </head>
  <body>   
  <form id="theform" action="show.do" method="post" class="form-horizontal">
  	<input type="hidden" name="id" value="${tenantMember.id}" />
				<fieldset>
					<legend><s:message code="tenantMember.ui.fieldset.base" /></legend>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="tenantMember.id"><s:message code="tenantMember.id" /></label>
								<div class="controls">
									<c:out value="${tenantMember.id}" />									
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="tenantMember.tenantId"><s:message code="tenantMember.tenantId" /></label>
								<div class="controls">
									<c:out value="${tenantMember.tenantId}" />									
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="tenantMember.appId"><s:message code="tenantMember.appId" /></label>
								<div class="controls">
									<c:out value="${tenantMember.appId}" />									
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="tenantMember.userId"><s:message code="tenantMember.userId" /></label>
								<div class="controls">
									<c:out value="${tenantMember.userId}" />									
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="tenantMember.isAdmin"><s:message code="tenantMember.isAdmin" /></label>
								<div class="controls">
									<c:out value="${tenantMember.isAdmin}" />									
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="tenantMember.status"><s:message code="tenantMember.status" /></label>
								<div class="controls">
									<c:out value="${tenantMember.status}" />									
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="tenantMember.createUserId"><s:message code="tenantMember.createUserId" /></label>
								<div class="controls">
									<c:out value="${tenantMember.createUserId}" />									
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="tenantMember.createDatetime"><s:message code="tenantMember.createDatetime" /></label>
								<div class="controls">
									<c:out value="${tenantMember.createDatetime}" />									
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="tenantMember.updateUserId"><s:message code="tenantMember.updateUserId" /></label>
								<div class="controls">
									<c:out value="${tenantMember.updateUserId}" />									
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="tenantMember.updateDatetime"><s:message code="tenantMember.updateDatetime" /></label>
								<div class="controls">
									<c:out value="${tenantMember.updateDatetime}" />									
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