<%@ page contentType="text/html;charset=UTF-8"%>
<!-- Modal -->
<div ng-controller="CustomerSelectCtrl" id="customerModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="customerModalLabel" aria-hidden="true" style="width: 60%;margin-left: -30%;">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="customerModalLabel"><s:message code="customer.ui.dialog.choose" /></h3>
  </div>
  <div class="modal-body">
  	<div>
  		<!-- form filter -->
  		<form class="form-search" id="search-form" name="search-form" method="POST">				
			<input type="hidden" id="pageIndex" name="pageIndex" value="${pageParam.pageIndex }" />
			<input type="hidden" id="pageSize" name="pageSize" value="${pageParam.pageSize }" />
			<input type="hidden" id="sortField" name="sortField"  value="${pageParam.sortField}"/>
			<input type="hidden" id="sortOrder" name="sortOrder"  value="${pageParam.sortOrder}"/>
			<table class="search-form">
				<tr>
					<td><label for="s_code"><s:message code="customer.code" /></label></td>
					<td><input type="text" id="s_code" name="code" /></div>
					<td><label for="s_name"><s:message code="customer.name" /></label></td>
					<td><input type="text" id="s_name" name="name" /></td>
					<td><button type="button" class="btn btn-small" ng-click="filter();"><i class="icon-search"></i>&nbsp;<s:message code="btn.query"></s:message></button></td>
				</tr>			
			</table>     
        </form>
  	</div>  	
  	<div class="space"></div>
    <div id="panel-customer">
	    <table class="table table-striped table-hover table-condensed">
	    	<thead>
	    		<tr>
	    			<th></th>
	    			<th><s:message code="customer.code" /></th>
	    			<th><s:message code="customer.name" /></th>
	    		</tr>	
	    	</thead>
	    	<tbody>		    	
	    		<tr ng-repeat="customer in customers">	 
	    			<td><input type="radio" name="customerId" value="{{$index}}" ng-model="formData.customerId" />  </td>   			
					<td>{{customer.code}}</td>
					<td>{{customer.name}}</td>		
	    		</tr>
	    	
	    	</tbody>
	    </table>
    </div>
  </div>  
  <div class="modal-footer">
    <button type="button" class="btn" data-dismiss="modal" aria-hidden="true"><s:message code="btn.cancel" /></button>
    <button type="button" class="btn btn-primary" ng-click="select();"><s:message code="btn.confirm" /></button>
  </div>
</div>					
<script type="text/javascript">
$(document).ready(function(){	
	
	angular.element(document).ready(function() {	
		angular.bootstrap(document);	 
	});
	
});	

function CustomerSelectCtrl($scope, $http)
{
	$scope.customers = [];
	$scope.formData = {};
	  
	$scope.filter = function() {		
		$http.get('query.json', {
			params: {
				code: $("#s_code").val(),
				name: $("#s_name").val()
			}
			
		}).success(function(data) {
		    $scope.customers = data.customers;
		}); 
	};
	  
	$scope.select = function() {
	    if($scope.formData.customerId!=null)
	    {
	    	var index = $scope.formData.customerId;
	    	var customer = $scope.customers[index];
	    	onCustomerSelect(customer);	//这个事件需要具体页面定义
	    }	
	};
}
</script>