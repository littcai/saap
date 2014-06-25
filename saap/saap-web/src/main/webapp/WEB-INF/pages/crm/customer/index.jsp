<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.jspf"%>
<%@ include file="/common/taglibs.jspf"%>
<html lang="en">
  <head>
	<meta name="decorator" content="main_with_bar" />		
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
                	<option value="code"><s:message code="customer.code" /></option>	
                	<option value="name"><s:message code="customer.name" /></option>						
                </select>                      
                <input type="text" class="input-large search-query" value="" name="search_text">
                <button type="button" class="btn btn-small" onClick="callSearch('Basic');"><i class="icon-search"></i>&nbsp;<s:message code="btn.query"></s:message></button>                
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

				  <div class="btn-group" style="margin-top:2px;">
					<a class="btn btn-small btn-inverse dropdown-toggle" data-toggle="dropdown" href="#">
						<i class="icon-edit icon-white"></i> 批量操作
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<li>
							<a href="#" onclick="javascript:quick_edit(this, 'quickedit', 'Accounts');return false;"> 批量修改</a>
						</li>
						<li>
							<a href="#" onclick="javascript:change(this,'changeowner');return false;" > 修改负责人</a>
						</li>
					</ul>
				</div>
                <!--<button class="btn btn-small btn-inverse" style="margin-top:2px;" onclick="javascript:quick_edit(this, 'quickedit', 'Accounts');return false;" >-->
                <!--   
                <button class="btn btn-small btn-success" style="margin-top:2px;" onclick="javascript:location.href='index.php?module=Accounts&action=Import&step=1&return_module=Accounts&return_action=index&parenttab=Customer'">
                  <i class="icon-download icon-white"></i> <s:message code="btn.import" /></button>
                <button class="btn btn-small btn-success" style="margin-top:2px;" onclick="return selectedRecords('Accounts','Customer')" >
                  <i class="icon-upload icon-white"></i> <s:message code="btn.export" /></button>
                 <button class="btn btn-small " style="margin-top:2px;" onclick="javascript:qunfa_mail(this, 'qunfamail', 'Accounts');return false;">
                  <i class="icon-envelope"></i> 发送邮件</button>
                 <button class="btn btn-small " style="margin-top:2px;" onclick="javascript:qunfa_sms(this, 'qunfasms', 'Accounts');return false;">
                  <i class="icon-comment"></i> 发送短信</button>
                 -->  
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
						<th class="sort code"><s:message code="customer.code" /></th>	
						<th class="sort name"><s:message code="customer.name" /></th>
						<th><s:message code="customer.contacts" /></th>	
						<th><s:message code="customer.phone" /></th>					
						<th><s:message code="customer.email" /></th>
						<th><s:message code="customer.chargeUser" /></th>						
						<th><s:message code="common.action" /></th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${pageList.rsList }" var="row">
					<tr>
						<td class="checkCol"><input type="checkbox" name="customerIds" value="${row.ID }" /></td>
						<td><a href="show.do?id=${row.ID }"><c:out value="${row.CODE }"></c:out></a></td>
						<td><a href="show.do?id=${row.ID }"><c:out value="${row.NAME }"></c:out></a></td>
						<td><c:out value="${row.CONTACTS_NAME }"></c:out></td>
						<td><c:out value="${row.PHONE }"></c:out></td>
						<td><a href="mailto:${row.EMAIL }">${row.EMAIL }</a></td>
						<td><c:out value="${row.CHARGE_USER_NAME }"></c:out></td>
						<td class="action-buttons">
							<div class="action-buttons">
							<a href="edit.do?id=${row.ID }" class="blue" >
								<i class="icon-pencil"></i>
							</a>
							<span class="vbar"></span>	
							<a href="javascript:;" class="red" onclick="rowDelete(${row.ID});">
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
