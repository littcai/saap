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
		        <label><s:message code="contactsGroup.name" />:</label>                                   
                <input type="text" class="input-large search-query" value="${param.s_searchValue }" name="s_searchValue">
        		<button type="submit" class="btn btn-small"><i class="icon-search"></i>&nbsp;<s:message code="btn.query" /></button>        
             </form>
		</div>	
		<div class="clear"></div> 	
		<!-- toolbar -->
		<div style="margin-top:2px;padding-top:5px;margin-bottom:8px;border-top:2px solid #0088CC;">
			<div class="pull-left" style="margin-bottom:5px;">
                <button class="btn btn-small btn-primary" style="margin-top:2px;" onclick="javascript:location.href='add.do'">
                  <i class="icon-plus icon-white"></i> <s:message code="btn.add" /></button>
                <button class="btn btn-small btn-danger" style="margin-top:2px;" onclick="javascript:return batchDelete();">
                  <i class="icon-trash icon-white"></i> <s:message code="btn.delete" /></button>
                <button class="btn btn-small btn-info" style="margin-top:2px;" onclick="javascript:location.href='../contacts/index.do'">
	              <i class="icon-user icon-white"></i> <s:message code="contacts" /></button>
            </div>      
		</div>	
		<div class="clear"></div> 	
		<!-- datatable -->
		<div>
			<table class="table table-striped table-bordered table-hover datatable">
				<thead>
					<tr>			
						<th class="checkCol"><input type="checkbox" id="checkAll" name="checkAll" /></th>		
						<th class="sort name"><s:message code="contactsGroup.name" /></th>
						<th><s:message code="contactsGroup.members" /></th>
						<th><s:message code="common.action" /></th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${pageList.rsList }" var="row">
					<tr>
						<td class="checkCol"><input type="checkbox" class="checkItem" name="contactsIds" value="${row.id }" /></td>
						<td><c:out value="${row.name }" /></td>						
						<td><c:out value="${row.members }" /></td>
						<td class="action-buttons">
							<div class="action-buttons">
							<a href="edit.do?id=${row.id }" class="blue" >
								<i class="icon-pencil"></i>
							</a>&nbsp;
							<a href="javascript:;" class="red" onclick="rowDelete(${row.id});">
								<i class="icon-trash"></i>
							</a>
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
			//checkall
			var checkboxs = $.webtools.checkboxs({
				checkAll: "#checkAll",
				checkItem: ".checkItem"				
			});
		})
		
		function batchDelete(fieldName)
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
		</script>			
	</body>
</html>
