/** 
 * 设置JS的一些默认行为
 * 
 */
$(document).ready(function(){
	
	/*
	 * fieldset支持点击legend隐藏显示
	 * 用法：为fieldset增加collapsible属性
	 */
	$('fieldset.collapsible > legend').prepend('<i class="icon-chevron-down"></i>&nbsp;');
	$('fieldset.collapsible > legend').click(function () {
	    var $divs = $(this).siblings();
	    $divs.slideToggle();
	    	    
      	$(this).find('i').toggleClass('icon-chevron-down').toggleClass('icon-chevron-up');  
	});
	
	/*
	 * 初始化按钮的loading功能，点击后将显示Loading字样，并且按钮被disabled掉，无法连续点击，防止二次提交
	 * 超过3秒后按钮将恢复原状
	 */
	$('button[data-loading-text]').click(function () {
	    var btn = $(this).button('loading');
	    setTimeout(function () {
	        btn.button('reset');
	    }, 1500);
	});
	
	/*
	 * checkbox in dropdown-menu which can be clicked and won't hide menu
	 */
	$("ul.dropdown-menu").on("click", "[data-stopPropagation]", function(e) {
    e.stopPropagation();
	});
	
	$.ajaxSetup({
	  statusCode: {404: function() {
	      alert('Service not available');
	    }
	  },
		error : function (XMLHttpRequest, textStatus, errorThrown) {  		
			//如果用户未登录，则跳转到登录页面
			$.pnotify({    			
    		    title: eval("(" + XMLHttpRequest.responseText +  ")").exception,
    		    type: 'error',
    		    hide: true,
    		    history: false,    		    
    		    sticker: false
    		});			
		}		
	});
	
	//form校验的默认行为
	if(jQuery.validator)
	{
		jQuery.validator.setDefaults({
			errorElement: 'div',
			//errorClass: 'help-block',
		  errorPlacement: function(error, element) {     
		    	
		      // if the input has a prepend or append element, put the validation msg after the parent div
		      if(element.parent().hasClass('input-prepend') || element.parent().hasClass('input-append')) {
		        error.insertAfter(element.parent());		
		      // else just place the validation message immediatly after the input
		      } else {
		        error.insertAfter(element);
		      }
		    },
		    //errorElement: "small", // contain the error msg in a small tag
		    //wrapper: "span", // wrap the error message and small tag in a div
		    //highlight: function(element) {
		    //  $(element).closest('.control-group').addClass('error'); // add the Bootstrap error class to the control group
		    //},
		    //success: function(element) {
		    //  $(element).addClass('valid')
		    //  	.closest('.control-group').removeClass('error'); // remove the Boostrap error class from the control group
		    //}
		    
		    highlight : function(element) {
//		      $(element).closest('.control-group')
//						.removeClass('success')
//						.addClass('error');
		      $(element).removeClass('success').addClass('error');
			},
			success : function(element) {
				//element.closest('.control-group').removeClass('error').addClass('success');
				//element.removeClass('error').addClass('success');
				element.remove();
			}
		    
		    
		});	
	}
	
	//设置dataTable的默认行为
	if($.fn.dataTable)
	{
		$.extend( true, $.fn.dataTable.defaults, {	
		  "ordering": false,
			"paging": false,
			"lengthChange": false, 
	        "searching": false,
	        "info": false,
	        "ordering": false,
	        //"sDom": "<'row-fluid'r>t<'row-fluid'<'span6'il><'span6'p>>",
	        "dom": "t",
			"language": {
	            //"url": "${contextPath}/widgets/jquery-datatables/i18n/zh_CN.txt"
	        }		
		});	
	}
	
});	

/**
 * webtools工具箱
 * namespace:webtools
 * 
 * ----------------------------------------------
 * Function：ajax
 * Descr: ajax enhancement
 * Dependencies: jquery.pnotify
 * Options：
 * 		type: POST or GET
 * 		url: target
 * 		params:	
 * 		isShowLoading: true
 * 		messageDelay: 1500
 * 		success: callback function when success
 * 		error: 	callback function when error
 * Example：$.webtools.ajax({
 *			url: "delete.json",
 *			params: {"id":id},
 *			success: function(reply) {
 *				location.reload();
 *			}
 *		 });	
 * ----------------------------------------------
 * 
 * ----------------------------------------------
 * Popup信息提示
 * 弹出式信息提示，代替默认的alert
 * 
 * Function：notify
 * Descr: alert and info enhancement
 * Dependencies: jquery.pnotify
 * Options：
 * 		title: the subject of the popup
 * 		message: the content of the popup
 * 		type: notice, info, error, success
 * 		hide: if the popup will be hide automatically
 * 		closeable: if the popup can be closed by click	
 * 		sticker: if the popup will be stick
 * 		error: 	callback function when error
 * Example：$.webtools.notify({
 *			type: "info",
 *			message: "I'm superman."			
 *		 }); 
 * ----------------------------------------------
 * 
 * ----------------------------------------------
 * 
 * 
 * 
 */
