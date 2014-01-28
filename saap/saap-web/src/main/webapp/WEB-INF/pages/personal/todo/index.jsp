<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.inc"%>
<%@ include file="/common/taglibs.inc"%>
<html lang="en">
  <head>
	<meta name="decorator" content="main_with_bar" />	
	<!-- jquery inline editor -->
	<link href="${contextPath}/widgets/x-editable/css/bootstrap-editable.css" rel="stylesheet" />	
	<script src="${contextPath }/widgets/x-editable/js/bootstrap-editable.min.js"></script>	
	</head>
	<body> 	
		<!-- time and other filter -->
		<div>	
				
		</div>
		<!-- quick filter -->
		<div>			
			<ul class="nav nav-pills">	
			  <li <c:if test="${empty param.filter }">class="active"</c:if> >
			    <a href="index.do"><s:message code="todo.filter.all" /></a>
			  </li>
			  <li <c:if test="${'today' eq param.filter }">class="active"</c:if> ><a href="index.do?filter=today"><s:message code="todo.filter.today" /></a></li>
			  <li <c:if test="${'overdue' eq param.filter }">class="active"</c:if> ><a href="index.do?filter=overdue"><s:message code="todo.filter.overdue" /></a></li>
			  <li <c:if test="${'done' eq param.filter }">class="active"</c:if> ><a href="index.do?filter=done"><s:message code="todo.filter.done" /></a></li>
			</ul>
		</div>		
		<!-- todo list -->
		<div>
			<form action="save.json" method="post" name="theform" id="theform">
				<div id="create-todo" class="well">	
				   <div class="control-group">
						<div class="controls">
							<div class="input-append span12">
								<input id="new-todo" class="span8" type="text" placeholder="<s:message code='todo.ui.content.placeholder' />" name="content" required/>							
								<button class="btn" type="submit"><i class="icon-ok"></i> <s:message code="btn.save"></s:message></button>					
							</div>
							<span class="help-block visible-ie8 visible-ie9"><s:message code="todo.ui.content.tooltip" /></span>
						</div>	
					</div>	          
	            </div>
             </form>
			<ul id="todos" class="item-list unstyled">				
				
				<c:forEach items="${pageList.rsList }" var="row">
					<c:set var="_itemColor" value=""/>
					<c:if test="${-2==row.status }">
						<c:set var="_itemColor" value="item-red"/>
					</c:if>
					<c:if test="${-1==row.status }">
						<c:set var="_itemColor" value="item-dark"/>
					</c:if>
					<c:if test="${1==row.status }">
						<c:set var="_itemColor" value="item-default"/>			
					</c:if>
					<c:if test="${2==row.status }">
						<c:set var="_itemColor" value="item-blue"/>
					</c:if>
					<c:if test="${3==row.status }">
						<c:set var="_itemColor" value="item-green"/>
					</c:if>						
				
					<li class="${_itemColor } clearfix">
						<label class="checkbox inline">
							<input type="checkbox" onclick="changeStatusDone(${row.id}, this);" value="${row.status }" <c:if test="${3==row.status || -1==row.status }">checked="checked"</c:if> />
						</label>
						<c:if test="${-2==row.status }">
							<span class="badge badge-warning"><s:message code="todo.status.${row.status }" /></span>
						</c:if>
						<c:if test="${-1==row.status }">
							<span class="badge badge-inverse"><s:message code="todo.status.${row.status }" /></span>
						</c:if>
						<c:if test="${1==row.status }">
							<span class="badge badge-info"><s:message code="todo.status.${row.status }" /></span>								
						</c:if>
						<c:if test="${2==row.status }">
							<span class="badge badge-important"><s:message code="todo.status.${row.status }" /></span>
						</c:if>
						<c:if test="${3==row.status }">
							<span class="badge badge-success"><s:message code="todo.status.${row.status }" /></span>
						</c:if>				
						&nbsp;&nbsp;
						<label id="todo_${row.id }" class="inline inline-edit <c:if test="${3==row.status || -1==row.status }">f-deleted</c:if>"
								data-type="text" data-pk="${row.id }" data-url="updateContent.json" data-title="Enter Content"
							  ><c:out value="${row.content }" /></label>
						<div class="pull-right action-buttons">
							<a href="javascript:;" class="blue editTodo" data-id="${row.id }">
								<i class="icon-pencil"></i>
							</a>
							<span class="vbar"></span>
	
							<a href="javascript:;" class="red" onclick="deleteTodo(${row.id});">
								<i class="icon-trash"></i>
							</a>
	
							<span class="vbar"></span>
	
							<a href="#" class="green">
								<i class="icon-flag"></i>	<!-- 尚未实现 -->
							</a>
							
							<a class="dropdown-toggle" data-toggle="dropdown" data-target="#">
								<i class="icon-cog icon-only"></i>
							</a>							
							<ul class="dropdown-menu dropdown-icon-only dropdown-yellow pull-right dropdown-close">
								<li><a tabindex="-1" href="#" onclick="changeTodoStatus(${row.id }, 2);"><s:message code="todo.status.2" /></a></li>		
								<li><a tabindex="-1" href="#" onclick="changeTodoStatus(${row.id }, 3);"><s:message code="todo.status.3" /></a></li>	
								<li><a tabindex="-1" href="#" onclick="changeTodoStatus(${row.id }, -2);"><s:message code="todo.status.-2" /></a></li>	
								<li><a tabindex="-1" href="#" onclick="changeTodoStatus(${row.id }, -1);"><s:message code="todo.status.-1" /></a></li>								
							</ul>							
						</div>
					</li>
				</c:forEach>	
			</ul>
		</div>
		<!--page specific plugin scripts-->				
		<script type="text/javascript">
		$(document).ready(function(){			
			
			$(".inline-edit").editable({
				mode: "inline",
				showbuttons:false,
				params: function(params) {				   
				    params.id = params.pk;
				    params.content = params.value;
				    return params;
				},
				validate: function(value) {
				    if($.trim(value) == '') {
				        return "<s:message code='validate.required' />";
					}
				}    
				
			});	
			
			$(".editTodo").click(function(e){
			    e.stopPropagation();	//关键
			    var id = $(this).attr("data-id");
			    $('#todo_'+id).editable('toggle');
			});
			
			
			$("#theform").validate({  
				 submitHandler: function(form) {  
					 $(form).ajaxSubmit({							   
					    	dataType:  'json',         
					    	success:   function(){
					    		location.reload();
					    	}
					  });     	
					  return false;						    
				 }				 
			});  
		});	
				
		function deleteTodo(id)
		{
			bootbox.confirm("<s:message code='common.delete.confirm' />", function(result){
				if(result)
				{
					$.post("delete.json", {"id":id}, function(reply){			
						location.reload();
					});	
				}				
			});			
		}
		
		function changeStatusDone(id, chkbox)
		{	
			previousStatus = chkbox.value;
			if($(chkbox).is(':checked'))
			{
				$.post("updateStatus.json", {"id":id, "status":3}, function(reply){			
					$("#todo_"+id).addClass("f-deleted");
				});	
			}
			else
			{
				if(previousStatus==3 || previousStatus==-2)	//如果原状态是DONE或者CANCEL，则取消选择变为InProgress
					previousStatus = 2;
				$.post("updateStatus.json", {"id":id, "status":previousStatus}, function(reply){			
					$("#todo_"+id).removeClass("f-deleted");
				});	
			}
		}
		
		function changeTodoStatus(id, status)
		{
			$.post("updateStatus.json", {"id":id, "status":status}, function(reply){			
				location.reload();
			});		
		}
		</script>			
	</body>
</html>
