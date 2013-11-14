<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.litt.core.exception.NotLoginException"%>
<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/taglibs.inc"%>
<head>
<title>404 <s:message code="error.400.title" /></title>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
<meta http-equiv="Cache-Control" content="no-store"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Expires" content="0"/> 
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<!-- 先加载样式 -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<!--basic styles-->

	<link href="${contextPath}/theme/default/css/bootstrap.min.css" rel="stylesheet" />
	<link href="${contextPath}/theme/default/css/bootstrap-responsive.min.css" rel="stylesheet" />
	<link rel="stylesheet" href="${contextPath}/theme/default/css/font-awesome.min.css" />
	<link rel="stylesheet" href="${contextPath}/theme/default/css/cus-icons.css" />
	<!--[if IE 7]>
	  <link rel="stylesheet" href="${contextPath}/theme/default/css/font-awesome-ie7.min.css" />
	<![endif]-->
	
	<!--[if lt IE 9]>
	<script src="//html5shiv.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->	
	<!--page specific plugin styles-->
	<!--fonts-->

	<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />
		
	
	<!--inline styles related to this page-->
	<link href="${contextPath}/theme/default/css/main.css" rel="stylesheet" />
	
<!--basic scripts-->

		<!--[if !IE]>-->

		<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>

		<!--<![endif]-->

		<!--[if IE]>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
		<![endif]-->

		<!--[if !IE]>-->

		<script type="text/javascript">
			window.jQuery || document.write("<script src='${contextPath}/theme/default/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
		</script>

		<!--<![endif]-->

		<!--[if IE]>
		<script type="text/javascript">
		 window.jQuery || document.write("<script src='${contextPath}/theme/default/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
		</script>
		<![endif]-->

		<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='${contextPath}/theme/default/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="${contextPath}/theme/default/js/bootstrap.min.js"></script>

		<!-- common plugin scripts-->		
		<!-- jquery bootbox -->
		<script src="${contextPath }/widgets/bootbox/bootbox.min.js"></script>	
		
		<!-- jquery pnotify -->
		<link href="${contextPath}/widgets/pnotify/css/jquery.pnotify.default.css" rel="stylesheet" />
		<script src="${contextPath }/widgets/pnotify/js/jquery.pnotify.min.js"></script>	
		
		<!-- jquery form and validate -->
<script src="${contextPath }/widgets/jquery-validate/js/jquery.validate.min.js"></script>
<script src="${contextPath }/widgets/jquery-validate/js/jquery.validate.ex.js"></script>
<c:if test="${!empty locale && !fn:startsWith(locale, 'en') }">
	<script src="${contextPath }/widgets/jquery-validate/i18n/message_${locale}.js"></script>
</c:if>
<script src="${contextPath }/widgets/jquery-form/jquery.form.min.js"></script>
<!-- jquery select2 -->
<link href="${contextPath}/widgets/jquery-select2/css/select2.css" rel="stylesheet" />
<script src="${contextPath }/widgets/jquery-select2/js/select2.min.js"></script>
		
		<script src="${contextPath}/theme/default/js/common.js"></script>
		<script src="${contextPath}/theme/default/js/i18n/messages_zh_CN.js"></script>		
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
