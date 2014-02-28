<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.inc"%>
<%@ include file="/common/taglibs.inc"%>
<html lang="en">
  <head>		
  </head>
  <body>   
  <form id="theform" action="update.json" method="post" class="form-horizontal">
  	<input type="hidden" name="id" value="${userGroup.id}" />
				<fieldset>
					<legend><s:message code="userGroup.ui.fieldset.base" /></legend>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="userGroup.id"><s:message code="userGroup.id" /></label>
								<div class="controls">
									<input id="userGroup.id" name="id" placeholder="" type="text" value="<c:out values='${userGroup.id}' />" />
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="userGroup.tenantId"><s:message code="userGroup.tenantId" /></label>
								<div class="controls">
									<input id="userGroup.tenantId" name="tenantId" placeholder="" type="text" value="<c:out values='${userGroup.tenantId}' />" />
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="userGroup.parentId"><s:message code="userGroup.parentId" /></label>
								<div class="controls">
									<input id="userGroup.parentId" name="parentId" placeholder="" type="text" value="<c:out values='${userGroup.parentId}' />" />
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="userGroup.code"><s:message code="userGroup.code" /></label>
								<div class="controls">
									<input id="userGroup.code" name="code" placeholder="" type="text" value="<c:out values='${userGroup.code}' />" />
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="userGroup.name"><s:message code="userGroup.name" /></label>
								<div class="controls">
									<input id="userGroup.name" name="name" placeholder="" type="text" value="<c:out values='${userGroup.name}' />" />
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="userGroup.type"><s:message code="userGroup.type" /></label>
								<div class="controls">
									<input id="userGroup.type" name="type" placeholder="" type="text" value="<c:out values='${userGroup.type}' />" />
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="userGroup.description"><s:message code="userGroup.description" /></label>
								<div class="controls">
									<input id="userGroup.description" name="description" placeholder="" type="text" value="<c:out values='${userGroup.description}' />" />
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="userGroup.status"><s:message code="userGroup.status" /></label>
								<div class="controls">
									<input id="userGroup.status" name="status" placeholder="" type="text" value="<c:out values='${userGroup.status}' />" />
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="userGroup.createUserId"><s:message code="userGroup.createUserId" /></label>
								<div class="controls">
									<input id="userGroup.createUserId" name="createUserId" placeholder="" type="text" value="<c:out values='${userGroup.createUserId}' />" />
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="userGroup.createDatetime"><s:message code="userGroup.createDatetime" /></label>
								<div class="controls">
									<input id="userGroup.createDatetime" name="createDatetime" placeholder="" type="text" value="<c:out values='${userGroup.createDatetime}' />" />
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="userGroup.updateUserId"><s:message code="userGroup.updateUserId" /></label>
								<div class="controls">
									<input id="userGroup.updateUserId" name="updateUserId" placeholder="" type="text" value="<c:out values='${userGroup.updateUserId}' />" />
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="userGroup.updateDatetime"><s:message code="userGroup.updateDatetime" /></label>
								<div class="controls">
									<input id="userGroup.updateDatetime" name="updateDatetime" placeholder="" type="text" value="<c:out values='${userGroup.updateDatetime}' />" />
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