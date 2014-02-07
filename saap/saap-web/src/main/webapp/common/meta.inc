<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
<meta http-equiv="Cache-Control" content="no-store"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Expires" content="0"/> 
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<!-- 先加载样式 -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<!--basic styles-->

	<link href="${contextPath}/${theme}/css/bootstrap.min.css" rel="stylesheet" />
	<link href="${contextPath}/${theme}/css/bootstrap-responsive.min.css" rel="stylesheet" />
	<link rel="stylesheet" href="${contextPath}/${theme}/css/glyphicons.css" />	
	<link rel="stylesheet" href="${contextPath}/${theme}/css/cus-icons.css" />
	<!--[if IE 7]>
	  <link rel="stylesheet" href="${contextPath}/${theme}/css/font-awesome-ie7.min.css" />
	<![endif]-->
	<link rel="stylesheet" href="${contextPath}/${theme}/css/font-awesome.min.css" />
	
	<!--[if lt IE 9]>
	<script src="//html5shiv.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->	
	<!--page specific plugin styles-->
	<!--fonts-->

	<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />
		
	
	<!--inline styles related to this page-->
	<link href="${contextPath}/${theme}/css/main.css" rel="stylesheet" />
	
<!--basic scripts-->

		<!--[if !IE]>-->

		<script src="${contextPath}/${theme}/js/jquery-2.0.3.min.js"></script>

		<!--<![endif]-->

		<!--[if IE]>
		<script src="${contextPath}/${theme}/js/jquery-1.10.2.min.js"></script>
		<![endif]-->

		<!--[if !IE]>-->

		<script type="text/javascript">
			window.jQuery || document.write("<script src='${contextPath}/${theme}/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
		</script>

		<!--<![endif]-->

		<!--[if IE]>
		<script type="text/javascript">
		 window.jQuery || document.write("<script src='${contextPath}/${theme}/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
		</script>
		<![endif]-->

		<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='${contextPath}/${theme}/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="${contextPath}/${theme}/js/bootstrap.min.js"></script>

		<!-- common plugin scripts-->		
		<!-- jquery bootbox -->
		<script src="${contextPath }/widgets/bootbox/bootbox.min.js"></script>	
		
		<!-- jquery pnotify -->
		<link href="${contextPath}/widgets/pnotify/css/jquery.pnotify.default.css" rel="stylesheet" />
		<script src="${contextPath }/widgets/pnotify/js/jquery.pnotify.min.js"></script>	
		
		<!-- jquery form and validate -->
<script src="${contextPath }/widgets/jquery-validate/js/jquery.validate.min.js"></script>
<script src="${contextPath }/widgets/jquery-validate/js/jquery.validate.ex.js"></script>
<c:if test="${!empty SESSION_USER && !empty SESSION_USER.locale && !fn:startsWith(SESSOION_USER.locale, 'en') }">
	<script src="${contextPath }/widgets/jquery-validate/i18n/messages_${SESSION_USER.locale }.js"></script>
</c:if>
<script src="${contextPath }/widgets/jquery-form/jquery.form.min.js"></script>
<!-- jquery select2 -->
<link href="${contextPath}/widgets/jquery-select2/css/select2.css" rel="stylesheet" />
<script src="${contextPath }/widgets/jquery-select2/js/select2.min.js"></script>
		
		<script src="${contextPath}/${theme}/js/common.js"></script>
		<script src="${contextPath}/${theme}/js/i18n/messages_zh_CN.js"></script>		