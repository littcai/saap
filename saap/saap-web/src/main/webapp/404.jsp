<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.litt.core.exception.NotLoginException"%>
<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/taglibs.inc"%>
<head>
<title>404 <s:message code="error.400.title" /></title>
<%@ include file="/common/meta.inc"%>
<%@ include file="/common/include_form.inc"%>
<script language="javascript">
$(document).ready(function(){	
	
	$('#theform').littFormSubmit({
		rules : {
			comment : {
				minlength : 2,
				required : true
			}
		},	
		beforeSerialize: function() {
			//$("#message").val();
		},
		success: function(reply){
			$("#comment").val("");
			$("#sendErrorBox").toggle();				
		}
	});	
});		

  function showDetail()
  {
      $("#errorDetailBox").toggle();
  }
  
  function sendError()
  {
      $("#sendErrorBox").toggle();
  }
</script>
</head>
<body>
<!-- container -->
<div class="container-fluid" style="margin-top: 15px;">
  <div class="row-fluid">
  	<h1 class="grey lighter smaller">
		<span class="blue bigger-125">
			<i class="icon-wrench icon-animated-wrench bigger-125"></i> 404
		</span>
		<s:message code="error.400.title" />
	</h1>
  	<hr />  	
  	<div class="space"></div>
  	<div>
		<h4 class="lighter smaller"><s:message code="error.400.help.title" /></h4>
		<ul class="unstyled">
			<li>
				<i class="icon-hand-right blue"></i>
				<s:message code="error.400.func.checkUrl" />
			</li>
			<li>
				<i class="icon-hand-right blue"></i>
				<s:message code="error.400.func.readFaq" />
			</li>
			<li>
				<i class="icon-hand-right blue"></i>
				<a href="javascript:;" onclick="sendError();"><s:message code="error.400.func.sendError" /></a>				
			</li>
		</ul>
		<div id="sendErrorBox" class="hide">
			<form id="theform" action="${contextPath }/sendError.json" method="post">
				<input type="hidden" id="errorCode" name="errorCode" value="404" />
				<input type="hidden" id="url" name="url" value="<%=request.getRequestURI() %>" />
				<input type="hidden" id="message" name="message" value="<%=exception.getCause().getMessage()%>" />
				<textarea id="comment" name="comment" class="span6" rows="6" cols="80" required></textarea>
				<div>
				  <button type="submit" class="btn btn-primary"><s:message code="btn.submit" /></button>
				  <button type="button" class="btn"><s:message code="btn.cancel" /></button>
				</div>
			</form>
		</div>
	</div>
  	<hr />
	<div class="space"></div>
	<div class="text-center">			
			<a href="javascript:history.back();"  class="btn btn-grey">
				<i class="icon-arrow-left"></i>
				<s:message code="error.500.func.goback" />		
			</a>
			<a href="javascript:location.reload();" class="btn btn-primary">
				<i class="icon-refresh"></i>
				<s:message code="error.500.func.refresh" />		
			</a>			
	</div>
  </div>  
</div>  
</body>
</html>