//创建一个闭包    
(function($) {    
	
	$.extend({ webtools: { messages:{} } });	//增加一个namespace，防止方法名冲突
	
	/*
	 * Ajax组件
	 */
	$.extend($.webtools, {
		ajax: function(options)
		{
			var defaults = {
					
				messages: {
					loading: "Processing...",
					error: "System error",
					success: "Success"
				},	
				type: "POST",
				url: "",
				params: {},
				isShowLoading: true,
				messageDelay: 1500,
				success: null,
				error: null
			};
			var setting = $.extend({}, defaults.messages, $.webtools.messages);		//继承webtools的消息，实现默认国际化
			setting = $.extend({}, defaults, options);				
			var loading;			
			
			$.ajax({
				
				type: setting.type,
				url: setting.url,
				data: setting.params, 
				success: function(data, textStatus){
					if(setting.isShowLoading && loading)
					{
						var _opts = {
			    				icon: "icon-ok-sign",	
			    				title: setting.messages.success,
			    	    		type: 'success',
			    	    		hide: true,
			    	    		delay: setting.messageDelay,
			    	    		before_close: function(pnotify) {
			    	    			var callback = setting.success;	    	    			
			    	    			(callback && typeof(callback) === "function") && callback(data, textStatus);
			    	    		}
			    		}; 
			    		loading.pnotify(_opts);
					}
					else
					{
			    		var callback = setting.success;	    	    			
	    	    		(callback && typeof(callback) === "function") && callback(data, textStatus);
					}
				},
				error: function(XMLHttpRequest, textStatus, errorThrown){					    		
		    		if(!loading)
		    		{		
		    			loading = $.pnotify({
						    title: setting.messages.loading,
						    type: 'info',
						    icon: 'loading',
						    hide:false,
						    history: false,
						    sticker: false									    
						});
		    		}
		    		//效果：将loading转换为error
	    			var _opts = {
	    				icon: "icon-warning-sign",	
	    				title: eval("(" + XMLHttpRequest.responseText +  ")").exception,
	    	    		type: 'error',
	    	    		hide: true
	    			}; 
	    			loading.pnotify(_opts);
		    		//调用回调
		    		var callback = setting.error;	    	    			
		    		(callback && typeof(callback) === "function") && callback(data, textStatus);		    		
				},	
				beforeSend: function(XMLHttpRequest) {
					if(setting.isShowLoading)
					{
						loading = $.pnotify({
						    title: setting.messages.loading,
						    type: 'info',
						    icon: 'loading',
						    hide:false,
						    history: false,
						    sticker: false									    
						});
					}
				}
			}); 
			
		}
	});
	
	/*
	 * notify组件
	 */
	$.extend($.webtools, {
		notify: function(options)
		{
			var defaults = {
					title: "",
					message: "",
					type: 'info',
					hide: true,
					delay: 1500,
					sticker: false,
					closeable: true,
					position: 'top-right',	//top-left,top-center,top-right, center, bottom-left, bottom-center, bottm-right
					callback: null
			};
			
			options = $.extend({}, defaults, options);
			
			$.pnotify({    			
	  		    title: ""==options.title?options.message:options.title,
	  		    text: ""==options.title?"":options.message,		
	  		    type: options.type,
	  		    hide: options.hide,
	  		    delay: options.delay,
	  		    history: false,    		    
	  		    sticker: options.sticker,
	  		    before_open: function(pnotify){
	  		    	if("center"==options.position)
	  		    	{
		  		      pnotify.css({
		  		        //"top": Math.max(0, (($(window).height() - $(this).outerHeight()) / 2) + $(window).scrollTop()) + "px",
		  		    	//"left": Math.max(0, (($(document).width() - $(this).outerWidth()) / 2) + $(window).scrollLeft()) + "px"
		  		    	"top": "100px",		  		       
		  		    	"left": ($(window).width() / 2) - (pnotify.width() / 2)
		  		      });
	  		    	}  
	  		    },
            before_close: function(pnotify) {
              var callback = options.callback;                 
              (callback && typeof(callback) === "function") && callback();
            }
	  		});				
		}
	});
	
	/*
	 * 数据表格多选处理
	 * 
	 * 1、映射全选与单选框，实现选择联动
	 * 2、获取所有选中单选框的值，以数组或字符串的形式
	 */
	$.extend($.webtools, {				
		checkboxs: function(options)
		{
			var defaults = {
				checkAll: "#checkAll",	//选择器或jquery对象
				checkItem: ".checkItem"
			};				
			var setting = $.extend({}, defaults, options);	
			
			//绑定全选和单选对象
			var checkAll;
			if(setting.checkAll instanceof jQuery)
				checkAll = setting.checkAll;
			else{
				checkAll = $(setting.checkAll);
			}
			
			checkAll.bind("click", function(){
				$(setting.checkItem).prop("checked", $(this).is(':checked'));				
			});
			
			$(setting.checkItem).each(function(){
				$(this).bind("click", function(){
//					if ($(setting.checkItem+":checked").length == $(setting.checkItem).length) 
//					{
//						checkAll.prop("checked", true);
//					}
//					else{
//						checkAll.prop("checked", false);
//					}
					if ($(setting.checkItem+":checked").length == 0) 
					{
						checkAll.prop("checked", false);
					}
					else{
						checkAll.prop("checked", true);
					}
						
				});
				
			});

			/*
			validate = function(){
				
				if($("INPUT[name='"+ setting.checkOneName +"']:checked").length<=0)
				{
					$.webtools.notify({
						type: "notice",
						message: "请至少选择一项"
					});
				}
			};	
			
			//获得所有选中的值的数组
			getValueArray = function(){
				var values = new Array();
				$("INPUT[name='"+ setting.checkOneName +"']:checked").each(function() {
					values.push($(this).val());
				});
				return values;
			};
			
			//获得所有选中的值
			getValues = function(){
				var values = new Array();
				$("INPUT[name='"+ setting.checkOneName +"']:checked").each(function() {
					values.push($(this).val());
				});
				
				return values.join(',');
			};
			*/
		}		
	});
	
	/*
	 * 数据表格多选处理
	 * 
	 * 获得复选框的值，用逗号分隔
	 */
	$.extend($.webtools, {				
		getCheckValues: function(checkItem)
		{			
			var values = new Array();
			$(checkItem+":checked").each(function() {
				values.push($(this).val());
			});			
			return values.join(',');
		}
	});
	
	/*
	 * 数据表格多选处理
	 * 
	 * 获得复选框的值，用逗号分隔
	 */
	$.extend($.webtools, {				
		getCheckValuesArray: function(checkItem)
		{			
			var values = new Array();
			$(checkItem+":checked").each(function() {
				values.push($(this).val());
			});			
			return values;
		}
	});
	
	/*
	 * inner信息提示
   * 嵌入式信息提示，代替默认的alert
   * 
   * Function：alert
   * Descr: alert and info enhancement
   * Dependencies: bootstrap
   * Options：
   *      containerId: the container for messages
   *    title: the subject of the message
   *    message: the content of the message
   *    type: notice, info, error, success
   *    hide: if the message will be hide automatically
   *    closeable: if the popup can be closed by click  
   *    overwrite: if overwrite previous messages
   *    position: how to insert message to container.
   * Example：$.webtools.alert({
   *      containerId: "id",
   *      message: "I'm superman.", 
   *      type: "info",
   *      hide: false, 
   *      closeable: true,
   *      overwrite: true,
   *      position: 'append'   
   *     }); 
	 */
	$.extend($.webtools, {
		alert: function(options)
		{
			var defaults = {
					containerId: null,
					message: "",
					type: 'info',		//类型：error,success,info 
					hide: false,		//是否自动隐藏
					closeable: true,	//是否支持手工关闭
					overwrite: true,	//是否覆盖前面的消息
					position: 'append'	//插入位置：append：最后追加，prepend：最前追加
			};			
			var setting = $.extend({}, defaults, options || {}); 
			if(setting.containerId==null)
				alert("Message Container is not defined!");
			if(setting.overwrite)
				$("#"+setting.containerId).empty();
			
//			setTimeout(function () {
//		        btn.button('reset');
//		    }, 1500);
			
			var content = $("<div class='alert-message alert alert-" + setting.type + " fade in'><button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>" + setting.message + "</div>");
			if('prepend'==setting.position)
			{
				$("#"+setting.containerId).show().prepend(content);		
			}
			else
			{
				$("#"+setting.containerId).show().append(content);
			}
			if(setting.hide)
			{
				window.setTimeout(function() { content.alert('close'); }, 3000);
			}
		}
	});
	
	// 闭包结束    
})(jQuery);  

