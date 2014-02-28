<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.inc"%>
<%@ include file="/common/taglibs.inc"%>
<html lang="en">
  <head>		
  </head>
  <body>   
  <form id="theform" action="update.json" method="post" class="form-horizontal">
  	<input type="hidden" name="id" value="${role.id}" />
				<fieldset>
					<legend><s:message code="role.ui.fieldset.base" /></legend>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="role.id"><s:message code="role.id" /></label>
								<div class="controls">
									<input id="role.id" name="id" placeholder="" type="text" value="<c:out values='${role.id}' />" />
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="role.tenantId"><s:message code="role.tenantId" /></label>
								<div class="controls">
									<input id="role.tenantId" name="tenantId" placeholder="" type="text" value="<c:out values='${role.tenantId}' />" />
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="role.name"><s:message code="role.name" /></label>
								<div class="controls">
									<input id="role.name" name="name" placeholder="" type="text" value="<c:out values='${role.name}' />" />
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="role.status"><s:message code="role.status" /></label>
								<div class="controls">
									<input id="role.status" name="status" placeholder="" type="text" value="<c:out values='${role.status}' />" />
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="role.remark"><s:message code="role.remark" /></label>
								<div class="controls">
									<input id="role.remark" name="remark" placeholder="" type="text" value="<c:out values='${role.remark}' />" />
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