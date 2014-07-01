<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.jspf"%>
<%@ include file="/common/taglibs.jspf"%>
<html lang="en">
<head>
  <meta name="decorator" content="popup" />  
</head>
<body>
  <div><h1>${li:out(affiche.title) }</h1></div>
  <div style="border-bottom:1px dashed #B299A5;margin-bottom: 8px;">
    <div class="pull-right">
      <small class="text-info"><i class="icon-calendar"></i> ${li:formatDateTime(affiche.updateDatetime) }</small>
    </div>
    <div class="clear"></div>
  </div>
  <div>
      ${affiche.content }
  </div>

    <div class="center">
      <button type="button" class="btn" onclick="window.opener=null;window.open('','_self');window.close();"> <s:message code="btn.close" /></button>
    </div>

</body>
</html>