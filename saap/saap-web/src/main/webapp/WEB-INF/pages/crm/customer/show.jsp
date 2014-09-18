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
									<input id="chargeUser" name="chargeUser" placeholder="" type="text" value="${li:out(chargeUser.userName)}" readonly="readonly" />								
								</div>
							</div>
						</div>
					</div>
					<div class="row-fluid">
						<div class="control-group">
							<label class="control-label" for="remark"><s:message code="customer.remark" /></label>
							<div class="controls">
								<textarea rows="3" cols="8" id="remark" name="remark" class="input-block-level limited" readonly="readonly" ><c:out value="${customer.remark }"></c:out></textarea>
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
						<legend><s:message code="customer.ui.fieldset.financeInfo" /></legend>
						<div class="row-fluid">	
						  <div class="span6">
							<div class="control-group">
								<label class="control-label" for="paymentDays"><s:message code="customer.paymentDays" /></label>
								<div class="controls">
									<input id="paymentDays" name="customer_paymentDays" type="text" value="<c:out value='${customer.paymentDays }'/>"  readonly="readonly" />
								</div>
								</div>
							</div>
							<div class="span6">
								<div class="control-group">
									<label class="control-label" for="billingFullName"><s:message code="customer.billingFullName" /></label>
									<div class="controls">
										<input id="billingFullName" name="customer_billingFullName" type="text" value="<c:out value='${customer.billingFullName }'/>" readonly="readonly"  />
									</div>
								</div>
							</div>
						</div>
						<div class="row-fluid">	
						  <div class="span6">
							<div class="control-group">
								<label class="control-label" for="accountNo"><s:message code="customer.accountNo" /></label>
								<div class="controls">
									<input id="accountNo" name="customer_accountNo" type="text" value="<c:out value='${customer.accountNo }'/>" readonly="readonly"  />
								</div>
								</div>
							</div>
							<div class="span6">
							<div class="control-group">
								<label class="control-label" for="bankName"><s:message code="customer.bankName" /></label>
								<div class="controls">
									<input id="bankName" name="customer_bankName" type="text" value="<c:out value='${customer.bankName }'/>" readonly="readonly"  />
								</div>
								</div>
							</div>
							
						</div>
						<div class="row-fluid">	
						  <div class="span6">
								<div class="control-group">
									<label class="control-label" for="taxNo"><s:message code="customer.taxNo" /></label>
									<div class="controls">
										<input id="taxNo" name="customer_taxNo" type="text" value="<c:out value='${customer.taxNo }'/>"  readonly="readonly" />
									</div>
								</div>
						  </div>
						</div>
						<div class="row-fluid">	
							<div class="control-group">
								<label class="control-label" for="billingAddress"><s:message code="customer.billingAddress" /></label>
								<div class="controls">
									<input id="billingAddress" name="customer_billingAddress" type="text" class="input-block-level" value="<c:out value='${customer.billingAddress }'/>" readonly="readonly"  />
								</div>
							</div>
						</div>
						<div class="row-fluid">	
							<div class="control-group">
								<label class="control-label" for="mailingAddress"><s:message code="customer.mailingAddress" /></label>
								<div class="controls">
									<input id="mailingAddress" name="customer_mailingAddress" type="text" class="input-block-level" value="<c:out value='${customer.mailingAddress }'/>" readonly="readonly"  />
								</div>
							</div>
						</div>
						
						<div class="row-fluid">
							<div class="control-group">
								<label class="control-label" for="billingRemark"><s:message code="customer.billingRemark" /></label>
								<div class="controls">
									<textarea rows="3" cols="8" id="billingRemark" name="customer_billingRemark" class="input-block-level" readonly="readonly" ><c:out value="${customer.billingRemark }"></c:out></textarea>
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
