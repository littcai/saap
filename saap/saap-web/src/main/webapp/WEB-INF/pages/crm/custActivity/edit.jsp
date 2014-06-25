<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.jspf"%>
<%@ include file="/common/taglibs.jspf"%>
<html lang="en">
  <head>		
  </head>
  <body>   
  <form id="theform" action="update.json" method="post" class="form-horizontal">
  	<input type="hidden" name="id" value="${custActivity.id}" />
				<fieldset>
					<legend><s:message code="custActivity.ui.fieldset.base" /></legend>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custActivity.id"><s:message code="custActivity.id" /></label>
								<div class="controls">
									<input id="custActivity.id" name="id" placeholder="" type="text" value="<c:out values='${custActivity.id}' />" />
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custActivity.tenantId"><s:message code="custActivity.tenantId" /></label>
								<div class="controls">
									<input id="custActivity.tenantId" name="tenantId" placeholder="" type="text" value="<c:out values='${custActivity.tenantId}' />" />
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custActivity.customerId"><s:message code="custActivity.customerId" /></label>
								<div class="controls">
									<input id="custActivity.customerId" name="customerId" placeholder="" type="text" value="<c:out values='${custActivity.customerId}' />" />
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custActivity.contactId"><s:message code="custActivity.contactId" /></label>
								<div class="controls">
									<input id="custActivity.contactId" name="contactId" placeholder="" type="text" value="<c:out values='${custActivity.contactId}' />" />
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custActivity.actType"><s:message code="custActivity.actType" /></label>
								<div class="controls">
									<input id="custActivity.actType" name="actType" placeholder="" type="text" value="<c:out values='${custActivity.actType}' />" />
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custActivity.subject"><s:message code="custActivity.subject" /></label>
								<div class="controls">
									<input id="custActivity.subject" name="subject" placeholder="" type="text" value="<c:out values='${custActivity.subject}' />" />
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custActivity.content"><s:message code="custActivity.content" /></label>
								<div class="controls">
									<input id="custActivity.content" name="content" placeholder="" type="text" value="<c:out values='${custActivity.content}' />" />
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custActivity.actDate"><s:message code="custActivity.actDate" /></label>
								<div class="controls">
									<input id="custActivity.actDate" name="actDate" placeholder="" type="text" value="<c:out values='${custActivity.actDate}' />" />
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custActivity.chargeUserId"><s:message code="custActivity.chargeUserId" /></label>
								<div class="controls">
									<input id="custActivity.chargeUserId" name="chargeUserId" placeholder="" type="text" value="<c:out values='${custActivity.chargeUserId}' />" />
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custActivity.createDatetime"><s:message code="custActivity.createDatetime" /></label>
								<div class="controls">
									<input id="custActivity.createDatetime" name="createDatetime" placeholder="" type="text" value="<c:out values='${custActivity.createDatetime}' />" />
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custActivity.createUserId"><s:message code="custActivity.createUserId" /></label>
								<div class="controls">
									<input id="custActivity.createUserId" name="createUserId" placeholder="" type="text" value="<c:out values='${custActivity.createUserId}' />" />
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custActivity.updateDatetime"><s:message code="custActivity.updateDatetime" /></label>
								<div class="controls">
									<input id="custActivity.updateDatetime" name="updateDatetime" placeholder="" type="text" value="<c:out values='${custActivity.updateDatetime}' />" />
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custActivity.updateUserId"><s:message code="custActivity.updateUserId" /></label>
								<div class="controls">
									<input id="custActivity.updateUserId" name="updateUserId" placeholder="" type="text" value="<c:out values='${custActivity.updateUserId}' />" />
								</div>
							</div>
						</div>								
					</div>
				</fieldset>					
						
				<div class="form-actions">
					<button type="submit" class="btn btn-primary"><i class="icon-ok"></i> <s:message code="btn.save" /></button>
					<button type="button" class="btn" onclick="history.back();"><s:message code="btn.cancel" /></button>
				</div>					
				
			</form>				
		<!--page specific plugin scripts-->				
		<script type="text/javascript">
		$(document).ready(function(){	
			
			$('#theform').littFormSubmit({
				rules : {
					name : {
						required : true
					}
				},			
				success: function(reply){
					 location.href = <h:returnUrl value="index.do"></h:returnUrl>;					
				}
			});
		});
		</script>	  
  </body>	
</html>