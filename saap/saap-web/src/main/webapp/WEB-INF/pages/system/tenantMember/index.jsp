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
        <select name="s_searchField" id="s_searchField" style="width:130px">
        	<option value="loginId" ${li:renderSelected(param.s_searchField, "loginId")}><s:message code="userInfo.loginId" /></option>	
        	<option value="userName" ${li:renderSelected(param.s_searchField, "userName")}><s:message code="userInfo.userName" /></option>	
        	<option value="mobile" ${li:renderSelected(param.s_searchField, "mobile")}><s:message code="userInfo.mobile" /></option>	
        	<option value="email" ${li:renderSelected(param.s_searchField, "email")}><s:message code="userInfo.email" /></option>					
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
						<th><s:message code="userInfo.loginId" /></th>
						<th><s:message code="userInfo.userName" /></th>
						<th><s:message code="userInfo.nickName" /></th>
						<th><s:message code="userInfo.gender" /></th>
						<th><s:message code="userInfo.status" /></th>
						<th><s:message code="tenantMember.createDatetime" /></th>
						<th><s:message code="tenantMember.updateDatetime" /></th>
						<th><s:message code="common.action" /></th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${pageList.rsList }" var="row">
					<tr>
						<td class="checkCol"><input type="checkbox" class="checkItem" name="tenantMemberIds" value="${row.tenantMember.id }" /></td>
						<td><a href="show.do?id=${row.tenantMember.id }"><c:out value="${row.userInfo.loginId }"></c:out>&nbsp;<i class="icon-info-sign"></i></a></td>
						<td><c:out value="${row.userInfo.userName }"></c:out></td>
						<td><c:out value="${row.userInfo.nickName }"></c:out></td>
						<td><c:out value="${li:genDictContent('0002', row.userInfo.gender) }"></c:out></td>
						<td><c:out value="${li:genDictContent('1002', row.userInfo.status) }"></c:out></td>
						<td><c:out value="${li:formatDateTime(row.userInfo.createDatetime) }"></c:out></td>
						<td><c:out value="${li:formatDateTime(row.userInfo.updateDatetime) }"></c:out></td>
						<td>
							<div class="action-buttons">
							<a href="edit.do?id=${row.tenantMember.id }" class="blue" >
								<i class="icon-pencil"></i>
							</a>&nbsp;
							<a href="editRole.do?id=${row.tenantMember.id }" class="blue" >
								<i class="icon-cog"></i>
							</a>&nbsp;	
							<a href="editUserGroup.do?id=${row.tenantMember.id }" class="blue" >
								<i class="icon-group"></i>
							</a>&nbsp;		
							<c:if test="${row.tenantMember.status==-1 }">
							<a href="javascript:;" class="green" onclick="rowResume(${row.tenantMember.id});">
								<i class="icon-repeat"></i>
							</a>
							</c:if>	
							<c:if test="${row.tenantMember.status!=-1 }">				
							<a href="javascript:;" class="red" onclick="rowDelete(${row.tenantMember.id});">
								<i class="icon-trash"></i>
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
			bootbox.confirm("<s:message code='tenantMember.func.resume.confirm' />", function(result){
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
