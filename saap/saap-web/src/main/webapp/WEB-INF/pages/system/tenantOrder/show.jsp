<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.jspf"%>
<%@ include file="/common/taglibs.jspf"%>
<html lang="en">
  <head>		
  </head>
  <body>   
  <form id="theform" action="show.do" method="post" class="form-horizontal">
    <input type="hidden" name="id" value="${tenantOrder.id}" />
    <fieldset>
      <legend><s:message code="tenantOrder.ui.fieldset.base" /></legend>
      <div class="row-fluid">
        <div class="span6">
	  <div class="control-group">
	    <label class="control-label" for="tenantOrder.id"><s:message code="tenantOrder.id" /></label>
	    <div class="controls">
	      <input id="tenantOrder.id" name="id" type="text" value="<c:out values='${tenantOrder.id}' />" readonly="readonly" />
	    </div>
	  </div>
	</div>								
        <div class="span6">
	  <div class="control-group">
	    <label class="control-label" for="tenantOrder.orderNo"><s:message code="tenantOrder.orderNo" /></label>
	    <div class="controls">
	      <input id="tenantOrder.orderNo" name="orderNo" type="text" value="<c:out values='${tenantOrder.orderNo}' />" readonly="readonly" />
	    </div>
	  </div>
	</div>								
      </div>
      <div class="row-fluid">
        <div class="span6">
	  <div class="control-group">
	    <label class="control-label" for="tenantOrder.orderType"><s:message code="tenantOrder.orderType" /></label>
	    <div class="controls">
	      <input id="tenantOrder.orderType" name="orderType" type="text" value="<c:out values='${tenantOrder.orderType}' />" readonly="readonly" />
	    </div>
	  </div>
	</div>								
        <div class="span6">
	  <div class="control-group">
	    <label class="control-label" for="tenantOrder.tenantId"><s:message code="tenantOrder.tenantId" /></label>
	    <div class="controls">
	      <input id="tenantOrder.tenantId" name="tenantId" type="text" value="<c:out values='${tenantOrder.tenantId}' />" readonly="readonly" />
	    </div>
	  </div>
	</div>								
      </div>
      <div class="row-fluid">
        <div class="span6">
	  <div class="control-group">
	    <label class="control-label" for="tenantOrder.tenantCode"><s:message code="tenantOrder.tenantCode" /></label>
	    <div class="controls">
	      <input id="tenantOrder.tenantCode" name="tenantCode" type="text" value="<c:out values='${tenantOrder.tenantCode}' />" readonly="readonly" />
	    </div>
	  </div>
	</div>								
        <div class="span6">
	  <div class="control-group">
	    <label class="control-label" for="tenantOrder.tenantAlias"><s:message code="tenantOrder.tenantAlias" /></label>
	    <div class="controls">
	      <input id="tenantOrder.tenantAlias" name="tenantAlias" type="text" value="<c:out values='${tenantOrder.tenantAlias}' />" readonly="readonly" />
	    </div>
	  </div>
	</div>								
      </div>
      <div class="row-fluid">
        <div class="span6">
	  <div class="control-group">
	    <label class="control-label" for="tenantOrder.bagCode"><s:message code="tenantOrder.bagCode" /></label>
	    <div class="controls">
	      <input id="tenantOrder.bagCode" name="bagCode" type="text" value="<c:out values='${tenantOrder.bagCode}' />" readonly="readonly" />
	    </div>
	  </div>
	</div>								
        <div class="span6">
	  <div class="control-group">
	    <label class="control-label" for="tenantOrder.isolatedMode"><s:message code="tenantOrder.isolatedMode" /></label>
	    <div class="controls">
	      <input id="tenantOrder.isolatedMode" name="isolatedMode" type="text" value="<c:out values='${tenantOrder.isolatedMode}' />" readonly="readonly" />
	    </div>
	  </div>
	</div>								
      </div>
      <div class="row-fluid">
        <div class="span6">
	  <div class="control-group">
	    <label class="control-label" for="tenantOrder.price"><s:message code="tenantOrder.price" /></label>
	    <div class="controls">
	      <input id="tenantOrder.price" name="price" type="text" value="<c:out values='${tenantOrder.price}' />" readonly="readonly" />
	    </div>
	  </div>
	</div>								
        <div class="span6">
	  <div class="control-group">
	    <label class="control-label" for="tenantOrder.quantity"><s:message code="tenantOrder.quantity" /></label>
	    <div class="controls">
	      <input id="tenantOrder.quantity" name="quantity" type="text" value="<c:out values='${tenantOrder.quantity}' />" readonly="readonly" />
	    </div>
	  </div>
	</div>								
      </div>
      <div class="row-fluid">
        <div class="span6">
	  <div class="control-group">
	    <label class="control-label" for="tenantOrder.status"><s:message code="tenantOrder.status" /></label>
	    <div class="controls">
	      <input id="tenantOrder.status" name="status" type="text" value="<c:out values='${tenantOrder.status}' />" readonly="readonly" />
	    </div>
	  </div>
	</div>								
        <div class="span6">
	  <div class="control-group">
	    <label class="control-label" for="tenantOrder.createBy"><s:message code="tenantOrder.createBy" /></label>
	    <div class="controls">
	      <input id="tenantOrder.createBy" name="createBy" type="text" value="<c:out values='${tenantOrder.createBy}' />" readonly="readonly" />
	    </div>
	  </div>
	</div>								
      </div>
      <div class="row-fluid">
        <div class="span6">
	  <div class="control-group">
	    <label class="control-label" for="tenantOrder.createDatetime"><s:message code="tenantOrder.createDatetime" /></label>
	    <div class="controls">
	      <input id="tenantOrder.createDatetime" name="createDatetime" type="text" value="<c:out values='${tenantOrder.createDatetime}' />" readonly="readonly" />
	    </div>
	  </div>
	</div>								
        <div class="span6">
	  <div class="control-group">
	    <label class="control-label" for="tenantOrder.payChannel"><s:message code="tenantOrder.payChannel" /></label>
	    <div class="controls">
	      <input id="tenantOrder.payChannel" name="payChannel" type="text" value="<c:out values='${tenantOrder.payChannel}' />" readonly="readonly" />
	    </div>
	  </div>
	</div>								
      </div>
      <div class="row-fluid">
        <div class="span6">
	  <div class="control-group">
	    <label class="control-label" for="tenantOrder.payDatetime"><s:message code="tenantOrder.payDatetime" /></label>
	    <div class="controls">
	      <input id="tenantOrder.payDatetime" name="payDatetime" type="text" value="<c:out values='${tenantOrder.payDatetime}' />" readonly="readonly" />
	    </div>
	  </div>
	</div>								
        <div class="span6">
	  <div class="control-group">
	    <label class="control-label" for="tenantOrder.activateDatetime"><s:message code="tenantOrder.activateDatetime" /></label>
	    <div class="controls">
	      <input id="tenantOrder.activateDatetime" name="activateDatetime" type="text" value="<c:out values='${tenantOrder.activateDatetime}' />" readonly="readonly" />
	    </div>
	  </div>
	</div>								
      </div>
    </fieldset>					
						
    <div class="form-actions">					
      <button type="button" class="btn" onclick="history.back();"><s:message code="btn.cancel" /></button>
    </div>					
				
  </form>		  
  </body>	
</html>