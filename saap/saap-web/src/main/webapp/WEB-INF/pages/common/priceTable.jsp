<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.jspf"%>
<%@ include file="/common/taglibs.jspf"%>
<html>
  <head>
  <style type="text/css">
  /***
Pricing table
***/
.pricing-table {
  border: 3px solid transparent;
  padding: 10px;
  background-color: #f1f2f2;
}

.pricing-table:hover {
  border-color: #4b8df8;
}

.pricing-table h3 {
  margin-left: -2px;
  padding-left: 0px;
  font-size: 26px;
  margin-bottom: 5px;
  line-height: 26px;
  color: #111;
  margin-top: 0px;
}

.pricing-table .descr {
  margin-bottom: 10px;
  padding-bottom: 15px;
  color: #666;
  border-bottom: 1px solid #ddd;
}

.pricing-table ul {
  margin: 0px;
  margin-bottom: 15px;
  padding: 0px;
  list-style: none;
}

.pricing-table ul li {
  padding: 6px 0px;
  padding-left: 11px;
  font-size: 13px;
  line-height: 13px;
  color: #666;
}

.pricing-table ul li i {
  position: absolute;
  margin-right: 0px;
  margin-top: -2px;
  margin-left: -17px;
  color: #35aa47;
  font-size: 16px;
}

.pricing-table .rate {
  border-top: 1px solid #ddd;
  margin-bottom: 10px; 
  padding-top: 15px;
  clear: both;
}

.pricing-table.selected .rate {
  border-top-color: #fff;
}

.pricing-table .rate:before,
.pricing-table .rate:after {
  display: table;
  line-height: 0;
  content: "";
}
.pricing-table .rate:after {
  clear: both;
}

.pricing-table .rate .price {
  display: inline-block;
  float: left; 
  clear: both;
}

.pricing-table .rate .btn {
  margin-top: 3px;
  float: right;
  display: block;
}

.pricing-table .rate .price .currency {
  padding-top: 4px;
  float: left;
  width: 50px;
  text-align: right;
  font-size: 13px;
  line-height: 14px;
  font-weight: 300;
  margin-right: 2px;
}

.pricing-table .rate .price .amount {
  padding-top: 4px;  
  letter-spacing: -3px;
  float: left;
  text-align: right;
  font-size: 36px;
  line-height: 30px;
  font-weight: 300;
}

.pricing-table.selected {
  background-color: #4b8df8;
}

.pricing-table.selected:hover {
  border-color: #ddd;
}

.pricing-table.selected .descr {
  border-bottom-color: #fff;
}

.pricing-table.selected h3,
.pricing-table.selected .descr,
.pricing-table.selected ul li,
.pricing-table.selected ul li i,
.pricing-table.selected .rate {
  color: #fff;
}

.pricing-table.selected a {
  color: #fff;
}

.pricing-table.selected a:hover {
  color: #bbb;
}
  </style>	
  </head>
  <body>	
    <div class="row-fluid">
      <c:forEach items="${tenantDefList }" var="tenantDef">
      <div class="span3">
        <div class="pricing-table ${'business' eq tenantDef.code?'selected':'' }">
          <h3>${tenantDef.code }</h3>
          <div class="descr"><s:message code="tenantType.descr.${tenantDef.code }"></s:message></div>
          <ul>
            <li><i class="icon-chevron-right"></i><s:message code="tenantType.maxMembers"></s:message>:&nbsp;<b><c:choose>
                  <c:when test="${tenantDef.maxMembers<=0 }"><s:message code="tenantType.ultimate"></s:message></c:when>
                  <c:otherwise>${tenantDef.maxMembers }</c:otherwise>
                  </c:choose></b>
            </li>
            <li><i class="icon-chevron-right"></i><s:message code="tenantType.maxStorage"></s:message>:&nbsp;<b><c:choose>
                  <c:when test="${tenantDef.maxStorage<=0 }"><s:message code="tenantType.ultimate"></s:message></c:when>
                  <c:otherwise>${tenantDef.maxStorage }</c:otherwise>
                  </c:choose></b>
            </li>
            <li><i class="icon-chevron-right"></i><s:message code="tenantType.modules"></s:message>:&nbsp;<b><c:choose>
                  <c:when test="${tenantDef.modules<=0 }"><a href="javascript:;" onclick="showModuleDetail('${tenantDef.code }')"><s:message code="tenantType.ultimate"></s:message></a></c:when>
                  <c:otherwise><a href="javascript:;" onclick="showModuleDetail('${tenantDef.code }')">${tenantDef.modules }</a></c:otherwise>
                  </c:choose></b>
            </li>
            <li><i class="icon-chevron-right"></i><s:message code="tenantType.support"></s:message>:&nbsp;<b>${tenantDef.support }</b></li>
          </ul>
          <div class="rate">
            <div class="price">
              <div class="currency">¥<br/><s:message code="tenantType.unit"></s:message></div>
              <div class="amount">${tenantDef.price }</div>
            </div>
            <a class="btn green" href="order.do?tenantTypeCode=${tenantDef.code }"><s:message code="tenant.func.buy"></s:message>&nbsp;<i class="icon-hand-right"></i></a>
          </div>
        </div>
      </div>
      </c:forEach>
    </div>
  </body>
</html>
