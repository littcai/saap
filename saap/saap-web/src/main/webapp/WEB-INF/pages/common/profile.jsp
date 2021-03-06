<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.jspf"%>
<%@ include file="/common/taglibs.jspf"%>
<html lang="en">
	<head>
		<title><s:message code="login.ui.title" /></title>
	</head>
	<body> 	
	<div>	
		<c:if test="${not empty SESSION_USER.tenant }">
		<div class="btn-toolbar">
			<button class="btn btn-warning" onclick="quitTenant();"><s:message code="tenantMember.func.quit"/></button>
		</div>
		</c:if>
		<ul class="nav nav-tabs">
		  <li class="active"><a href="#basic" data-toggle="tab"><s:message code="profile.ui.tab.basic" /></a></li>
		  <li><a href="#state" data-toggle="tab"><s:message code="profile.ui.tab.state" /></a></li>
		  <li><a href="#tenant" data-toggle="tab"><s:message code="profile.ui.tab.tenant" /></a></li>
		  <li><a href="#password" data-toggle="tab"><s:message code="profile.ui.tab.password" /></a></li>
		</ul>
		
		<div class="tab-content ">
		  <div class="tab-pane active" id="basic">
		  	<form id="basic-form" action="update.json" method="POST" class="form-horizontal">
		  		<fieldset>
		  			<legend><s:message code="profile.ui.fieldset.general" /></legend>
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
				<fieldset>
		  			<legend><s:message code="profile.ui.fieldset.setting" /></legend>	
					<div class="control-group">
						<label class="control-label visible-ie8 visible-ie9"><s:message code='userInfo.locale' /></label>
						<div class="controls">				
							<select name="locale">
								<li:dictOptions dictType="0003" dictValue="${userInfo.locale }"/> 
							</select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label visible-ie8 visible-ie9"><s:message code='userInfo.timezone' /></label>
						<div class="controls">							
							<select name="timezone">
								<li:dictOptions dictType="0004" dictValue="${userInfo.timezone }"/>
							</select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label visible-ie8 visible-ie9"><s:message code='userInfo.theme' /></label>
						<div class="controls">							
							<select name="theme">
								<li:dictOptions dictType="0005" dictValue="${userInfo.theme }"/>
							</select>
						</div>
					</div>
		  		</fieldset>
		  		<div class="form-actions">
					<button type="submit" class="btn btn-primary" data-loading-text="<s:message code='common.processing' />"><i class="icon-ok"></i> <s:message code="btn.submit" /></button>
				</div>
		  	</form>
		  </div>	
		  <div class="tab-pane" id="state">
		  	<form id="state-form" action="" method="POST" class="form-horizontal">
		  		<fieldset>
		  			<div class="control-group">
						<label class="control-label visible-ie8 visible-ie9"><s:message code='userState.totalLoginTimes' /></label>
						<div class="controls">							
							<input type="text" name="totalLoginTimes" value='<c:out value="${userState.totalLoginTimes }"/>' readonly="readonly" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label visible-ie8 visible-ie9"><s:message code='userState.lastLoginDatetime' /></label>
						<div class="controls">							
							<input type="text" name="lastLoginDatetime" value='<c:out value="${userState.lastLoginDatetime }"/>' readonly="readonly" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label visible-ie8 visible-ie9"><s:message code='userState.lastLoginIp' /></label>
						<div class="controls">							
							<input type="text" name="lastLoginIp" value='<c:out value="${userState.lastLoginIp }"/>' readonly="readonly" />
						</div>
					</div>
				</fieldset>
			</form>		
		  </div>	 
		  <div class="tab-pane" id="tenant">
		  	<div class="alert alert-info">
  				<s:message code="profile.ui.currentTenant" />: <strong>${currentTenant.tenantAlias }</strong>
			</div>		  
		  	<table class="table table-striped table-bordered table-hover datatable">
		  		<thead>
		  			<tr>
		  				<th><s:message code="tenant.tenantCode"></s:message></th>
		  				<th><s:message code="tenant.tenantAlias"></s:message></th>
		  				<th><s:message code="tenant.status"></s:message></th>
		  				<th><s:message code="tenant.maxMembers"></s:message></th>
		  				<th><s:message code="tenant.expiredDate"></s:message></th>
		  				<th><s:message code="common.action" /></th>
		  			</tr>
		  		</thead>
		  		<tbody>
		  			<c:forEach items="${tenantList }" var="row">
					<tr>
						<td><c:out value="${row.tenantCode }"/></td>
						<td><c:out value="${row.tenantAlias }"></c:out>&nbsp;[<a href="javascript:;" class="blue" onclick="switchTenant(${row.id});"><s:message code="tenant.func.switch" /></a>]</td>
						<td style="text-align: center"><s:message code="tenant.status.${row.status }"/></td>
						<td style="text-align: right"><c:out value="${row.maxMembers }"></c:out></td>
						<td style="text-align: center"><c:out value="${li:formatDate(row.expiredDate) }"></c:out></td>
						<td class="action-buttons"> 
							<div class="action-buttons">
							<a href="edit.do?id=${row.id }" class="blue" >
								<i class="icon-pencil"></i>
							</a>
							<span class="vbar"></span>	
							<a href="javascript:;" class="red" onclick="rowActivate(${row.id});">
								<i class="fa-sign-out"></i>
							</a>
							</div>
						</td>
					</tr>
					</c:forEach>
		  		</tbody>
		  	</table>
		  </div>
		  <div class="tab-pane" id="password">
		  	<form id="password-form" action="updatePassword.json" method="POST" class="form-horizontal">		  		
		  		<fieldset>		  			
		  			  <div class="control-group">
						<label class="control-label visible-ie8 visible-ie9"><s:message code='resetPassword.newPassword' /></label>
						<div class="controls">
							<div class="input-prepend">
								<span class="add-on"><i class="icon-lock"></i></span>
									<input class="" type="password" id="newPassword" placeholder="<s:message code='resetPassword.newPassword' />" name="newPassword"/>
								</div>
								<span class="help-block visible-ie8 visible-ie9 hide"><s:message code='register.password.help' /></span>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label visible-ie8 visible-ie9"><s:message code='login.rpassword' /></label>
							<div class="controls">
								<div class="input-prepend">
									<span class="add-on"><i class="icon-ok"></i></span>
									<input class="" type="password" id="rpassword" placeholder="<s:message code='login.rpassword' />" name="rpassword"/>							
								</div>
								<span class="help-block visible-ie8 visible-ie9 hide"><s:message code='login.rpassword.help' /></span>
							</div>
						</div>
		  		</fieldset>
		  		<div class="form-actions">
					<button type="submit" class="btn btn-primary" data-loading-text="<s:message code='common.processing' />"><i class="icon-ok"></i> <s:message code="btn.submit" /></button>
				</div>
		  	</form>
		  </div>
		</div>
	</div>	 

