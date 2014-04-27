<%@ page contentType="text/html;charset=UTF-8"%>
<!-- Modal -->
<div id="contactsModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="contactsModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="contactsModalLabel"><s:message code="contacts.ui.dialog.choose" /></h3>
  </div>
  <div class="modal-body">
  	<div>
  		<button id="btn-contacts" type="button" class="btn btn-primary hide" onclick="switchContacts();"><i class="icon-search"></i> 联系人</button>
  		<button id="btn-contactsGroup" type="button" class="btn btn-primary" onclick="switchContacts();"><i class="icon-search"></i> 联系人分组</button>
  	</div>
  	<div class="space"></div>
    <div id="panel-contacts">
	    <table class="table table-striped table-hover table-condensed">
	    	<thead>
	    		<tr>
	    			<th><input type="checkbox" class="checkAll" data-target="#panel-contacts .checkItem" /></th>
	    			<th><s:message code="contacts.name" /></th>
	    			<th><s:message code="contacts.mobile" /></th>
	    		</tr>	
	    	</thead>
	    	<tbody>	
	    	<c:forEach items="${contactsList }" var="contacts">
	    		<tr>
	    			<td class="checkCol">
	    				<c:if test="${not empty contacts.mobile }"><input type="checkbox" class="checkItem" name="contactsIds" value="${contacts.mobile }" /></c:if>
	    			</td>
					<td><c:out value="${contacts.name }"></c:out></td>
					<td><c:out value="${contacts.mobile }"></c:out></td>		
	    		</tr>
	    	</c:forEach>
	    	</tbody>
	    </table>
    </div>
    <div id="panel-contactsGroup" class="tabbable tabs-left hide">
	  <ul class="nav nav-tabs">
	  	<li class="active"><a href="#group-default" data-toggle="tab"><s:message code="contactsGroup.noGroup" /></a>
	  	<c:forEach items="${contactsGroupList }" var="contactsGroup">
	  		 <li><a href="#group-${contactsGroup.contactsGroup.id }" data-toggle="tab">${contactsGroup.contactsGroup.name }</a></li>
	  	</c:forEach>		 
	  </ul>
	  <div class="tab-content">
		  <div class="tab-pane active" id="group-default">
		  	<table class="table table-striped table-hover table-condensed">
		    	<thead>
		    		<tr>
		    			<th><input type="checkbox" class="checkAll" data-target="#group-default .checkItem" /></th>
		    			<th><s:message code="contacts.name" /></th>
		    			<th><s:message code="contacts.mobile" /></th>
		    		</tr>	
		    	</thead>
		    	<tbody>	
		    	<c:forEach items="${noGroupContactsList }" var="contacts">
		    		<tr>
		    			<td class="checkCol">
		    				<c:if test="${not empty contacts.mobile }"><input type="checkbox" class="checkItem" name="contactsIds" value="${contacts.mobile }" /></c:if>
		    			</td>
						<td><c:out value="${contacts.name }"></c:out></td>
						<td><c:out value="${contacts.mobile }"></c:out></td>		
		    		</tr>
		    	</c:forEach>
		    	</tbody>
		    </table>
		  
		  </div>
		  <c:forEach items="${contactsGroupList }" var="contactsGroup">
		  	<div class="tab-pane" id="group-${contactsGroup.contactsGroup.id }">
		  		<table class="table table-striped table-hover table-condensed">
			    	<thead>
			    		<tr>
			    			<th><input type="checkbox" class="checkAll" data-target="#group-${contactsGroup.contactsGroup.id } .checkItem" /></th>
			    			<th><s:message code="contacts.name" /></th>
			    			<th><s:message code="contacts.mobile" /></th>
			    		</tr>	
			    	</thead>
			    	<tbody>	
			    	<c:forEach items="${contactsGroup.contactsList }" var="contacts">
			    		<tr>
			    			<td class="checkCol">
			    				<c:if test="${not empty contacts.mobile }"><input type="checkbox" class="checkItem" name="contactsIds" value="${contacts.mobile }" /></c:if>
			    			</td>
							<td><c:out value="${contacts.name }"></c:out></td>
							<td><c:out value="${contacts.mobile }"></c:out></td>		
			    		</tr>
			    	</c:forEach>
			    	</tbody>
			    </table>
		  	</div>
		  </c:forEach>
	  </div>
	</div>
	
    
  </div>
  <div class="modal-footer">
    <button class="btn" data-dismiss="modal" aria-hidden="true"><s:message code="btn.cancel" /></button>
    <button class="btn btn-primary" onclick="confirmContacts();"><s:message code="btn.confirm" /></button>
  </div>
</div>					
