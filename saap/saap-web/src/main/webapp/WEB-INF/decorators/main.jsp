<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.inc"%>
<%@ include file="/common/taglibs.inc"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<html lang="en">
  <head>	
	<decorator:head />
	<style type="text/css">
	body {
		padding-top: 60px;
	}
	</style>	
	</head>
	<body>			
		<!-- Common -->
		<div class="navbar navbar-fixed-top">
		  <div class="navbar-inner">
	        <div class="container-fluid">
	        	<!-- .btn-navbar is used as the toggle for collapsed navbar content -->
			    <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
			      <span class="icon-bar"></span>
			      <span class="icon-bar"></span>
			      <span class="icon-bar"></span>
			    </a>	                 
	          <a class="brand" href="#">Saap</a>
	          <div class="nav-collapse collapse">	            
	            <ul class="nav nav-pills">
	              <li class="active"><a href="#"><i class="icon-home"></i><s:message code="main.ui.nav.home" /></a></li>
	              <li class="dropdown">
				    <a class="dropdown-toggle" data-toggle="dropdown" data-target="#" href="#">
				        Personal
				        <b class="caret"></b>
				      </a>
				    <ul class="dropdown-menu">
				      	<li ><a tabindex="-1" href="http://google.com">Action</a></li>
                        <li ><a tabindex="-1" href="#anotherAction">Another action</a></li>
                        <li ><a tabindex="-1" href="#">Something else here</a></li>
                        <li class="divider"></li>
                        <li><a tabindex="-1" href="#">Separated link</a></li>
				    </ul>
				  </li>
	              <li><a href="">Customer</a></li>
	              <li><a href="#contact">Product</a></li>
	            </ul>
	            <ul class="pull-right nav">
	            	<li ><a href="shortMessage.do"><i class="cus-phone"></i>手机短信</a></li>
	            	<li ><a href="profile.do"><i class="cus-cog"></i>&nbsp;个人设置</a></li>
					<li ><a href="logout.do"><i class="icon-off"></i>&nbsp;退出</a></li>
					<li ><a href="help.html" target="_blank"><i class="icon-question-sign"></i>&nbsp;帮助</a></li>
	            </ul>
	          </div><!--/.nav-collapse -->
	        </div>
	      </div>
	    </div>		
	    <!-- 内容区 -->
	    <decorator:body />	 	    
	</body>
</html>
