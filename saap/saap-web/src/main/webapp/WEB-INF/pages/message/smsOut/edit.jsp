<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.inc"%>
<%@ include file="/common/taglibs.inc"%>
<html lang="en">
  <head>
  	<script src="${contextPath }/widgets/jquery-input/jquery-input.js"></script>
  	<link href="${contextPath }/widgets/jquery-select2/css/select2.css" rel="stylesheet" />
  	<script src="${contextPath }/widgets/jquery-select2/js/select2.min.js"></script>
  	<style type="text/css">
  	.bootstrap-input{
  		box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
  		border-radius: 4px; 
  	}
  	</style>			
  </head>
  <body>   
  <form id="theform" action="update.json" method="post" class="form-horizontal">
    <input type="hidden" name="id" value="${smsOut.id }" />
				<fieldset>
					<legend><s:message code="common.ui.fieldset.base" /></legend>
					<div class="row-fluid">
						<div class="span10">
							<div class="control-group">
								<label class="control-label" for="receiver"><s:message code="smsOut.receiver" /></label>
								<div class="controls">
									<input id="receiver" name="receiver" placeholder="Add Mobiles" type="text" class="input-block-level"/>
									<div id="receiver_error"></div>
									<div class="help-block left"><s:message code="smsOut.receiver.tip"/></div>
								</div>
							</div>
						</div>						
					</div>						
					<div class="row-fluid">
						<div class="span10">
							<div class="control-group">
								<label class="control-label" for="content"><s:message code="smsOut.content" /></label>
								<div class="controls">
									<textarea rows="5" cols="8" id="content" name="content" class="input-block-level limited"></textarea>
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
			
			$.getJSON("${contextPath }/personal/contacts/getContactList.json", function(reply){
				var contactTags = [];
				$.each(reply.contactList, function(i, n){
					var contact = {
						id: n.mobile,
						text: n.name
					};
					
					contactTags.push(contact);
				});
				
				$("#receiver").select2({
					tags: contactTags
				});
				
				$(".select2-choices").addClass("bootstrap-input");
				
			});
			
			$('#content').inputCount({
				'maxCharacterSize': 200
			});
			
			$.validator.setDefaults({ 
			    ignore: [],
			    // any other default options and/or rules
			});
			
			$('#theform').littFormSubmit({
				rules : {
					receiver : {
						required : true
					},
					content : {
						required : true
					}
				},
				errorPlacement: function(error, element) {     
			    	
				      if(element.parent().hasClass('input-prepend') || element.parent().hasClass('input-append')) {
				        error.insertAfter(element.parent());	
				      }else {
				        error.insertAfter(element);
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