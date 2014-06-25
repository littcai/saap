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
			<form class="form-search" style="margin-bottom:5px;" id="search-form" name="search-form" action="index.do" method="POST">				
				<input type="hidden" id="pageIndex" name="pageIndex" value="${pageParam.pageIndex }" />
				<input type="hidden" id="pageSize" name="pageSize" value="${pageParam.pageSize }" />
				<input type="hidden" id="sortField" name="sortField"  value="${pageParam.sortField}"/>
				<input type="hidden" id="sortOrder" name="sortOrder"  value="${pageParam.sortOrder}"/>
                <select name="searchField" id="searchField" style="width:130px">
                    <option label="姓名" value="accountname">姓名</option>	
                    <option label="性别" value="accountname">性别</option>	
                    <option label="手机号码" value="phone">手机号码</option>				
					<option label="Email" value="email">Email</option>
					<option label="联系人" value="membername">电话</option>
					<option label="职位" value="title">职位</option>
					
                </select>                      
                      <input type="text" class="input-large search-query" value="" name="search_text">
                      <button type="button" class="btn btn-small" onClick="callSearch('Basic');"><i class="icon-search"></i>&nbsp;搜索</button>
                      <button type="button" class="btn btn-small " onClick="clearSearchResult('Accounts','BasicSearch');">
                      <i class="icon-remove-sign"></i>&nbsp;取消查找</button>
                      <button type="button" class="btn btn-small " onClick="openAdvanceDialogs('Accounts');">
                      <i class="icon-share-alt"></i>&nbsp;高级搜索</button>
             </form>
		</div>	
		<div class="clear"></div> 				
		<!-- quick filter -->
		<div>			
			<ul class="nav nav-pills" style="margin-bottom: 5px;">	
			  <li <c:if test="${empty param.filter }">class="active"</c:if> >
			    <a href="index.do"><s:message code="contacts.filter.all" /></a>
			  </li>
			  <li <c:if test="${'today' eq param.filter }">class="active"</c:if> ><a href="index.do?filter=today"><s:message code="contacts.filter.fav" /></a></li>
			</ul>
		</div>
		<div class="clear"></div> 		
		<!-- toolbar -->
		<div style="margin-top:2px;padding-top:5px;margin-bottom:8px;border-top:2px solid #0088CC;">
			<div class="pull-left" style="margin-bottom:5px;">
                <button class="btn btn-small btn-primary" style="margin-top:2px;" onclick="javascript:location.href='add.do'">
                  <i class="icon-plus icon-white"></i> <s:message code="btn.add" /></button>
                <button class="btn btn-small btn-danger" style="margin-top:2px;" onclick="javascript:return batchDelete('contactsIds');">
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
                  
                <button class="btn btn-small btn-success" style="margin-top:2px;" onclick="javascript:location.href='index.php?module=Accounts&action=Import&step=1&return_module=Accounts&return_action=index&parenttab=Customer'">
                  <i class="icon-download icon-white"></i> <s:message code="btn.import" /></button>
                <button class="btn btn-small btn-success" style="margin-top:2px;" onclick="return selectedRecords('Accounts','Customer')" >
                  <i class="icon-upload icon-white"></i> <s:message code="btn.export" /></button>
                 <button class="btn btn-small " style="margin-top:2px;" onclick="javascript:qunfa_mail(this, 'qunfamail', 'Accounts');return false;">
                  <i class="icon-envelope"></i> 发送邮件</button>
                 <button class="btn btn-small " style="margin-top:2px;" onclick="javascript:qunfa_sms(this, 'qunfasms', 'Accounts');return false;">
                  <i class="icon-comment"></i> 发送短信</button>
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
						<th class="sort name"><s:message code="contacts.name" /></th>
						<th class="sort gender"><s:message code="contacts.gender" /></th>
						<th><s:message code="contacts.mobile" /></th>
						<th><s:message code="contacts.email" /></th>
						<th><s:message code="contacts.phone" /></th>
						<th><s:message code="common.action" /></th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${pageList.rsList }" var="row">
					<tr>
						<td class="checkCol"><input type="checkbox" name="contactsIds" value="${row.id }" /></td>
						<td><c:out value="${row.name }" /></td>
						<td>${li:genDictContent("0002", row.gender)}</td>
						<td><c:out value="${row.mobile }" /></td>
						<td><a href="mailto:${row.email }"><c:out value="${row.email }" /></a></td>
						<td><c:out value="${row.phone }" /></td>
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
			//checkall
			$('.datatable th input:checkbox').on('click' , function(){
				var that = this;
				$(this).closest('table').find('tr > td:first-child input:checkbox')
				.each(function(){
					this.checked = that.checked;
					$(this).closest('tr').toggleClass('selected');
				});					
			});
			//toggle checkall
			//$(this).closest('table').find('.datatable tr > td:first-child input:checkbox').on('click' , function(){
				
				
				
			//});
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