<!-- inline scripts related to this page -->
<script type="text/javascript">
$(document).ready(function(){	
	
	$('#basic-form').littFormSubmit({		
		rules : {
			userName: {
                required: true
            },
            gender: {
            	required: true
            },
            email: {
            	required: true,
            	email: true
            },
            mobile: {
            	required: true
            }
		},	
		success: function(reply){			
			$.webtools.alert({
				containerId: "basic-form",
				type: "success",
				hide: true,
				overwrite: false,
				position: "prepend",
				message: "<s:message code='updateProfile.success.message' />"				
			}); 				
		}
	});
	
	$('#password-form').littFormSubmit({		
		rules : {
			newPassword: {
                required: true,
				minlength: 6,
				maxlength: 50
            },
            rpassword: {
            	required: true,
				minlength: 6,
				maxlength: 50,
                equalTo: "#newPassword"
            }
		},	
		success: function(reply){			
			$.webtools.alert({
				containerId: "password-form",
				type: "success",
				overwrite: false,
				position: "prepend",
				message: "<s:message code='updatePassword.success.message' />"				
			}); 				
		}
	});	
	
});	

function switchTenant(tenantId)
{
	bootbox.confirm("<s:message code='tenantMember.func.switch.confirm' />", function(result){
		if(result)
		{
			$.webtools.ajax({
				url: "switchTenant.json",
				params: {"tenantId":tenantId},
				success: function(reply) {
					location.reload();
				}
			});	
		}
	});		
}

function quitTenant()
{
	bootbox.confirm("<s:message code='tenantMember.func.quit.confirm' />", function(result){
		if(result)
		{
			$.webtools.ajax({
				url: "quitTenant.json",
				params: {},
				success: function(reply) {
					location.reload();
				}
			});	
		}
	});		
}
</script>
</body>
</html>