<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.jspf"%>
<%@ include file="/common/taglibs.jspf"%>
<html lang="en">
  <head>	
    <!-- bootstrap-datepicker -->
    <link href="${contextPath}/static/widgets/bootstrap-datepicker/css/bootstrap-datepicker.css" rel="stylesheet" />
    <script src="${contextPath }/static/widgets/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
    <c:if test="${!empty SESSION_USER && !empty SESSION_USER.locale && !fn:startsWith(SESSION_USER.locale, 'en') }">
      <script src="${contextPath }/static/widgets/bootstrap-datepicker/js/locales/bootstrap-datepicker.${SESSION_USER.locale }.js"></script>
    </c:if>	
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
									<input id="customer" name="customer" type="text" value="${customer.name}" readonly="readonly" />
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custContacts.name"><s:message code="custContacts.name" /></label>
								<div class="controls">
									<input id="name" name="name" type="text" value="${custContacts.name}" readonly="readonly" />
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custContacts.gender"><s:message code="custContacts.gender" /></label>
								<div class="controls">
									<input id="gender" name="gender" type="text" value="${li:genDictContent('0002', custContacts.gender )}" readonly="readonly" />
								</div>
							</div>
						</div>	
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="birthday"><s:message code="custContacts.birthday" /></label>
								<div class="controls">
									<input id="birthday" name="birthdayFmt" type="text" value="${li:formatDate(custContacts.birthday) }" readonly="readonly"/>
								</div>
							</div>
						</div>				
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custContacts.mobile"><s:message code="custContacts.mobile" /></label>
								<div class="controls">
									<input id="mobile" name="mobile" type="text" value="${custContacts.mobile}" readonly="readonly" />
								</div>
							</div>
						</div>				
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custContacts.phone"><s:message code="custContacts.phone" /></label>
								<div class="controls">
									<input id="phone" name="phone" type="text" value="${custContacts.phone}" readonly="readonly" />
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custContacts.email"><s:message code="custContacts.email" /></label>
								<div class="controls">
									<input id="email" name="email" type="text" value="${custContacts.email}" readonly="readonly" />
								</div>
							</div>
						</div>	
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custContacts.fax"><s:message code="custContacts.fax" /></label>
								<div class="controls">
									<input id="fax" name="fax" type="text" value="${custContacts.fax}" readonly="readonly" />
								</div>
							</div>
						</div>					
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custContacts.zipCode"><s:message code="custContacts.zipCode" /></label>
								<div class="controls">
									<input id="zipCode" name="zipCode" type="text" value="${custContacts.zipCode}"  readonly="readonly"/>
								</div>
							</div>
						</div>					
					</div>
					<div class="row-fluid">
						<div class="control-group">
							<label class="control-label" for="custContacts.address"><s:message code="custContacts.address" /></label>
							<div class="controls">
								<input id="address" name="address" type="text" value="${custContacts.address}" class="input-block-level" readonly="readonly" />
							</div>
						</div>
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custContacts.remark"><s:message code="custContacts.remark" /></label>
								<div class="controls">
									<textarea rows="3" cols="8" id="remark" name="remark" class="input-block-level" readonly="readonly"><c:out value="${custContacts.remark }" /></textarea>
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