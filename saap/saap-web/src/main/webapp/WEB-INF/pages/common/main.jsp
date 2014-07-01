<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.jspf"%>
<%@ include file="/common/taglibs.jspf"%>
<html lang="en" ng-app="MyModule">
	<head>
      <!-- angularjs -->
      <script type="text/javascript" src="${contextPath }/theme/default/js/angular.js"></script>		
	</head>
	<body>	
      <!-- affiche -->
	  <div class="row-fluid" id="affichePanel" ng-controller="AfficheCtrl">
        <div class="panel panel-info">
          <div class="panel-heading"><s:message code="affiche"></s:message>
            <a href="javascript:;" ng-click="showMoreAffiche()"><span class="badge badge-info pull-right"><s:message code="btn.more"/></span></a>
          </div>
          <ul class="list-group">
            <li class="list-group-item" ng-repeat="affiche in afficheList">
              <span class="badge">{{affiche.updateDatetime | date : 'yyyy-MM-dd hh:mm:ss' }}</span>
              <span class="label label-info">{{affiche.typeDescr }}</span>&nbsp;&nbsp;<a href="${contextPath }/message/affiche/show.do?id={{affiche.id}}" target="_blank">{{affiche.title}}</a>
            </li>
          </ul>
        </div>
      </div>
      <!-- feedback -->
      <div class="row-fluid" ng-controller="FeedbackCtrl">
          <div class="panel panel-info">
            <div class="panel-heading"><s:message code="feedback"></s:message>
              <a href="javascript:;" ng-click="showMoreFeedback()"><span class="badge badge-info pull-right"><s:message code="btn.more" /></span></a>
            </div>
            <ul class="list-group">
              <li class="list-group-item" ng-repeat="feedback in feedbackList">
                <span class="badge">{{feedback.createDatetime | date : 'yyyy-MM-dd hh:mm:ss' }}</span>
                <span class="label label-info">{{feedback.typeDescr }}</span>&nbsp;&nbsp;<a href="javascript:;" ng-click="showFeedback(feedback)">{{feedback.content}}</a>
                
                <div class="hide" id="feedbackReplyBox{{feedback.id}}">
                  <span class="badge pull-right">{{feedback.replyDatetime | date : 'yyyy-MM-dd hh:mm:ss' }}</span>
                  <span class="label label-success"><s:message code="feedback.reply" /></span>
                  {{feedback.reply}}
                </div>
              </li>
            </ul>
          </div>
        </div>
      <!-- inline scripts related to this page -->
      <script type="text/javascript">
      $(document).ready(function(){        
        angular.element(document).ready(function() {	
      		//angular.bootstrap(document);	
      		// Your app's root module...
          angular.module('MyModule', []).config(function($httpProvider) {
            // Use x-www-form-urlencoded Content-Type
            $httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded';
            $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
            /**
             * The workhorse; converts an object to x-www-form-urlencoded serialization.
             * @param {Object} obj
             * @return {String}
             */ 
            var param = function(obj) {
              var query = '', name, value, fullSubName, subName, subValue, innerObj, i;
                
              for(name in obj) {
                value = obj[name];
                  
                if(value instanceof Array) {
                  for(i=0; i<value.length; ++i) {
                    subValue = value[i];
                    fullSubName = name + '[' + i + ']';
                    innerObj = {};
                    innerObj[fullSubName] = subValue;
                    query += param(innerObj) + '&';
                  }
                }
                else if(value instanceof Object) {
                  for(subName in value) {
                    subValue = value[subName];
                    fullSubName = name + '[' + subName + ']';
                    innerObj = {};
                    innerObj[fullSubName] = subValue;
                    query += param(innerObj) + '&';
                  }
                }
                else if(value !== undefined && value !== null)
                  query += encodeURIComponent(name) + '=' + encodeURIComponent(value) + '&';
              }
                
              return query.length ? query.substr(0, query.length - 1) : query;
            };

            // Override $http service's default transformRequest
            $httpProvider.defaults.transformRequest = [function(data) {
              return angular.isObject(data) && String(data) !== '[object File]' ? param(data) : data;
            }];
          });
      	
          angular.bootstrap(document, ['MyModule']);	
      	});
      });
      
      function AfficheCtrl($scope, $http, $filter) {
        $scope.afficheList = [];
        var pageIndex = 2;
        
        $http.post("${contextPath}/message/affiche/getTopN.json").success(function(data) {
          var afficheList = data.afficheList;
          for(i=0;i<afficheList.length;i++)
          {
            var affiche = afficheList[i];
            if(1==affiche.type)
            {
              affiche.typeDescr = "<s:message code='dictparam.affiche-type.1' />"; 
            }
            else if(2==affiche.type)
            {
              affiche.typeDescr = "<s:message code='dictparam.affiche-type.2' />"; 
            }
            else if(3==affiche.type)
            {
              affiche.typeDescr = "<s:message code='dictparam.affiche-type.3' />"; 
            }
          }
          $scope.afficheList = afficheList;
        });
        
        $scope.showMoreAffiche = function(){
          $http.post("${contextPath}/message/affiche/getTopN.json", {"pageIndex": pageIndex}).success(function(data) {
            var afficheList = data.afficheList;
            if(0==afficheList.length)
            {
              $.webtools.notify({
                type: "notice",
                position: "center",
                delay: 1500,
                message: "<s:message code='btn.more.nomore' />"			
              }); 
              return;
            }
            for(i=0;i<afficheList.length;i++)
            {
              var affiche = afficheList[i];
              if(1==affiche.type)
              {
                affiche.typeDescr = "<s:message code='dictparam.affiche-type.1' />"; 
              }
              else if(2==affiche.type)
              {
                affiche.typeDescr = "<s:message code='dictparam.affiche-type.2' />"; 
              }
              else if(3==affiche.type)
              {
                affiche.typeDescr = "<s:message code='dictparam.affiche-type.3' />"; 
              }
              $scope.afficheList.push(affiche);
            }
            pageIndex++;
          });
        }        
      }
      
      function FeedbackCtrl($scope, $http, $filter) {
        $scope.feedbackList = [];
        var pageIndex = 2;
        
        $http.post("${contextPath}/message/feedback/getTopN.json").success(function(data) {
          var feedbackList = data.feedbackList;
          for(i=0;i<feedbackList.length;i++)
          {
            var feedback = feedbackList[i];
            if(1==feedback.type)
            {
              feedback.typeDescr = "<s:message code='feedback.type.1' />"; 
            }
            else if(2==feedback.type)
            {
              feedback.typeDescr = "<s:message code='feedback.type.2' />"; 
            }
            else if(3==feedback.type)
            {
              feedback.typeDescr = "<s:message code='feedback.type.3' />"; 
            }
            else if(4==feedback.type)
            {
              feedback.typeDescr = "<s:message code='feedback.type.4' />"; 
            }
          }
          $scope.feedbackList = feedbackList;
        });   
        
        $scope.showMoreFeedback = function(){
          $http.post("${contextPath}/message/feedback/getTopN.json", {"pageIndex": pageIndex}).success(function(data) {
            var feedbackList = data.feedbackList;
            if(0==feedbackList.length)
            {
              $.webtools.notify({
                type: "notice",
                position: "center",
                delay: 1500,
                message: "<s:message code='btn.more.nomore' />"			
              }); 
              return;
            }
            for(i=0;i<feedbackList.length;i++)
            {
              var feedback = feedbackList[i];
              if(1==feedback.type)
              {
                feedback.typeDescr = "<s:message code='feedback.type.1' />"; 
              }
              else if(2==feedback.type)
              {
                feedback.typeDescr = "<s:message code='feedback.type.2' />"; 
              }
              else if(3==feedback.type)
              {
                feedback.typeDescr = "<s:message code='feedback.type.3' />"; 
              }
              else if(4==feedback.type)
              {
                feedback.typeDescr = "<s:message code='feedback.type.4' />"; 
              }
              $scope.feedbackList.push(feedback);
            }
            pageIndex++;
          });   
        }
        
        $scope.showFeedback = function(feedback) {
          $("#feedbackReplyBox"+feedback.id).toggle();
        }
      }
      </script>
	</body>
</html>
