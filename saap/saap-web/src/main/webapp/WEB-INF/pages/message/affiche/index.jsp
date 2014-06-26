<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.jspf"%>
<%@ include file="/common/taglibs.jspf"%>
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
	  <form class="form-search" id="search-form" name="search-form" action="index.do" method="get">				
		<input type="hidden" id="pageIndex" name="pageIndex" value="${pageParam.pageIndex }" />
		<input type="hidden" id="pageSize" name="pageSize" value="${pageParam.pageSize }" />
		<input type="hidden" id="sortField" name="sortField"  value="${pageParam.sortField}"/>
		<input type="hidden" id="sortOrder" name="sortOrder"  value="${pageParam.sortOrder}"/>
                           
        <button type="submit" class="btn btn-small"><i class="icon-search"></i>&nbsp;<s:message code="btn.query" /></button>        
      </form>
	</div>			
	<!-- quick view -->
	<div class="quickview">			
	  <ul>	
	    <li><span class="brand"><i class="icon-tags"></i></span></li>
		<li <c:if test="${empty param.quickView }">class="active"</c:if> >
	      <a href="index.do"><s:message code="quickView.all" /></a>
		</li>			  
	  </ul>
	</div>		
	<!-- toolbar -->
	<div class="toolbar">
		<div class="pull-left">
		  <button class="btn btn-small btn-primary" style="margin-top:2px;" onclick="javascript:location.href='add.do'">
		    <i class="icon-plus icon-white"></i> <s:message code="btn.add" /></button>
		  <button class="btn btn-small btn-danger" style="margin-top:2px;" onclick="javascript:return batchDelete();">
		    <i class="icon-trash icon-white"></i> <s:message code="btn.delete" /></button> 				
       	</div> 	
       	<div class="clear"></div> 	 		  
	</div>	
	<!-- datatable -->
	<div>
	  <table class="table table-striped table-bordered table-hover datatable">
		<thead>
		  <tr>			
			<th class="checkCol"><input type="checkbox" id="checkAll" name="checkAll" /></th>	
			<th class="sort customerId"><s:message code="custContacts.customer" /></th>
			<th class="sort name"><s:message code="custContacts.name" /></th>
			<th><s:message code="custContacts.gender" /></th>
			<th><s:message code="custContacts.mobile" /></th>
			<th><s:message code="custContacts.email" /></th>
			<th><s:message code="custContacts.phone" /></th>						
			<th><s:message code="common.action" /></th>
		 </tr>
		</thead>
		<tbody>
		<c:forEach items="${pageList.rsList }" var="row">
		<tr>
		  <td class="checkCol"><input type="checkbox" class="checkItem" name="afficheIds" value="${row.id }" /></td>			
		  <td><a href="show.do?id=${row.id }"><c:out value="${row.title }"></c:out></a></td>
		  <td><s:message code="affiche.type.${row.type }" /></td>
		  <td><c:out value="${row.custContacts.email }"></c:out></td>
		  <td><c:out value="${row.custContacts.phone }"></c:out></td>
		  <td>
		    <div class="action-buttons">
			  <a href="edit.do?id=${row.custContacts.id }" class="blue" >
				<i class="icon-pencil"></i>
			  </a>&nbsp;	
			  <a href="javascript:;" class="red" onclick="rowDelete(${row.custContacts.id});">
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
