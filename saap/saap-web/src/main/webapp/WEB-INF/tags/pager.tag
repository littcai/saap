<%@ tag language="java" pageEncoding="UTF-8"  import="com.litt.core.dao.page.IPageList" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ attribute name="formId" required="true" rtexprvalue="true" %>
<%@ attribute name="funcName" required="false" description="js function name" %> 
<%@ attribute name="params" required="true" rtexprvalue="true" type="com.litt.core.dao.page.IPageList"%>
<c:if test="${empty funcName }">
	<c:set var="funcName" value="jumpPage" />
</c:if>
<%
int DEFAULT_PAGE_SIZE = 15;

IPageList pageList = (IPageList)jspContext.getAttribute("params");
String funcName = jspContext.getAttribute("funcName").toString();

int curPageNo = 1;
int totalPage = 1;
int pageSize = DEFAULT_PAGE_SIZE;
int totalSize = 0;

if(pageList!=null)
{
	curPageNo = pageList.getPageIndex();
	totalPage = pageList.getTotalPage();
	pageSize = pageList.getPageSize();
	totalSize = pageList.getTotalSize();
}
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

int firstRow = totalSize==0?0:((curPageNo-1) * pageSize + 1);
int lastRow = firstRow + curPageNo==totalPage?(totalSize - firstRow ):totalSize;

StringBuilder sb = new StringBuilder(50);
sb.append("<select size=\"1\" ")
.append(" style=\"width:70px;height:25px;padding:2px 3px;margin:0 4px;\"")
.append(" onchange=\"javascript:" + funcName + "(" + curPageNo + ", this.value);\" >");
if(pageSize==DEFAULT_PAGE_SIZE)
	sb.append("<option selected value='"+DEFAULT_PAGE_SIZE+"'>"+ DEFAULT_PAGE_SIZE +"</option>");
else 
	sb.append("<option value='"+DEFAULT_PAGE_SIZE+"'>"+ DEFAULT_PAGE_SIZE +"</option>");
if(pageSize==20)
	sb.append("<option selected value='20'>20</option>");
else 
	sb.append("<option value='20'>20</option>");
if(pageSize==50)
	sb.append("<option selected value='50'>50</option>");
else 
	sb.append("<option value='50'>50</option>");
if(pageSize==100)
	sb.append("<option selected value='100'>100</option>");
else 
	sb.append("<option value='100'>100</option>");
if(pageSize==200)
	sb.append("<option selected value='200'>200</option>");
else 
	sb.append("<option value='200'>200</option>");
sb.append("</select>"); 
%>
<c:set var="firstRow" value="<%=firstRow %>"></c:set>
<c:set var="lastRow" value="<%=lastRow %>"></c:set>
<c:set var="totalPage" value="<%=totalPage %>"></c:set>
<c:set var="pagerLength" value="<%=sb.toString() %>"></c:set>
<c:if test="${not empty params || totalPage==0 }">
<div class="row-fluid" style="margin: 8px 0 8px 0;">  
	<div class="span6">
		<div class="pull-left"  style="margin: 0 0 0 12px;">第 ${firstRow } - ${lastRow }  / 共  <%=totalSize %> 项&nbsp;&nbsp;
			每页显示: ${pagerLength } 项
		</div>
	</div>
	<div class="span6">
		<div class='pagination pull-right'  style="margin: 0 12px;">	
			<ul>
				<!-- first page -->
				<c:if test="${params.pageIndex>1 }">
					<li><a href="javascript:${funcName }(1, <%=pageSize%>);">首页</a></li>  
				</c:if>
				<c:if test="${params.pageIndex<=0 }">
					<li class="disabled"><span>首页</span></li>  
				</c:if>
				
				<!-- 前一页按钮 -->  
		        <c:if test="${params.pageIndex <= 1 }">  
		            <li class="disabled"><span>上一页</span></li>  
		        </c:if>  
		        <c:if test="${params.pageIndex > 1 }">  
		            <li><a href="javascript:${funcName }(<%=curPageNo-1%>, <%=pageSize%>);">上一页</a></li>  
		        </c:if>         
		        
		        <!-- 页码 -->
		        <%for(int i=startPageNo; i< endPageNo; i++) {%>        
		        	<li <%=((i==curPageNo)?"class='active'":"")%>><a href="javascript:${funcName }(<%=i%>, <%=pageSize%>);"><%=i %></a>        
		        <%} %>
		  
		        <!-- 下一页按钮 -->  
		        <c:if test="${params.pageIndex >= params.totalPage}">  
		            <li class="disabled"><span>下一页</span></li>  
		        </c:if>  
		        <c:if test="${params.pageIndex < params.totalPage}">  
		            <li><a href="javascript:${funcName }(<%=curPageNo+1%>, <%=pageSize%>);">下一页</a></li>  
		        </c:if>  
		  
		        <!-- 分页尾页按钮 -->  
		        <c:if test="${params.pageIndex > params.totalPage}">  
		            <li class="disabled"><span>末页</span></li>  
		        </c:if>  
		        <c:if test="${params.pageIndex < params.totalPage}">  
		            <li><a href="javascript:${funcName }(<%=totalPage%>, <%=pageSize%>);">末页</a></li>  
		        </c:if>  
			</ul>	
		</div>	
	</div>
</div>
<script type="text/javascript">
<!--
function jumpPage(pageNo, pageSize)
{
	var url = location.href;
	var params = url.substring(url.indexOf("?")+1, url.length);
	//var reg = new RegExp("(^|\\?|&)"+ name +"=([^&]*)(\\s|&|$)", "i");  
    //if (reg.test(location.href)) return unescape(RegExp.$2.replace(/\+/g, " ")); return "";
	
	if(url.indexOf("pageIndex")>0)
	{
		url = url.replaceAll("pageIndex=<%=curPageNo%>", "pageIndex="+pageNo);
	}
	else if(url.indexOf("?")<0)
	{
		url = url + "?pageIndex=" + pageNo;
	}
	else
	{
		url = url + "&pageIndex=" + pageNo;
	}
	
	if(url.indexOf("pageSize")>0)
	{
		url = url.replaceAll("pageSize=<%=pageSize%>", "pageSize="+pageSize);
	}
	else
	{
		url = url + "&pageSize=" + pageSize;
	}
	
	location.href = url;
}
//-->
</script>
</c:if>
