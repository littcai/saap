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
  <form id="theform" action="update.json" method="post" class="form-horizontal">
  	<input type="hidden" name="id" value="${custContacts.id}" />
				<fieldset>
					<legend><s:message code="custContacts.ui.fieldset.base" /></legend>					
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custContacts.customerId"><s:message code="custContacts.customer" /></label>
								<div class="controls">
									<select id="customerId" name="customerId">
										<li:optionsCollection collection="${customerList }" var="customer" value="${custContacts.customerId}">	
											<li:option property="${customer.id }">${customer.name }</li:option>			
										</li:optionsCollection>
									</select>	
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custContacts.name"><s:message code="custContacts.name" /></label>
								<div class="controls">
									<input id="name" name="name" placeholder="" type="text" value="${custContacts.name}" />
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custContacts.gender"><s:message code="custContacts.gender" /></label>
								<div class="controls">
									<select id="gender" name="gender">
										<li:dictOptions dictType="0002" dictValue="${custContacts.gender}"/>
									</select>										
								</div>
							</div>
						</div>	
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="birthday"><s:message code="custContacts.birthday" /></label>
								<div class="controls">
									<div class="input-append date datepicker" data-date-format="yyyy-mm-dd">
						                <input id="birthday" name="birthdayFmt" type="text" value="${li:formatDate(custContacts.birthday) }"/>
						                <span class="add-on"><i class="icon-calendar"></i></span>
						            </div>
								</div>
							</div>
						</div>				
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custContacts.mobile"><s:message code="custContacts.mobile" /></label>
								<div class="controls">
									<input id="mobile" name="mobile" placeholder="" type="text" value="${custContacts.mobile}" />
								</div>
							</div>
						</div>				
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custContacts.phone"><s:message code="custContacts.phone" /></label>
								<div class="controls">
									<input id="phone" name="phone" placeholder="" type="text" value="${custContacts.phone}" />
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custContacts.email"><s:message code="custContacts.email" /></label>
								<div class="controls">
									<input id="email" name="email" placeholder="" type="text" value="${custContacts.email}" />
								</div>
							</div>
						</div>	
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custContacts.fax"><s:message code="custContacts.fax" /></label>
								<div class="controls">
									<input id="fax" name="fax" placeholder="" type="text" value="${custContacts.fax}" />
								</div>
							</div>
						</div>					
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custContacts.zipCode"><s:message code="custContacts.zipCode" /></label>
								<div class="controls">
									<input id="zipCode" name="zipCode" placeholder="" type="text" value="${custContacts.zipCode}" />
								</div>
							</div>
						</div>					
					</div>
					<div class="row-fluid">
						<div class="control-group">
							<label class="control-label" for="custContacts.address"><s:message code="custContacts.address" /></label>
							<div class="controls">
								<input id="address" name="address" placeholder="" type="text" value="${custContacts.address}" class="input-block-level"/>
							</div>
						</div>
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="custContacts.remark"><s:message code="custContacts.remark" /></label>
								<div class="controls">
									<textarea rows="3" cols="8" id="remark" name="remark" class="input-block-level"><c:out value="${custContacts.remark }" /></textarea>
								</div>
							</div>
						</div>								
					</div>
				</fieldset>					
						
				<div class="form-actions">
					<button type="submit" class="btn btn-primary"><i class="icon-ok"></i> <s:message code="btn.save" /></button>
					<button type="button" class="btn" onclick="history.back();"><s:message code="btn.cancel" /></button>
				</div>					
				
			</form>				
		<!--page specific plugin scripts-->				
		<script type="text/javascript">
		$(document).ready(function(){	
			$('.datepicker').datepicker({
		  		  todayBtn: true,
		  		  todayHighlight: true
		  	});
			
			$("#customerId").select2({
			  width: 'resolve'
			});				
			
			$('#theform').littFormSubmit({
				rules : {
					name : {
						required : true
					},
					gender : {
						required : true
					},
					mobile : {
						required : true
					},
					email : {
						required : true,
						email : true
					},
					phone : {
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