/**
 * 表单提交封装(默认使用Ajax).
 * 1、封装了表单校验，Ajax方式的表单提交，支持处理失败时回调函数，支持处理成功时回调函数
 * 1、增加提交时的状态显示，提交后成功或者失败的信息提示
 * 
 * dependencies: jquery.form, jquery.validate, jquery.pnotify
 * 
 * @author cai
 * @since 2013-10-14
 */
//创建一个闭包    
(function($) {   
	var defaults = {
			type: 'ajax',
			enableValidator: true,		//是否启用表单校验
			enableChangeCheck: true, 	//是否启用变更检查
			rules: {},	//检验规则
			messages: {
				loading: "Processing...",
				error: "System error",
				success: "Success",
				formChanged: "You have unsaved changes."
			},
			errorMessages:{},
			errorContainer: $([]),
			errorLabelContainer: $([]),
			errorPlacement: $.validator.defaults.errorPlacement,
			beforeSerialize: null,
			beforeSubmit: null,
			success: null,
			error: null
	};
	
	
	
  //定义插件
  $.fn.littFormSubmit = function(options) {
	  
	$.extend(defaults.messages, $.webtools.messages);		//继承webtools的消息，实现默认国际化 
	// build main options before element iteration    
	var setting = $.extend({}, defaults, options || {});    	
	// iterate and reformat each matched element
	return this.each(function() {    
	  var $this = $(this);
	  
	  //记录表单的变化状态，若修改并离开页面，需要提示用户保存
	  if(setting.enableChangeCheck)
	  {
		  var formValue = $this.serialize();
		  
//		  $this.data("_changed", false);		  
//		  
//		  //使用jQuery实现
//		  $this.children(":text,:password,textarea,select").change(function() {
//			  $this.data("_changed", true);
//		  });
//		  $this.children(":checkbox,:radio").change(function() {
//			  $this.data("_changed", true);
//		  });
		  
		  $(window).bind('beforeunload', function (e) {		
			  var changed = (formValue != $this.serialize());
			  if(changed)
			  {
				  //alert(formValue);
				  //alert($this.serialize());
				  return setting.messages.formChanged;
			  }
		  });
	  }	  
	  
	  var loading;  
	  $this.validate({
		  	rules : setting.rules,	
		  	messages: setting.errorMessages,
		  	errorContainer: setting.errorContainer,
			errorLabelContainer: setting.errorLabelContainer,
			errorPlacement: setting.errorPlacement,
			submitHandler: function(form) { 			
				
				$(form).ajaxSubmit({ 							   
			    	dataType:  'json',         
			    	success:   function(data, textStatus){
			    		//移除beforeunload事件
			    		if(setting.enableChangeCheck)
			    		{			    			
			    			$(window).unbind('beforeunload');
			    		}

			    		var _opts = {
			    				icon: "icon-ok-sign",	
			    				title: setting.messages.success,
			    	    		type: 'success',
			    	    		hide: true,
			    	    		delay: 1500,
			    	    		before_close: function(pnotify) {
			    	    			var callback = setting.success;	    	    			
			    	    			(callback && typeof(callback) === "function") && callback(data, textStatus);
			    	    		}
			    		}; 
			    		loading.pnotify(_opts);
			    		
			    	},
			    	beforeSerialize: function() {
			    		var callback = setting.beforeSerialize;	    	    			
			    		var valid = (callback && typeof(callback) === "function") && callback();
			    		
			    		if(valid!=null && !valid)
    	    				return valid;
			    	},
			    	beforeSubmit: function(arr) { 
			    		var callback = setting.beforeSubmit;	    	    			
    	    			var valid = (callback && typeof(callback) === "function") && callback(arr);    	
    	    			
    	    			if(valid!=null && !valid)
    	    				return valid;
			    		//processing...					    		
						loading = $.pnotify({
							    title: setting.messages.loading,
							    type: 'info',
							    icon: 'loading',
							    hide:false,
							    history: false,
							    sticker: false									    
						 });
			    	},					    	
			    	error: function(XMLHttpRequest, textStatus, errorThrown){					    		
			    		if(loading)
			    		{		
			    			
			    			//效果：将loading转换为error
			    			var _opts = {
			    				icon: "icon-warning-sign",	
			    				title: eval("(" + XMLHttpRequest.responseText +  ")").exception,
			    	    		type: 'error',
			    	    		hide: true
			    			}; 
			    			loading.pnotify(_opts);
			    			//调用回调
			    			var callback = setting.error;	    	    			
			    			(callback && typeof(callback) === "function") && callback(data, textStatus);
			    			
			    			//效果：移除loading再新的popup中显示错误
			    			//loading.pnotify_remove();
			    			//$.pnotify({    			
			        		//    title: "Error:",
			        		//    text: eval("(" + XMLHttpRequest.responseText +  ")").exception,
			        		//    type: 'error',
			        		//    history: false,    		    
			        		//    sticker: false
			        		//});	
			    		}
			    	}					    	
			  });
			  return false;						    
			}			
	  });
	    
	});    
  };	
	
// 闭包结束    
})(jQuery);  
	

