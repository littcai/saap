<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.jspf"%>
<%@ include file="/common/taglibs.jspf"%>
<html lang="en">
  <head>		
  </head>
  <body>   
  <form id="theform" action="update.json" method="post" class="form-horizontal">
    <input type="hidden" name="id" value="${tenantOrder.id}" />
    <fieldset>
      <legend><s:message code="tenantOrder.ui.fieldset.base" /></legend>
	<div class="row-fluid">
          <div class="span6">
            <div class="control-group">
	      <label class="control-label" for="tenantOrder.id"><s:message code="tenantOrder.id" /></label>
	      <div class="controls">
	        <input id="tenantOrder.id" name="id" placeholder="" type="text" value="<c:out values='${tenantOrder.id}' />" />
	    </div>
	  </div>
	</div>								
          <div class="span6">
            <div class="control-group">
	      <label class="control-label" for="tenantOrder.orderNo"><s:message code="tenantOrder.orderNo" /></label>
	      <div class="controls">
	        <input id="tenantOrder.orderNo" name="orderNo" placeholder="" type="text" value="<c:out values='${tenantOrder.orderNo}' />" />
	    </div>
	  </div>
	</div>								
	</div>
	<div class="row-fluid">
          <div class="span6">
            <div class="control-group">
	      <label class="control-label" for="tenantOrder.orderType"><s:message code="tenantOrder.orderType" /></label>
	      <div class="controls">
	        <input id="tenantOrder.orderType" name="orderType" placeholder="" type="text" value="<c:out values='${tenantOrder.orderType}' />" />
	    </div>
	  </div>
	</div>								
          <div class="span6">
            <div class="control-group">
	      <label class="control-label" for="tenantOrder.tenantId"><s:message code="tenantOrder.tenantId" /></label>
	      <div class="controls">
	        <input id="tenantOrder.tenantId" name="tenantId" placeholder="" type="text" value="<c:out values='${tenantOrder.tenantId}' />" />
	    </div>
	  </div>
	</div>								
	</div>
	<div class="row-fluid">
          <div class="span6">
            <div class="control-group">
	      <label class="control-label" for="tenantOrder.tenantCode"><s:message code="tenantOrder.tenantCode" /></label>
	      <div class="controls">
	        <input id="tenantOrder.tenantCode" name="tenantCode" placeholder="" type="text" value="<c:out values='${tenantOrder.tenantCode}' />" />
	    </div>
	  </div>
	</div>								
          <div class="span6">
            <div class="control-group">
	      <label class="control-label" for="tenantOrder.tenantAlias"><s:message code="tenantOrder.tenantAlias" /></label>
	      <div class="controls">
	        <input id="tenantOrder.tenantAlias" name="tenantAlias" placeholder="" type="text" value="<c:out values='${tenantOrder.tenantAlias}' />" />
	    </div>
	  </div>
	</div>								
	</div>
	<div class="row-fluid">
          <div class="span6">
            <div class="control-group">
	      <label class="control-label" for="tenantOrder.bagCode"><s:message code="tenantOrder.bagCode" /></label>
	      <div class="controls">
	        <input id="tenantOrder.bagCode" name="bagCode" placeholder="" type="text" value="<c:out values='${tenantOrder.bagCode}' />" />
	    </div>
	  </div>
	</div>								
          <div class="span6">
            <div class="control-group">
	      <label class="control-label" for="tenantOrder.isolatedMode"><s:message code="tenantOrder.isolatedMode" /></label>
	      <div class="controls">
	        <input id="tenantOrder.isolatedMode" name="isolatedMode" placeholder="" type="text" value="<c:out values='${tenantOrder.isolatedMode}' />" />
	    </div>
	  </div>
	</div>								
	</div>
	<div class="row-fluid">
          <div class="span6">
            <div class="control-group">
	      <label class="control-label" for="tenantOrder.price"><s:message code="tenantOrder.price" /></label>
	      <div class="controls">
	        <input id="tenantOrder.price" name="price" placeholder="" type="text" value="<c:out values='${tenantOrder.price}' />" />
	    </div>
	  </div>
	</div>								
          <div class="span6">
            <div class="control-group">
	      <label class="control-label" for="tenantOrder.quantity"><s:message code="tenantOrder.quantity" /></label>
	      <div class="controls">
	        <input id="tenantOrder.quantity" name="quantity" placeholder="" type="text" value="<c:out values='${tenantOrder.quantity}' />" />
	    </div>
	  </div>
	</div>								
	</div>
	<div class="row-fluid">
          <div class="span6">
            <div class="control-group">
	      <label class="control-label" for="tenantOrder.status"><s:message code="tenantOrder.status" /></label>
	      <div class="controls">
	        <input id="tenantOrder.status" name="status" placeholder="" type="text" value="<c:out values='${tenantOrder.status}' />" />
	    </div>
	  </div>
	</div>								
          <div class="span6">
            <div class="control-group">
	      <label class="control-label" for="tenantOrder.createBy"><s:message code="tenantOrder.createBy" /></label>
	      <div class="controls">
	        <input id="tenantOrder.createBy" name="createBy" placeholder="" type="text" value="<c:out values='${tenantOrder.createBy}' />" />
	    </div>
	  </div>
	</div>								
	</div>
	<div class="row-fluid">
          <div class="span6">
            <div class="control-group">
	      <label class="control-label" for="tenantOrder.createDatetime"><s:message code="tenantOrder.createDatetime" /></label>
	      <div class="controls">
	        <input id="tenantOrder.createDatetime" name="createDatetime" placeholder="" type="text" value="<c:out values='${tenantOrder.createDatetime}' />" />
	    </div>
	  </div>
	</div>								
          <div class="span6">
            <div class="control-group">
	      <label class="control-label" for="tenantOrder.payChannel"><s:message code="tenantOrder.payChannel" /></label>
	      <div class="controls">
	        <input id="tenantOrder.payChannel" name="payChannel" placeholder="" type="text" value="<c:out values='${tenantOrder.payChannel}' />" />
	    </div>
	  </div>
	</div>								
	</div>
	<div class="row-fluid">
          <div class="span6">
            <div class="control-group">
	      <label class="control-label" for="tenantOrder.payDatetime"><s:message code="tenantOrder.payDatetime" /></label>
	      <div class="controls">
	        <input id="tenantOrder.payDatetime" name="payDatetime" placeholder="" type="text" value="<c:out values='${tenantOrder.payDatetime}' />" />
	    </div>
	  </div>
	</div>								
          <div class="span6">
            <div class="control-group">
	      <label class="control-label" for="tenantOrder.activateDatetime"><s:message code="tenantOrder.activateDatetime" /></label>
	      <div class="controls">
	        <input id="tenantOrder.activateDatetime" name="activateDatetime" placeholder="" type="text" value="<c:out values='${tenantOrder.activateDatetime}' />" />
	    </div>
	  </div>
	</div>								
	</div>
    </fieldset>					
						
    <div class="form-actions">
      <button type="submit" class="btn btn-primary"><i class="icon-ok"></i> <s:message code="btn.save" /></button>
      <button type="button" class="btn" onclick="history.back();"><s:message code="btn.cancel" /></button>
    </div>					
				
  </form>				
  <!--page specific plugin scripts-->				
  <script type="text/javascript">
  $(document).ready(function(){	
			
    $('#theform').littFormSubmit({
      rules : {
        name : {
          required : true
	}
      },			
      success: function(reply){
        location.href = <h:returnUrl value="index.do"></h:returnUrl>;					
      }
    });
  });
  </script>	  
  </body>	
</html>