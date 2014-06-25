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
	<!-- bootstrap-datetimepicker -->
	<link href="${contextPath}/widgets/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" />
    <script src="${contextPath }/widgets/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
	<c:if test="${!empty SESSION_USER && !empty SESSION_USER.locale && !fn:startsWith(SESSION_USER.locale, 'en') }">
		<script src="${contextPath }/widgets/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.${SESSION_USER.locale }.js"></script>
	</c:if>		
  </head>
 <body>
 	<!-- form filter -->	
		<div>
			<form class="form-search" style="margin-bottom:5px;" id="search-form" name="search-form" action="index.do" method="GET">				
				<input type="hidden" id="pageIndex" name="pageIndex" value="${pageParam.pageIndex }" />
				<input type="hidden" id="pageSize" name="pageSize" value="${pageParam.pageSize }" />
				<input type="hidden" id="sortField" name="sortField"  value="${pageParam.sortField}"/>
				<input type="hidden" id="sortOrder" name="sortOrder"  value="${pageParam.sortOrder}"/>
		<div>		
	        <select name="s_searchField" id="s_searchField" style="width:130px">
	        	<option value="sender" ${li:renderSelected(param.s_searchField, "sender")}><s:message code="smsIn.sender" /></option>	
	        	<option value="receiver" ${li:renderSelected(param.s_searchField, "receiver")}><s:message code="smsIn.receiver" /></option>						
	        </select>                      
	        <input type="text" class="input-large search-query" value="${param.s_searchValue }" name="s_searchValue">&nbsp;
	        <c:if test="${not empty SESSION_USER.tenant && SESSION_USER.tenant.isAdmin}">
		        <label class="control-label" for="createBy"><s:message code="common.field.createBy" />:</label>
		        <select name="createBy" id="createBy">
		        	<option value=""><s:message code="common.ui.select" /></option>		
		        	<c:forEach items="${tenantUserList }" var="row">
		        		<option value="${row.id }" ${li:renderSelected(param.createBy, row.id) }  >${row.userName }[${row.loginId }]</option>
		        	</c:forEach>		
		        </select>
	        </c:if>
	    </div>
        <div> 
	        <label class="control-label" for="startDate"><s:message code="smsOut.sendDatetime" />:</label>
	        <div class="input-append date datetimepicker" data-date-format="yyyy-mm-dd">
				<input id="startDate" name="startDate" placeholder="" type="text" readonly="readonly" value="${param.startDate }"/>
				<span class="add-on"><i class="icon-calendar"></i></span>
			</div>&nbsp;-&nbsp;
			<div class="input-append date datetimepicker" data-date-format="yyyy-mm-dd">
				<input id="endDate" name="endDate" placeholder="" type="text" readonly="readonly" value="${param.endDate }"/>
				<span class="add-on"><i class="icon-calendar"></i></span>
			</div>&nbsp;
			<button type="submit" class="btn btn-small"><i class="icon-search"></i>&nbsp;<s:message code="btn.query" /></button>  
        </div>    
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
						<th><s:message code="smsOut.sender" /></th>
						<th><s:message code="smsOut.receiver" /></th>
						<th><s:message code="smsOut.content" /></th>
						<th><s:message code="smsOut.sendFlag" /></th>
						<th><s:message code="smsOut.createBy" /></th>
						<th><s:message code="smsOut.createDatetime" /></th>
						<th><s:message code="smsOut.sendDatetime" /></th>
						<th><s:message code="common.action" /></th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${pageList.rsList }" var="row">
					<tr>
						<td class="checkCol"><input type="checkbox" class="checkItem" name="smsOutIds" value="${row.id }" /></td>
						<td><c:out value="${row.sender }"></c:out></td>
						<td><c:out value="${row.receiver }"></c:out></td>
						<td><c:out value="${li:formatLongString(row.content, 10) }"></c:out></td>
						<td><c:out value="${row.sendFlag }"></c:out></td>
						<td><c:out value="${li:getUser(row.createBy).loginId }"></c:out></td>
						<td><c:out value="${li:formatDateTime(row.createDatetime) }"></c:out></td>
						<td><c:out value="${li:formatDateTime(row.sendDatetime) }"></c:out></td>
						<td>
							<div class="action-buttons">
							<a href="show.do?id=${row.id }">
								<i class="icon-eye-open"></i>
							</a>&nbsp;
							<a href="javascript:;" onclick="rowResend(${row.id});">
								<i class="icon-repeat"></i>
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
			
			$('.datetimepicker').datetimepicker({
				todayHighlight: true,
				minView: 2
			});
			
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
		
		function rowResend(id)
		{			
			$.webtools.ajax({
				url: "resend.json",
				params: {"id":id},
				success: function(reply) {
					//location.reload();
				}
			});			
		}	
		</script>
  </body>
</html>
