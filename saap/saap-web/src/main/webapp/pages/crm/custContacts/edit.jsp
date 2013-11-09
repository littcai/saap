<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.inc"%>
<%@ include file="/common/taglibs.inc"%>
<html lang="en">
  <head>		
  </head>
  <body>   
  <form id="theform" action="update.json" method="post" class="form-horizontal">
  	<input type="hidden" name="id" value="${custContacts.id}" />
				<fieldset>
					<legend><s:message code="custContacts.ui.fieldset.base" /></legend>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custContacts.id"><s:message code="custContacts.id" /></label>
								<div class="controls">
									<input id="id" name="id" placeholder="" type="text" value="${custContacts.id}" />
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custContacts.tenantId"><s:message code="custContacts.tenantId" /></label>
								<div class="controls">
									<input id="tenantId" name="tenantId" placeholder="" type="text" value="${custContacts.tenantId}" />
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custContacts.customerId"><s:message code="custContacts.customerId" /></label>
								<div class="controls">
									<input id="customerId" name="customerId" placeholder="" type="text" value="${custContacts.customerId}" />
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custContacts.name"><s:message code="custContacts.name" /></label>
								<div class="controls">
									<input id="name" name="name" placeholder="" type="text" value="${custContacts.name}" />
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custContacts.gender"><s:message code="custContacts.gender" /></label>
								<div class="controls">
									<input id="gender" name="gender" placeholder="" type="text" value="${custContacts.gender}" />
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custContacts.mobile"><s:message code="custContacts.mobile" /></label>
								<div class="controls">
									<input id="mobile" name="mobile" placeholder="" type="text" value="${custContacts.mobile}" />
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custContacts.email"><s:message code="custContacts.email" /></label>
								<div class="controls">
									<input id="email" name="email" placeholder="" type="text" value="${custContacts.email}" />
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custContacts.phone"><s:message code="custContacts.phone" /></label>
								<div class="controls">
									<input id="phone" name="phone" placeholder="" type="text" value="${custContacts.phone}" />
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custContacts.fax"><s:message code="custContacts.fax" /></label>
								<div class="controls">
									<input id="fax" name="fax" placeholder="" type="text" value="${custContacts.fax}" />
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custContacts.address"><s:message code="custContacts.address" /></label>
								<div class="controls">
									<input id="address" name="address" placeholder="" type="text" value="${custContacts.address}" />
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custContacts.zipCode"><s:message code="custContacts.zipCode" /></label>
								<div class="controls">
									<input id="zipCode" name="zipCode" placeholder="" type="text" value="${custContacts.zipCode}" />
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custContacts.createUserId"><s:message code="custContacts.createUserId" /></label>
								<div class="controls">
									<input id="createUserId" name="createUserId" placeholder="" type="text" value="${custContacts.createUserId}" />
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custContacts.createDatetime"><s:message code="custContacts.createDatetime" /></label>
								<div class="controls">
									<input id="createDatetime" name="createDatetime" placeholder="" type="text" value="${custContacts.createDatetime}" />
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custContacts.updateDatetime"><s:message code="custContacts.updateDatetime" /></label>
								<div class="controls">
									<input id="updateDatetime" name="updateDatetime" placeholder="" type="text" value="${custContacts.updateDatetime}" />
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custContacts.remark"><s:message code="custContacts.remark" /></label>
								<div class="controls">
									<input id="remark" name="remark" placeholder="" type="text" value="${custContacts.remark}" />
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