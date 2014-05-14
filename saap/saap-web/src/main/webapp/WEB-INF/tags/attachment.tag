<%@ tag language="java" pageEncoding="UTF-8" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ attribute name="recordId" required="true" rtexprvalue="true"%>
<%@ attribute name="moduleCode" required="true" rtexprvalue="true"%>
<%@ attribute name="attachmentList" required="false" rtexprvalue="true"%>
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
        <table class="table table-striped">
        	<col/>
        	<col width="10%"/>
        	<col width="10%"/>				        	
        	<tbody id="files">
        		<c:forEach items="${attachmentList }" var="attachment">
        		<tr id="attachmentBox${attachment.uid }">
        			<td><p><c:out value="${attachment.displayName }"></c:out></p></td>
        			<td>${attachment.fileSize }</td>
        			<td><button type="button" class="btn btn-warning" onclick='deleteUploadFile("${attachment.uid }")'><s:message code="btn.delete" /></button></td>
        		</tr>
        		</c:forEach>
        	</tbody>
        </table>
    </div>
    <script id="files-template" type="text/x-handlebars-template">
      {{#fileList}}
        <tr id="attachmentBox{{uid}}">
          <td><p>{{srcFileName}}</p>
			{{#if success}}
				<strong class="text-success"><s:message code="upload.success.message"/></strong>
			{{/if}}
			{{#ifCond 'ILLEGAL_MIME_TYPE' '==' "errorCode"}}
				<strong class="text-error"><s:message code="upload.error.illegalMimeType"/></strong>
			{{/ifCond}}
			{{#ifCond 'OUT_OF_SIZE_LIMIT' '==' "errorCode"}}
				<strong class="text-error"><s:message code="upload.error.outOfSizeLimit"/></strong>
			{{/ifCond}}
			{{#ifCond 'STORE_FAILED' '==' "errorCode"}}
				<strong class="text-error"><s:message code="upload.error.storeFailed"/></strong>
			{{/ifCond}}
		  </td>
		  <td>{{displayFileSize}}</td>
		  <td>{{#if success}}
			<button type="button" class="btn btn-warning" onclick="deleteUploadFile('{{uid}}')"><s:message code="btn.delete" /></button>
			<input name="attachmentUids[]" type="hidden" value="{{uid}}">
		  {{/if}}
		  </td>
        </tr>
      {{/fileList}}
    </script>
    <script type="text/javascript">
		$(document).ready(function(){	
			$('#fileupload').fileupload({
		        url: "${contextPath}/assistant/attachment/upload.json",
		        dataType: 'json',
		        formData: {'recordId': '${recordId}', 'moduleCode': '${moduleCode}'},
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
		        		// compile our template
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
		
		function deleteUploadFile(uid)
		{
			bootbox.confirm("<s:message code='attachment.func.delete.confirm'/>", function(result){
				if(result)
				{
					$.webtools.ajax({
						url: "${contextPath}/assistant/attachment/deleteByUid.json",
						params: {'uid': uid},
						success: function(reply) {
							$("#attachmentBox"+uid).remove();
						}
					});						
				}				
			});			
		}
		</script>	