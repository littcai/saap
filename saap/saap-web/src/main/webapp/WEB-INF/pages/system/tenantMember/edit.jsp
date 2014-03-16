<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.inc"%>
<%@ include file="/common/taglibs.inc"%>
<html lang="en">
  <head>		
  </head>
  <body>   
  <form id="theform" action="update.json" method="post" class="form-horizontal">
  	<input type="hidden" name="tenantMemberId" value="${tenantMember.id }" />
				<fieldset>
					<legend><s:message code="common.ui.fieldset.base" /></legend>
					<div class="control-group">
						<label class="control-label visible-ie8 visible-ie9"><s:message code='userInfo.loginId' /></label>
						<div class="controls">							
							<input type="text" name="loginId" value='<c:out value="${userInfo.loginId }"/>' />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label visible-ie8 visible-ie9"><s:message code='userInfo.userName' /></label>
						<div class="controls">							
							<input type="text" name="userName" value='<c:out value="${userInfo.userName }"/>' />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label visible-ie8 visible-ie9"><s:message code='userInfo.nickName' /></label>
						<div class="controls">							
							<input type="text" name="nickName" value='<c:out value="${userInfo.nickName }"/>' />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label visible-ie8 visible-ie9"><s:message code='userInfo.gender' /></label>
						<div class="controls">							
							<select id="gender" name="gender">
								<li:dictOptions dictType="0002" dictValue="${userInfo.gender }"/>
							</select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label visible-ie8 visible-ie9"><s:message code='userInfo.email' /></label>
						<div class="controls">							
							<input type="text" name="email" value='<c:out value="${userInfo.email }"/>' />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label visible-ie8 visible-ie9"><s:message code='userInfo.mobile' /></label>
						<div class="controls">							
							<input type="text" name="mobile" value='<c:out value="${userInfo.mobile }"/>' />
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
					userName : {
						required : true
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