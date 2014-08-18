<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.jspf"%>
<%@ include file="/common/taglibs.jspf"%>
<html>
  <head>
  </head>
  <body>	
    <form id="orderForm" action="saveOrder.json" method="post" class="form-horizontal">
      <input type="hidden" id="tenantTypeCode" name="tenantTypeCode" value="${tenantDef.code }">
    <fieldset>
      <legend><s:message code="tenantOrder.ui.fieldset.baseInfo"></s:message></legend>
    <div class="row-fluid">
      <div class="control-group">
              <label class="control-label" for="tenantCode"><s:message code="tenantOrder.tenantCode"></s:message></label>
                <div class="controls">
                  <input type="text" id="tenantCode" name="tenantCode">
                  <span class="help-block"><s:message code="tenantOrder.tenantCode.help"></s:message></span>
                </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="tenantAlias"><s:message code="tenantOrder.tenantAlias"></s:message></label>
                <div class="controls">
                  <input type="text" id="tenantAlias" name="tenantAlias">
                  <span class="help-block"><s:message code="tenantOrder.tenantAlias.help"></s:message></span>
                </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="price"><s:message code="tenantOrder.price"></s:message></label>
                <div class="controls">
                  <input type="text" id="price" name="price" value="${tenantDef.price }" readonly="readonly">
                </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="quantity"><s:message code="tenantOrder.quantity"></s:message></label>
                <div class="controls">
                	<div class="input-prepend input-append">
  						<button class="btn" type="button" onclick="minusQuantity();">-</button>
					    <input type="text" id="quantity" name="quantity" value="3" class="span6">
						<button class="btn" type="button" onclick="plusQuantity();">+</button>
						<span class="help-inline"><s:message code="tenantOrder.quantity.help"></s:message></span>
				   </div>
                </div>
            </div>
            <div class="control-group">
			  <label class="control-label" for="remark"><s:message code="common.field.remark" /></label>
			<div class="controls">
			  <textarea rows="3" cols="8" id="remark" name="remark" class="span8"></textarea>
			</div>
			 </div>
    </div>
    </fieldset>
    
    <fieldset>
    	<legend><s:message code="tenantOrder.ui.fieldset.productInfo"></s:message></legend>
    	<div class="row-fluid">
    		<table class="table table-hover table-condensed">
    			<col width="10%"/>
    			<col width="50%"/>
    			<thead>
    				<tr>
    				  <th><s:message code="tenantType.code"></s:message></th>
    				  <th><s:message code="tenantType.descr"></s:message></th>
    				  <th><s:message code="tenantType.items"></s:message></th>
    				</tr>
    			</thead>
    			<tbody>
    				<tr>
    					<td>${tenantDef.code }</td>
    					<td><s:message code="tenantType.descr.${tenantDef.code }"></s:message></td>
    					<td>
    						<ul class="unstyled">
					            <li><i class="icon-chevron-right"></i><s:message code="tenantType.maxMembers"></s:message>:&nbsp;<b><c:choose>
					                  <c:when test="${tenantDef.maxMembers<=0 }"><s:message code="tenantType.ultimate"></s:message></c:when>
					                  <c:otherwise>${tenantDef.maxMembers }</c:otherwise>
					                  </c:choose></b>
					            </li>
					            <li><i class="icon-chevron-right"></i><s:message code="tenantType.maxStorage"></s:message>:&nbsp;<b><c:choose>
					                  <c:when test="${tenantDef.maxStorage<=0 }"><s:message code="tenantType.ultimate"></s:message></c:when>
					                  <c:otherwise>${tenantDef.maxStorage }</c:otherwise>
					                  </c:choose></b>
					            </li>
					            <li><i class="icon-chevron-right"></i><s:message code="tenantType.modules"></s:message>:&nbsp;<b><c:choose>
					                  <c:when test="${tenantDef.modules<=0 }"><s:message code="tenantType.ultimate"></s:message></c:when>
					                  <c:otherwise>${tenantDef.modules }</c:otherwise>
					                  </c:choose></b>
					            </li>
					            <li><i class="icon-chevron-right"></i><s:message code="tenantType.support"></s:message>:&nbsp;<b>${tenantDef.support }</b></li>
					          </ul>
    					</td>
    				</tr>
    			</tbody>
    		</table>
    	</div>
    </fieldset>	
    
      <div class="form-actions">
		<button type="submit" class="btn btn-primary" data-loading-text="<s:message code='common.processing' />"><i class="icon-ok"></i> <s:message code="btn.submit" /></button>
		<button type="button" class="btn" onclick="history.back();"><s:message code="btn.cancel" /></button>
	  </div>
    </form>
    <!-- inline scripts related to this page -->
    <script type="text/javascript">
      $(document).ready(function(){        
    	  $('#orderForm').littFormSubmit({
				rules : {
					tenantCode : {
						required : true,
						minlength : 4,
						maxlength: 50
					},	
					tenantAlias : {
						required : true,
						minlength : 4,
						maxlength: 50
					},	
					quantity: {
						required: true,
						digits: true,
						min: 1
					}
				},		
				success: function(reply){
					 //goto paychannel
				}
			});	
      });
      
      function minusQuantity()
      {
    	  var quantity = $("#quantity").val();
    	  if(!$.isNumeric(quantity))
    		  quantity = "3";
    	  var value = parseInt(quantity);
    	  value = value - 1;
    	  $("#quantity").val(value>0?value:1);
      }
      
      function plusQuantity()
      {
    	  var quantity = $("#quantity").val();
    	  if(!$.isNumeric(quantity))
    		  quantity = "3";
    	  var value = parseInt(quantity);
    	  value = value + 1;
    	  $("#quantity").val(value>0?value:1);
      }
    </script>
  </body>
</html>
