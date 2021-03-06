<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.jspf"%>
<%@ include file="/common/taglibs.jspf"%>
<html lang="en">
  <head>	
	<!-- jquery file upload-8.7.1 -->
	<link href="${contextPath}/static/widgets/jquery-fileupload-8.7.1/css/jquery.fileupload-ui.css" rel="stylesheet" />	
	<script src="${contextPath }/static/widgets/jquery-fileupload-8.7.1/js/jquery.ui.widget.js"></script>
	<script src="${contextPath }/static/widgets/jquery-fileupload-8.7.1/js/jquery.iframe-transport.js"></script>
	<script src="${contextPath }/static/widgets/jquery-fileupload-8.7.1/js/jquery.fileupload.js"></script>	
	<!-- handlebars -->
	<script src="${contextPath }/static/widgets/handlebars/handlebars-v1.3.0.js"></script>	
	<script src="${contextPath }/static/widgets/handlebars/handlebars-ext.js"></script>
	<!-- angularjs -->
	<script type="text/javascript" src="${contextPath }/theme/default/js/angular.js"></script>			
  </head>
	<body> 			
		<form id="theform" action="save.json" method="post" class="form-horizontal">
			<input id="parentId" name="customer_parentId" type="hidden">
			
			<fieldset>
				<legend><s:message code="common.ui.fieldset.base" /></legend>
				<div class="row-fluid">
					<div class="span6">
						<div class="control-group">
							<label class="control-label" for="name"><s:message code="customer.name" /></label>
							<div class="controls">
								<input id="name" name="customer_name" type="text" />
							</div>
						</div>
					</div>
					<div class="span6">
						<div class="control-group">
							<label class="control-label" for="code"><s:message code="customer.code" /></label>
							<div class="controls">
								<input id="code" name="customer_code" type="text" />
							</div>
						</div>
					</div>
				</div>
				<div class="row-fluid">	
                    <div class="span6">
                      <div class="control-group">
                        <label class="control-label" for="fullName"><s:message code="customer.fullName" /></label>
                        <div class="controls">
                          <input id="fullName" name="customer_fullName" type="text" />
                        </div>
                      </div>
                    </div>
					<!-- new line -->
					<div class="span6">
						<div class="control-group">
							<label class="control-label" for="parentName"><s:message code="customer.parent" /></label>
							<div class="controls">								
								<div class="input-append">
								  <input id="parentName" name="customer_parentName" type="text" readonly="readonly">
								  <button class="btn" type="button" onclick="selectParent(this);"><i class="icon-search"></i></button>  
								</div>								
							</div> 
						</div>
					</div>					
				</div>
				<div class="row-fluid">							
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="contactsId"><s:message code="customer.contacts" /></label>
								<div class="controls">
									<select id="contactsId" name="customer_contactsId" data-placeholder="<s:message code='common.ui.select' />">
										<option value=""></option>										
									</select>								
								</div>
							</div>
						</div>
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="chargeBy"><s:message code="customer.chargeUser" /></label>
								<div class="controls">
									<select id="chargeBy" name="customer_chargeBy" data-placeholder="<s:message code='common.ui.select' />">
										<option value=""></option>
										<li:optionsCollection collection="${chargeUserList }" var="row">	
											<li:option property="${row.id }">${row.userName } (${row.loginId})</li:option>			
										</li:optionsCollection>	
									</select>									
								</div>
							</div>
						</div>
					</div>					
					
					
					<div class="row-fluid">
							<div class="control-group">
								<label class="control-label" for="customer.remark"><s:message code="customer.remark" /></label>
								<div class="controls">
									<textarea rows="3" cols="8" id="remark" name="customer_remark" class="input-block-level limited"></textarea>
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
									<input id="phone" name="customer_phone" type="text" />
								</div>
								</div>
							</div>
							<div class="span6">
								<div class="control-group">
									<label class="control-label" for="fax"><s:message code="customer.fax" /></label>
									<div class="controls">
										<input id="fax" name="customer_fax" type="text" />
									</div>
								</div>
							</div>
						</div>
						<div class="row-fluid">		
							<!-- new line -->
							<div class="span6">
								<div class="control-group">
									<label class="control-label" for="email"><s:message code="customer.email" /></label>
									<div class="controls">
										<input id="email" name="customer_email" type="text" />
									</div>
								</div>
							</div>
							<div class="span6">
								<div class="control-group">
									<label class="control-label" for="website"><s:message code="customer.website" /></label>
									<div class="controls">
										<div class="input-prepend">
	  										<span class="add-on">http://</span>
											<input id="website" name="customer_website" type="text" />
										</div>	
									</div>
								</div>
							</div>
						</div>	
						<div class="row-fluid">		
							<div class="control-group">
								<label class="control-label" for="zipCode"><s:message code="customer.zipCode" /></label>
								<div class="controls">
									<input id="zipCode" name="customer_zipCode" type="text" />
								</div>
							</div>
						</div>
						<div class="row-fluid">	
							<div class="control-group">
								<label class="control-label" for="address"><s:message code="customer.address" /></label>
								<div class="controls">
									<input id="address" name="customer_addess" type="text" class="input-block-level"/>
								</div>
							</div>
						</div>				
				
					</fieldset>
					
					<fieldset>
						<legend><s:message code="customer.ui.fieldset.financeInfo" /></legend>
						<div class="row-fluid">	
						  <div class="span6">
							<div class="control-group">
								<label class="control-label" for="paymentDays"><s:message code="customer.paymentDays" /></label>
								<div class="controls">
									<input id="paymentDays" name="customer_paymentDays" type="text" />
								</div>
								</div>
							</div>
							<div class="span6">
								<div class="control-group">
									<label class="control-label" for="billingFullName"><s:message code="customer.billingFullName" /></label>
									<div class="controls">
										<input id="billingFullName" name="customer_billingFullName" type="text" />
									</div>
								</div>
							</div>
						</div>
						<div class="row-fluid">	
						  <div class="span6">
							<div class="control-group">
								<label class="control-label" for="accountNo"><s:message code="customer.accountNo" /></label>
								<div class="controls">
									<input id="accountNo" name="customer_accountNo" type="text" />
								</div>
								</div>
							</div>
							<div class="span6">
							<div class="control-group">
								<label class="control-label" for="bankName"><s:message code="customer.bankName" /></label>
								<div class="controls">
									<input id="bankName" name="customer_bankName" type="text" />
								</div>
								</div>
							</div>
							
						</div>
						<div class="row-fluid">	
						  <div class="span6">
								<div class="control-group">
									<label class="control-label" for="taxNo"><s:message code="customer.taxNo" /></label>
									<div class="controls">
										<input id="taxNo" name="customer_taxNo" type="text" />
									</div>
								</div>
						  </div>
						</div>
						<div class="row-fluid">	
							<div class="control-group">
								<label class="control-label" for="billingAddress"><s:message code="customer.billingAddress" /></label>
								<div class="controls">
									<input id="billingAddress" name="customer_billingAddress" type="text" class="input-block-level"/>
								</div>
							</div>
						</div>
						<div class="row-fluid">	
							<div class="control-group">
								<label class="control-label" for="mailingAddress"><s:message code="customer.mailingAddress" /></label>
								<div class="controls">
									<input id="mailingAddress" name="customer_mailingAddress" type="text" class="input-block-level"/>
								</div>
							</div>
						</div>
						
						<div class="row-fluid">
							<div class="control-group">
								<label class="control-label" for="billingRemark"><s:message code="customer.billingRemark" /></label>
								<div class="controls">
									<textarea rows="3" cols="8" id="billingRemark" name="customer_billingRemark" class="input-block-level"></textarea>
								</div>
							</div>
						</div>
					</fieldset>	
					
					<fieldset>
						<legend><s:message code="customer.ui.fieldset.attachment" /></legend>
						<h:attachment recordId="0" moduleCode="customer"></h:attachment>
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
					customer_name : {
						minlength : 2,
						required : true
					},	
					customer_code : {
						minlength : 2,
						required : true
					},	
					customer_chargeBy: {
						required: true
					},
					customer_email : {
						email : true
					},
					customer_phone : {
						maxlength : 50
					},
					customer_fax : {
						maxlength : 50
					},
					customer_address : {
						maxlength : 200
					},
					customer_zipCode : {
						maxlength : 50
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
