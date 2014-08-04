<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.jspf"%>
<%@ include file="/common/taglibs.jspf"%>
<html lang="en">
  <head>		
  </head>
  <body>   
  <form id="theform" action="save.json" method="post" class="form-horizontal">
	<fieldset>
		<legend><s:message code="common.ui.fieldset.base" /></legend>
		<div class="row-fluid">
			<div class="control-group">
				<label class="control-label" for="userGroup.code"><s:message code="userGroup.code" /></label>
				<div class="controls">
					<input id="userGroup.code" name="code" placeholder="" type="text" />
				</div>
			</div>						
		</div>
		<div class="row-fluid">
			<div class="control-group">
				<label class="control-label" for="userGroup.name"><s:message code="userGroup.name" /></label>
				<div class="controls">
					<input id="userGroup.name" name="name" placeholder="" type="text" />
				</div>
			</div>									
		</div>
		<div class="row-fluid">
			<div class="control-group">
				<label class="control-label" for="description"><s:message code="common.field.description" /></label>
				<div class="controls">
					<textarea rows="6" cols="8" id="description" name="description" class="input-block-level limited"></textarea>
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
		  	code : {
				required : true
			},
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