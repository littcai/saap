<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.inc"%>
<%@ include file="/common/taglibs.inc"%>
<html lang="en">
  <head>		
  </head>
  <body>   
  <form id="theform" action="save.json" method="post" class="form-horizontal">
				<fieldset>
					<legend><s:message code="custActivity.ui.fieldset.base" /></legend>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custActivity.id"><s:message code="custActivity.id" /></label>
								<div class="controls">
									<input id="custActivity.id" name="id" placeholder="" type="text" />
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custActivity.tenantId"><s:message code="custActivity.tenantId" /></label>
								<div class="controls">
									<input id="custActivity.tenantId" name="tenantId" placeholder="" type="text" />
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custActivity.customerId"><s:message code="custActivity.customerId" /></label>
								<div class="controls">
									<input id="custActivity.customerId" name="customerId" placeholder="" type="text" />
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custActivity.contactId"><s:message code="custActivity.contactId" /></label>
								<div class="controls">
									<input id="custActivity.contactId" name="contactId" placeholder="" type="text" />
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custActivity.actType"><s:message code="custActivity.actType" /></label>
								<div class="controls">
									<input id="custActivity.actType" name="actType" placeholder="" type="text" />
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custActivity.subject"><s:message code="custActivity.subject" /></label>
								<div class="controls">
									<input id="custActivity.subject" name="subject" placeholder="" type="text" />
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custActivity.content"><s:message code="custActivity.content" /></label>
								<div class="controls">
									<input id="custActivity.content" name="content" placeholder="" type="text" />
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custActivity.actDate"><s:message code="custActivity.actDate" /></label>
								<div class="controls">
									<input id="custActivity.actDate" name="actDate" placeholder="" type="text" />
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custActivity.chargeUserId"><s:message code="custActivity.chargeUserId" /></label>
								<div class="controls">
									<input id="custActivity.chargeUserId" name="chargeUserId" placeholder="" type="text" />
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custActivity.createDatetime"><s:message code="custActivity.createDatetime" /></label>
								<div class="controls">
									<input id="custActivity.createDatetime" name="createDatetime" placeholder="" type="text" />
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custActivity.createUserId"><s:message code="custActivity.createUserId" /></label>
								<div class="controls">
									<input id="custActivity.createUserId" name="createUserId" placeholder="" type="text" />
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custActivity.updateDatetime"><s:message code="custActivity.updateDatetime" /></label>
								<div class="controls">
									<input id="custActivity.updateDatetime" name="updateDatetime" placeholder="" type="text" />
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custActivity.updateUserId"><s:message code="custActivity.updateUserId" /></label>
								<div class="controls">
									<input id="custActivity.updateUserId" name="updateUserId" placeholder="" type="text" />
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