<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.jspf"%>
<%@ include file="/common/taglibs.jspf"%>
<html lang="en">
  <head>
	<script type="text/javascript" src="${contextPath }/theme/default/js/angular.js"></script>		
	<!-- jquery inline editor -->
	<link href="${contextPath}/widgets/x-editable/css/bootstrap-editable.css" rel="stylesheet" />	
	<script src="${contextPath }/widgets/x-editable/js/bootstrap-editable.min.js"></script>	
	</head>
	<body> 	
		<!-- todo list -->
		<div>
			<form action="save.json" method="post" name="theform" id="theform">
				<div id="create-todo" class="well">	
				   <div class="control-group">
						<div class="controls">
							<div class="input-append span12">
								<textarea id="new-note-content" class="span8" name="content" rows="10" cols="10" placeholder="<s:message code='note.ui.content.placeholder' />" required></textarea>
													
								<button class="btn" type="submit"><i class="icon-ok"></i> <s:message code="btn.save"></s:message></button>					
							</div>
							<span class="help-block visible-ie8 visible-ie9"><s:message code="note.ui.content.tooltip" /></span>
						</div>	
					</div>	          
	            </div>
             </form>
			<ul id="notes" class="item-list unstyled">				
				<c:forEach items="${noteList }">
				<li><c:out value="${row.content }"></c:out></li>
				</c:forEach>
			</ul>
		</div>
		<!--page specific plugin scripts-->				
		<script type="text/javascript">
		$(document).ready(function(){			
			
			$(".inline-edit").editable({
				type: "textarea",
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
		
		</script>			
	</body>
</html>
