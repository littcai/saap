<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.jspf"%>
<%@ include file="/common/taglibs.jspf"%>
<html lang="en">
  <head>
  </head>
	<body> 			
		<form id="theform" action="show.json" method="post" class="form-horizontal">
			<fieldset>
				<legend><s:message code="common.ui.fieldset.base" /></legend>
				<div class="row-fluid">
					<div class="span6">
						<div class="control-group">
							<label class="control-label" for="name"><s:message code="customer.name" /></label>
							<div class="controls">
								<input id="name" name="name" placeholder="" type="text" value="${li:out(customer.name)}" readonly="readonly" />
							</div>
						</div>
					</div>
					<div class="span6">
						<div class="control-group">
							<label class="control-label" for="code"><s:message code="customer.code" /></label>
							<div class="controls">
								<input id="code" name="code" placeholder="" type="text" value="${li:out(customer.code)}" readonly="readonly" />
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
								<input id="parentName" name="parentName" placeholder="" type="text" value="${li:out(parentCustomer.name)}" readonly="readonly" />
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
										<input id="contactsName" name="contactsName" placeholder="" type="text" value="${li:out(custContacts.name)}" readonly="readonly" />										
									</div>	
								</div>
							</div>
						</div>
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="chargeUser"><s:message code="customer.chargeUser" /></label>
								<div class="controls">
									<input id="chargeUser" name="chargeUser" placeholder="" type="text" value="${li:out(chargeUser.name)}" readonly="readonly" />								
								</div>
							</div>
						</div>
					</div>
					<div class="row-fluid">
							<div class="span12">
								<div class="control-group">
									<label class="control-label" for="remark"><s:message code="customer.remark" /></label>
									<div class="controls">
										<textarea rows="3" cols="8" id="remark" name="remark" class="input-block-level limited" readonly="readonly" ><c:out value="${customer.remark }"></c:out></textarea>
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
								<input id="phone" name="phone" placeholder="" type="text" value="${li:out(customer.phone)}" readonly="readonly"  />
							</div>
							</div>
						</div>
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="fax"><s:message code="customer.fax" /></label>
								<div class="controls">
									<input id="fax" name="fax" placeholder="" type="text" value="${li:out(customer.fax)}" readonly="readonly"  />
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
									<input id="email" name="email" placeholder="" type="text" value="${li:out(customer.email)}" readonly="readonly"  />
								</div>
							</div>
						</div>
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="website"><s:message code="customer.website" /></label>
								<div class="controls">
									<div class="input-prepend">
  										<span class="add-on">http://</span>
										<input id="website" name="website" placeholder="" type="text"  value="${li:out(customer.website)}" readonly="readonly"  />
									</div>	
								</div>
							</div>
						</div>
					</div>	
					<div class="row-fluid">	
						<div class="control-group">
							<label class="control-label" for="zipCode"><s:message code="customer.zipCode" /></label>
							<div class="controls">
								<input id="zipCode" name="zipCode" placeholder="" type="text" value="${li:out(customer.zipCode)}" readonly="readonly"  />
							</div>
						</div>
					</div>	
					<div class="row-fluid">	
						<div class="control-group">
							<label class="control-label" for="address"><s:message code="customer.address" /></label>
							<div class="controls">
								<input id="address" name="addess" placeholder="" type="text" class="input-block-level" value="${li:out(customer.address)}" readonly="readonly" />
							</div>
						</div>
					</div>
				</fieldset>	
				
				<fieldset>
						<legend><s:message code="customer.ui.fieldset.attachment" /></legend>
						<h:attachment recordId="${customer.id }" moduleCode="customer" attachmentList="${attachmentList }" readonly="true"></h:attachment>
				</fieldset>						
						
				<div class="form-actions">						
					<button type="button" class="btn" onclick="history.back();"><s:message code="btn.back" /></button>					
				</div>						
			</form>
							
		<!--page specific plugin scripts-->				
		<script type="text/javascript">
		
		</script>			
	</body>
</html>
