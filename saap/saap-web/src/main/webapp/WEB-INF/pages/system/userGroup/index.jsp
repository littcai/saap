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
	  <form class="search-form" id="search-form" name="search-form" action="index.do" method="GET">				
		<input type="hidden" id="pageIndex" name="pageIndex" value="${pageParam.pageIndex }" />
		<input type="hidden" id="pageSize" name="pageSize" value="${pageParam.pageSize }" />
		<input type="hidden" id="sortField" name="sortField"  value="${pageParam.sortField}"/>
		<input type="hidden" id="sortOrder" name="sortOrder"  value="${pageParam.sortOrder}"/>
        <select name="s_searchField" id="s_searchField">
        	<option value="code" ${li:renderSelected(param.s_searchField, "code")}><s:message code="userGroup.code" /></option>	
        	<option value="name" ${li:renderSelected(param.s_searchField, "name")}><s:message code="userGroup.name" /></option>						
        </select>                      
        <input type="text" class="input-large search-query" value="${param.s_searchValue }" name="s_searchValue">
        <button type="submit" class="btn btn-small"><i class="icon-search"></i>&nbsp;<s:message code="btn.query" /></button>        
      </form>
	</div>	
	<!-- quick filter -->
    <div class="row-fluid">
      <div id="quickview" class="quickview pull-left">
        <ul>
          <li><span class="brand"><i class="icon-tags"></i> <s:message code="quickView" />:</span></li>
          <li ${empty param.quickViewId?"class='active'":"" }><a href="index.do"><s:message code="quickView.all" /></a></li>
              <c:forEach items="${quickViewList }" var="quickView">
                <li ${quickView.id eq param.quickView?"class='active'":"" }><a href="index.do?quickView=${quickView.id }"><c:out value="${quickView.name }"></c:out></a></li>
              </c:forEach>
        </ul>
      </div>
      <c:if test="${not empty quickViewList }">
      <div class="pull-right">
        <div class="btn-group dropdown-checkbox">
            <a class="btn dropdown-toggle btn-small" data-toggle="dropdown" href="#">
              <i class="icon-cog"></i> 配置
              <span class="caret"></span>
            </a>
            <ul class="dropdown-menu dropdown-checkbox-menu">
              <!-- dropdown menu links -->
              <c:forEach items="${quickViewList }" var="quickView">
                <li><label><c:out value="${quickView.name }"></c:out>&nbsp;&nbsp;<a href="javascript:;" onclick="deleteQuickView(${quickView.id});"><i class="icon-remove-sign"></i></a></label></li>
              </c:forEach>
            </ul>
          </div>
      </div>
      </c:if>
      <div class="clear"></div>
     </div> 
	<!-- toolbar -->
	<div class="toolbar">
		<div class="pull-left">
      	  <button type="button" class="btn btn-small btn-primary" onclick="javascript:location.href='add.do'">
        	<i class="icon-plus icon-white"></i> <s:message code="btn.add" /></button>
          <button type="button" class="btn btn-small btn-danger" onclick="javascript:return batchDelete();">
        	<i class="icon-trash icon-white"></i> <s:message code="btn.deleteBatch" /></button>
       </div> 
       <div class="clear"></div>  	 		  
	</div>	
	<!-- datatable -->
		<div>
			<table class="table table-striped table-bordered table-hover datatable">
				<thead>
					<tr>			
						<th class="checkCol"><input type="checkbox" id="checkAll" name="checkAll" /></th>							
						<th><s:message code="userGroup.code" /></th>
						<th><s:message code="userGroup.name" /></th>
						<th><s:message code="userGroup.status" /></th>
						<th><s:message code="common.action" /></th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${pageList.rsList }" var="row">
					<tr>
						<td class="checkCol"><input type="checkbox" name="userGroupIds" value="${row.id }" /></td>						
						<td><c:out value="${row.code }"></c:out></td>
						<td><a href="show.do?id=${row.id }"><c:out value="${row.name }"></c:out></a></td>
						<td><s:message code="dictparam.userGroup-status.${row.status }"></s:message></td>
						<td class="action-buttons">
                            <li:p code="900302">
							  <a href="edit.do?id=${row.id }">
    						    <i class="icon-pencil"></i>
    						  </a>&nbsp;  
                            </li:p>
    						<a href="showMembers.do?id=${row.id }">
    						  <i class="icon-group"></i>
    						</a>&nbsp;	
                            <li:p code="900303">
      						  <a href="javascript:;" class="red" onclick="rowDelete(${row.id});">
      						    <i class="icon-trash"></i>
      						  </a>
                            </li:p>
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
