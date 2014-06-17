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
					<legend><s:message code="common.ui.fieldset.base" /></legend>
					<div class="row-fluid">
							<div class="control-group">
								<label class="control-label" for="custActivity.actType"><s:message code="custActivity.actType" /></label>
								<div class="controls">
									<select id="actType" name="actType" data-placeholder="<s:message code='common.ui.select' />">
										<option value=""></option>
										<option value="">电话拜访</option>
										<option value="">电话来访</option>
									</select>
								</div>
							</div>
					</div>		
					<div class="row-fluid">
							<div class="control-group">
								<label class="control-label" for="custActivity.subject"><s:message code="custActivity.subject" /></label>
								<div class="controls">
									<input id="custActivity.subject" name="subject" placeholder="" type="text" />
								</div>
							</div>						
					</div>
					<div class="row-fluid">
							<div class="control-group">
								<label class="control-label" for="custActivity.content"><s:message code="custActivity.content" /></label>
								<div class="controls">
									<textarea rows="3" cols="8" id="custActivity.content" name="content" class="input-block-level limited"></textarea>
								</div>
							</div>
					</div>		
					<div class="row-fluid">
							<div class="control-group">
								<label class="control-label" for="custActivity.actDate"><s:message code="custActivity.actDate" /></label>
								<div class="controls">
									<input id="custActivity.actDate" name="actDate" placeholder="" type="text" />
								</div>
							</div>						
					</div>	
					<div class="row-fluid">
							<div class="control-group">
								<label class="control-label" for="nextActDate"><s:message code="custActivity.nextActDate" /></label>
								<div class="controls">
									<input id="nextActDate" name="nextActDate" placeholder="" type="text" />
								</div>
							</div>						
					</div>					
					<div class="row-fluid">
							<div class="control-group">
								<label class="control-label" for="customerId"><s:message code="custActivity.customerId" /></label>
								<div class="controls">
									<select id="customerId" name="customerId" data-placeholder="<s:message code='common.ui.select' />">
										<option value=""></option>
										<li:optionsCollection collection="${customerList }" var="customer" value="${custContacts.customerId}">	
											<li:option property="${customer.id }">${customer.name }</li:option>			
										</li:optionsCollection>
									</select>	
								</div>
							</div>
					</div>								
					<div class="row-fluid">
							<div class="control-group">
								<label class="control-label" for="custActivity.contactId"><s:message code="custActivity.contactId" /></label>
								<div class="controls">
									<input id="custActivity.contactId" name="contactId" placeholder="" type="text" />
								</div>
							</div>						
					</div>	
					<div class="row-fluid">
							<div class="control-group">
								<label class="control-label" for="custActivity.chargeBy"><s:message code="custActivity.chargeBy" /></label>
								<div class="controls">
									<select id="chargeBy" name="chargeBy" data-placeholder="<s:message code='common.ui.select' />">
										<option value=""></option>
										<li:optionsCollection collection="${chargeUserList }" var="row">	
											<li:option property="${row.id }">${row.userName } (${row.loginId})</li:option>			
										</li:optionsCollection>	
									</select>
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
			
			$("#customerId").select2();			
			
			$('#theform').littFormSubmit({
				rules : {
					customerId : {
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