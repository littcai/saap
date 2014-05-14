<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.inc"%>
<%@ include file="/common/taglibs.inc"%>
<html lang="en">
  <head>
  	<!-- angularjs -->
  	<script type="text/javascript" src="${contextPath }/theme/default/js/angular.js"></script>	
	<!-- jquery file upload-8.7.1 -->
	<link href="${contextPath}/widgets/jquery-fileupload-8.7.1/css/jquery.fileupload-ui.css" rel="stylesheet" />	
	<script src="${contextPath }/widgets/jquery-fileupload-8.7.1/js/jquery.ui.widget.js"></script>
	<script src="${contextPath }/widgets/jquery-fileupload-8.7.1/js/jquery.iframe-transport.js"></script>
	<script src="${contextPath }/widgets/jquery-fileupload-8.7.1/js/jquery.fileupload.js"></script>	
	<script src="${contextPath }/widgets/jquery-fileupload-8.7.1/js/jquery.fileupload-angular.js"></script>	
  </head>
	<body> 			
		<form id="theform" action="update.json" method="post" class="form-horizontal">
			<input id="customerId" name="id" type="hidden" value="${customer.id }">
			<input id="parentId" name="parentId" type="hidden" value="${customer.parentId }">
			<fieldset>
				<legend><s:message code="contacts.ui.fieldset.base" /></legend>
				<div class="row-fluid">
					<div class="span6">
						<div class="control-group">
							<label class="control-label" for="name"><s:message code="customer.name" /></label>
							<div class="controls">
								<input id="name" name="name" placeholder="" type="text" value="${li:out(customer.name)}" />
							</div>
						</div>
					</div>
					<div class="span6">
						<div class="control-group">
							<label class="control-label" for="code"><s:message code="customer.code" /></label>
							<div class="controls">
								<input id="code" name="code" placeholder="" type="text" value="${li:out(customer.code)}" />
							</div>
						</div>
					</div>
				</div>
				<div class="row-fluid">	
					<!-- new line -->
					<div class="span12">
						<div class="control-group">
							<label class="control-label" for="parentName"><s:message code="customer.parent" /></label>
							<div class="controls">								
								<div class="input-append">
								  <input id="parentName" name="parentName" placeholder="" type="text" readonly="readonly" value="${li:out(parentCustomer.name)}">
								  <button class="btn" type="button" onclick="selectParent(this);"><i class="icon-search"></i></button>  
								</div>								
							</div>
						</div>
					</div>					
				</div>
				<div class="row-fluid">		
					<!-- new line -->
					<div class="span6">
						<div class="control-group">
							<label class="control-label" for="phone"><s:message code="customer.phone" /></label>
							<div class="controls">
								<input id="phone" name="phone" placeholder="" type="text" value="${li:out(customer.phone)}" />
							</div>
							</div>
						</div>
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="fax"><s:message code="customer.fax" /></label>
								<div class="controls">
									<input id="fax" name="fax" placeholder="" type="text" value="${li:out(customer.fax)}" />
								</div>
							</div>
						</div>
					</div>
					<!-- new line -->
					<div class="row-fluid">							
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="email"><s:message code="customer.email" /></label>
								<div class="controls">
									<input id="email" name="email" placeholder="" type="text" value="${li:out(customer.email)}" />
								</div>
							</div>
						</div>
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="website"><s:message code="customer.website" /></label>
								<div class="controls">
									<div class="input-prepend">
  										<span class="add-on">http://</span>
										<input id="website" name="website" placeholder="" type="text"  value="${li:out(customer.website)}" />
									</div>	
								</div>
							</div>
						</div>
					</div>	
					<!-- new line -->
					<div class="row-fluid">							
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="contactsName"><s:message code="customer.contacts" /></label>
								<div class="controls">
									<div class="input-append">
										<input id="contactsName" name="contactsName" placeholder="" type="text" value="${li:out(contacts.name)}" readonly="readonly"  />									  
										<button class="btn" type="button" onclick="selectContacts(this);"><i class="icon-search"></i></button>  
									</div>	
								</div>
							</div>
						</div>
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="chargeUserId"><s:message code="customer.chargeUser" /></label>
								<div class="controls">
									<select id="chargeUserId" name="chargeUserId" data-placeholder="<s:message code='common.ui.select' />">
										<option></option>
										<li:optionsCollection collection="${chargeUserList }" var="row" value="${customer.chargeUserId}">	
											<li:option property="${row.id }">${row.userName } (${row.loginId})</li:option>			
										</li:optionsCollection>	
									</select>									
								</div>
							</div>
						</div>
					</div>					
					
					<div class="row-fluid">
							<div class="span12">
								<div class="control-group">
									<label class="control-label" for="remark"><s:message code="customer.remark" /></label>
									<div class="controls">
										<textarea rows="3" cols="8" id="remark" name="remark" class="input-block-level limited">${li:out(customer.remark)}</textarea>
									</div>
								</div>
							</div>
						</div>
					</fieldset>
						
					<div class="form-actions">
						<button type="submit" class="btn btn-primary" data-loading-text="<s:message code='common.processing' />"><i class="icon-ok"></i> <s:message code="btn.save" /></button>
						<button type="button" class="btn" onclick="history.back();"><s:message code="btn.cancel" /></button>					
					</div>						
			</form>
		<!-- contacts select -->		
		
							
		<!--page specific plugin scripts-->				
		<script type="text/javascript">
		$(document).ready(function(){	
						
			//init charge user select
			$("#chargeUserId").select2();		
			
			$('#theform').littFormSubmit({
				rules : {
					name : {
						minlength : 2,
						required : true
					},					
					
					email : {
						required : true,
						email : true
					},
					phone : {
						required: true,
						maxlength : 50
					},
					fax : {
						maxlength : 50
					},
					address : {
						maxlength : 200
					},
					zipCode : {
						maxlength : 50
					},
					chargeUserId: {
						required: true
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
