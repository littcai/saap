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
	<!-- handlebars -->
	<script src="${contextPath }/widgets/handlebars/handlebars-v1.3.0.js"></script>	
	<script src="${contextPath }/widgets/handlebars/handlebars-ext.js"></script>
  </head>
	<body> 			
		<form id="theform" action="update.json" method="post" class="form-horizontal">
			<input id="customerId" name="id" type="hidden" value="${customer.id }">
			<input id="parentId" name="customer_parentId" type="hidden" value="${customer.parentId }">
			<fieldset>
				<legend><s:message code="common.ui.fieldset.base" /></legend>
				<div class="row-fluid">
					<div class="span6">
						<div class="control-group">
							<label class="control-label" for="name"><s:message code="customer.name" /></label>
							<div class="controls">
								<input id="name" name="customer_name" placeholder="" type="text" value="${li:out(customer.name)}" />
							</div>
						</div>
					</div>
					<div class="span6">
						<div class="control-group">
							<label class="control-label" for="code"><s:message code="customer.code" /></label>
							<div class="controls">
								<input id="code" name="customer_code" placeholder="" type="text" value="${li:out(customer.code)}" />
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
								  <input id="parentName" name="customer_parentName" placeholder="" type="text" value="<c:out value='${parentCustomer.name}' />" readonly="readonly">
								  <button class="btn" type="button" onclick="selectParent(this);"><i class="icon-search"></i></button>  
								</div>		
							</div>
						</div>
					</div>					
				</div>
				<div class="row-fluid">							
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="contactsName"><s:message code="customer.contacts" /></label>
								<div class="controls">
									<div class="input-append">
										<select id="contactsId" name="customer_contactsId" data-placeholder="<s:message code='common.ui.select' />">
											<option value=""></option>		
											<li:optionsCollection var="row" collection="${custContactsList }" value="${customer.contactsId }">
												<li:option property="${row.id }">${row.name }</li:option>
											</li:optionsCollection>								
										</select>											
									</div>	
								</div>
							</div>
						</div>
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="chargeBy"><s:message code="customer.chargeUser" /></label>
								<div class="controls">
									<select id="chargeBy" name="customer_chargeBy" data-placeholder="<s:message code='common.ui.select' />">
										<option value=""></option>
										<li:optionsCollection collection="${chargeUserList }" var="row" value="${customer.chargeBy }">	
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
										<textarea rows="3" cols="8" id="remark" name="customer_remark" class="input-block-level limited"><c:out value="${customer.remark }"></c:out></textarea>
									</div>
								</div>
							</div>
						</div>
				</fieldset>
				<fieldset>
						<legend><s:message code="customer.ui.fieldset.contactInfo" /></legend>
				
				<div class="row-fluid">		
					<!-- new line -->
					<div class="span6">
						<div class="control-group">
							<label class="control-label" for="phone"><s:message code="customer.phone" /></label>
							<div class="controls">
								<input id="phone" name="customer_phone" placeholder="" type="text" value="${li:out(customer.phone)}" />
							</div>
							</div>
						</div>
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="fax"><s:message code="customer.fax" /></label>
								<div class="controls">
									<input id="fax" name="customer_fax" placeholder="" type="text" value="${li:out(customer.fax)}" />
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
									<input id="email" name="customer_email" placeholder="" type="text" value="${li:out(customer.email)}" />
								</div>
							</div>
						</div>
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="website"><s:message code="customer.website" /></label>
								<div class="controls">
									<div class="input-prepend">
  										<span class="add-on">http://</span>
										<input id="website" name="customer_website" placeholder="" type="text"  value="${li:out(customer.website)}" />
									</div>	
								</div>
							</div>
						</div>
					</div>	
					<div class="row-fluid">	
						<div class="control-group">
							<label class="control-label" for="zipCode"><s:message code="customer.zipCode" /></label>
							<div class="controls">
								<input id="zipCode" name="customer_zipCode" placeholder="" type="text" value="${li:out(customer.zipCode)}" />
							</div>
						</div>
					</div>	
					<div class="row-fluid">	
						<div class="control-group">
							<label class="control-label" for="address"><s:message code="customer.address" /></label>
							<div class="controls">
								<input id="address" name="customer_addess" placeholder="" type="text" class="input-block-level" value="${li:out(customer.address)}"/>
							</div>
						</div>
					</div>
				</fieldset>	
				
				<fieldset>
						<legend><s:message code="customer.ui.fieldset.attachment" /></legend>
						<h:attachment recordId="${customer.id }" moduleCode="customer" attachmentList="${attachmentList }"></h:attachment>
				</fieldset>						
						
					<div class="form-actions">
						<button type="submit" class="btn btn-primary" data-loading-text="<s:message code='common.processing' />"><i class="icon-ok"></i> <s:message code="btn.save" /></button>
						<button type="button" class="btn" onclick="history.back();"><s:message code="btn.cancel" /></button>					
					</div>						
			</form>
		<%@ include file="customerSelect.jsp"%>	
							
		<!--page specific plugin scripts-->				
		<script type="text/javascript">
		$(document).ready(function(){	
						
			//init charge user select
			$("#chargeBy").select2({
				width: 'resolve'
			});	
			$("#contactsId").select2({
				width: 'resolve'
			});
			
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
		
		function selectParent()
		{
			$('#customerModal').modal();
		}
		
		function onCustomerSelect(customer)
		{
			$('#customerModal').modal('hide');
		}	
		</script>			
	</body>
</html>
