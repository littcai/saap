<%@ tag language="java" pageEncoding="UTF-8" import="com.litt.core.dao.page.IPageList" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ attribute name="formId" required="true" rtexprvalue="true" %>
<%@ attribute name="funcName" required="false" description="调用跳转的js的函数名" %> 
<%@ attribute name="params" required="true" rtexprvalue="true" type="com.litt.core.dao.page.IPageList"%>
<c:if test="${empty funcName }">
	<c:set var="funcName" value="jumpPage" />
</c:if>
<%
IPageList pageList = (IPageList)jspContext.getAttribute("params");
String funcName = jspContext.getAttribute("funcName").toString();

int curPageNo = pageList.getPageIndex();
int totalPage = pageList.getTotalPage();
int pageSize = pageList.getPageSize();
int totalSize = pageList.getTotalSize();

int displayLinkCount = totalPage<10?totalPage:10;	//显示10个页码跳转

int countBeforeCurrent = displayLinkCount / 2;

int startPageNo = curPageNo - countBeforeCurrent;
if(startPageNo <= 0)
{
	startPageNo = 1;
	countBeforeCurrent = curPageNo - 1;
}
int countBehindCurrent = displayLinkCount - countBeforeCurrent;
int endPageNo = curPageNo + countBehindCurrent;

boolean isFirstPage = curPageNo==1;//是否是第一页
boolean isLastPage = curPageNo==totalPage;//是否是最后一页

int firstRow = 0;
int lastRow = 0;

StringBuilder sb = new StringBuilder(50);
sb.append("<select size=\"1\" ")
.append(" style=\"width:70px;height:25px;padding:2px 3px;margin:0 4px;\"")
.append(" onchange=\"javascript:" + funcName + "(" + curPageNo + ", this.value);\" >");
if(pageSize==10)
	sb.append("<option selected>10</option>");
else 
	sb.append("<option>10</option>");
if(pageSize==20)
	sb.append("<option selected>20</option>");
else 
	sb.append("<option>20</option>");
if(pageSize==50)
	sb.append("<option selected>50</option>");
else 
	sb.append("<option>50</option>");
if(pageSize==100)
	sb.append("<option selected>100</option>");
else 
	sb.append("<option>100</option>");
if(pageSize==200)
	sb.append("<option selected>200</option>");
else 
	sb.append("<option>200</option>");
sb.append("</select>"); 
%>
<c:set var="firstRow" value="<%=firstRow %>"></c:set>
<c:set var="lastRow" value="<%=lastRow %>"></c:set>
<c:set var="pagerLength" value="<%=sb.toString() %>"></c:set>
<div class="row-fluid" style="margin: 8px 0 8px 0;">  
	<div class="span6">
		<div class="pull-left"  style="margin: 0 0 0 12px;"><s:message code="pager.info" arguments="${firstRow },${lastRow },${params.totalSize}" />&nbsp;&nbsp;
			<s:message code="pager.length" arguments="${pagerLength }" argumentSeparator="~" />
		</div>
	</div>
	<div class="span6">
		<div class='pagination pull-right'  style="margin: 0 12px;">	
			<ul>
				<!-- first page -->
				<c:if test="${params.pageIndex>1 }">
					<li><a href="javascript:${funcName }(1, <%=pageSize%>);"><s:message code="pager.first" /></a></li>  
				</c:if>
				<c:if test="${params.pageIndex<=0 }">
					<li class="disabled"><span><s:message code="pager.first" /></span></li>  
				</c:if>
				
				<!-- 前一页按钮 -->  
		        <c:if test="${params.pageIndex <= 1 }">  
		            <li class="disabled"><span><s:message code="pager.previous" /></span></li>  
		        </c:if>  
		        <c:if test="${params.pageIndex > 1 }">  
		            <li><a href="javascript:${funcName }(<%=curPageNo-1%>, <%=pageSize%>);"><s:message code="pager.previous" /></a></li>  
		        </c:if>         
		        
		        <!-- 页码 -->
		        <%for(int i=startPageNo; i< endPageNo; i++) {%>        
		        	<li <%=((i==curPageNo)?"class='active'":"")%>><a href="javascript:${funcName }(<%=i%>, <%=pageSize%>);"><%=i %></a>        
		        <%} %>
		  
		        <!-- 下一页按钮 -->  
		        <c:if test="${params.pageIndex >= params.totalPage}">  
		            <li class="disabled"><span><s:message code="pager.next" /></span></li>  
		        </c:if>  
		        <c:if test="${params.pageIndex < params.totalPage}">  
		            <li><a href="javascript:${funcName }(<%=curPageNo+1%>, <%=pageSize%>);"><s:message code="pager.next" /></a></li>  
		        </c:if>  
		  
		        <!-- 分页尾页按钮 -->  
		        <c:if test="${params.pageIndex > params.totalPage}">  
		            <li class="disabled"><span><s:message code="pager.last" /></span></li>  
		        </c:if>  
		        <c:if test="${params.pageIndex < params.totalPage}">  
		            <li><a href="javascript:${funcName }(<%=totalPage%>, <%=pageSize%>);"><s:message code="pager.last" /></a></li>  
		        </c:if>  
			</ul>	
		</div>	
	</div>
</div>
