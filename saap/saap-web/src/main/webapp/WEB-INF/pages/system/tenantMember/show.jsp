<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.inc"%>
<%@ include file="/common/taglibs.inc"%>
<html lang="en">
  <head>		
  </head>
  <body> 
  	<ul class="nav nav-tabs">
		  <li class="active"><a href="#basic" data-toggle="tab"><s:message code="tenantMember.ui.tab.basic" /></a></li>
		  <li><a href="#userRole" data-toggle="tab"><s:message code="tenantMember.ui.tab.userRole" /></a></li>
		  <li><a href="#userGroup" data-toggle="tab"><s:message code="tenantMember.ui.tab.userGroup" /></a></li>
		</ul>		
		<div class="tab-content ">
		  <div class="tab-pane active" id="basic">    
  			<form id="theform" action="show.do" method="post" class="form-horizontal">
			  <fieldset>
				<legend><s:message code="common.ui.fieldset.base" /></legend>	
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label"><s:message code='userInfo.loginId' /></label>
								<div class="controls">							
									<input type="text" name="loginId" value='<c:out value="${userInfo.loginId }"/>' readonly="readonly" />
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label"><s:message code='userInfo.userName' /></label>
								<div class="controls">							
									<input type="text" name="userName" value='<c:out value="${userInfo.userName }"/>' readonly="readonly" />
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label"><s:message code='userInfo.nickName' /></label>
								<div class="controls">							
									<input type="text" name="nickName" value='<c:out value="${userInfo.nickName }"/>' readonly="readonly" />
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label"><s:message code='userInfo.gender' /></label>
								<div class="controls">							
									<input type="text" name="gender" value="${li:genDictContent('0002', userInfo.gender) }" readonly="readonly" />
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label"><s:message code='userInfo.email' /></label>
								<div class="controls">							
									<input type="text" name="email" value='<c:out value="${userInfo.email }"/>' readonly="readonly" />
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label"><s:message code='userInfo.mobile' /></label>
								<div class="controls">							
									<input type="text" name="mobile" value='<c:out value="${userInfo.mobile }"/>' readonly="readonly" />
								</div>
							</div>
						</div>								
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="tenantMember.isAdmin"><s:message code="tenantMember.isAdmin" /></label>
								<div class="controls">		
									<h:yesno value="${tenantMember.isAdmin}"></h:yesno>					
								</div>
							</div>
						</div>								
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="userInfo.status"><s:message code="userInfo.status" /></label>
								<div class="controls">							
									<input type="text" name="userInfo.status" value="${li:genDictContent('1002', userInfo.status)}" readonly="readonly" />
								</div>
							</div>
						</div>								
					</div>					
					
					<div class="row-fluid">												
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="userInfo.createDatetime"><s:message code="userInfo.createDatetime" /></label>
								<div class="controls">
									<input type="text" name="userInfo.createDatetime" value='<c:out value="${li:formatDateTime(userInfo.createDatetime)}" />' readonly="readonly" />									
								</div>
							</div>
						</div>	
						<div class="span6">
							<div class="control-group">
								<label class="control-label" for="userInfo.updateDatetime"><s:message code="userInfo.updateDatetime" /></label>
								<div class="controls">
									<input type="text" name="userInfo.updateDatetime" value='<c:out value="${li:formatDateTime(userInfo.updateDatetime)}" />' readonly="readonly" />									
								</div>
							</div>
						</div>								
					</div>
				</fieldset>					
						
				<div class="form-actions">					
					<button type="button" class="btn" onclick="history.back();"><s:message code="btn.back" /></button>
				</div>					
				
			</form>	
		</div>
		
		<div class="tab-pane" id="userRole">    
  			<table class="table table-striped table-bordered table-hover datatable">				
				<thead>
				  <tr>
					<th width="30%"><s:message code="role.name"/></th>
					<th><s:message code="role.remark"/></th>
				  </tr>	
				</thead>
				<tbody>
				  <c:forEach items="${userRoleList }" var="row">
				    <tr>				    	
				    	<td><c:out value="${row.name }"></c:out></td>
				    	<td><c:out value="${row.remark }"></c:out></td>
				    </tr>
				  </c:forEach>
				</tbody>
			</table>
		 </div>
		 
		 <div class="tab-pane" id="userGroup">    
		 
		 </div>
		
	  </div>				
			  
  </body>	
</html>