<%@ page contentType="text/html;charset=UTF-8"%>
<div ng-controller="TenantMemberSelectCtrl" id="tenantMemberSelectModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="tenantMemberSelectModalLabel" aria-hidden="true" style="width: 60%;margin-left: -30%;">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="tenantMemberSelectModalLabel"><s:message code="tenantMember.func.select" /></h3>
  </div>
  <div class="modal-body">
      <!-- form filter -->
      
      <!-- datatable -->
      <div>
    	<table class="table table-striped table-bordered table-hover datatable">
          <thead>
            <tr>
              <th class="checkCol"><input type="checkbox" name="checkAll" ng-model="allChecked" ng-click="selectAll()" /></th>
              <th><s:message code="userInfo.loginId" /></th>
              <th><s:message code="userInfo.userName" /></th>
              <th><s:message code="userInfo.nickName" /></th>
            </tr>
          </thead>
          <tbody>
    		<tr ng-repeat="row in tenantUserList">
    		  <td class="checkCol"><input type="checkbox" name="userIds" value="{{row.obj.id }}" ng-model="row.checked" ng-click="selectOne(row);"/></td>
    		  <td>{{row.obj.loginId }}</td>
    		  <td>{{row.obj.userName }}</td>
    		  <td>{{row.obj.nickName }}</td>
    		</tr>
    	  </tbody>
    	</table>
        <div class="form-actions">
          <button type="submit" class="btn btn-primary" data-loading-text="<s:message code='common.processing' />" ng-click="confirmSelect()"><i class="icon-ok"></i> <s:message code="btn.confirm" /></button>
          <button class="btn" data-dismiss="modal" aria-hidden="true"><s:message code="btn.cancel" /></button>
        </div>
      </div>
    </div>  
</div>    
<script type="text/javascript">
  $(document).ready(function(){	
  	
  	angular.element(document).ready(function() {	
  		angular.bootstrap(document);	 
  	});
  	
  });
  
  function TenantMemberSelectCtrl($scope, $http) {
	  $scope.tenantUserList = [];
	  $scope.allChecked = false;
	  //init
	  $http.get('${contextPath}/system/userGroup/getTenantMembers.json?userGroupId=${userGroup.id}', {
		}).success(function(data) {
		    $scope.tenantUserList = data.tenantUserList;
		    angular.forEach($scope.tenantUserList, function(value, key){
        		if(value.checked)
        		  $scope.allChecked = true;
    		});
	  }); 
	  
	  $scope.selectAll = function(){
	    $scope.allChecked = !$scope.allChecked;
	    angular.forEach($scope.tenantUserList, function(value, key){
        	value.checked = $scope.allChecked;
    	});
	  }
	  
	  $scope.selectOne = function(row) {
		 row.checked = !row.checked;
		 $scope.allChecked = row.checked;
	  };
	  
	  $scope.confirmSelect = function(row) {
	    
	    if(!$scope.allChecked)
		{
			$.webtools.notify({
				type: "notice",
				message: "<s:message code='validate.checkone'/>"
			});
			return;
		}
		var userIds = new Array();
		angular.forEach($scope.tenantUserList, function(value, key){
        	if(value.checked)
        	  userIds.push(value.obj.id);
    	});
		//call function from parent
		onMemberSelected(userIds);
	  }
  }
</script>