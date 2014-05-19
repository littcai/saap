<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.inc"%>
<%@ include file="/common/taglibs.inc"%>
<html lang="en">
  <head>		
  </head>
  <body>   
  <form id="theform" action="show.do" method="post" class="form-horizontal">
  	<input type="hidden" name="id" value="${custActivity.id}" />
				<fieldset>
					<legend><s:message code="custActivity.ui.fieldset.base" /></legend>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custActivity.id"><s:message code="custActivity.id" /></label>
								<div class="controls">
									<c:out value="${custActivity.id}" />									
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custActivity.tenantId"><s:message code="custActivity.tenantId" /></label>
								<div class="controls">
									<c:out value="${custActivity.tenantId}" />									
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custActivity.customerId"><s:message code="custActivity.customerId" /></label>
								<div class="controls">
									<c:out value="${custActivity.customerId}" />									
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custActivity.contactId"><s:message code="custActivity.contactId" /></label>
								<div class="controls">
									<c:out value="${custActivity.contactId}" />									
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custActivity.actType"><s:message code="custActivity.actType" /></label>
								<div class="controls">
									<c:out value="${custActivity.actType}" />									
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custActivity.subject"><s:message code="custActivity.subject" /></label>
								<div class="controls">
									<c:out value="${custActivity.subject}" />									
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custActivity.content"><s:message code="custActivity.content" /></label>
								<div class="controls">
									<c:out value="${custActivity.content}" />									
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custActivity.actDate"><s:message code="custActivity.actDate" /></label>
								<div class="controls">
									<c:out value="${custActivity.actDate}" />									
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custActivity.chargeUserId"><s:message code="custActivity.chargeUserId" /></label>
								<div class="controls">
									<c:out value="${custActivity.chargeUserId}" />									
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custActivity.createDatetime"><s:message code="custActivity.createDatetime" /></label>
								<div class="controls">
									<c:out value="${custActivity.createDatetime}" />									
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custActivity.createUserId"><s:message code="custActivity.createUserId" /></label>
								<div class="controls">
									<c:out value="${custActivity.createUserId}" />									
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custActivity.updateDatetime"><s:message code="custActivity.updateDatetime" /></label>
								<div class="controls">
									<c:out value="${custActivity.updateDatetime}" />									
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custActivity.updateUserId"><s:message code="custActivity.updateUserId" /></label>
								<div class="controls">
									<c:out value="${custActivity.updateUserId}" />									
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