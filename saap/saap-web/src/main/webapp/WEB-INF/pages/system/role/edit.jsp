<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.jspf"%>
<%@ include file="/common/taglibs.jspf"%>
<html lang="en">
  <head>		
  </head>
  <body>   
  <form id="theform" action="update.json" method="post" class="form-horizontal">
  	<input type="hidden" name="id" value="${role.id}" />
				<fieldset class="collapsible">
					<legend><s:message code="common.ui.fieldset.base" /></legend>
					<div class="row-fluid">
						<div class="control-group">
							<label class="control-label" for="name"><s:message code="role.name" /></label>
							<div class="controls">
								<input id="name" name="name" placeholder="" type="text" value="<c:out value='${role.name}' />" />
							</div>
						</div>						
					</div>
					<div class="row-fluid">
						<div class="control-group">
							<label class="control-label" for="remark"><s:message code="role.remark" /></label>
							<div class="controls">
								<textarea rows="6" cols="8" id="remark" name="remark" class="input-block-level limited"><c:out value='${role.remark}' /></textarea>
							</div>
						</div>								
					</div>
				</fieldset>					
				<fieldset class="collapsible">
					<legend><s:message code="role.ui.fieldset.permission" /></legend>
					<!-- datatable -->
					<div>
						<div id="form-error-box"></div>
						<table class="table table-bordered table-hover datatable">
							<thead>
								<tr>
									<td colspan="2"><input type="checkbox" class="checkAll" id="checkAll" name="checkAll"/>&nbsp;<s:message code="common.ui.selectAll" /></td>	
								</tr>
							</thead>							
							<tbody>
							<c:forEach items="${permissionTree.subList }" var="domain">
								<tr>
									<td class="checkCol"><input type="checkbox" class="checkItem" id="domain${domain.code }" name="permissionCodes" value="${domain.code }" <c:if test="${domain.checked }">checked="checked"</c:if> /></td>						
									<td><s:message code="domain.${domain.code }"></s:message></td>									
								</tr>
								<c:forEach items="${domain.subList }" var="level2">
									<c:if test="${level2.type == 2 }">
										<tr>
											<td>&nbsp;</td>						
											<td><input type="checkbox" class="checkItem domain${domain.code }" id="module${level2.code }" name="permissionCodes" value="${level2.code }" <c:if test="${level2.checked }">checked="checked"</c:if> />&nbsp;<s:message code="module.${level2.code }"></s:message></td>									
										</tr>
										<tr>
											<td>&nbsp;</td>						
											<td>&nbsp;&nbsp;&nbsp;<c:forEach items="${level2.subList }" var="func">											
											<input type="checkbox" class="checkItem  domain${domain.code } module${level2.code }" name="permissionCodes" value="${func.code }"  <c:if test="${func.checked }">checked="checked"</c:if> />&nbsp;<s:message code="func.${func.code }"></s:message>&nbsp;
											</c:forEach>
											</td>									
										</tr>
									</c:if>
									<c:if test="${level2.type == 1 }">
										<tr>
											<td>&nbsp;</td>						
											<td><input type="checkbox" class="checkItem domain${domain.code }" id="domain${level2.code }" name="permissionCodes" value="${level2.code }" <c:if test="${level2.checked }">checked="checked"</c:if> />&nbsp;<s:message code="domain.${level2.code }"></s:message></td>									
										</tr>
										<c:forEach items="${level2.subList }" var="level3">
											<tr>
												<td>&nbsp;</td>						
												<td>&nbsp;&nbsp;&nbsp;<input type="checkbox" class="checkItem domain${domain.code } domain${level2.code }" id="module${level3.code }" name="permissionCodes" value="${level3.code }"  <c:if test="${level3.checked }">checked="checked"</c:if> />&nbsp;<s:message code="module.${level3.code }"></s:message></td>									
											</tr>
											<tr>
												<td>&nbsp;</td>							
												<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:forEach items="${level3.subList }" var="func">											
												<input type="checkbox" class="checkItem domain${domain.code } domain${level2.code } module${level3.code }" name="permissionCodes" value="${func.code }"  <c:if test="${func.checked }">checked="checked"</c:if> />&nbsp;<s:message code="func.${func.code }"></s:message>&nbsp;
												</c:forEach>
												</td>									
											</tr>
										</c:forEach>										
									</c:if>
								</c:forEach>
							</c:forEach>
							</tbody>
						</table>						
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
			
			<c:forEach items="${permissionTree.subList }" var="domain">
			{				
				<c:forEach items="${domain.subList }" var="level2">
					<c:if test="${level2.type == 2 }">
					{
						var code = "module"+"${level2.code}"; 
						$.webtools.checkboxs({
							checkAll: "#"+code,
							checkItem: "."+code				
						});
					}
					</c:if>
					<c:if test="${level2.type == 1 }">						
						<c:forEach items="${level2.subList }" var="level3">
						{
							var code = "module"+"${level3.code}"; 
							$.webtools.checkboxs({
								checkAll: "#"+code,
								checkItem: "."+code				
							});
						}
						</c:forEach>
						
						{
							var code = "domain"+"${level2.code}"; 
							$.webtools.checkboxs({
								checkAll: "#"+code,
								checkItem: "."+code				
							});
						}
					</c:if>	
				</c:forEach>
				
				var code = "domain"+"${domain.code}"; 
				var checkboxs = $.webtools.checkboxs({
					checkAll: "#"+code,
					checkItem: "."+code				
				});
			}
			</c:forEach>
			
			var checkboxs = $.webtools.checkboxs({
				checkAll: "#checkAll",
				checkItem: ".checkItem"				
			});
			
			$('#theform').littFormSubmit({
				rules : {
					name : {
						required : true,
						maxlength: 50
					}
				},	
				beforeSubmit: function(){
					if($(".checkItem:checked").length<=0)
					{
						$.webtools.alert({
			    			containerId: "form-error-box",
							type: "error",
							message: "<s:message code='validate.checkone'/>"			
				 		}); 						
						return false;
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