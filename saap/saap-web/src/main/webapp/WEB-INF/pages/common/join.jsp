<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.jspf"%>
<%@ include file="/common/taglibs.jspf"%>
<html lang="en">
	<head>
		<title><s:message code="login.ui.title" /></title>
		<meta name="decorator" content="none" />	
	</head>
	<body> 	

<div class="container" style="margin: 20px auto 0;">	
	<div class="row">
	  <div class="span10 offset1">
	  	<div class="widget-box">
	  		<div class="widget-header header-color-blue">
	        	<h5 class="bigger lighter"><s:message code="join" /></h5>
	        </div>
	        <div class="widget-body">
	        	<div class="widget-main">
	        	   <div id="form-error-container" class="form-error-container alert alert-block hide">
	        	   	 <i class="icon-warning-sign"></i> <strong><s:message code="form.error.invalid" />:</strong>	        	     
	        	     <ul></ul>
	        	   </div>
			       <form id="theform" name="theform" action="join.json" method="post" class="form-horizontal">
			       	<input type="hidden" name="code" value="${code }" />
			       	<fieldset>
			       		<legend></legend>
							<div class="control-group">	
							  	<label class="control-label visible-ie8 visible-ie9"><s:message code='login.email' /></label>
								<div class="controls">
									<div class="input-prepend">
										<span class="add-on"><i class="icon-envelope"></i></span>
										<input class="" type="text" placeholder="<s:message code='login.email.tip' />" name="email" value="${email }"/>							
									</div>
									<span class="help-block visible-ie8 visible-ie9 hide"><s:message code='register.email.help' /></span>
								</div>      		
							</div>
							<div class="control-group">
								<label class="control-label visible-ie8 visible-ie9"><s:message code='login.password' /></label>
								<div class="controls">
									<div class="input-prepend">
										<span class="add-on"><i class="icon-lock"></i></span>
										<input class="" type="password" id="register_password" placeholder="<s:message code='login.password' />" name="password"/>
									</div>
									<span class="help-block visible-ie8 visible-ie9 hide"><s:message code='register.password.help' /></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label visible-ie8 visible-ie9"><s:message code='login.rpassword' /></label>
								<div class="controls">
									<div class="input-prepend">
										<span class="add-on"><i class="icon-ok"></i></span>
										<input class="" type="password" placeholder="<s:message code='login.rpassword' />" name="rpassword"/>							
									</div>
									<span class="help-block visible-ie8 visible-ie9 hide"><s:message code='login.rpassword.help' /></span>
								</div>
							</div>		
					</fieldset> 					
			       	<div class="form-actions">
						<button type="submit" class="btn btn-primary" data-loading-text="<s:message code='common.processing' />"><i class="icon-ok"></i> <s:message code="join.form.btn.join" /></button>
					</div>	
			       </form> 
	        	</div>
	        </div>
	    </div>    
      </div> 
    </div>    
</div>
<!-- inline scripts related to this page -->
<script type="text/javascript">
$(document).ready(function(){		
	$('#theform').littFormSubmit({		
		rules : {			
			email : {
				required : true,
				email : true
			},
			password: {
                required: true
            },
            rpassword: {
            	required: true,
                equalTo: "#register_password"
            }
		},	
		success: function(reply){
			location.href = "${contextPath}/main.do";						
		}
	});	
	
});	
</script>
</body>
</html>