<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.jspf"%>
<%@ include file="/common/taglibs.jspf"%>
<html lang="en">
<head>
  <!-- kindeditor -->
  <script src="${contextPath }/static/widgets/kindeditor/kindeditor.js"></script>
  <!-- bootstrap-datepicker -->
  <link href="${contextPath}/static/widgets/bootstrap-datepicker/css/bootstrap-datepicker.css" rel="stylesheet" />
  <script src="${contextPath }/static/widgets/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
  <c:if test="${!empty SESSION_USER && !empty SESSION_USER.locale && !fn:startsWith(SESSION_USER.locale, 'en') }">
    <script src="${contextPath }/static/widgets/bootstrap-datepicker/js/locales/bootstrap-datepicker.${SESSION_USER.locale }.js"></script>
  </c:if> 
</head>
<body>
  <form id="theform" action="save.json" method="post" class="form-horizontal">
    <fieldset>
      <legend> <s:message code="common.ui.fieldset.base" />
      </legend>
      <div class="row-fluid">
        <div class="control-group">
          <label class="control-label" for="type"><s:message code="affiche.type" /></label>
          <div class="controls">
            <select id="type" name="type">
              <li:dictOptions dictType="affiche-type" />
            </select>
          </div>
        </div>
      </div>
      <div class="row-fluid">
        <div class="control-group">
          <label class="control-label" for="title"><s:message code="affiche.title" /></label>
          <div class="controls">
            <input id="title" name="title" type="text" />
          </div>
        </div>
      </div>
      <div class="row-fluid">
        <div class="control-group">
          <label class="control-label" for="expiredDate"><s:message code="affiche.expiredDate" /></label>
          <div class="controls">
            <div class="input-append date datepicker" id="expiredDate" data-date-format="yyyy-mm-dd">
                <input id="expiredDate" name="expiredDateFmt" type="text"/>
                <span class="add-on"><i class="icon-calendar"></i></span>
              </div>
          </div>
        </div>
      </div>
      <div class="row-fluid">
        <div class="control-group">
          <label class="control-label" for="content"><s:message code="affiche.content" /></label>
          <div class="controls">
            <textarea id="content" name="content" style="width:700px;height:300px;"></textarea>
          </div>
        </div>
      </div>
      <div class="row-fluid">
        <div class="control-group">
          <label class="control-label" for="content"><s:message code="affiche.isChecked" /></label>
          <div class="controls">
            <label class="radio inline"><input id="isCheckedYes" name="isChecked" type="radio" value="1" checked="checked" /> <s:message code="common.yes" /></label>&nbsp;
            <label class="radio inline"><input id="isCheckedNo" name="isChecked" type="radio" value="0" /> <s:message code="common.no" /></label>
          </div>
        </div>
      </div>

    </fieldset>

    <div class="form-actions">
      <button type="submit" class="btn btn-primary"><i class="icon-ok"></i> <s:message code="btn.save" /></button>
      <button type="button" class="btn" onclick="history.back();"> <s:message code="btn.cancel" /></button>
    </div>

  </form>
  <!--page specific plugin scripts-->
  <script type="text/javascript">
    var editor;
      $(document).ready(function() {
        $('.datepicker').datepicker({
  		  todayBtn: true,
  		  todayHighlight: true
  		});
        
        $('#theform').littFormSubmit({
          rules : {
            type : {
              required : true
            },
            title : {
              required : true
            }
          },
          beforeSerialize: function(){
            editor.sync();
          },
          success : function(reply) {
            location.href = <h:returnUrl value="index.do"></h:returnUrl>;
          }
        });
      });
      
      KindEditor.ready(function(K) {
        window.editor = K.create('#content', {
					resizeType : 1,
					allowPreviewEmoticons : false,
					allowImageUpload : false,
					items : [
						'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
						'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
						'insertunorderedlist', '|', 'emoticons', 'image', 'link']
				});
      });
    </script>
</body>
</html>