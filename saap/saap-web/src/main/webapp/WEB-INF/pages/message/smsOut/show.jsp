<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.jspf"%>
<%@ include file="/common/taglibs.jspf"%>
<html lang="en">
  <head>		
  </head>
  <body>   
  <form id="theform" action="show.do" method="post" class="form-horizontal">
  	<input type="hidden" name="id" value="${smsOut.id}" />
				<fieldset>
					<legend><s:message code="common.ui.fieldset.base" /></legend>					
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="smsOut.sender"><s:message code="smsOut.sender" /></label>
								<div class="controls">
									<input type="text" value='<c:out value="${smsOut.sender}" />' readonly="readonly" />									
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="smsOut.receiver"><s:message code="smsOut.receiver" /></label>
								<div class="controls">
									<input type="text" value='<c:out value="${smsOut.receiver}" />' readonly="readonly" />									
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span12">
							<div class="control-group">
								<label class="control-label" for="smsOut.content"><s:message code="smsOut.content" /></label>
								<div class="controls">
									<textarea rows="5" cols="8" id="content" name="content" class="input-block-level limited" readonly="readonly"><c:out value="${smsOut.content}" /></textarea>								
								</div>
							</div>
						</div>						
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="smsOut.createDatetime"><s:message code="smsOut.createDatetime" /></label>
								<div class="controls">
									<input type="text" value='<c:out value="${li:formatDateTime(smsOut.createDatetime)}" />' readonly="readonly" />									
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="smsOut.sendDatetime"><s:message code="smsOut.sendDatetime" /></label>
								<div class="controls">
									<input type="text" value='<c:out value="${li:formatDateTime(smsOut.sendDatetime)}" />' readonly="readonly" />									
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