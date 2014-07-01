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
        <select name="s_searchField" id="s_searchField" style="width:130px">
        	<option value="code" ${li:renderSelected(param.s_searchField, "code")}><s:message code="custActivity.customerId" /></option>	
        	<option value="name" ${li:renderSelected(param.s_searchField, "name")}><s:message code="custActivity.contactId" /></option>						
        </select>                      
        <input type="text" class="input-large search-query" value="${param.s_searchValue }" name="s_searchValue">
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
						<th><s:message code="custActivity.customerId" /></th>
						<th><s:message code="custActivity.contactId" /></th>
						<th><s:message code="custActivity.actType" /></th>
						<th><s:message code="custActivity.subject" /></th>
						<th><s:message code="custActivity.actDate" /></th>
						<th><s:message code="custActivity.chargeBy" /></th>
						<th><s:message code="common.action" /></th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${pageList.rsList }" var="row">
					<tr>
						<td class="checkCol"><input type="checkbox" name="custActivityIds" value="${row.custActivity.id }" /></td>						
						<td><c:out value="${row.customer.name}"></c:out></td>
						<td><c:out value="${row.custContacts.name }"></c:out></td>
						<td>电话拜访</td>
						<td><c:out value="${row.custActivity.subject }"></c:out></td>
						<td><c:out value="${li:formatDate(row.custActivity.actDate) }"></c:out></td>
						<td><c:out value="${row.chargeUser.userName }"></c:out></td>
						<td class="action-buttons">
							<div class="action-buttons">
							<a href="edit.do?id=${row.custActivity.id }" class="blue" >
								<i class="icon-pencil"></i>
							</a>
							<span class="vbar"></span>	
							<a href="javascript:;" class="red" onclick="rowDelete(${row.custActivity.id});">
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
		</script>
  </body>
</html>
