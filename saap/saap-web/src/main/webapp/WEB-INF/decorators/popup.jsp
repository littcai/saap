<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.jspf"%>
<%@ include file="/common/taglibs.jspf"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<title><decorator:title default="${SYSTEM_TITLE}" /></title>
	<%@ include file="/common/meta.jspf"%>	
	<!-- 加载表格组件 -->
	<link rel="stylesheet" type="text/css" href="${contextPath }/widgets/jqgrid/css/ui.jqgrid.css"/>
	<script type="text/javascript" src="${contextPath }/widgets/jqgrid/scripts/i18n/grid.locale-cn.js"></script>	
	<script type="text/javascript" src="${contextPath }/widgets/jqgrid/scripts/jquery.jqGrid.min.js"></script>
	<decorator:head />	
	</head>
	<body class="login-layout">
	    <!-- 内容区 -->
	    <decorator:body />	    
	</body>
</html>
