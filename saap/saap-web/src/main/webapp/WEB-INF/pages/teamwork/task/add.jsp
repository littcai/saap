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
					<legend><s:message code="task.ui.fieldset.base" /></legend>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="task.id"><s:message code="task.id" /></label>
								<div class="controls">
									<input id="id" name="id" placeholder="" type="text" />
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="task.tenantId"><s:message code="task.tenantId" /></label>
								<div class="controls">
									<input id="tenantId" name="tenantId" placeholder="" type="text" />
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="task.projectId"><s:message code="task.projectId" /></label>
								<div class="controls">
									<input id="projectId" name="projectId" placeholder="" type="text" />
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="task.title"><s:message code="task.title" /></label>
								<div class="controls">
									<input id="title" name="title" placeholder="" type="text" />
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="task.content"><s:message code="task.content" /></label>
								<div class="controls">
									<input id="content" name="content" placeholder="" type="text" />
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="task.priority"><s:message code="task.priority" /></label>
								<div class="controls">
									<input id="priority" name="priority" placeholder="" type="text" />
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="task.status"><s:message code="task.status" /></label>
								<div class="controls">
									<input id="status" name="status" placeholder="" type="text" />
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="task.assignee"><s:message code="task.assignee" /></label>
								<div class="controls">
									<input id="assignee" name="assignee" placeholder="" type="text" />
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="task.tags"><s:message code="task.tags" /></label>
								<div class="controls">
									<input id="tags" name="tags" placeholder="" type="text" />
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="task.createUserId"><s:message code="task.createUserId" /></label>
								<div class="controls">
									<input id="createUserId" name="createUserId" placeholder="" type="text" />
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="task.createDatetime"><s:message code="task.createDatetime" /></label>
								<div class="controls">
									<input id="createDatetime" name="createDatetime" placeholder="" type="text" />
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="task.updateUserId"><s:message code="task.updateUserId" /></label>
								<div class="controls">
									<input id="updateUserId" name="updateUserId" placeholder="" type="text" />
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="task.updateDatetime"><s:message code="task.updateDatetime" /></label>
								<div class="controls">
									<input id="updateDatetime" name="updateDatetime" placeholder="" type="text" />
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