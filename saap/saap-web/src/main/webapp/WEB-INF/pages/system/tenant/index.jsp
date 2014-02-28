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
		<ul class="nav nav-tabs">
		  <li class="active"><a href="#basic" data-toggle="tab"><s:message code="tenant.ui.tab.summary" /></a></li>
		  <li><a href="#stat" data-toggle="tab"><s:message code="tenant.ui.tab.stat" /></a></li>	
		  <li><a href="#conf" data-toggle="tab"><s:message code="tenant.ui.tab.conf" /></a></li>	    
		</ul>
		
		<div class="tab-content ">
		  <div class="tab-pane active" id="summary">
		  	<form id="basic-form" action="index.do" method="GET" class="form-horizontal">
		  		<fieldset>
		  			<legend>General</legend>
		  			<div class="control-group">
						<label class="control-label visible-ie8 visible-ie9"><s:message code='tenant.code' /></label>
						<div class="controls">							
							<input type="text" name="code" value='<c:out value="${tenant.code }"/>' readonly="readonly" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label visible-ie8 visible-ie9"><s:message code='tenant.appAlias' /></label>
						<div class="controls">							
							<input type="text" name="appAlias" value='<c:out value="${tenant.appAlias }"/>' readonly="readonly" />
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
							&nbsp;<button type="button" class="btn btn-primary"><s:message code="tenant.btn.recharge" /></button>
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
		  		</div>
	</div>	 

<!-- inline scripts related to this page -->
<script type="text/javascript">
$(document).ready(function(){	
	
	
	
});	
</script>
</body>
</html>