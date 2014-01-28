<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.inc"%>
<%@ include file="/common/taglibs.inc"%>
<html lang="en">
  <head>		
  </head>
  <body>   
  <form id="theform" action="update.json" method="post" class="form-horizontal">
  	<input type="hidden" name="id" value="${knowledgeSpace.id}" />
				<fieldset>
					<legend><s:message code="knowledgeSpace.ui.fieldset.base" /></legend>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="knowledgeSpace.id"><s:message code="knowledgeSpace.id" /></label>
								<div class="controls">
									<input id="id" name="id" placeholder="" type="text" value="${knowledgeSpace.id}" />
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="knowledgeSpace.tenantId"><s:message code="knowledgeSpace.tenantId" /></label>
								<div class="controls">
									<input id="tenantId" name="tenantId" placeholder="" type="text" value="${knowledgeSpace.tenantId}" />
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="knowledgeSpace.code"><s:message code="knowledgeSpace.code" /></label>
								<div class="controls">
									<input id="code" name="code" placeholder="" type="text" value="${knowledgeSpace.code}" />
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="knowledgeSpace.name"><s:message code="knowledgeSpace.name" /></label>
								<div class="controls">
									<input id="name" name="name" placeholder="" type="text" value="${knowledgeSpace.name}" />
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="knowledgeSpace.descr"><s:message code="knowledgeSpace.descr" /></label>
								<div class="controls">
									<input id="descr" name="descr" placeholder="" type="text" value="${knowledgeSpace.descr}" />
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="knowledgeSpace.createUserId"><s:message code="knowledgeSpace.createUserId" /></label>
								<div class="controls">
									<input id="createUserId" name="createUserId" placeholder="" type="text" value="${knowledgeSpace.createUserId}" />
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="knowledgeSpace.createDatetime"><s:message code="knowledgeSpace.createDatetime" /></label>
								<div class="controls">
									<input id="createDatetime" name="createDatetime" placeholder="" type="text" value="${knowledgeSpace.createDatetime}" />
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="knowledgeSpace.updateUserId"><s:message code="knowledgeSpace.updateUserId" /></label>
								<div class="controls">
									<input id="updateUserId" name="updateUserId" placeholder="" type="text" value="${knowledgeSpace.updateUserId}" />
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="knowledgeSpace.updateDatetime"><s:message code="knowledgeSpace.updateDatetime" /></label>
								<div class="controls">
									<input id="updateDatetime" name="updateDatetime" placeholder="" type="text" value="${knowledgeSpace.updateDatetime}" />
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