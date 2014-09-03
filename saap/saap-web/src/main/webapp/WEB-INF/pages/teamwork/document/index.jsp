<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.jspf"%>
<%@ include file="/common/taglibs.jspf"%>
<html lang="en">
  <head>	
    <!-- jquery file upload-8.7.1 -->
    <link href="${contextPath}/static/widgets/jquery-fileupload-8.7.1/css/jquery.fileupload-ui.css" rel="stylesheet" /> 
    <script src="${contextPath }/static/widgets/jquery-fileupload-8.7.1/js/jquery.ui.widget.js"></script>
    <script src="${contextPath }/static/widgets/jquery-fileupload-8.7.1/js/jquery.iframe-transport.js"></script>
    <script src="${contextPath }/static/widgets/jquery-fileupload-8.7.1/js/jquery.fileupload.js"></script>  
    <!-- handlebars -->
    <script src="${contextPath }/static/widgets/handlebars/handlebars-v1.3.0.js"></script>  
    <script src="${contextPath }/static/widgets/handlebars/handlebars-ext.js"></script> 	
  </head>
  <body>
 	<form id="theform" action="save.json" method="post" class="form-horizontal">
 		<fieldset>
              <div class="row-fluid">
                <div class="span16 fileupload-buttonbar">
                  <span class="btn btn-success fileinput-button">
                        <i class="icon-plus icon-white"></i>
                        <span><s:message code="upload.func.selectFile"></s:message></span>
                        <!-- The file input field used as target for the file upload widget -->
                        <input id="fileupload" type="file" name="files[]" multiple>
                    </span>            
                    </div>
              </div>          
              <div class="row-fluid">
                <!-- The global progress bar -->
                  <div id="progress" class="progress progress-success progress-striped">
                      <div class="bar"></div>
                  </div>
                  <div class="progress-extended"></div>
                  	<div class="pull-left">
				      		<a type="button" class="btn btn-primary" href="${contextPath }/teamwork/document/downloadAll.do"  target="_blank">
				        		<i class="icon-download-alt icon-white"></i> <s:message code="btn.downloadAll" /></a>
			       	</div>  
			       		<div class="clear"></div> 
						<div class="space"></div>	
					
                    <table class="table table-striped">
                      <col/>
                      <col width="10%"/>
                      <col width="20%"/>                  
                      <tbody id="files"> 
                        <c:forEach items="${docList }" var="doc">
                          <tr id="attachmentBox${doc.id}">
                            <td><p><c:out value="${doc.srcFileName }"></c:out></p></td>
                            <td></td>
                            <td>
                            <a type="button" class="btn" href="${contextPath }/teamwork/document/download.do?id=${doc.id}" target="_blank"><i class="icon-download-alt"></i>&nbsp;<s:message code="btn.download" /></a>&nbsp;
                            <button type="button" class="btn btn-warning" onclick='deleteUploadFile("${doc.id }")'><s:message code="btn.delete" /></button></td>
                          </tr>
                        </c:forEach>                   
                      </tbody>
                    </table>
                </div>
            </fieldset>
 	</form>
 	<!--page specific plugin scripts-->	
<script id="files-template" type="text/x-handlebars-template">
      {{#docList}}
        <tr id="attachmentBox{{id}}">
          <td><p>{{srcFileName}}</p>
      {{#if success}}
        <strong class="text-success"><s:message code="upload.success.message"/></strong>
      {{/if}}
      {{#ifCond 'ILLEGAL_MIME_TYPE' '==' errorCode}}
        <strong class="text-error"><s:message code="upload.error.illegalMimeType"/></strong>
      {{/ifCond}}
      {{#ifCond 'OUT_OF_SIZE_LIMIT' '==' errorCode}}
        <strong class="text-error"><s:message code="upload.error.outOfSizeLimit"/></strong>
      {{/ifCond}}
      {{#ifCond 'STORE_FAILED' '==' errorCode}}
        <strong class="text-error"><s:message code="upload.error.storeFailed"/></strong>
      {{/ifCond}}
      </td>
      <td></td>
      <td>
      <a type="button" class="btn" href="${contextPath }/teamwork/document/download.do?id={{id}}" target="_blank"><i class="icon-download-alt"></i>&nbsp;<s:message code="btn.download" /></a>&nbsp;
      <button type="button" class="btn btn-warning" onclick="deleteUploadFile('{{id}}')"><s:message code="btn.delete" /></button>
    
      </td>
        </tr>
      {{/docList}}
</script> 			
		<script type="text/javascript">
		$(document).ready(function(){
			$('#fileupload').fileupload({
	              url: "${contextPath}/teamwork/document/upload.json",
	              dataType: 'json',
	              formData: {'recordId': '0', 'moduleCode': 'document'},
	              done: function (e, data) {
	              	var result = data.result;
	              	if(result.error)
	              	{
	              		$.webtools.notify({
	        				type: "error",
	        				message: result.errorMessage
	        			});		        		
	              	}
	              	else
	              	{	
	                    var template = Handlebars.compile($("#files-template").html());
	                            
	                    $('#files').append(template(result));				            
	              	}
	              },
	              start: function (e) {
	              	$('#progress .bar').css(
	                   'width', '0%'
	                );
	              },
	              progressall: function (e, data) {
	                  var progress = parseInt(data.loaded / data.total * 100, 10);
	                  $('#progress .bar').css(
	                      'width',
	                      progress + '%'
	                  );
	              }
	      		});
				
		});
		
		function deleteUploadFile(id)
		{
			bootbox.confirm("<s:message code='common.delete.confirm'/>", function(result){
				if(result)
				{
					$.webtools.ajax({
						url: "${contextPath}/teamwork/document/delete.json",
						params: {'id': id},
						success: function(reply) {
							$("#attachmentBox"+id).remove();
						}
					});						
				}				
			});			
		}	
		</script>	  
  </body>
</html>
