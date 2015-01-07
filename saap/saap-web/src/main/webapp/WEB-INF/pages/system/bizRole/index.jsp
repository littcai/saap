<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.jspf"%>
<%@ include file="/common/taglibs.jspf"%>
<html lang="en">
  <head>		
	<!-- jquery datatables -->
	<link href="${contextPath}/static/widgets/jquery-datatables/css/jquery.dataTables.css" rel="stylesheet" />	
	<script src="${contextPath }/static/widgets/jquery-datatables/js/jquery.dataTables.min.js"></script>
	<script src="${contextPath }/static/widgets/jquery-datatables/js/jquery.dataTables.bootstrap.js"></script>	
	</head>
 <body>
 	<!-- form filter -->	
		<div>
			<form class="form-search" style="margin-bottom:5px;" id="search-form" name="search-form" action="index.do" method="GET">				
				<input type="hidden" id="pageIndex" name="pageIndex" value="${pageParam.pageIndex }" />
				<input type="hidden" id="pageSize" name="pageSize" value="${pageParam.pageSize }" />
				<input type="hidden" id="sortField" name="sortField"  value="${pageParam.sortField}"/>
				<input type="hidden" id="sortOrder" name="sortOrder"  value="${pageParam.sortOrder}"/>
        <label class="control-label" for="name"><s:message code="role.name" />:</label>                     
        <input type="text" class="input-large search-query" value="${param.name }" name="name">
        <button type="submit" class="btn btn-small"><i class="icon-search"></i>&nbsp;<s:message code="btn.query" /></button>        
      </form>
		</div>	
		<div class="clear"></div> 		
		<!-- quick filter -->
		<div>			
			<ul class="nav nav-pills" style="margin-bottom: 5px;">	
			  <li <c:if test="${empty param.quickView }">class="active"</c:if> >
			    <a href="index.do"><s:message code="quickView.all" /></a>
			  </li>			  
			</ul>
		</div>
		<div class="clear"></div> 		
		<!-- toolbar -->
		<div style="margin-top:2px;padding-top:5px;margin-bottom:8px;border-top:2px solid #0088CC;">
			<div class="pull-left" style="margin-bottom:5px;">
      	<button type="button" class="btn btn-small btn-primary" style="margin-top:2px;" onclick="javascript:location.href='add.do'">
        	<i class="icon-plus icon-white"></i> <s:message code="btn.add" /></button>
        <button type="button" class="btn btn-small btn-danger" style="margin-top:2px;" onclick="javascript:return batchDelete();">
        	<i class="icon-trash icon-white"></i> <s:message code="btn.delete" /></button>
       </div> 	 		  
		</div>	
		<div class="clear"></div> 	
		<!-- message notify -->
		<h:notify content="${messageBox.content }" type="${messageBox.type }"></h:notify>
		<!-- datatable -->
		<div>
			<table class="table table-striped table-bordered table-hover datatable">
				<thead>
					<tr>			
						<th class="checkCol"><input type="checkbox" id="checkAll" name="checkAll" /></th>	
						<th><s:message code="role.name" /></th>
						<th><s:message code="role.status" /></th>
						<th><s:message code="common.action" /></th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${pageList.rsList }" var="row">
					<tr>
						<td class="checkCol"><input type="checkbox" class="checkItem" name="roleIds" value="${row.id }" /></td>
						<td><c:out value="${row.name }"></c:out></td>
						<td style="text-align: center"><s:message code="role.status.${row.status }"></s:message></td>
						<td class="action-buttons">							
							<div class="action-buttons">
								
							<%-- <c:if test="${row.status==1 }"> --%>
								<a href="edit.do?id=${row.id }" class="blue" >
									<i class="icon-pencil"></i>
								</a>&nbsp;
								<a href="javascript:;" class="red" onclick="rowDelete(${row.id});">
									<i class="icon-trash"></i>
								</a>
							<%-- </c:if> --%>
							<c:if test="${row.status==2 }">
								<a href="javascript:;" class="green" onclick="rowResume(${row.id});">
									<i class="icon-repeat"></i>
								</a>
							</c:if>
							</div>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
			<h:pager params="${pageList }" formId="datatable"></h:pager>
		</div> 
 	<!--page specific plugin scripts-->	  			
		<script type="text/javascript">
		$(document).ready(function(){				
			$('.datatable').dataTable();	
			
			var checkboxs = $.webtools.checkboxs({
				checkAll: "#checkAll",
				checkItem: ".checkItem"				
			});
		})
		
		function batchDelete()
		{					
			if($(".checkItem:checked").length<=0)
			{
				$.webtools.notify({
					type: "notice",
					message: "<s:message code='validate.checkone'/>"
				});
				return;
			}
			var ids = $.webtools.getCheckValuesArray(".checkItem");	
		
			bootbox.confirm("<s:message code='common.delete.confirm' />", function(result){
				if(result)
				{
					$.webtools.ajax({
						url: "deleteBatch.json",
						params: {"ids":ids},
						success: function(reply) {
							location.reload();
						}
					});						
				}				
			});			
		}	
				
		function rowDelete(id)
		{			
			bootbox.confirm("<s:message code='common.delete.confirm' />", function(result){
				if(result)
				{
					$.webtools.ajax({
						url: "delete.json",
						params: {"id":id},
						success: function(reply) {
							location.reload();
						}
					});					
				}				
			});			
		}		
		
		function rowResume(id)
		{			
			bootbox.confirm("<s:message code='role.func.resume.confirm' />", function(result){
				if(result)
				{
					$.webtools.ajax({
						url: "resume.json",
						params: {"id":id},
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
