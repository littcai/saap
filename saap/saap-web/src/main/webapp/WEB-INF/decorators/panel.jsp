<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.inc"%>
<%@ include file="/common/taglibs.inc"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<html lang="en">
  <head>
	<%@ include file="/common/meta.inc"%>
	<meta charset="utf-8" />	
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<!--basic styles-->

	<link href="${contextPath}/<s:theme code='path'/>/css/bootstrap.min.css" rel="stylesheet" />
	<link href="${contextPath}/<s:theme code='path'/>/css/bootstrap-responsive.min.css" rel="stylesheet" />
	<link rel="stylesheet" href="${contextPath}/<s:theme code='path'/>/css/font-awesome.min.css" />

	<!--[if IE 7]>
	  <link rel="stylesheet" href="${contextPath}/<s:theme code='path'/>/css/font-awesome-ie7.min.css" />
	<![endif]-->

	<!--page specific plugin styles-->
	<!--fonts-->

	<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />

	<!--ace styles-->

	<link rel="stylesheet" href="${contextPath}/<s:theme code='path'/>/css/ace.min.css" />
	<link rel="stylesheet" href="${contextPath}/<s:theme code='path'/>/css/ace-responsive.min.css" />
	<link rel="stylesheet" href="${contextPath}/<s:theme code='path'/>/css/ace-skins.min.css" />

	<!--[if lte IE 8]>
	  <link rel="stylesheet" href="${contextPath}/<s:theme code='path'/>/css/ace-ie.min.css" />
	<![endif]-->

	<!--inline styles related to this page-->

	<!--ace settings handler-->

	<script src="${contextPath}/<s:theme code='path'/>/js/ace-extra.min.js"></script>	
	<decorator:head />	
	</head>
	<body>			
	    <!-- 内容区 -->	    
	    <decorator:body  />
	    <!--basic scripts-->

		<!--[if !IE]>-->

		<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>

		<!--<![endif]-->

		<!--[if IE]>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
		<![endif]-->

		<!--[if !IE]>-->

		<script type="text/javascript">
			window.jQuery || document.write("<script src='${contextPath}/<s:theme code='path'/>/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
		</script>

		<!--<![endif]-->

		<!--[if IE]>
		<script type="text/javascript">
		 window.jQuery || document.write("<script src='${contextPath}/<s:theme code='path'/>/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
		</script>
		<![endif]-->

		<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='${contextPath}/<s:theme code='path'/>/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="${contextPath}/<s:theme code='path'/>/js/bootstrap.min.js"></script>

		<!--page specific plugin scripts-->

		<!--ace scripts-->

		<script src="${contextPath}/<s:theme code='path'/>/js/ace-elements.min.js"></script>
		<script src="${contextPath}/<s:theme code='path'/>/js/ace.min.js"></script>		  
	</body>
</html>
