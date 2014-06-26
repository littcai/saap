<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.jspf"%>
<%@ include file="/common/taglibs.jspf"%>
<html lang="en">
<head>
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
            <input id="title" name="title" placeholder="" type="text" />
          </div>
        </div>
      </div>
      <div class="row-fluid">
        <div class="control-group">
          <label class="control-label" for="expiredDate"><s:message code="affiche.expiredDate" /></label>
          <div class="controls">
            <input id="expiredDate" name="expiredDate" placeholder="" type="text" />
          </div>
        </div>
      </div>
      <div class="row-fluid">
        <div class="control-group">
          <label class="control-label" for="content"><s:message code="affiche.content" /></label>
          <div class="controls">
            <input id="content" name="content" placeholder="" type="text" />
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
      $(document).ready(function() {

        $("#customerId").select2();

        $('#theform').littFormSubmit({
          rules : {
            name : {
              required : true
            },
            gender : {
              required : true
            },
            mobile : {
              required : true
            },
            email : {
              required : true,
              email : true
            },
            phone : {
              maxlength : 50
            },
            fax : {
              maxlength : 50
            },
            address : {
              maxlength : 200
            },
            zipCode : {
              maxlength : 50
            }
          },
          success : function(reply) {
            location.href = <h:returnUrl value="index.do"></h:returnUrl>;
          }
        });
      });
    </script>
</body>
</html>