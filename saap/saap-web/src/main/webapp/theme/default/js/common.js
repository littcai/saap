/** 
 * 设置JS的一些默认行为
 * 
 */
$(document).ready(function(){
	
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
	
	$.ajaxSetup({
		
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
				$(element).closest('.control-group')
						.removeClass('success')
						.addClass('error');
			},
			success : function(element) {
				element.text('OK!').addClass('valid')
						.closest('.control-group')
						.removeClass('error').addClass(
								'success');
			}
		    
		    
		});	
	}
	
	//设置dataTable的默认行为
	if($.fn.dataTable)
	{
		$.extend( true, $.fn.dataTable.defaults, {	
			"bPaginate": false,
			"bLengthChange": false, 
	        "bFilter": false,
	        "bInfo": false,
	        "bSort": false,
	        //"sDom": "<'row-fluid'r>t<'row-fluid'<'span6'il><'span6'p>>",
	        "sDom": "t",
			"oLanguage": {
	            //"sUrl": "${contextPath}/widgets/jquery-datatables/i18n/zh_CN.txt"
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
 * inner信息提示
 * 嵌入式信息提示，代替默认的alert
 * 
 * Function：alert
 * Descr: alert and info enhancement
 * Dependencies: bootstrap
 * Options：
 * 	    containerId: the container for messages
 * 		title: the subject of the message
 * 		message: the content of the message
 * 		type: notice, info, error, success
 * 		hide: if the message will be hide automatically
 * 		closeable: if the popup can be closed by click	
 * 		overwrite: if overwrite previous messages
 * 		position: how to insert message to container.
 * Example：$.webtools.alert({
 * 			containerId: "id",
 *			type: "info",
 *			message: "I'm superman."			
 *		 }); 
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
					if(loading)
					{
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
					}
					else
					{
						var callback = setting.success;	    	    			
    	    			(callback && typeof(callback) === "function") && callback(data, textStatus);
					}
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
		    		}	
		    		//调用回调
		    		var callback = setting.error;	    	    			
		    		(callback && typeof(callback) === "function") && callback(data, textStatus);		    		
				},	
				beforeSend: function(XMLHttpRequest) {
					loading = $.pnotify({
					    title: setting.messages.loading,
					    type: 'info',
					    icon: 'loading',
					    hide:false,
					    history: false,
					    sticker: false									    
					});
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
					sticker: false,
					closeable: true,
					position: 'top-right'	//top-left,top-center,top-right, center, bottom-left, bottom-center, bottm-right
			};
			
			options = $.extend({}, defaults, options);
			
			$.pnotify({    			
	  		    title: ""==options.title?options.message:options.title,
	  		    text: ""==options.title?"":options.message,		
	  		    type: options.type,
	  		    hide: options.hide,
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
				checkAllId: null,
				checkOneName: null
			};	
						
			var setting = $.extend({}, defaults, options);	
			
			//绑定全选和单选对象
			$('#'+ this.setting.checkAllId).bind("click", function(){
				$("INPUT[name='"+ this.setting.checkOneName +"']").attr("checked", $(this).attr("checked"));				
			});
			
			$("INPUT[name='"+ this.setting.checkOneName +"']").each(function(){
				$(this).bind("click", function(){
					if ($("input[name='"+ this.setting.checkOneName +"']:checked").length == $("input[name='"+ this.setting.checkOneName +"']").length) 
					{
						$('#'+ this.setting.checkAllId).attr("checked", true);
					}
					else{
						$('#'+ this.setting.checkAllId).attr("checked", false);
					}
						
				});
				
			});

			
			validate = function(){
				
				if($("INPUT[name='"+ this.setting.checkOneName +"']:checked").length<=0)
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
				$("INPUT[name='"+ this.setting.checkOneName +"']:checked").each(function() {
					values.push($(this).val());
				});
				return values;
			};
			
			//获得所有选中的值
			getValues = function(){
				var values = new Array();
				$("INPUT[name='"+ this.setting.checkOneName +"']:checked").each(function() {
					values.push($(this).val());
				});
				
				return values.join(',');
			};
		}		
	});
	
	/*
	 * alert组件
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
			enableValidator: true,	//是否启用表单校验
			rules: {},	//检验规则
			messages: {
				loading: "Processing...",
				error: "System error",
				success: "Success"
			},
			errorMessages:{},
			errorContainer: $([]),
			errorLabelContainer: $([]),					
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
	  $this = $(this);	
	  var loading;  
	  $this.validate({
		  	rules : setting.rules,	
		  	messages: setting.errorMessages,
		  	errorContainer: setting.errorContainer,
			errorLabelContainer: setting.errorLabelContainer,
			submitHandler: function(form) { 			
				
				$(form).ajaxSubmit({ 							   
			    	dataType:  'json',         
			    	success:   function(data, textStatus){
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
