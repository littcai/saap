<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.jspf"%>
<%@ include file="/common/taglibs.jspf"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<html lang="en">
<head>
<%@ include file="/common/meta.jspf"%>
<title><c:if test="${empty SESSION_USER.tenant }" >SaaP</c:if><c:if test="${not empty SESSION_USER.tenant }">${SESSION_USER.tenant.tenantAlias }</c:if></title>
<decorator:head />
</head>
<body>
  <div class="container-fluid">
    <decorator:body />
  </div>
</body>
</html>