/**
 * Popup信息提示
 * 弹出式信息提示，代替默认的alert
 * @deprecated 该方法已放到webtools中
 * 
 * Function：littNotify
 * Descr: alert and info enhancement
 * Dependencies: jquery.pnotify
 * Options：
 * 		title: the subject of the popup
 * 		message: the content of the popup
 * 		type: notice, info, error, success
 * 		delay: the time popup will display before close
 * 		hide: if the popup will be hide automatically
 * 		closeable: if the popup can be closed by click	
 * 		sticker: if the popup will be stick
 * 		error: 	callback function when error
 * Example：$.littNotify({
 *			type: "info",
 *			message: "I'm superman."			
 *		 });
 * 
 * @author cai
 * @since 2013-10-14
 */
//创建一个闭包    
(function($) {   
	var defaults = {
		title: "",
		message: "",
		type: 'info',
		delay: 2000,
		hide: true,
		sticker: false,
		closeable: true
	};
	
	// 插件的定义    
	$.fn.littNotify = function(options) {	
		
		 // build main options before element iteration    
	    var opts = $.extend({}, defaults, options);    	
	    
	    // iterate and reformat each matched element    
	    return this.each(function() {    
	      $this = $(this);    
	      // build element specific options    
	      var _opts = $.meta ? $.extend({}, opts, $this.data()) : opts;  
	      $.pnotify({    			
	  		    title: ""==_opts.title?_opts.message:_opts.title,
	  		    text: ""==_opts.title?"":_opts.message,		
	  		    type: _opts.type,
	  		    delay: _opts.delay,
	  		    hide: _opts.hide,
	  		    history: false,    		    
	  		    sticker: _opts.sticker
	  		});	
	    });    
	};
	
	// 闭包结束    
})(jQuery);  

