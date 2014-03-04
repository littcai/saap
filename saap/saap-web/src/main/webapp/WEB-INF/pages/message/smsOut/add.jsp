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
  	<div>
  		<button id="btn-contacts" type="button" class="btn btn-primary hide" onclick="switchContacts();"><i class="icon-search"></i> 联系人</button>
  		<button id="btn-contactsGroup" type="button" class="btn btn-primary" onclick="switchContacts();"><i class="icon-search"></i> 联系人分组</button>
  	</div>
  	<div class="space"></div>
    <div id="panel-contacts">
	    <table class="table table-striped table-hover table-condensed">
	    	<thead>
	    		<tr>
	    			<th><input type="checkbox" class="checkAll" data-target="#panel-contacts .checkItem" /></th>
	    			<th><s:message code="contacts.name" /></th>
	    			<th><s:message code="contacts.mobile" /></th>
	    		</tr>	
	    	</thead>
	    	<tbody>	
	    	<c:forEach items="${contactsList }" var="contacts">
	    		<tr>
	    			<td class="checkCol">
	    				<c:if test="${not empty contacts.mobile }"><input type="checkbox" class="checkItem" name="contactsIds" value="${contacts.mobile }" /></c:if>
	    			</td>
					<td><c:out value="${contacts.name }"></c:out></td>
					<td><c:out value="${contacts.mobile }"></c:out></td>		
	    		</tr>
	    	</c:forEach>
	    	</tbody>
	    </table>
    </div>
    <div id="panel-contactsGroup" class="tabbable tabs-left hide">
	  <ul class="nav nav-tabs">
	  	<li class="active"><a href="#group-default" data-toggle="tab"><s:message code="contactsGroup.noGroup" /></a>
	  	<c:forEach items="${contactsGroupList }" var="contactsGroup">
	  		 <li><a href="#group-${contactsGroup.contactsGroup.id }" data-toggle="tab">${contactsGroup.contactsGroup.name }</a></li>
	  	</c:forEach>		 
	  </ul>
	  <div class="tab-content">
		  <div class="tab-pane active" id="group-default">
		  	<table class="table table-striped table-hover table-condensed">
		    	<thead>
		    		<tr>
		    			<th><input type="checkbox" class="checkAll" data-target="#group-default .checkItem" /></th>
		    			<th><s:message code="contacts.name" /></th>
		    			<th><s:message code="contacts.mobile" /></th>
		    		</tr>	
		    	</thead>
		    	<tbody>	
		    	<c:forEach items="${noGroupContactsList }" var="contacts">
		    		<tr>
		    			<td class="checkCol">
		    				<c:if test="${not empty contacts.mobile }"><input type="checkbox" class="checkItem" name="contactsIds" value="${contacts.mobile }" /></c:if>
		    			</td>
						<td><c:out value="${contacts.name }"></c:out></td>
						<td><c:out value="${contacts.mobile }"></c:out></td>		
		    		</tr>
		    	</c:forEach>
		    	</tbody>
		    </table>
		  
		  </div>
		  <c:forEach items="${contactsGroupList }" var="contactsGroup">
		  	<div class="tab-pane" id="group-${contactsGroup.contactsGroup.id }">
		  		<table class="table table-striped table-hover table-condensed">
			    	<thead>
			    		<tr>
			    			<th><input type="checkbox" class="checkAll" data-target="#group-${contactsGroup.contactsGroup.id } .checkItem" /></th>
			    			<th><s:message code="contacts.name" /></th>
			    			<th><s:message code="contacts.mobile" /></th>
			    		</tr>	
			    	</thead>
			    	<tbody>	
			    	<c:forEach items="${contactsGroup.contactsList }" var="contacts">
			    		<tr>
			    			<td class="checkCol">
			    				<c:if test="${not empty contacts.mobile }"><input type="checkbox" class="checkItem" name="contactsIds" value="${contacts.mobile }" /></c:if>
			    			</td>
							<td><c:out value="${contacts.name }"></c:out></td>
							<td><c:out value="${contacts.mobile }"></c:out></td>		
			    		</tr>
			    	</c:forEach>
			    	</tbody>
			    </table>
		  	</div>
		  </c:forEach>
	  </div>
	</div>
	
    
  </div>
  <div class="modal-footer">
    <button class="btn" data-dismiss="modal" aria-hidden="true"><s:message code="btn.cancel" /></button>
    <button class="btn btn-primary" onclick="confirmContacts();"><s:message code="btn.confirm" /></button>
  </div>
</div>					
		<!--page specific plugin scripts-->				
		<script type="text/javascript">
		$(document).ready(function(){	
			
			$(".checkAll").each(function(i){
				$this = $(this);
				var checkboxs = $.webtools.checkboxs({
					checkAll: $this,
					checkItem: $this.attr("data-target")			
				});
			});			
						
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
		
		function switchContacts()
		{
			$("#btn-contacts").toggle();
			$("#btn-contactsGroup").toggle();
			$("#panel-contacts").toggle();
			$("#panel-contactsGroup").toggle();			
		}
		</script>	  
  </body>	
</html>