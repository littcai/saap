/*
 * Function extension for jquery.validate 
 */

/**
 * 正则表达式校验.
 * param[0] 正则表达式
 */
jQuery.validator.addMethod("pattern", function(value, element, param) {	
	return this.optional(element) || (param[0].test(value));
}, "Invalid format.");

/**
 * 自定义函数校验.
 * param[0] 函数字符串，需返回 boolean表示校验成功还是失败
 */
jQuery.validator.addMethod("func", function(value, element, param) {	
	return this.optional(element) || eval(param[0]);
}, "Invalid data.");

/**
 * 受限输入校验.
 * 仅能包含中文、英文、数字或下划线
 */
jQuery.validator.addMethod("stringCheck", function(value, element) {
	return this.optional(element) || /^[\u0391-\uFFE5\w]+$/.test(value);
}, "Letters, numbers, and underscores only please");

/**
 * 字节长度区间校验.
 * 中文算2字节
 */
jQuery.validator.addMethod("byteRangeLength",
		function(value, element, param) {
			var length = value.length;
			for ( var i = 0; i < value.length; i++) {
				if (value.charCodeAt(i) > 127) {
					length++;
				}
			}
			return this.optional(element)
					|| (length >= param[0] && length <= param[1]);
},  jQuery.validator.format("Please enter a value between {0} and {1} characters long."));

jQuery.validator.addMethod("isIp", function(value, element, param) {
	return this.optional(element) || /^(25[0-5]|2[0-4]\d|[01]?\d\d?)\.(25[0-5]|2[0-4]\d|[01]?\d\d?)\.(25[0-5]|2[0-4]\d|[01]?\d\d?)\.(25[0-5]|2[0-4]\d|[01]?\d\d?)$/i.test(value);
}, "Please enter a valid IP Address.");

/**
 * 电话号码格式校验.
 */
jQuery.validator.addMethod("isTelephone", function(value, element) {
	var tel = /^\d{3,4}-?\d{7,9}$/; //电话号码格式010-12345678   
	return this.optional(element) || (tel.test(value));
}, "Please enter a valid Telephone.");

/**
 * 邮政编码校验.
 */
jQuery.validator.addMethod("isZipCode", function(value, element) {
	var tel = /^[0-9]{6}$/;
	return this.optional(element) || (tel.test(value));
}, "Please enter a valid Zip Code.");


function setValidatorDefaults(appendElementType)
{
	/*
	 * bootstrap popover样式
	 * 注：popover且focus方式不适用于checkbox，radio之类的控件
	 */
	jQuery.validator.setDefaults({
		
		showErrors: function(errorMap, errorList) {
			  
			  $.each( this.successList , function(index, value) {
				  $(value).popover('hide');
			  });					  
			
			  $.each( errorList , function(index, value) { 						  
				   var _popover = $(value.element).popover({
						trigger: 'manual',
						placement: 'top',
						content: value.message,
						template: '<div class="popover"><div class="arrow"></div><div class="popover-inner"><div class="popover-content"><p></p></div></div></div>'
					});
			
				   _popover.data('popover').options.content = value.message;
				   
				   $(value.element).popover('show');	

			  });
		}		  
		
	});
}
