<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.inc"%>
<%@ include file="/common/taglibs.inc"%>
<html lang="en">
  <head>
  	<script src="${contextPath }/widgets/jquery-input/jquery-input.js"></script>
  	<link href="${contextPath }/widgets/bootstrap-tagsinput/css/bootstrap-tagsinput.css" rel="stylesheet" />
  	<script src="${contextPath }/widgets/bootstrap-tagsinput/js/bootstrap-tagsinput.js"></script>
  	<style type="text/css">
  	.bootstrap-tagsinput {
  		width: 100%;
  	}
  	</style>			
  </head>
  <body>   
  <form id="theform" action="save.json" method="post" class="form-horizontal">
				<fieldset>
					<legend><s:message code="common.ui.fieldset.base" /></legend>					
					<div class="row-fluid">												
						<div class="span9">
							<div class="control-group">
								<label class="control-label" for="receiver"><s:message code="smsOut.receiver" /></label>
								<div class="controls">
									<input id="receiver" name="receiver" placeholder="Add Mobiles" type="text" class="input-block-level"/>
									<div id="receiver_error"></div>
									<div class="help-block left"><s:message code="smsOut.receiver.tip"/></div>
								</div>
							</div>
						</div>
						<div class="span1"><button type="button" class="btn" onclick="showContacts();"><i class="fa-book"></i></button></div>								
					</div>
					<div class="row-fluid">
						<div class="span10">
							<div class="control-group">
								<label class="control-label" for="content"><s:message code="smsOut.content" /></label>
								<div class="controls">
									<textarea rows="5" cols="8" id="content" name="content" class="input-block-level limited"></textarea>
								</div>
							</div>
						</div>						
					</div>					
				</fieldset>					
						
				<div class="form-actions">
					<button type="submit" class="btn btn-primary"><i class="icon-ok"></i> <s:message code="btn.save" /></button>
					<button type="button" class="btn" onclick="history.back();"><s:message code="btn.cancel" /></button>
				</div>					
				
			</form>	
<!-- Modal -->
<div id="contactsModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="contactsModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="contactsModalLabel"><s:message code="contacts.ui.dialog.choose" /></h3>
  </div>
  <div class="modal-body">
    <table class="table table-striped table-bordered table-hover">
    	<thead>
    		<tr>
    			<th></th>
    			<th><s:message code="contacts.name" /></th>
    			<th><s:message code="contacts.mobile" /></th>
    		</tr>	
    	</thead>
    	<tbody>	
    	<c:forEach items="${contactList }" var="contact">
    		<tr>
    			<td class="checkCol">
    				<c:if test="${not empty contact.mobile }"><input type="checkbox" class="checkItem" name="contactIds" value="${contact.mobile }" /></c:if>
    			</td>
				<td><c:out value="${contact.name }"></c:out></td>
				<td><c:out value="${contact.mobile }"></c:out></td>		
    		</tr>
    	</c:forEach>
    	</tbody>
    </table>
  </div>
  <div class="modal-footer">
    <button class="btn" data-dismiss="modal" aria-hidden="true"><s:message code="btn.cancel" /></button>
    <button class="btn btn-primary" onclick="confirmContacts();"><s:message code="btn.confirm" /></button>
  </div>
</div>					
		<!--page specific plugin scripts-->				
		<script type="text/javascript">
		$(document).ready(function(){	
						
			$("#receiver").tagsinput({
				 confirmKeys: [13, 188],
				 maxTags: 50
			});
			
			$('#content').inputCount({
				'maxCharacterSize': 200
			});
			
			$.validator.setDefaults({ 
			    ignore: [],
			    // any other default options and/or rules
			});
			
			$('#theform').littFormSubmit({
				rules : {
					receiver : {
						required : true
					},
					content : {
						required : true
					}
				},
				errorPlacement: function(error, element) {     
			    	
				      if(element.parent().hasClass('input-prepend') || element.parent().hasClass('input-append')) {
				        error.insertAfter(element.parent());	
				      } else if(element.prop("id")=="receiver")
				      {
				    	  error.appendTo($("#receiver_error")); 
				      }
				      else {
				        error.insertAfter(element);
				      }
				},
				success: function(reply){
					location.href = "index.do";					
				}
			});
		});
		
		function showContacts()
		{
			$('#contactsModal').modal();
		}
		
		function confirmContacts()
		{
			if($(".checkItem:checked").length<=0)
			{
				$.webtools.notify({
					type: "notice",
					message: "<s:message code='validate.checkone'/>"
				});
				return;
			}
			var ids = $.webtools.getCheckValues(".checkItem");
			$('#receiver').tagsinput('add', ids);
			
			$('#contactsModal').modal('hide');
		}
		</script>	  
  </body>	
</html>