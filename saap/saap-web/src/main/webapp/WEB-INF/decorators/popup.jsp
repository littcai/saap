<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.jspf"%>
<%@ include file="/common/taglibs.jspf"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<html lang="en">
<head>
<title><decorator:title default="${SYSTEM_TITLE}" /></title>
<%@ include file="/common/meta.jspf"%>
<decorator:head />
</head>
<body>
  <div class="container-fluid">
    <decorator:body />
  </div>
</body>
</html>
