<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.jspf"%>
<%@ include file="/common/taglibs.jspf"%>
<html lang="en">
  <head>		
  </head>
  <body>   
  <form id="theform" action="show.do" method="post" class="form-horizontal">
  	<input type="hidden" name="id" value="${custEvent.id}" />
				<fieldset>
					<legend><s:message code="custEvent.ui.fieldset.base" /></legend>
				</fieldset>					
						
				<div class="form-actions">					
					<button type="button" class="btn" onclick="history.back();"><s:message code="btn.cancel" /></button>
				</div>					
				
			</form>			
			  
  </body>	
</html>