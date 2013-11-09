<%@ page language="java" pageEncoding="UTF-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<div class='pagination'>
	<ul>
		<!-- first page -->
		<c:if test="${page.pageIndex>1 }">
			<li><a href="?pageNo=1">首页</a></li>  
		</c:if>
		<c:if test="${page.pageIndex<=0 }">
			<li class="disabled"><span>首页</span></li>  
		</c:if>
		
		<!-- 前一页按钮 -->  
        <c:if test="${page.pageIndex <= 1 }">  
            <li class="disabled"><span>前一页</span></li>  
        </c:if>  
        <c:if test="${page.pageIndex > 1 }">  
            <li><a href="?pageNo=${page.currentPageNo - 1}">前一页</a></li>  
        </c:if>  
        
        <!-- 跳转 -->  
        <li><a> 第 <input id="page" type="text" id="current_page" autocomplete="off" value="${page.pageIndex }"  
                style="margin-bottom: 2px; margin-top: 2px; width: 15px; height: 16px; direction: rtl;"> 页  
        </a></li>         
  
        <!-- 下一页按钮 -->  
        <c:if test="${page.pageIndex >= page.totalPage}">  
            <li class="disabled"><span>下一页</span></li>  
        </c:if>  
        <c:if test="${page.pageIndex < page.totalPage}">  
            <li><a href="?pageNo=${page.currentPageNo + 1}">下一页</a></li>  
        </c:if>  
  
        <!-- 分页尾页按钮 -->  
        <c:if test="${page.pageIndex >= page.totalPage}">  
            <li class="disabled"><span>尾页</span></li>  
        </c:if>  
        <c:if test="${page.pageIndex < page.totalPage}">  
            <li><a href="?pageNo=${page.allPages}">尾页</a></li>  
        </c:if>  
	</ul>
	<ul class="pull-right">  
        <li class="disabled"><a>第${page.firstRow} - ${page.lastRow }条记录 / 共${page.allRows }条</a></li>  
    </ul>  	
</div>	