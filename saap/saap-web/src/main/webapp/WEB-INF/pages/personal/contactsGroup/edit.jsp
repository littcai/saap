<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.inc"%>
<%@ include file="/common/taglibs.inc"%>
<html lang="en">
  <head>		
  </head>
  <body>   
  <form id="theform" action="update.json" method="post" class="form-horizontal">
  	<input type="hidden" name="id" value="${contactsGroup.id}" />
				<fieldset>
					<legend><s:message code="common.ui.fieldset.base" /></legend>					
					<div class="row-fluid">														
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="name"><s:message code="contactsGroup.name" /></label>
								<div class="controls">
									<input id="name" name="name" placeholder="" type="text" value="${contactsGroup.name}" />
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