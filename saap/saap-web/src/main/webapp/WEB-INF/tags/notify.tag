<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.inc"%>
<%@ attribute name="content" type="java.lang.String" required="true" description="消息内容"%>
<%@ attribute name="type" type="java.lang.String" description="消息类型：info、success、warning、error、loading"%>
<c:if test="${not empty content}">
	<c:if test="${not empty type}"><c:set var="ctype" value="${empty type?'info': type}"/></c:if>
	<div id="messageBox" class="alert alert-${ctype} fade in"><button type="button" data-dismiss="alert" class="close">&times;</button>${content}</div> 
<script type="text/javascript">
$(document).ready(function(){	 
	//var alert = $("#messageBox").alert();	
	window.setTimeout(function() { $("#messageBox").alert('close') }, 10000);
	//display notify
	$.pnotify({
	    title: "${content}",
	    type: '${ctype}',
	    history: false,
	    sticker: false,
	    delay: 3000
	});	
	
});
</script>
</c:if>
