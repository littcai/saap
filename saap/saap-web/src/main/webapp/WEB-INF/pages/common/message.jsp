<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.jspf"%>
<%@ include file="/common/taglibs.jspf"%>
<html lang="en">
	<head>
		<title><s:message code="login.ui.title" /></title>
		<meta name="decorator" content="none" />
		<!-- 
		<meta http-equiv="refresh" content="5;url=${contextPath }/${redirectUrl }" />		
		-->
	</head>
	<body> 		
<div class="navbar navbar-fixed-top">	
	<div class="navbar-inner">
		<div class="container">
			<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span> 				
			</a>			
			<ul class="nav pull-right">
				<li class="" style="line-height: 30px;">
					<a href="" target="_blank">SaaP</a>
				</li>
			</ul>
		</div> <!-- /container -->
	</div> <!-- /navbar-inner -->
</div> <!-- /navbar -->
<div class="container" style="margin: 120px auto 0;">	
	<div class="row">
	  <div class="span10 offset1">
	  	<div class="widget-box">
	  		<div class="widget-header header-color-blue">
	        	<h5 class="bigger lighter"><s:message code="common.message.info" /></h5>
	        </div>
	        <div class="widget-body">
	        	<div class="widget-main">
			        <p>${message }</p>
			        <p class="text-center"><b id="lblTimeLeft">5</b>&nbsp;秒后自动跳转&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="${contextPath }/${redirectUrl }">[立即跳转]</a></p>
	        	</div>
	        </div>
	    </div>    
      </div> 
    </div>    
</div>
<!-- inline scripts related to this page -->
<script type="text/javascript">
var timeLeft = 5;

function redirectPage()
{
	$("#lblTimeLeft").text(timeLeft);		
	if(timeLeft==0)
	{
		location.href = "${contextPath }/${redirectUrl }";
		return;
	}	
	timeLeft -= 1;
	setTimeout(redirectPage, 1000);	
}

$(document).ready(function(){		
	setTimeout(redirectPage, 1000);	
});	
</script>
</body>
</html>
