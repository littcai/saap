<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.jspf"%>
<%@ include file="/common/taglibs.jspf"%>
<html lang="en">
  <head>		
  </head>
  <body>   
  <form id="theform" action="save.json" method="post" class="form-horizontal">
    <fieldset>
      <legend><s:message code="common.ui.fieldset.base" /></legend>
            <div class="row-fluid">
      <div class="span6">
        <div class="control-group">
	  <label class="control-label" for="tenantOrder.id"><s:message code="tenantOrder.id" /></label>
	  <div class="controls">
	    <input id="tenantOrder.id" name="id" placeholder="" type="text" />
	  </div>
	</div>
      </div>								
      <div class="span6">
        <div class="control-group">
	  <label class="control-label" for="tenantOrder.orderNo"><s:message code="tenantOrder.orderNo" /></label>
	  <div class="controls">
	    <input id="tenantOrder.orderNo" name="orderNo" placeholder="" type="text" />
	  </div>
	</div>
      </div>								
      </div>
      <div class="row-fluid">
      <div class="span6">
        <div class="control-group">
	  <label class="control-label" for="tenantOrder.orderType"><s:message code="tenantOrder.orderType" /></label>
	  <div class="controls">
	    <input id="tenantOrder.orderType" name="orderType" placeholder="" type="text" />
	  </div>
	</div>
      </div>								
      <div class="span6">
        <div class="control-group">
	  <label class="control-label" for="tenantOrder.tenantId"><s:message code="tenantOrder.tenantId" /></label>
	  <div class="controls">
	    <input id="tenantOrder.tenantId" name="tenantId" placeholder="" type="text" />
	  </div>
	</div>
      </div>								
      </div>
      <div class="row-fluid">
      <div class="span6">
        <div class="control-group">
	  <label class="control-label" for="tenantOrder.tenantCode"><s:message code="tenantOrder.tenantCode" /></label>
	  <div class="controls">
	    <input id="tenantOrder.tenantCode" name="tenantCode" placeholder="" type="text" />
	  </div>
	</div>
      </div>								
      <div class="span6">
        <div class="control-group">
	  <label class="control-label" for="tenantOrder.tenantAlias"><s:message code="tenantOrder.tenantAlias" /></label>
	  <div class="controls">
	    <input id="tenantOrder.tenantAlias" name="tenantAlias" placeholder="" type="text" />
	  </div>
	</div>
      </div>								
      </div>
      <div class="row-fluid">
      <div class="span6">
        <div class="control-group">
	  <label class="control-label" for="tenantOrder.bagCode"><s:message code="tenantOrder.bagCode" /></label>
	  <div class="controls">
	    <input id="tenantOrder.bagCode" name="bagCode" placeholder="" type="text" />
	  </div>
	</div>
      </div>								
      <div class="span6">
        <div class="control-group">
	  <label class="control-label" for="tenantOrder.isolatedMode"><s:message code="tenantOrder.isolatedMode" /></label>
	  <div class="controls">
	    <input id="tenantOrder.isolatedMode" name="isolatedMode" placeholder="" type="text" />
	  </div>
	</div>
      </div>								
      </div>
      <div class="row-fluid">
      <div class="span6">
        <div class="control-group">
	  <label class="control-label" for="tenantOrder.price"><s:message code="tenantOrder.price" /></label>
	  <div class="controls">
	    <input id="tenantOrder.price" name="price" placeholder="" type="text" />
	  </div>
	</div>
      </div>								
      <div class="span6">
        <div class="control-group">
	  <label class="control-label" for="tenantOrder.quantity"><s:message code="tenantOrder.quantity" /></label>
	  <div class="controls">
	    <input id="tenantOrder.quantity" name="quantity" placeholder="" type="text" />
	  </div>
	</div>
      </div>								
      </div>
      <div class="row-fluid">
      <div class="span6">
        <div class="control-group">
	  <label class="control-label" for="tenantOrder.status"><s:message code="tenantOrder.status" /></label>
	  <div class="controls">
	    <input id="tenantOrder.status" name="status" placeholder="" type="text" />
	  </div>
	</div>
      </div>								
      <div class="span6">
        <div class="control-group">
	  <label class="control-label" for="tenantOrder.createBy"><s:message code="tenantOrder.createBy" /></label>
	  <div class="controls">
	    <input id="tenantOrder.createBy" name="createBy" placeholder="" type="text" />
	  </div>
	</div>
      </div>								
      </div>
      <div class="row-fluid">
      <div class="span6">
        <div class="control-group">
	  <label class="control-label" for="tenantOrder.createDatetime"><s:message code="tenantOrder.createDatetime" /></label>
	  <div class="controls">
	    <input id="tenantOrder.createDatetime" name="createDatetime" placeholder="" type="text" />
	  </div>
	</div>
      </div>								
      <div class="span6">
        <div class="control-group">
	  <label class="control-label" for="tenantOrder.payChannel"><s:message code="tenantOrder.payChannel" /></label>
	  <div class="controls">
	    <input id="tenantOrder.payChannel" name="payChannel" placeholder="" type="text" />
	  </div>
	</div>
      </div>								
      </div>
      <div class="row-fluid">
      <div class="span6">
        <div class="control-group">
	  <label class="control-label" for="tenantOrder.payDatetime"><s:message code="tenantOrder.payDatetime" /></label>
	  <div class="controls">
	    <input id="tenantOrder.payDatetime" name="payDatetime" placeholder="" type="text" />
	  </div>
	</div>
      </div>								
      <div class="span6">
        <div class="control-group">
	  <label class="control-label" for="tenantOrder.activateDatetime"><s:message code="tenantOrder.activateDatetime" /></label>
	  <div class="controls">
	    <input id="tenantOrder.activateDatetime" name="activateDatetime" placeholder="" type="text" />
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