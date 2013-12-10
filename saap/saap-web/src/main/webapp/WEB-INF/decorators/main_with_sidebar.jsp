<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.inc"%>
<%@ include file="/common/taglibs.inc"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<html lang="en">
  <head>	
  	<%@ include file="/common/meta.inc"%> 	
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
	          <a class="brand" href="#">Saap</a>
	          <!-- Everything you want hidden at 940px or less, place within here -->
	          <div class="nav-collapse collapse" id="topMenu">	            
	            <ul class="nav nav-pills">
	              <li <c:if test="${empty __moduleCode }">class="active"</c:if> ><a href="${contextPath }/main.do"><i class="icon-home"></i> <s:message code="main.ui.nav.home" /></a></li>	
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
				              	<c:choose> 
				              		<c:when test="${fn:startsWith(__moduleCode, subMenu.menuCode) }">
				              			<c:set var="subActiveCss" value="active"></c:set>
				              		</c:when>
				              		<c:otherwise>
				              			<c:set var="subActiveCss" value=""></c:set>
				              		</c:otherwise>
				              	</c:choose>	
	              		  		<li class="${subActiveCss }" ><a tabindex="-1" href="${contextPath}/menu.do?menuCode=${subMenu.menuCode}">${subMenu.menuName }</a></li>
	              		  	</c:forEach>
	              		  </ul>
	              		</li>
	              	</c:if>	 
	              </c:forEach>	              
	            </ul>
	            <ul class="pull-right nav">
	            	<li ><a href="${contextPath }/personal/shortMessage/index.do"><i class="icon-envelope"></i> 站内信</a></li>
	            	<li class="dropdown">
					      <a class="dropdown-toggle" data-toggle="dropdown" data-target="#" href="#">	
					      	蔡源1				       
					        <b class="caret"></b>
					      </a>
	              		  <ul class="dropdown-menu"> 
	              		  	<li ><a href="profile.do"><i class="icon-user"></i>&nbsp;个人设置</a></li>
			            	<li ><a href="${contextPath }/login/invite.do"><i class="icon-group"></i>&nbsp;邀请用户</a></li>
			            	<li ><a href="setting.do"><i class="icon-settings"></i>&nbsp;系统设置</a></li>
			            	<li ><a href="${contextPath }/login/logout.do"><i class="icon-off"></i>&nbsp;退出</a></li>
							<li ><a href="help.html" target="_blank"><i class="icon-question-sign"></i>&nbsp;帮助</a></li>
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
						  <li><a href="${contextPath }/">首页</a></li>
						  <c:forEach items="${menuTree }" var="menu">	
			       			<c:if test="${fn:startsWith(__moduleCode, menu.menuCode) }">
						  		<li> <span class="divider">/</span> ${menu.menuName }</li>					  	
						  		<c:forEach items="${menu.subList }" var="subMenu">	   	
						  			<c:if test="${fn:startsWith(__moduleCode, subMenu.menuCode) }">					  
						  				<li class="active"> <span class="divider">/</span> ${subMenu.menuName }</li>
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
