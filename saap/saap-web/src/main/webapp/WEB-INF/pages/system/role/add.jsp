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
							<label class="control-label" for="name"><s:message code="role.name" /></label>
							<div class="controls">
								<input id="role.name" name="name" placeholder="" type="text" />
							</div>
						</div>
					</div>
					<div class="row-fluid">
						<div class="control-group">
							<label class="control-label" for="remark"><s:message code="role.remark" /></label>
							<div class="controls">
								<textarea rows="6" cols="8" id="remark" name="remark" class="input-block-level limited"></textarea>
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
						required : true,
						maxlength: 50
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