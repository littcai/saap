<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.inc"%>
<%@ include file="/common/taglibs.inc"%>
<html lang="en">
  <head>	
	<!-- jquery file upload-8.7.1 -->
	<link href="${contextPath}/widgets/jquery-fileupload-8.7.1/css/jquery.fileupload-ui.css" rel="stylesheet" />	
	<script src="${contextPath }/widgets/jquery-fileupload-8.7.1/js/jquery.ui.widget.js"></script>
	<script src="${contextPath }/widgets/jquery-fileupload-8.7.1/js/jquery.iframe-transport.js"></script>
	<script src="${contextPath }/widgets/jquery-fileupload-8.7.1/js/jquery.fileupload.js"></script>	
	<!-- handlebars -->
	<script src="${contextPath }/widgets/handlebars/handlebars-v1.3.0.js"></script>	
	<script src="${contextPath }/widgets/handlebars/handlebars-ext.js"></script>	
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
								<input id="name" name="customer_name" placeholder="" type="text" />
							</div>
						</div>
					</div>
					<div class="span6">
						<div class="control-group">
							<label class="control-label" for="code"><s:message code="customer.code" /></label>
							<div class="controls">
								<input id="code" name="customer_code" placeholder="" type="text" />
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
								  <input id="parentName" name="customer_parentName" placeholder="" type="text" readonly="readonly">
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
										<input id="contactsName" name="customer_contactsName" placeholder="" type="text" value="" readonly="readonly"  />
										<input id="contactsId" name="customer_contactsId" type="hidden">									  
										<button class="btn" type="button" onclick="selectContacts(this);"><i class="icon-search"></i></button>  
									</div>	
								</div>
							</div>
						</div>
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="chargeUserId"><s:message code="customer.chargeUser" /></label>
								<div class="controls">
									<select id="chargeUserId" name="customer_chargeUserId" data-placeholder="<s:message code='common.ui.select' />">
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
							<div class="span12">
								<div class="control-group">
									<label class="control-label" for="customer.remark"><s:message code="customer.remark" /></label>
									<div class="controls">
										<textarea rows="3" cols="8" id="remark" name="remark" class="input-block-level limited"></textarea>
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
									<input id="phone" name="customer_phone" placeholder="" type="text" />
								</div>
								</div>
							</div>
							<div class="span6">
								<div class="control-group">
									<label class="control-label" for="fax"><s:message code="customer.fax" /></label>
									<div class="controls">
										<input id="fax" name="customer_fax" placeholder="" type="text" />
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
										<input id="email" name="customer_email" placeholder="" type="text" />
									</div>
								</div>
							</div>
							<div class="span6">
								<div class="control-group">
									<label class="control-label" for="website"><s:message code="customer.website" /></label>
									<div class="controls">
										<div class="input-prepend">
	  										<span class="add-on">http://</span>
											<input id="website" name="customer_website" placeholder="" type="text" />
										</div>	
									</div>
								</div>
							</div>
						</div>	
						<div class="row-fluid">		
							<div class="control-group">
								<label class="control-label" for="zipCode"><s:message code="customer.zipCode" /></label>
								<div class="controls">
									<input id="zipCode" name="customer_zipCode" placeholder="" type="text" />
								</div>
							</div>
						</div>
						<div class="row-fluid">	
							<div class="control-group">
								<label class="control-label" for="address"><s:message code="customer.address" /></label>
								<div class="controls">
									<input id="address" name="customer_addess" placeholder="" type="text" class="input-block-level"/>
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
		<!--page specific plugin scripts-->	
		<script type="text/javascript">
		$(document).ready(function(){	
			
			//init charge user select
			$("#chargeUserId").select2({
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
			
		}
		
		function selectContacts()
		{
			
		}
		
		
		</script>			
	</body>
</html>
