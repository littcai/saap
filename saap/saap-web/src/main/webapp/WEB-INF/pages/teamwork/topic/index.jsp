<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.inc"%>
<%@ include file="/common/taglibs.inc"%>
<html lang="en">
  <head>		
	<!-- jquery datatables -->
	<link href="${contextPath}/widgets/jquery-datatables/css/jquery.dataTables.css" rel="stylesheet" />	
	<script src="${contextPath }/widgets/jquery-datatables/js/jquery.dataTables.min.js"></script>
	<script src="${contextPath }/widgets/jquery-datatables/js/jquery.dataTables.bootstrap.js"></script>	
	</head>
 <body>
 	<!-- form filter -->	
		<div>
			<form class="form-search" style="margin-bottom:5px;" id="search-form" name="search-form" action="index.do" method="POST">				
				<input type="hidden" id="pageIndex" name="pageIndex" value="${pageParam.pageIndex }" />
				<input type="hidden" id="pageSize" name="pageSize" value="${pageParam.pageSize }" />
				<input type="hidden" id="sortField" name="sortField"  value="${pageParam.sortField}"/>
				<input type="hidden" id="sortOrder" name="sortOrder"  value="${pageParam.sortOrder}"/>
        <select name="searchField" id="searchField" style="width:130px">
        	<option value="code"><s:message code="code" /></option>	
        	<option value="name"><s:message code="name" /></option>						
        </select>                      
        <input type="text" class="input-large search-query" value="" name="search_text">
        <button type="submit" class="btn btn-small"><i class="icon-search"></i>&nbsp;搜索</button>        
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
      	<button class="btn btn-small btn-primary" style="margin-top:2px;" onclick="javascript:location.href='add.do'">
        	<i class="icon-plus icon-white"></i> <s:message code="btn.add" /></button>
        <button class="btn btn-small btn-danger" style="margin-top:2px;" onclick="javascript:return batchDelete('customerIds');">
        	<i class="icon-trash icon-white"></i> <s:message code="btn.delete" /></button>
 				<button class="btn btn-small btn-success" style="margin-top:2px;" onclick="javascript:location.href='index.php?module=Accounts&action=Import&step=1&return_module=Accounts&return_action=index&parenttab=Customer'">
        	<i class="icon-download icon-white"></i> <s:message code="btn.import" /></button>
        <button class="btn btn-small btn-success" style="margin-top:2px;" onclick="return selectedRecords('Accounts','Customer')" >
        	<i class="icon-upload icon-white"></i> <s:message code="btn.export" /></button>
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
						<th><s:message code="topic.id" /></th>
						<th><s:message code="topic.tenantId" /></th>
						<th><s:message code="topic.projectId" /></th>
						<th><s:message code="topic.title" /></th>
						<th><s:message code="topic.content" /></th>
						<th><s:message code="topic.createUserId" /></th>
						<th><s:message code="topic.createDatetime" /></th>
						<th><s:message code="topic.updateUserId" /></th>
						<th><s:message code="topic.updateDatetime" /></th>
						<th><s:message code="common.action" /></th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${pageList.rsList }" var="row">
					<tr>
						<td class="checkCol"><input type="checkbox" name="topicIds" value="${row.id }" /></td>
						<td><c:out value="${row.id }"></c:out></td>
						<td><c:out value="${row.tenantId }"></c:out></td>
						<td><c:out value="${row.projectId }"></c:out></td>
						<td><c:out value="${row.title }"></c:out></td>
						<td><c:out value="${row.content }"></c:out></td>
						<td><c:out value="${row.createUserId }"></c:out></td>
						<td><c:out value="${row.createDatetime }"></c:out></td>
						<td><c:out value="${row.updateUserId }"></c:out></td>
						<td><c:out value="${row.updateDatetime }"></c:out></td>
						<td class="action-buttons">
							<div class="action-buttons">
							<a href="edit.do?id=${row.id }" class="blue" >
								<i class="icon-pencil"></i>
							</a>
							<span class="vbar"></span>	
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
		})
		
		function batchDelete(fieldName)
		{					
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