/************************************
String类扩展 START
*************************************/
/**
* String类去空格的原生对象
*/
String.prototype.trim = function (){	
return this.replace(/(^\s*)|(\s*$)/g, "");		
}

/**
* String类去左空格的原生对象
*/
String.prototype.ltrim = function (){	
return this.replace(/(^\s*)/g, "");		
}

/**
* String类去右空格的原生对象
*/
String.prototype.ltrim = function (){	
return this.replace(/(\s*$)/g, "");		
}


/**
* 验证字符串是否为NULL或空
*/
String.prototype.isEmpty = function()
{
if(this.trim()=="")
	return true;
else
	return false;	
}

/**
* 是否以指定字符串结尾.
* @param {} str
* @return {Boolean}
*/
String.prototype.endWith=function(str)
{
if(str.isEmpty()||str.length>this.length)
  return false;
if(this.substring(this.length-str.length)==str)
  return true;
else
  return false;	
}

/**
* 是否以指定字符串开始.
* @param {} str
* @return {Boolean}
*/
String.prototype.startWith=function(str)
{
if(str.isEmpty()||str.length>this.length)
  return false;
if(this.substr(0,str.length)==str)
  return true;
else
  return false;
}

/**
* 获取字符串长度，中文算2个.
*/
String.prototype.ansiLength = function()
{
var len = 0;
for (var i = 0; i < this.length; i++)
{
	if (this.charCodeAt(i) > 127)
		len = len + 2;
	else
		len++;
}
return len;	
} 

