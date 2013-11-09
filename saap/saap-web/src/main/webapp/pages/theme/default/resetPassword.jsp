<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.inc"%>
<%@ include file="/common/taglibs.inc"%>
<html lang="en">
	<head>
		<title><s:message code="login.ui.title" /></title>
		<meta name="decorator" content="none" />	
		<link href="${contextPath}/<s:theme code='path'/>/css/login.css" rel="stylesheet" />
		<!--page specific plugin scripts-->		
		<script src="${contextPath }/<s:theme code='path'/>/js/common.js"></script>
		
		<script src="${contextPath }/widgets/jquery-validate/js/jquery.validate.min.js"></script>
		<script src="${contextPath }/widgets/jquery-validate/js/jquery.validate.ex.js"></script>
		<c:if test="${!fn:startsWith(locale, 'en') && !empty locale }">
			<script src="${contextPath }/widgets/jquery-validate/i18n/messages_${locale}.js"></script>
		</c:if>
		<script src="${contextPath }/widgets/jquery-form/jquery.form.min.js"></script>		
	</head>
	<body> 		
<div class="navbar navbar-fixed-top">	
	<div class="navbar-inner">
		<div class="container">
			<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span> 				
			</a>			
			<ul class="nav pull-right">
				<li class="" style="line-height: 30px;">
					<a href="" target="_blank">SaaP</a>
				</li>
			</ul>
		</div> <!-- /container -->
	</div> <!-- /navbar-inner -->
</div> <!-- /navbar -->


<div id="login-container">
	<div id="login-header">
		<h3 style="line-height: 15px;">SaaP</h3>
	</div> <!-- /login-header -->
	<div id="login-content" class="clearfix">
		<!-- BEGIN RESET FORM -->			
			<form id="reset-form" class="form-vertical" action="login/resetPassword.json" >
				<h3 class=""><s:message code="login.ui.resetPasswordForm" /></h3>
				<div id="form-message-summary"></div>	
				<div class="control-group">
					<label class="control-label visible-ie8 visible-ie9"><s:message code="UserInfo.userName" /></label>
					<div class="controls">
						${userInfo.userName } [${userInfo.email }]
					</div>
				</div>
				<div class="control-group">
					<label class="control-label visible-ie8 visible-ie9"><s:message code='login.password' /></label>
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on"><i class="icon-lock"></i></span>
							<input class="" type="password" id="register_password" placeholder="<s:message code='login.password' />" name="password"/>
						</div>
						<span class="help-block visible-ie8 visible-ie9 hide"><s:message code='login.password.help' /></span>
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
				<div class="form-actions">					
					<button type="submit" id="submit-btn" class="btn btn-warning pull-right">
						<s:message code="btn.submit" />
					</button>            
				</div>
			</form>
			<!-- END REGISTRATION FORM -->				
		</div> <!-- /login-content -->
		
		<div id="login-extra">
			<p>

			</p>
			
             <br>
             <p>Copyright &copy; 2004~2013 <a href=" target="_blank">LITTCORE</a>, all rights reserved.</p>
			<!--<p>我能用它做什么？让系统自动的帮您跟踪客户，真正的实现销售自动化(SFA)，</p>
			<p>让您感觉销售是件非常Easy的事情！还不心动吗，赶紧试试吧！</p>-->
			<p>联系电话：12345678 &nbsp;&nbsp;E-mail: <a href="mailto:littcai@gmail.com">littcai@gmail.com</a></p>
		</div> <!-- /login-extra -->
	
</div> <!-- /login-wrapper -->
		<!-- inline scripts related to this page -->
		<script type="text/javascript">
		$(document).ready(function(){				
			
			$("#login-form").validate({ 
				rules: {	                
	                password: {
	                    required: true,
	                    minlength: 6,
	                    maxlength: 50
	                },
	                rpassword: {
	                    equalTo: "#register_password"
	                }	                
	            },				
				 success: function (element) {
	            	element.closest('.control-group').removeClass('error');
	            	element.remove();
	             },
				 submitHandler: function(form) { 
					 $(form).ajaxSubmit({ 						   
					    	dataType:  'json',         
					    	success:   function(){
					    		//display notify
					    		showAlert("form-message-summary", "success", "<s:message code='forgetPassword.success.message' />");				    		
							},
							error : function (XMLHttpRequest, textStatus, errorThrown) {  		
								showAlert("form-message-summary", "error", eval("(" + XMLHttpRequest.responseText +  ")").exception);	
							}									
					 });
					 return false;	
				 }
			}); 			
		});			
		</script>
	</body>
</html>
