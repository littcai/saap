<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.jspf"%>
<%@ include file="/common/taglibs.jspf"%>
<html lang="en">
  <head>		
  </head>
  <body>   
  <form id="theform" action="updateRole.json" method="post" class="form-horizontal">
  	<input type="hidden" name="tenantMemberId" value="${tenantMember.id }" />
  	<input type="hidden" name="userId" value="${userInfo.id }" />
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
		</fieldset>					
		<fieldset>
			<legend><s:message code="tenantMember.ui.fieldset.role" /></legend>
			<table class="table table-striped table-bordered table-hover datatable">				
				<thead>
				  <tr>
					<th class="checkCol"><input type="checkbox" id="checkAll" name="checkAll" /></th>
					<th width="30%"><s:message code="role.name"/></th>
					<th><s:message code="role.remark"/></th>
				  </tr>	
				</thead>
				<tbody>
				  <c:forEach items="${roleCheckItemList }" var="row">
				    <tr>
				    	<td class="checkCol"><input type="checkbox" class="checkItem" name="roleIds[]" value="${row.obj.id }" <c:if test="${row.checked }">checked="checked"</c:if>  /></td>
				    	<td><c:out value="${row.obj.name }"></c:out></td>
				    	<td><c:out value="${row.obj.remark }"></c:out></td>
				    </tr>
				  </c:forEach>
				</tbody>
			</table>
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
					roleIds : {
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