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
					<legend><s:message code="project.ui.fieldset.base" /></legend>
					<div class="row-fluid">												
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="project.code"><s:message code="project.code" /></label>
								<div class="controls">
									<input id="project.code" name="code" placeholder="" type="text" />
								</div>
							</div>
						</div>	
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="project.name"><s:message code="project.name" /></label>
								<div class="controls">
									<input id="project.name" name="name" placeholder="" type="text" />
								</div>
							</div>
						</div>									
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="project.status"><s:message code="project.status" /></label>
								<div class="controls">
									<select id="project.status" name="status">
										<li:dictOptions dictType="4001"/>
									</select>										
								</div>
							</div>
						</div>						
					</div>	
					<div class="row-fluid">												
						<div class="span12">
							<div class="control-group">
								<label class="control-label" for="project.descr"><s:message code="project.descr" /></label>
								<div class="controls">
									<textarea rows="3" cols="8" id="project.descr" name="descr" class="input-block-level limited"></textarea>
								</div>
							</div>
						</div>								
					</div>					
					<div class="row-fluid">
						<div class="span12">
							<div class="control-group">
								<label class="control-label" for="project.tags"><s:message code="project.tags" /></label>
								<div class="controls">
									<input id="tags" name="tags" placeholder="" type="text" class="input-block-level limited" />
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
					code : {
						required : true
					}
					, name : {
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