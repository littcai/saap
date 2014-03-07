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
	<!-- jquery file upload -->
	<link href="${contextPath}/widgets/jquery-fileupload/css/jquery.fileupload.css" rel="stylesheet" />	
	<script src="${contextPath }/widgets/jquery-fileupload/js/jquery.ui.widget.js"></script>
	<script src="${contextPath }/widgets/jquery-fileupload/js/jquery.iframe-transport.js"></script>
	<script src="${contextPath }/widgets/jquery-fileupload/js/jquery.fileupload.js"></script>
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
			        	<option value="name" ${li:renderSelected(param.s_searchField, "name")}><s:message code="contacts.name" /></option>	
			        	<option value="mobile" ${li:renderSelected(param.s_searchField, "mobile")}><s:message code="contacts.mobile" /></option>
			        	<option value="email" ${li:renderSelected(param.s_searchField, "email")}><s:message code="contacts.email" /></option>						
			        </select>                                    
	                <input type="text" class="input-large search-query" value="${param.s_searchValue }" name="s_searchValue">
	        		<button type="submit" class="btn btn-small"><i class="icon-search"></i>&nbsp;<s:message code="btn.query" /></button>        
	             </form>
			</div>	
			<div class="clear"></div> 				
			<!-- quick filter -->
			<div>			
				<ul class="nav nav-pills" style="margin-bottom: 5px;">	
				  <li <c:if test="${empty param.filter }">class="active"</c:if> >
				    <a href="index.do"><s:message code="contacts.filter.all" /></a>
				  </li>			  
				</ul>
			</div>
			<div class="clear"></div> 		
			<!-- toolbar -->
			<div style="margin-top:2px;padding-top:7px;margin-bottom:8px;border-top:2px solid #0088CC;">
				<div class="pull-left" style="margin-bottom:5px;">
	                <button class="btn btn-small btn-primary" onclick="javascript:location.href='add.do'">
	                  <i class="icon-plus icon-white"></i> <s:message code="btn.add" /></button>
	                <button class="btn btn-small btn-danger" onclick="javascript:return batchDelete();">
	                  <i class="icon-trash icon-white"></i> <s:message code="btn.delete" /></button>
	                <button class="btn btn-small btn-info" onclick="javascript:location.href='../contactsGroup/index.do'">
	                  <i class="icon-book icon-white"></i> <s:message code="contactsGroup" /></button>
	                <span class="btn btn-small btn-success fileinput-button">
				        <i class="icon-upload icon-white"></i>
				        <span><s:message code="btn.import" /></span>
				        <!-- The file input field used as target for the file upload widget -->
				        <input id="btnImport" type="file" name="files[]" multiple>
				    </span>	                 
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
							<th class="checkCol"><input type="checkbox" id="checkAll" name="checkAll"/></th>		
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
							<td class="checkCol"><input type="checkbox" class="checkItem" name="contactsIds" value="${row.id }" /></td>
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
			
			$('#btnImport').fileupload({
		        dataType: 'json',
		        url: 'imp.json',
		        add: function (e, data) {
		            //data.context = $('<p/>').text('Uploading...').appendTo(document.body);
		            data.submit();
		        },
		        done: function (e, data) {
		        	var result = data.result;
		        	if(result.error)
		        	{
		        		$.webtools.notify({
							type: "error",
							message: result.errorMessage
						});		        		
		        	}
		        	else
		        	{
		        		$.webtools.notify({
							type: "success",
							message: "<s:message code='common.import.success'/>"
						});	
		        	}
		        }
		    });
			
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
