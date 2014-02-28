<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.inc"%>
<%@ include file="/common/taglibs.inc"%>
<html lang="en">
  <head>		
  </head>
  <body>   
  <form id="theform" action="show.do" method="post" class="form-horizontal">
  	<input type="hidden" name="id" value="${smsIn.id}" />
				<fieldset>
					<legend><s:message code="common.ui.fieldset.base" /></legend>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="smsIn.sender"><s:message code="smsIn.sender" /></label>
								<div class="controls">
									<c:out value="${smsIn.sender}" />									
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="smsIn.receiver"><s:message code="smsIn.receiver" /></label>
								<div class="controls">
									<c:out value="${smsIn.receiver}" />									
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span12">
							<div class="control-group">
								<label class="control-label" for="smsIn.content"><s:message code="smsIn.content" /></label>
								<div class="controls">
									<textarea rows="5" cols="8" id="content" name="content" class="input-block-level limited" readonly="readonly"><c:out value="${smsIn.content}" /></textarea>																		
								</div>
							</div>
						</div>			
													
					</div>
					<div class="row-fluid">												
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="smsIn.createDatetime"><s:message code="smsIn.createDatetime" /></label>
								<div class="controls">
									<c:out value="${li:formatDateTime(smsIn.createDatetime)}" />									
								</div>
							</div>
						</div>								
					</div>
				</fieldset>					
						
				<div class="form-actions">					
					<button type="button" class="btn" onclick="history.back();"><s:message code="btn.back" /></button>
				</div>					
				
			</form>			
			  
  </body>	
</html>