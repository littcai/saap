<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.inc"%>
<%@ include file="/common/taglibs.inc"%>
<html lang="en">
	<head>
	</head>
	<body>	
		<a href="${contextPath }/login/activateTenant.do?orderNo=123456&userId=${SESSION_USER.opId}" target="_blank">开通Basic</a> | 
		<a href="${contextPath }/login/upgradeTenantPermission.do" target="_blank">升级配置</a> | 
		<a href="${contextPath }/login/deactivateTenant.do" target="_blank">注销该工作空间</a> | 
		<a href="${contextPath }/login/quiteTenant.do?userId=${SESSION_USER.opId}" target="_blank">退出该工作空间</a>
	</body>
</html>