/**
* 全文字符串替换(支持正则表达式)
* 
* @param toReplace 需要替换的内容
* @param replacement 替换后的内容
*/
String.prototype.replaceAll = function(toReplace,replacement){
return this.replace(new RegExp(toReplace,"gm"),replacement);
}

/**
* String类去HTML编码的原生对象
*/
String.prototype.escapeHTML = function (){	
if(this.isEmpty())
{
    return "";
} 
else
{
   var content = this;	 
       
   //Stripts the <script> tags from the html
   var scriptregex = "<script[^>.]*>[sS]*?</script>";
   var scripts = new RegExp(scriptregex, "gim");
   content = content.replace(scripts, "");

   //Stripts the <style> tags from the html
   var styleregex = "<style[^>.]*>[sS]*?</style>";
   var styles = new RegExp(styleregex , "gim");
   content = content.replace(styles, "");

   //Strips the HTML tags from the html
   var objRegExp = new RegExp("<(.| )+?>", "gim");
   content = content.replace(objRegExp, "");
   
   return content;
}		
}

/**
* XML对HTML的转码
*/
String.prototype.encodeXML=function()
{
var str=this;
str=str.replace(/\x26/g,"&#38;");
str=str.replace(/\x3c/g,"&#60;");
str=str.replace(/\x3e/g,"&#62;");
str=str.replace(/\x22/g,"&#34;");
str=str.replace(/\x27/g,"&#39;");
str=str.replace(/\x20/g,"&#160;");
return str;
}



/**
* 将内容中的HTML代码编码，用于在页面上呈现HTML的内容
* @param content 内容
* @return 编码后的内容
*/
String.prototype.encodeHTML = function()
{
var str = this;

if(str.isEmpty())
{
    return "";
} 
else
{
    var objRegExp = null;
    
    objRegExp = new RegExp("<", "g");
		str =  str.replace(objRegExp, "&lt;"); 
		objRegExp = new RegExp(">", "g");
		str =  str.replace(objRegExp, "&gt;"); 
		objRegExp = new RegExp("\"", "g");
		str =  str.replace(objRegExp, "&quot;"); 
		objRegExp = new RegExp("'", "g");
		str =  str.replace(objRegExp, "&#039;"); 
		objRegExp = new RegExp(" ", "g");
		str =  str.replace(objRegExp, "&nbsp;"); 
		objRegExp = new RegExp("\r\n", "g");
		str =  str.replace(objRegExp, "<br/>"); 
		objRegExp = new RegExp("\r", "g");
		str =  str.replace(objRegExp, "<br/>"); 
		objRegExp = new RegExp("\n", "g");
		str =  str.replace(objRegExp, "<br/>");	 
		str = "<p>"+str+"</p>";
    return str;
}
}

/************************************
String类扩展 END
*************************************/ 

/************************************
Date类扩展 START
*************************************/
/**       
 * 对Date的扩展，将 Date 转化为指定格式的String       
 * 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q) 可以用 1-2 个占位符       
 * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)       
 * eg:       
 * (new Date()).pattern("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423       
 * (new Date()).pattern("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04       
 * (new Date()).pattern("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04       
 * (new Date()).pattern("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04       
 * (new Date()).pattern("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18       
 */          
Date.prototype.pattern=function(fmt) {           
    var o = {           
    "M+" : this.getMonth()+1, //月份           
    "d+" : this.getDate(), //日           
    "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时           
    "H+" : this.getHours(), //小时           
    "m+" : this.getMinutes(), //分           
    "s+" : this.getSeconds(), //秒           
    "q+" : Math.floor((this.getMonth()+3)/3), //季度           
    "S" : this.getMilliseconds() //毫秒           
    };           
    var week = {           
    "0" : "/u65e5",           
    "1" : "/u4e00",           
    "2" : "/u4e8c",           
    "3" : "/u4e09",           
    "4" : "/u56db",           
    "5" : "/u4e94",           
    "6" : "/u516d"          
    };           
    if(/(y+)/.test(fmt)){           
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));           
    }           
    if(/(E+)/.test(fmt)){           
        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[this.getDay()+""]);           
    }           
    for(var k in o){           
        if(new RegExp("("+ k +")").test(fmt)){           
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));           
        }           
    }           
    return fmt;           
}       



/************************************
Date类扩展 END
*************************************/ 
