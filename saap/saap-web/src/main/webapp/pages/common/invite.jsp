<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.inc"%>
<%@ include file="/common/taglibs.inc"%>
<html lang="en">
	<head>
		<title><s:message code="login.ui.title" /></title>		
	</head>
	<body> 		

<div class="container" style="margin: 20px auto 0;">	
	<div class="row">
	  <div class="span10 offset1">
	  	<div class="widget-box">
	  		<div class="widget-header header-color-blue">
	        	<h5 class="bigger lighter"><s:message code="invite" /></h5>
	        </div>
	        <div class="widget-body">
	        	<div class="widget-main">
	        	   <div id="form-error-container" class="form-error-container alert alert-block hide">
	        	   	 <i class="icon-warning-sign"></i> <strong><s:message code="form.error.invalid" />:</strong>	        	     
	        	     <ul></ul>
	        	   </div>
			       <form id="theform" name="theform" action="invite.json" method="post" class="form-horizontal">
			       	<fieldset>
			       		<legend><s:message code="invite.form.legend.base" /></legend>
			       		<c:forEach begin="0" end="2" step="1" varStatus="loopStatus">
				       		<div id="memberBox${loopStatus.index }" style="margin-bottom: 10px">
				       			<input name="emails" placeholder="<s:message code='invite.email' />" type="text" class="span6" />&nbsp;
				       			<select name="roleIds">			       				
				       				<c:forEach items="${roleList }" var="role" varStatus="status">
				       				<option value="${role.id }"><c:out value="${role.name }" /></option>
				       				</c:forEach>
				       			</select>&nbsp;
				       			<button type="button" class="btn" onclick="removeMember(${loopStatus.index });" ><s:message code="btn.delete"/></button>				       		
							</div>	
						</c:forEach>
						<button type="button" class="btn" onclick="addMember();"><s:message code="invite.form.btn.addMore" /></button>	
					</fieldset>
 					<fieldset>
			       		<legend><s:message code="invite.form.legend.comment" /></legend>
			       		<div>
			       			<textarea rows="5" cols="8" id="comment" name="comment" class="input-block-level limited"></textarea>
			       		</div>
			       	</fieldset>	
			       	<div class="form-actions">
						<button type="submit" class="btn btn-primary" data-loading-text="<s:message code='common.processing' />"><i class="icon-ok"></i> <s:message code="invite.form.btn.sendInvite" /></button>
						<button type="button" class="btn" onclick="history.back();"><s:message code="btn.cancel" /></button>
					</div>	
			       </form> 
	        	</div>
	        </div>
	    </div>    
      </div> 
    </div>    
</div>
<!-- inline scripts related to this page -->
<script type="text/javascript">
$(document).ready(function(){	
	
	$('#theform').littFormSubmit({
		rules : {
			emails : {
				required : true,
				email : true
				
			},						
			comment : {				
				maxlength : 500
			}
		},	
		errorMessages :{
			emails : {
				required : "<s:message code='invite.error.inputEmpty' />"
			}
		},
		errorContainer: $("#form-error-container"),
		errorLabelContainer: $("#form-error-container ul"), 
		success: function(reply){
			 location.href = "index.do";					
		}
	});	
});		
</script>
</body>
</html>
