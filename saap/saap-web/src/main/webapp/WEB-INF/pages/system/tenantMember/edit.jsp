<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.inc"%>
<%@ include file="/common/taglibs.inc"%>
<html lang="en">
  <head>		
  </head>
  <body>   
  <form id="theform" action="update.json" method="post" class="form-horizontal">
  	<input type="hidden" name="id" value="${tenantMember.id}" />
				<fieldset>
					<legend><s:message code="tenantMember.ui.fieldset.base" /></legend>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="tenantMember.id"><s:message code="tenantMember.id" /></label>
								<div class="controls">
									<input id="tenantMember.id" name="id" placeholder="" type="text" value="<c:out values='${tenantMember.id}' />" />
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="tenantMember.tenantId"><s:message code="tenantMember.tenantId" /></label>
								<div class="controls">
									<input id="tenantMember.tenantId" name="tenantId" placeholder="" type="text" value="<c:out values='${tenantMember.tenantId}' />" />
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="tenantMember.appId"><s:message code="tenantMember.appId" /></label>
								<div class="controls">
									<input id="tenantMember.appId" name="appId" placeholder="" type="text" value="<c:out values='${tenantMember.appId}' />" />
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="tenantMember.userId"><s:message code="tenantMember.userId" /></label>
								<div class="controls">
									<input id="tenantMember.userId" name="userId" placeholder="" type="text" value="<c:out values='${tenantMember.userId}' />" />
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="tenantMember.isAdmin"><s:message code="tenantMember.isAdmin" /></label>
								<div class="controls">
									<input id="tenantMember.isAdmin" name="isAdmin" placeholder="" type="text" value="<c:out values='${tenantMember.isAdmin}' />" />
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="tenantMember.status"><s:message code="tenantMember.status" /></label>
								<div class="controls">
									<input id="tenantMember.status" name="status" placeholder="" type="text" value="<c:out values='${tenantMember.status}' />" />
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="tenantMember.createUserId"><s:message code="tenantMember.createUserId" /></label>
								<div class="controls">
									<input id="tenantMember.createUserId" name="createUserId" placeholder="" type="text" value="<c:out values='${tenantMember.createUserId}' />" />
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="tenantMember.createDatetime"><s:message code="tenantMember.createDatetime" /></label>
								<div class="controls">
									<input id="tenantMember.createDatetime" name="createDatetime" placeholder="" type="text" value="<c:out values='${tenantMember.createDatetime}' />" />
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="tenantMember.updateUserId"><s:message code="tenantMember.updateUserId" /></label>
								<div class="controls">
									<input id="tenantMember.updateUserId" name="updateUserId" placeholder="" type="text" value="<c:out values='${tenantMember.updateUserId}' />" />
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="tenantMember.updateDatetime"><s:message code="tenantMember.updateDatetime" /></label>
								<div class="controls">
									<input id="tenantMember.updateDatetime" name="updateDatetime" placeholder="" type="text" value="<c:out values='${tenantMember.updateDatetime}' />" />
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
					 location.href = "index.do";					
				}
			});
		});
		</script>	  
  </body>	
</html>