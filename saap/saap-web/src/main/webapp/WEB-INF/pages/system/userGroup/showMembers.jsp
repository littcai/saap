<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.jspf"%>
<%@ include file="/common/taglibs.jspf"%>
<html lang="en">
  <head>
    <!-- jquery datatables -->
    <link href="${contextPath}/static/widgets/jquery-datatables/css/jquery.dataTables.css" rel="stylesheet" />  
    <script src="${contextPath }/static/widgets/jquery-datatables/js/jquery.dataTables.min.js"></script>
    <script src="${contextPath }/static/widgets/jquery-datatables/js/jquery.dataTables.bootstrap.js"></script>  
    <!-- angularjs -->
    <script type="text/javascript" src="${contextPath }/theme/default/js/angular.js"></script>  		
  </head>
  <body>   
  <form id="theform" action="show.do" method="post" class="form-horizontal">
    <input type="hidden" name="id" value="${userGroup.id}" />
	<fieldset>
		<legend><s:message code="common.ui.fieldset.base" /></legend>
		<div class="row-fluid">
			<div class="control-group">
				<label class="control-label" for="userGroup.code"><s:message code="userGroup.code" /></label>
				<div class="controls">
					<input id="userGroup.code" name="code" type="text" value="<c:out value='${userGroup.code }'/>" readonly="readonly"/>
				</div>
			</div>						
		</div>
		<div class="row-fluid">
			<div class="control-group">
				<label class="control-label" for="userGroup.name"><s:message code="userGroup.name" /></label>
				<div class="controls">
					<input id="userGroup.name" name="name" type="text" value="<c:out value='${userGroup.name }'/>" readonly="readonly"/>
				</div>
			</div>									
		</div>
		<div class="row-fluid">
			<div class="control-group">
				<label class="control-label" for="description"><s:message code="common.field.description" /></label>
				<div class="controls">
					<textarea rows="6" cols="8" id="description" name="description" class="input-block-level limited" readonly="readonly"><c:out value='${userGroup.description }'/></textarea>
				</div>
			</div>								
		</div>
	</fieldset>	       
    <fieldset>
      <legend><s:message code="userGroupMember"></s:message></legend>
      <!-- toolbar -->
      <div class="toolbar">
        <div class="pull-left">
          <button type="button" class="btn btn-small btn-primary" id="btnAddMember">
            <i class="icon-plus icon-white"></i> <s:message code="btn.add" /></button>
          <button type="button" class="btn btn-small btn-danger" onclick="javascript:return batchDelete();">
            <i class="icon-trash icon-white"></i> <s:message code="btn.deleteBatch" /></button>
        </div> 
        <div class="clear"></div>          
      </div>  
      <table class="table table-striped table-bordered table-hover datatable">
        <thead>
          <tr>      
            <th class="checkCol"><input type="checkbox" id="checkAll" name="checkAll" /></th> 
            <th><s:message code="userInfo.loginId" /></th>
            <th><s:message code="userInfo.userName" /></th>
            <th><s:message code="userInfo.nickName" /></th>
            <th><s:message code="userInfo.status" /></th>
            <th><s:message code="common.action" /></th>
          </tr>
        </thead>
        <tbody>
        <c:forEach items="${memberList }" var="row">
          <tr id="userGroupBox_${row.userGroupMember.id }">
            <td class="checkCol"><input type="checkbox" class="checkItem" name="userGroupMemberIds" value="${row.userGroupMember.id }" /></td>
            <td><a href="${contextPath }/system/tenantMember/showByUserId.do?userId=${row.userGroupMember.userId }&decorator=popup" target="_blank"><c:out value="${row.userInfo.loginId }"></c:out>&nbsp;<i class="icon-info-sign"></i></a></td>
            <td><c:out value="${row.userInfo.userName }"></c:out></td>
            <td><c:out value="${row.userInfo.nickName }"></c:out></td>
            <td><c:out value="${li:genDictContent('1002', row.userInfo.status) }"></c:out></td>
            <td class="action-buttons">                 
              <a href="javascript:;" class="red" onclick="rowDelete(${row.userGroupMember.id});">
                <i class="icon-trash"></i>
              </a>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </fieldset>
    <div class="form-actions">
      <button type="button" class="btn" onclick="history.back();"><s:message code="btn.back" /></button>
    </div> 
  </form>
<!-- user select dialog -->
<%@ include file="selectUser.jsp"%>
<!--page specific plugin scripts-->         
<script type="text/javascript">
    $(document).ready(function(){       
      $('.datatable').dataTable();  
      
      var checkboxs = $.webtools.checkboxs({
        checkAll: "#checkAll",
        checkItem: ".checkItem"       
      });
      
      $("#btnAddMember").click(function(){
        $("#tenantMemberSelectModal").modal();
        
      });
    })
    
    function onMemberSelected(userIds)
    {
      bootbox.confirm("<s:message code='tenantMember.func.select.confirm' />", function(result){
  			if(result)
  			{
  				$.webtools.ajax({
  					url: "saveMemberBatch.json",
  					params: {"groupId": "${userGroup.id}","userIds":userIds},
  					success: function(reply) {
  						location.reload();
  					}
  				});						
  			}				
  	  });		
    }
    
    function batchDelete()
    {         
      if($(".checkItem:checked").length<=0)
      {
        $.webtools.notify({
          type: "notice",
          message: "<s:message code='validate.checkone'/>"
        });
        return;
      }
      var ids = $.webtools.getCheckValuesArray(".checkItem"); 
    
      bootbox.confirm("<s:message code='common.delete.confirm' />", function(result){
        if(result)
        {
          $.webtools.ajax({
            url: "deleteMemberBatch.json",
            params: {"ids":ids},
            success: function(reply) {
              for(var i=0;i<ids.length;i++)
              {
                $("#userGroupBox_"+ids[i]).remove();
              }
            }
          });           
        }       
      });     
    } 
        
    function rowDelete(id)
    {     
      bootbox.confirm("<s:message code='common.delete.confirm' />", function(result){
        if(result)
        {
          $.webtools.ajax({
            url: "deleteMember.json",
            params: {"id":id},
            success: function(reply) {
              $("#userGroupBox_"+id).remove();
            }
          });         
        }       
      });     
    }   
</script>  	
  </body>	
</html>