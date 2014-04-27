<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.inc"%>
<%@ include file="/common/taglibs.inc"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<html lang="en">
  <head>	
  	<%@ include file="/common/meta.inc"%>  	
  	<title><c:if test="${empty SESSION_USER.tenant }" >SaaP</c:if><c:if test="${not empty SESSION_USER.tenant }">${SESSION_USER.tenant.appAlias }</c:if></title>	 	
	<decorator:head />	
	<!--  -->
	<script type="text/javascript">
	$(document).ready(function(){	
		
	});	
	</script>		
	</head>
	<body>	
<header id="header">
    <!--[if lte IE 9]>
    <div id="warning_info" class="text-warning fade in mb_0">
        <button data-dismiss="alert" class="close" type="button">×</button>
        <strong>您正在使用低版本浏览器，</strong> 在本页面的显示效果可能有差异。
        建议您升级到
        <a href="http://www.google.cn/intl/zh-CN/chrome/" target="_blank">Chrome</a>
        或以下浏览器：
        <a href="www.mozilla.org/en-US/firefox/‎" target="_blank">Firefox</a> /
        <a href="http://www.apple.com.cn/safari/" target="_blank">Safari</a> /
        <a href="http://www.opera.com/" target="_blank">Opera</a> /
        <a href="http://windows.microsoft.com/en-us/internet-explorer/download-ie" target="_blank">
            Internet Explorer 10</a>
    </div>
    <![endif]-->
