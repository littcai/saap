<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.inc"%>
<%@ include file="/common/taglibs.inc"%>
<html lang="en">
	<head>
		<title><s:message code="login.ui.title" /></title>
	</head>
	<body> 	
	<div> 
		<div class="btn-toolbar">
		  		<button class="btn" onclick="activateTenant();"><s:message code="tenant.func.activate"/>(仅做测试)</button>
		  		<button class="btn" onclick="upgradeTenant();"><s:message code="tenant.func.upgrade"/>(仅做测试)</button>
		  		<button class="btn btn-warning" onclick="lockTenant();"><s:message code="tenant.func.lock"/></button>
		  		<button class="btn btn-danger" onclick="deactivateTenant();"><s:message code="tenant.func.deactivate"/></button>
		  		
		</div>
		<ul class="nav nav-tabs">
		  <li class="active"><a href="#summary" data-toggle="tab"><s:message code="tenant.ui.tab.summary" /></a></li>
		  <li><a href="#stat" data-toggle="tab"><s:message code="tenant.ui.tab.stat" /></a></li>	
		  <li><a href="#conf" data-toggle="tab"><s:message code="tenant.ui.tab.conf" /></a></li>	    
		</ul>
		
		<div class="tab-content ">
		  <div class="tab-pane active" id="summary">		  	
		  	<form id="basic-form" action="index.do" method="GET" class="form-horizontal">
		  		<fieldset>		  			
		  			<div class="control-group">
						<label class="control-label visible-ie8 visible-ie9"><s:message code='tenant.tenantCode' /></label>
						<div class="controls">							
							<input type="text" name="tenantCode" value='<c:out value="${tenant.tenantCode }"/>' readonly="readonly" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label visible-ie8 visible-ie9"><s:message code='tenant.tenantAlias' /></label>
						<div class="controls">							
							<input type="text" name="tenantAlias" value='<c:out value="${tenant.tenantAlias }"/>' readonly="readonly" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label visible-ie8 visible-ie9"><s:message code='tenant.status' /></label>
						<div class="controls">							
							<input type="text" name="status" value='<s:message code="tenant.status.${tenant.status }"/>' readonly="readonly" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label visible-ie8 visible-ie9"><s:message code='tenant.createUserId' /></label>
						<div class="controls">							
							<input type="text" name="createUserId" value='<c:out value="${li:getUser(tenant.createUserId).userName }"/>' readonly="readonly" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label visible-ie8 visible-ie9"><s:message code='tenant.createDatetime' /></label>
						<div class="controls">							
							<input type="text" name="createDatetime" value='<c:out value="${li:formatDateTime(tenant.createDatetime) }"/>' readonly="readonly" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label visible-ie8 visible-ie9"><s:message code='tenant.maxMembers' /></label>
						<div class="controls">				
							<input type="text" name="maxMembers" value='<c:out value="${tenant.maxMembers }"/>' readonly="readonly" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label visible-ie8 visible-ie9"><s:message code='tenant.trialDays' /></label>
						<div class="controls">							
							<input type="text" name="trialDays" value='<c:out value="${tenant.trialDays }"/>' readonly="readonly" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label visible-ie8 visible-ie9"><s:message code='tenant.expiredDate' /></label>
						<div class="controls">							
							<input type="text" name="expiredDate" value='<c:out value="${li:formatDate(tenant.expiredDate) }"/>' readonly="readonly" />
							&nbsp;<button type="button" class="btn btn-primary"><s:message code="tenant.func.recharge" /></button>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label visible-ie8 visible-ie9"><s:message code='tenant.price' /></label>
						<div class="controls">							
							<input type="text" name="price" value='<c:out value="${price }"/>' readonly="readonly" />
						</div>
					</div>
		  		</fieldset>		  		
		  	</form>
		  </div>	
		  <div class="tab-pane" id="stat">
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
		  <div class="tab-pane" id="conf">
			  <form id="conf-form" action="config.json" method="POST" class="form-horizontal">
			  		<fieldset>
			  			<div class="control-group">
							<label class="control-label"><s:message code='tenant.tenantAlias' /></label>
							<div class="controls">							
								<input type="text" name="tenantAlias" value='<c:out value="${tenant.tenantAlias }"/>'  />
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
	$('#conf-form').littFormSubmit({		
		rules : {
			tenantAlias: {
                required: true,
                minlength: 4,
                maxlength: 50
            }
		},	
		success: function(reply){			
			$.webtools.alert({
				containerId: "conf-form",
				type: "success",
				overwrite: false,
				position: "prepend",
				message: "<s:message code='tenant.func.conf.success' />"				
			}); 				
		}
	});
	
	
});	

function activateTenant()
{
	location.href = "${contextPath }/login/activateTenant.do";
}

function upgradeTenant()
{
	location.href = "${contextPath }/login/upgradeTenantPermission.do";		
}

function lockTenant()
{
	bootbox.confirm("<s:message code='tenant.func.lock.confirm' />", function(result){
		if(result)
		{
			$.webtools.ajax({
				url: "${contextPath }/login/lockTenant.json",
				params: {},
				success: function(reply) {
					location.reload();
				}
			});	
		}
	});	
}

function deactivateTenant()
{
	bootbox.confirm("<s:message code='tenant.func.deactivate.confirm' />", function(result){
		if(result)
		{
			$.webtools.ajax({
				url: "${contextPath }/login/deactivateTenant.json",
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