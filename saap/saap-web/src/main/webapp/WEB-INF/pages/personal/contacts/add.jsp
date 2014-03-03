<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.inc"%>
<%@ include file="/common/taglibs.inc"%>
<html lang="en">
  <head>
  	<link href="${contextPath }/widgets/bootstrap-select/bootstrap-select.min.css" rel="stylesheet" />
  	<script src="${contextPath }/widgets/bootstrap-select/bootstrap-select.min.js"></script>		
	</head>
	<body> 			
			<form id="theform" action="save.json" method="post" class="form-horizontal">
				<fieldset>
					<legend><s:message code="common.ui.fieldset.base" /></legend>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="name"><s:message code="contacts.name" /></label>
								<div class="controls">
									<input id="name" name="name" placeholder="" type="text" />
								</div>
							</div>
						</div>
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="gender"><s:message code="contacts.gender" /></label>
								<div class="controls">
									<select id="gender" name="gender">
										<li:dictOptions dictType="0002"/>
									</select>								
								</div>
							</div>
						</div>
					</div>
					<div class="row-fluid">	
						<!-- new line -->
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="mobile"><s:message code="contacts.mobile" /></label>
								<div class="controls">
									<input id="mobile" name="mobile" placeholder="" type="text" />
								</div>
							</div>
						</div>
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="email"><s:message code="contacts.email" /></label>
								<div class="controls">
									<input id="email" name="email" placeholder="" type="text" />
								</div>
							</div>
						</div>
					</div>
					<div class="row-fluid">		
						<!-- new line -->
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="phone"><s:message code="contacts.phone" /></label>
								<div class="controls">
									<input id="phone" name="phone" placeholder="" type="text" />
								</div>
							</div>
						</div>
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="fax"><s:message code="contacts.fax" /></label>
								<div class="controls">
									<input id="fax" name="fax" placeholder="" type="text" />
								</div>
							</div>
						</div>
					</div>
					<div class="row-fluid">		
						<!-- new line -->
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="address"><s:message code="contacts.address" /></label>
								<div class="controls">
									<input id="address" name="address" placeholder="" type="text" />
								</div>
							</div>
						</div>
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="zipCode"><s:message code="contacts.zipCode" /></label>
								<div class="controls">
									<input id="zipCode" name="zipCode" placeholder="" type="text" />
								</div>
							</div>
						</div>
					</div>
				</fieldset>	
				<fieldset>
					<legend><s:message code="contacts.ui.fieldset.group" /></legend>
					<div class="row-fluid">
						<div class="span12">
							<div class="control-group">
								<label class="control-label" for="contactsGroupIds"><s:message code="contactsGroup" /></label>
								<div class="controls">
									<select id="contactsGroupIds" name="contactsGroupIds" multiple="multiple" class="selectpicker show-menu-arrow show-tick span12"  title="<s:message code='common.ui.select' />">
										<c:forEach items="${contactsGroupList }" var="row">
										<option value="${row.id }">${row.name }</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>						
					</div>
				</fieldset>	
				<fieldset>
				<legend><s:message code="contacts.ui.fieldset.ext" /></legend>
						<div class="row-fluid">
							<div class="span12">
								<div class="control-group">
									<label class="control-label" for="remark"><s:message code="contacts.remark" /></label>
									<div class="controls">
										<textarea rows="3" cols="8" id="remark" name="remark" class="input-block-level limited"></textarea>
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
			
			$('.selectpicker').selectpicker();
			
			$('#theform').littFormSubmit({
				rules : {
					name : {
						minlength : 2,
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
					 location.href = "index.do";					
				}
			});
		});
		</script>			
	</body>
</html>
