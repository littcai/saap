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
<!--[if lte IE 6]><br/><div class='alert alert-block' style="text-align:left;padding-bottom:10px;"><a class="close" data-dismiss="alert">x</a><h4>温馨提示：</h4><p>你使用的浏览器版本过低。为了获得更好的浏览体验，我们强烈建议您 <a href="http://browsehappy.com" target="_blank">升级</a> 到最新版本的IE浏览器，或者使用较新版本的 Chrome、Firefox、Safari 等。</p></div><![endif]-->

<div id="login-container">
	<div id="login-header">
		<h3 style="line-height: 15px;">SaaP</h3>
	</div> <!-- /login-header -->
	<div id="login-content" class="clearfix">
		<!-- BEGIN LOGIN FORM -->	
		<form action="login.json" method="post" id="login-form" class="login-form">
			<input type="hidden" name="login_theme" value="default">
			<input type="hidden" name="lang" value="zh_CN">	
				<fieldset>				
					<h3 class="form-title"><s:message code="login.ui.loginForm" /></h3>	
					<c:if test="${!empty timeout }">
						<h:notify content="${timeout }" type="error"></h:notify>
					</c:if>							
					<div id="login-form-message-summary"></div>	
					<div class="control-group">
						<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
						<label class="control-label visible-ie8 visible-ie9" for="loginId"><s:message code="login.loginId" /></label>
						<div class="controls">			
							<input class="input-block-level" type="text" placeholder="<s:message code='login.loginId.tip' />" id="loginId" name="loginId" value="" autofocus required/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label visible-ie8 visible-ie9" for="password"><s:message code="login.password" /></label>
						<div class="controls">
							<input class="input-block-level" type="password" placeholder="<s:message code="login.password" />" id="password" name="password" required/>
						</div>
					</div>							
					<div class="control-group">
						<label class="control-label" for="captcha"><s:message code="login.captcha" /></label>
						<div class="controls">
							<input type="text" class="span2" id="captcha" name="captcha" required>&nbsp;&nbsp;
							<img id="captchaImg" name="captchaImg" class="captchaImg" alt="<s:message code='login.captcha'/>" src="<%=contextPath%>/opCaptcha" width="80" height="20" align="absmiddle" onclick="refreshCaptcha(this);"  title="<s:message code='login.captcha.tip'/>"/>
												
						</div>
					</div>
					<div id="remember-me" class="pull-left">
						<label class="checkbox">
							<input type="checkbox" name="isAutoLogin" value="1" /><s:message code="login.remeberme" />
						</label>					
					</div>	
					<div class="pull-right">									
						<button type="submit" class="btn btn-large btn-primary">
							<s:message code='login.func.login'/>
						</button>
					</div>						  					 
				</fieldset>	
				<div>
					<a id="register-btn"" href="javascript:;">&nbsp;<s:message code="login.func.register" /></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="forget-password-btn" href="javascript:;"><s:message code="login.func.forgetPassword" />&nbsp;</a>
				</div>
			</form>
			<!-- END LOGIN FORM -->        
			<!-- BEGIN FORGOT PASSWORD FORM -->
			<form id="forget-form" class="form-vertical forget-form" action="forgetPassword.json">
				<h3 class=""><s:message code="login.ui.forgetForm" /></h3>
				<div id="forget-form-message-container" class="hide"></div>
				<p><s:message code="login.ui.forgetForm.tip" /></p>
				<div class="control-group">
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on"><i class="icon-envelope"></i></span>
							<input class="" type="text" placeholder="<s:message code='login.email' />" name="email" />
						</div>
					</div>
				</div>
				<div class="form-actions">
					<button type="button" id="forget-password-back-btn" class="btn">
					<i class="m-icon-swapleft"></i> <s:message code="btn.back" />
					</button>
					<button type="submit" class="btn btn-primary pull-right">
					<s:message code="btn.submit" /> <i class="m-icon-swapright m-icon-white"></i>
					</button>            
				</div>
			</form>			
			<!-- END FORGOT PASSWORD FORM -->
			<!-- BEGIN REGISTRATION FORM -->
			<form id="register-form" class="form-vertical register-form" action="register.json" >
				<h3 class=""><s:message code="login.ui.registerForm" /></h3>
				<p><s:message code="login.ui.registerForm.tip" /></p>
				<!-- use email as loginId
				<div class="control-group">
					<label class="control-label visible-ie8 visible-ie9"><s:message code="login.loginId" /></label>
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on"><i class="icon-user"></i></span>
							<input class="" type="text" placeholder="<s:message code='login.loginId.tip' />" name="loginId"/>							
						</div>
						<span class="help-block visible-ie8 visible-ie9 hide"><s:message code='register.loginId.help' /></span>
					</div>
				</div>
				 -->
				<div class="control-group">
					<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
					<label class="control-label visible-ie8 visible-ie9"><s:message code='login.email' /></label>
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on"><i class="icon-envelope"></i></span>
							<input class="" type="text" placeholder="<s:message code='login.email.tip' />" name="email"/>							
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
				<div class="control-group">
					<div class="controls">
						<label class="checkbox">
							<input type="checkbox" name="agreement"/><s:message code="login.ui.agreement" arguments="termOfService.do,privacyPolicy.do" />
						</label>  
						<div id="register_agreement_error"></div>
					</div>
				</div>
				<div class="form-actions">
					<button id="register-back-btn" type="button" class="btn">
					<i class="m-icon-swapleft"></i>  <s:message code="btn.back" />
					</button>
					<button type="submit" id="register-submit-btn" class="btn btn-warning pull-right">
					<s:message code="btn.submit" /> <i class="m-icon-swapright m-icon-white"></i>
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
				 success: function (element) {
	            	element.closest('.control-group').removeClass('error');
	            	element.remove();
	             },
				 submitHandler: function(form) {  
					 $(form).ajaxSubmit({ 							   
					    	dataType:  'json',         
					    	success:   function(response, textStatus, xhr, form){
					    		location.href = "${contextPath}/main.do";					    		
					    	}, 
					 		error : function (XMLHttpRequest, textStatus, errorThrown) {  	
					 			$.webtools.alert({
					 				containerId: "login-form-message-summary",
					 				type: "error",
					 				message: eval("(" + XMLHttpRequest.responseText +  ")").exception			
					 			}); 					 						
								$("#captcha").val("");
								$("#captchaImg").attr("src", "<%=contextPath%>/opCaptcha?radom="+Math.random());   
							},
					  });     	
					  return false;						    
				 },
				 errorPlacement: function(error, element) { 
					   if (element.is("input[name=captcha]") ) 
					        error.appendTo ( element.parent() ); 
					   else 
					        error.insertAfter(element); 
				 } 
			}); 
			
			$('#forget-form').littFormSubmit({		
				rules : {			
					email : {
						required : true,
						email : true
					}
				},	
				success: function(reply){
					$.webtools.alert({
		    			containerId: "forget-form-message-container",
						type: "info",
						message: "<s:message code='forgetPassword.success.message' />"			
			 		}); 					
				}
			});	
			
			
			$('.register-form').validate({          
	            focusInvalid: false, // do not focus the last invalid input	           
	            rules: {
	                loginId: {
	                    required: true,
	                    minlength: 6
	                },
	                password: {
	                    required: true
	                },
	                rpassword: {
	                	required: true,
	                    equalTo: "#register_password"
	                },
	                email: {
	                    required: true,
	                    email: true
	                },
	                agreement: {
	                    required: true
	                }
	            },

	            messages: { // custom messages for radio buttons and checkboxes
	                agreement: {
	                    required: "<s:message code='register.error.agreement.required' />"
	                }
	            },

	            highlight: function (element) { // hightlight error inputs
	                $(element)
	                    .closest('.control-group').addClass('error'); // set error class to the control group
	            },

	            success: function (element) {
	            	element.closest('.control-group').removeClass('error');
	            	element.remove();
	            },

	            errorPlacement: function (error, element) {
	                if (element.attr("name") == "agreement") { // insert checkbox errors after the container                  
	                    error.addClass('help-small no-left-padding').insertAfter($('#register_agreement_error'));
	                } else {
	                    error.addClass('help-small no-left-padding').insertAfter(element);
	                }
	            }	            
      		
	            ,submitHandler: function (form) {
	            	$(form).ajaxSubmit({ 						   
				    	dataType:  'json',         
				    	success:   function(){
				    		//reset form
				    		$(form).resetForm();
				    		//switch to login form
				    		$('.login-form').show();
	            			$('.register-form').hide();
				    		//display notify
				    		$.pnotify({
				    		    title: "<s:message code='register.success.title' />",
				    		    text: "<s:message code='register.success.message' />",
				    		    type: 'success',
				    		    history: false,
				    		    sticker: false
				    		});				    		
				    	}
	            				  
				  });     	
				  return false;	
	            }
	        });
			
			jQuery('#forget-password-btn').click(function () {
	            jQuery('.login-form').hide();
	            jQuery('.forget-form').show();
	        });

	        jQuery('#forget-password-back-btn').click(function () {
	            jQuery('.login-form').show();
	            jQuery('.forget-form').hide();
	        });
			
			$('#register-btn').click(function () {
	            $('.login-form').hide();
	            $('.register-form').show();
	        });

	        $('#register-back-btn').click(function () {
	            $('.login-form').show();
	            $('.register-form').hide();
	        });
			
		});	
		
		function refreshCaptcha(image)
		{
			image.src = "<%=contextPath%>/opCaptcha?radom="+Math.random();
		}
		</script>
	</body>
</html>
