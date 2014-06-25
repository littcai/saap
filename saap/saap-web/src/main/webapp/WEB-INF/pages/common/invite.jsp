<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.jspf"%>
<%@ include file="/common/taglibs.jspf"%>
<html lang="en" ng-app>
	<head>
		<title><s:message code="login.ui.title" /></title>
		<script type="text/javascript" src="${contextPath }/theme/default/js/angular.js"></script>		
	</head>
	<body> 	

<div class="container" style="margin: 20px auto 0;">	
	<div class="row">
	  <div class="span10 offset1">
	  	<div class="widget-box">
	  		<div class="widget-header header-color-blue">
	        	<h5 class="bigger lighter"><s:message code="invite" /></h5>
	        </div>
	        <div class="widget-body">
	        	<div class="widget-main">
	        	   <div id="form-error-container" class="form-error-container alert alert-block hide">
	        	   	 <i class="icon-warning-sign"></i> <strong><s:message code="form.error.invalid" />:</strong>	        	     
	        	     <ul></ul>
	        	   </div>
			       <form id="theform" name="theform" action="invite.json" method="post" class="form-horizontal" novalidate>
			       	<fieldset>
			       		<legend><s:message code="invite.form.legend.base" /></legend>  
				       		<div ng-repeat="member in [1,2,3]" id="memberBox$index" style="margin-bottom: 10px" >		
				       		  <ng-form name="innerForm">
							  <div class="control-group " ng-class="{error:innerForm.emails.$error.email, error:innerForm.emails.$error.minlength, success:innerForm.emails.$valid}">	
				       			<input name="emails" placeholder="<s:message code='invite.email' />" type="text" class="span6"
				       				ng-model="emails" ng-minlength="3" ng-maxlength="50" ng-pattern="/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/i" 
				       				/>&nbsp;
				       			<select name="roleIds">			       				
				       				<c:forEach items="${roleList }" var="role" varStatus="status">
				       				<option value="${role.id }"><c:out value="${role.name }" /></option>
				       				</c:forEach>
				       			</select>
				       			<div ng-show="innerForm.emails.$invalid">
									<span class="help-inline" ng-show="innerForm.emails.$error.email"><s:message code="validate.email" /></span>
									<span class="help-inline" ng-show="innerForm.emails.$error.pattern"><s:message code="validate.email" /></span>
									<span class="help-inline" ng-show="innerForm.emails.$error.minlength"><s:message code="validate.minlength" /></span>
								</div>
				       		  </div>
				       		  </ng-form>	       		
							</div>
						<!-- template -->
						<div ng-controller="MemberBoxCtrl">							
							<div ng-repeat="member in members" id="memberBox$index" style="margin-bottom: 10px" >							  
							  <ng-form name="innerForm">
							  <div class="control-group " ng-class="{error:innerForm.emails.$error.email, error:innerForm.emails.$error.minlength, success:innerForm.emails.$valid}">							  								
								<input name="emails" id="email$index" placeholder="<s:message code='invite.email' />" type="text" class="span6"
									ng-model="emails" ng-minlength="3" ng-maxlength="50" ng-pattern="/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/i"
									/>&nbsp;
									<select name="roleIds">			       		 		
										<c:forEach items="${roleList }" var="role" varStatus="status">
										<option value="${role.id }"><c:out value="${role.name }" /></option>
										</c:forEach>
									</select>&nbsp;
								<button type="button" class="btn" ng-click="removeMember($index);" ><s:message code="btn.delete"/></button>	
								<div ng-show="innerForm.emails.$invalid">
									<span class="help-inline" ng-show="innerForm.emails.$error.email"><s:message code="validate.email" /></span>
									<span class="help-inline" ng-show="innerForm.emails.$error.pattern"><s:message code="validate.email" /></span>
									<span class="help-inline" ng-show="innerForm.emails.$error.minlength"><s:message code="validate.minlength" /></span>
								</div>
							  </div>
							  </ng-form>
							</div>	
							<button type="button" class="btn" ng-click="addMember()"><s:message code="invite.form.btn.addMore" /></button>
						</div>
					</fieldset>
 					<fieldset>
			       		<legend><s:message code="invite.form.legend.comment" /></legend>
			       		<div>
			       			<textarea rows="5" cols="8" id="comment" name="comment" class="input-block-level limited"></textarea>
			       		</div>
			       	</fieldset>	
			       	<div class="form-actions">
						<button type="submit" class="btn btn-primary" data-loading-text="<s:message code='common.processing' />" ng-disabled="theform.$invalid"><i class="icon-ok"></i> <s:message code="invite.form.btn.sendInvite" /></button>
						<button type="button" class="btn" onclick="history.back();"><s:message code="btn.cancel" /></button>
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
	
	angular.element(document).ready(function() {	
		angular.bootstrap(document);	 
	});
	
	$('#theform').littFormSubmit({	
		enableChangeCheck: false,
		beforeSubmit: function(fields) {
			var isEmpty = true;
			$('[name^="emails"]').each(function() {
				var val = $(this).val();
				if(val!=null && val!="")
					isEmpty = false;
			});
			if(isEmpty)
			{
				$("#form-error-container ul").append("<li><s:message code='invite.error.inputEmpty' /></li>");
				$("#form-error-container").show();
				return false;
			}
			else
			{
				$("#form-error-container ul").empty();
				$("#form-error-container").hide();
				return true;
			}
		},
		success: function(reply){
			location.reload();
			 //location.href = "index.do";					
		}
	});	
	
});	


function MemberBoxCtrl($scope) {
	  $scope.members = [];
	  
	  var i=0;
	  $scope.addMember = function() {
		    $scope.members.push({});		   
	  };
	  
	  $scope.removeMember = function(index) {
		    $scope.members.splice(index, 1);
	  };
	  
}

</script>
</body>
</html>
