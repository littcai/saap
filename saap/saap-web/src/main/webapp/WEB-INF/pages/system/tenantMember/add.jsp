<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.jspf"%>
<%@ include file="/common/taglibs.jspf"%>
<html lang="en">
  <head>		
  </head>
  <body>   
  <form id="theform" action="save.json" method="post" class="form-horizontal">
				<fieldset>
					<legend><s:message code="common.ui.fieldset.base" /></legend>
					<div class="control-group">
						<label class="control-label visible-ie8 visible-ie9"><s:message code='userInfo.loginId' /></label>
						<div class="controls">							
							<input type="text" name="loginId" placeholder="<s:message code='userInfo.loginId.help' />" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label visible-ie8 visible-ie9"><s:message code='userInfo.password' /></label>
						<div class="controls">
							<input class="" type="password" id="password" placeholder="<s:message code='userInfo.password' />" name="password"/>
						</div>
					</div>	
					<div class="control-group">
						<label class="control-label visible-ie8 visible-ie9"><s:message code='userInfo.rpassword' /></label>
						<div class="controls">
							<input class="" type="password" id="rpassword" placeholder="<s:message code='userInfo.rpassword' />" name="rpassword"/>							
						</div>
					</div>
					<div class="control-group">
						<label class="control-label visible-ie8 visible-ie9"><s:message code='userInfo.userName' /></label>
						<div class="controls">							
							<input type="text" name="userName" value='' />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label visible-ie8 visible-ie9"><s:message code='userInfo.nickName' /></label>
						<div class="controls">							
							<input type="text" name="nickName" value='' />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label visible-ie8 visible-ie9"><s:message code='userInfo.gender' /></label>
						<div class="controls">							
							<select id="gender" name="gender">
								<li:dictOptions dictType="0002" />
							</select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label visible-ie8 visible-ie9"><s:message code='userInfo.email' /></label>
						<div class="controls">							
							<input type="text" name="email" value='' />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label visible-ie8 visible-ie9"><s:message code='userInfo.mobile' /></label>
						<div class="controls">							
							<input type="text" name="mobile" value='' />
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
			
			$('#theform').littFormSubmit({
				rules : {
					
					loginId : {
						required : true,
						minlength: 4,
						maxlength: 50
					},
					password : {
						required : true,
						minlength: 6,
						maxlength: 20
					},
					rpassword : {
						required : true,
						minlength: 6,
						maxlength: 20,
						equalTo: "#password"
					},
					userName : {
						required : true
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