<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.jspf"%>
<%@ include file="/common/taglibs.jspf"%>
<html lang="en">
  <head>	
  	
  </head>
  <body>   
  <form id="theform" action="show.do" method="post" class="form-horizontal">
  	<input type="hidden" name="id" value="${custContacts.id}" />
				<fieldset>
					<legend><s:message code="custContacts.ui.fieldset.base" /></legend>					
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custContacts.customerId"><s:message code="custContacts.customer" /></label>
								<div class="controls">
									<c:out value="${customer.name}" />									
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custContacts.name"><s:message code="custContacts.name" /></label>
								<div class="controls">
									<c:out value="${custContacts.name}" />									
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custContacts.gender"><s:message code="custContacts.gender" /></label>
								<div class="controls">
									${li:genDictContent("0001", custContacts.gender)}	
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custContacts.mobile"><s:message code="custContacts.mobile" /></label>
								<div class="controls">
									<c:out value="${custContacts.mobile}" />								
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custContacts.email"><s:message code="custContacts.email" /></label>
								<div class="controls">
									<a href="mailto:<c:out value="${custContacts.email}" />"><c:out value="${custContacts.email}" /></a>										
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custContacts.phone"><s:message code="custContacts.phone" /></label>
								<div class="controls">
									<c:out value="${custContacts.phone}" />									
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custContacts.fax"><s:message code="custContacts.fax" /></label>
								<div class="controls">
									<c:out value="${custContacts.fax}" />									
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custContacts.address"><s:message code="custContacts.address" /></label>
								<div class="controls">
									<c:out value="${custContacts.address}" />									
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custContacts.zipCode"><s:message code="custContacts.zipCode" /></label>
								<div class="controls">
									<c:out value="${custContacts.zipCode}" />									
								</div>
							</div>
						</div>					
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custContacts.createDatetime"><s:message code="custContacts.createDatetime" /></label>
								<div class="controls">
									${li:formatDateTime(custContacts.createDatetime)}									
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custContacts.updateDatetime"><s:message code="custContacts.updateDatetime" /></label>
								<div class="controls">
									${li:formatDateTime(custContacts.updateDatetime)}							
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custContacts.remark"><s:message code="custContacts.remark" /></label>
								<div class="controls">
									${li:escapeTextarea(custContacts.remark)}								
								</div>
							</div>
						</div>								
					</div>
				</fieldset>					
						
				<div class="form-actions">					
					<button type="button" class="btn" onclick="history.back();"><s:message code="btn.cancel" /></button>
				</div>					
				
			</form>			
			  
  </body>	
</html>