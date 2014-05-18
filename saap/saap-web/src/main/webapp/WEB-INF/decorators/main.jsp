<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.inc"%>
<%@ include file="/common/taglibs.inc"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<html lang="en">
  <head>	
  	<%@ include file="/common/meta.inc"%> 
  	<title><c:if test="${empty SESSION_USER.tenant }" >SaaP</c:if><c:if test="${not empty SESSION_USER.tenant }">${SESSION_USER.tenant.tenantAlias }</c:if></title>	
	<decorator:head />	
	</head>
	<body>	
<header id="header">
    <!--[if lte IE 9]>
    <div id="warning_info" class="text-warning fade in mb_0">
        <button data-dismiss="alert" class="close" type="button">×</button>
        <strong>您正在使用低版本浏览器，</strong> 在本页面的显示效果可能有差异。
        建议您升级到
        <a href="http://www.google.cn/intl/zh-CN/chrome/" target="_blank">Chrome</a>
        或以下浏览器：
        <a href="www.mozilla.org/en-US/firefox/‎" target="_blank">Firefox</a> /
        <a href="http://www.apple.com.cn/safari/" target="_blank">Safari</a> /
        <a href="http://www.opera.com/" target="_blank">Opera</a> /
        <a href="http://windows.microsoft.com/en-us/internet-explorer/download-ie" target="_blank">
            Internet Explorer 10</a>
    </div>
    <![endif]-->
</header>			
		<!-- Common -->
		<div class="navbar navbar-static-top">
		  <div class="navbar-inner">
	        <div class="container-fluid">
	        	<!-- .btn-navbar is used as the toggle for collapsed navbar content -->			    
	          <a class="brand" href="${contextPath }"><c:if test="${empty SESSION_USER.tenant }" >SaaP</c:if><c:if test="${not empty SESSION_USER.tenant }">${SESSION_USER.tenant.tenantAlias }</c:if></a>	            
	          </div><!--/.nav-collapse -->
	        </div>
	      </div>
	    </div>		
		<div class="container-fluid" style="margin-top: 15px;">		  
		  	<div class="row-fluid">
				<!-- 内容区 -->
		    	<decorator:body />	 
			</div>		 
		</div>
	</body>
</html>