</header>			
		<!-- Common -->
		<div class="navbar navbar-static-top">
		  <div class="navbar-inner">
	        <div class="container-fluid">
	        	<!-- .btn-navbar is used as the toggle for collapsed navbar content -->
			    <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
			      <span class="icon-bar"></span>
			      <span class="icon-bar"></span>
			      <span class="icon-bar"></span>
			    </a>	                 
	          <a class="brand" href="${contextPath }"><c:if test="${empty SESSION_USER.tenant }" >SaaP</c:if><c:if test="${not empty SESSION_USER.tenant }">${SESSION_USER.tenant.appAlias }</c:if></a>
	          <!-- Everything you want hidden at 940px or less, place within here -->
	          <div class="nav-collapse collapse" id="topMenu">	            
	            <ul class="nav nav-pills">
	              <li <c:if test="${empty __moduleCode }">class="active"</c:if> ><a href="${contextPath }/main.do"><i class="icon-home"></i> <s:message code="common.ui.home" /></a></li>	
	              <c:set var="menuTree" value="${li:getMenuTree(SESSION_USER)}"></c:set>
	              <c:forEach items="${menuTree }" var="menu">	              	
	              		<c:choose>
				        	<c:when test="${fn:startsWith(__moduleCode, menu.menuCode) }">
				            	<c:set var="activeCss" value="active"></c:set>
				            </c:when>
				            <c:otherwise>
				            	<c:set var="activeCss" value=""></c:set>
				            </c:otherwise>
				        </c:choose>		              	 
	              	<c:if test="${menu.isLeaf }">	              		            	
	              		<li class="${activeCss }"><a href="${contextPath}/menu.do?menuCode=${menu.menuCode}">${menu.menuName }</a></li>	              	
	              	</c:if>
	              	<c:if test="${!menu.isLeaf }">
	              		<li class="dropdown ${activeCss }">
					      <a class="dropdown-toggle" data-toggle="dropdown" data-target="#" href="#">
					        ${menu.menuName } 
					        <b class="caret"></b>
					      </a>
	              		  <ul class="dropdown-menu">
	              		  	<c:forEach items="${menu.subList }" var="subMenu">	
	              		  		<c:if test="${subMenu.isLeaf }">
	              		  			<c:choose> 
					              		<c:when test="${fn:startsWith(__moduleCode, subMenu.menuCode) }">
					              			<c:set var="subActiveCss" value="active"></c:set>
					              		</c:when>
					              		<c:otherwise>
					              			<c:set var="subActiveCss" value=""></c:set>
					              		</c:otherwise>
					              	</c:choose>	
		              		  		<li class="${subActiveCss }" ><a tabindex="-1" href="${contextPath}/menu.do?menuCode=${subMenu.menuCode}">${subMenu.menuName }</a></li>
	              		  		</c:if>
	              		  		<c:if test="${!subMenu.isLeaf }">
	              		  			<li class="dropdown-submenu">
									    <a tabindex="-1" href="javascript:;">${subMenu.menuName }</a>
									    <ul class="dropdown-menu">
									    	<c:forEach items="${subMenu.subList }" var="subsubMenu">	
										    	<c:choose> 
								              		<c:when test="${fn:startsWith(__moduleCode, subsubMenu.menuCode) }">
								              			<c:set var="subActiveCss" value="active"></c:set>
								              		</c:when>
								              		<c:otherwise>
								              			<c:set var="subActiveCss" value=""></c:set>
								              		</c:otherwise>
								              	</c:choose>	
					              		  		<li class="${subActiveCss }" ><a tabindex="-1" href="${contextPath}/menu.do?menuCode=${subsubMenu.menuCode}">${subsubMenu.menuName }</a></li>
									    	</c:forEach>
									    </ul>
									</li>
	              		  		</c:if>              		  		
				              	
	              		  	</c:forEach>
	              		  </ul>
	              		</li>
	              	</c:if>	 
	              </c:forEach>	              
	            </ul>
	            <ul class="pull-right nav">
	            	<li><a href="${contextPath }/personal/shortMessage/index.do"><i class="icon-envelope"></i> <s:message code="common.ui.shortMessage" /></a></li>
	            	<li ><a href="#"><i class="icon-comment"></i> <s:message code="common.ui.feedback" /></a></li>
	            	<li class="dropdown">
					      <a class="dropdown-toggle" data-toggle="dropdown" data-target="#" href="#">
					      	<c:if test="${empty SESSION_USER.nickName }">${SESSION_USER.opName }</c:if>		
					      	<c:if test="${not empty SESSION_USER.nickName }">${SESSION_USER.nickName }</c:if>					      				       
					        <b class="caret"></b>
					      </a>
	              		  <ul class="dropdown-menu"> 
	              		  	<li ><a href="${contextPath }/user/profile.do"><i class="icon-user"></i>&nbsp;<s:message code="common.ui.profile" /></a></li>
			            	<li ><a href="${contextPath }/user/invite.do"><i class="icon-group"></i>&nbsp;<s:message code="common.ui.invite" /></a></li>
			            	<li ><a href="${contextPath }/login/logout.do"><i class="icon-off"></i>&nbsp;<s:message code="common.ui.logout" /></a></li>
							<li ><a href="help.html" target="_blank"><i class="icon-question-sign"></i>&nbsp;<s:message code="common.ui.help" /></a></li>
	            		  </ul>
	            	</li>
	            </ul>
	          </div><!--/.nav-collapse -->
	        </div>
	      </div>
	    </div>		
		<div class="container-fluid" style="margin-top: 15px;">
		  <c:if test="${not empty menuTree }">
		      <div class="row-fluid">
		      	<c:forEach items="${menuTree }" var="menu">	
			       <c:if test="${fn:startsWith(__moduleCode, menu.menuCode) }">
				        <div class="span2">
				          <div class="accordion" id="accordion2">
					            <div class="accordion-group">		            	
			            			<div class="accordion-heading">
					                    <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne">
					                        ${menu.menuName }
					                    </a>
					                </div>
					                <div id="collapseOne" class="accordion-body collapse" style="height: auto;">
					                    <div class="accordion-inner">
							  				<ul class="nav nav-list">	
							  				<c:forEach items="${menu.subList }" var="subMenu">	
							  					<c:if test="${subMenu.isLeaf }">
							  						<c:choose>
									              		<c:when test="${fn:startsWith(__moduleCode, subMenu.menuCode) }">
									              			<c:set var="subActiveCss" value="active"></c:set>
									              		</c:when>
									              		<c:otherwise>
									              			<c:set var="subActiveCss" value=""></c:set>
									              		</c:otherwise>
									              	</c:choose>	
								  					<li class="${subActiveCss }">
														<a href="${contextPath}/menu.do?menuCode=${subMenu.menuCode}">
															<i class="${subMenu.iconUrl }"></i> ${subMenu.menuName }
														</a>													
													</li>
												</c:if>
												<c:if test="${!subMenu.isLeaf }">
								  					<li class="nav-header">
														<i class="${subMenu.iconUrl }"></i> ${subMenu.menuName }
														
														<ul class="nav nav-list">
														<c:forEach items="${subMenu.subList }" var="subsubMenu">	              		  		
											              	<c:choose>
											              		<c:when test="${fn:startsWith(__moduleCode, subsubMenu.menuCode) }">
											              			<c:set var="subActiveCss" value="active"></c:set>
											              		</c:when>
											              		<c:otherwise>
											              			<c:set var="subActiveCss" value=""></c:set>
											              		</c:otherwise>
											              	</c:choose>	
											              	<li class="${subActiveCss }">
															<a href="${contextPath}/menu.do?menuCode=${subsubMenu.menuCode}">
																<i class="${subsubMenu.iconUrl }"></i> ${subsubMenu.menuName }
															</a>
															</li>
											            </c:forEach>  		
														</ul>													
													</li>
												</c:if>
											</c:forEach>	
					                		</ul>
					                	</div>
					                </div>		            		
					            </div>		            
					        </div>
				        </div><!--/span-->
					</c:if>
			    </c:forEach>		            
		        <div <c:if test="${not empty __moduleCode }">class="span10"</c:if> >
		        	<!-- 面包屑导航 -->
		        	<div>				
						<ul class="breadcrumb">
						  <li><a href="${contextPath }/"><s:message code="common.ui.home" /></a></li>
						  <c:forEach items="${menuTree }" var="menu">	
			       			<c:if test="${fn:startsWith(__moduleCode, menu.menuCode) }">
						  		<li> <span class="divider">/</span> ${menu.menuName }</li>					  	
						  		<c:forEach items="${menu.subList }" var="subMenu">	
						  			<c:if test="${fn:startsWith(__moduleCode, subMenu.menuCode) }">		
						  				<c:if test="${subMenu.isLeaf }">  
							  				<li class="active"> <span class="divider">/</span> ${subMenu.menuName }</li>
							  			</c:if>
							  			<c:if test="${!subMenu.isLeaf }">
							  			<li> <span class="divider">/</span> ${subMenu.menuName }</li>
							  			<c:forEach items="${subMenu.subList }" var="subsubMenu">	      
							  				<c:if test="${fn:startsWith(__moduleCode, subsubMenu.menuCode) }">		
							  					<li class="active"> <span class="divider">/</span> ${subsubMenu.menuName }</li>
							  				</c:if>
							  			</c:forEach>		
							  		</c:if>
							  		</c:if>							  			
						  		</c:forEach>
						  	</c:if>
						  </c:forEach>				
						</ul>
					</div>
					<div class="row-fluid">
						 <!-- 内容区 -->
		    			 <decorator:body />	 
					</div>
				</div>
			  </div>
		  </c:if>
		  <c:if test="${empty menuTree }">
		  	<div class="row-fluid">
				<!-- 内容区 -->
		    	<decorator:body />	 
			</div>
		  </c:if>
		</div>
	</body>
</html>
