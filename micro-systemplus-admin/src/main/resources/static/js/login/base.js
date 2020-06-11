/**
 * 自动去掉class值为ztrim的所有空格
 * @use <input  class="ztrim"/>
 * @author lqy
 * @since 2015-08-21
*/
$(":input").filter(".ztrim").keyup(function(){
	if(/\s/.test($(this).val())){//判断有没有空格，有则去掉
		var z_reg = /\s/g;
		$(this).val($(this).val().replace(z_reg,""));
	}
});

/**
* 是否为数字
* @param str 传入的字符串值
* @author likh
* @since 2016-10-21
*/
function isNumber(str){ 
	if(str == "") return true;
	if(!str) return false; 
	var strP=/^\d+(\.\d+)?$/; 
	if(!strP.test(str)) return false; 
	try{ 
		if(parseFloat(str)!=str) return false; 
	}catch(ex){ 
		return false; 
	} 
	return true; 
} 

/**
* 去掉字符串头尾空格
* @param str 传入的字符串值
* @author lqy
* @since 2015-08-21
*/
function trim(str) {
	if(str == null){
		return "";
	}
    return str.replace(/(^\s*)|(\s*$)/g, "");
};

/** 
 * 是否为Null 
 * @param object 
 * @returns {Boolean} 
 */  
function isNull(object){  
    if(object == null || typeof object == "undefined"){  
        return true;  
    }  
    return false;  
}; 

/** 
 * 是否为空字符串，有空格不是空字符串 
 * @param str 
 * @returns {Boolean} 
 */  
function isEmpty(str){  
    if(str == null || typeof str == "undefined" ||   
            str == ""){  
        return true;  
    }  
    return false;  
};

/** 
 * 是否为空字符串，全空格也是空字符串 
 * @param str 
 * @returns {Boolean} 
 */  
function isBlank(str){  
    if(str == null || typeof str == "undefined" ||   
            str == "" || trim(str) == ""){  
        return true;  
    }  
    return false;  
};


/**
 * 时间格式化
 * @user 使用方法：var now = new Date().format('yyyy-MM-dd hh:mm:ss');
 * @author lqy
 * @since 2015-08-21
 */
Date.prototype.format = function(format)
{
    var o = {
    "M+" : this.getMonth()+1, //month
    "d+" : this.getDate(),    //day
    "h+" : this.getHours(),   //hour
    "m+" : this.getMinutes(), //minute
    "s+" : this.getSeconds(), //second
    "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
    "S" : this.getMilliseconds() //millisecond
    };
    if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
    (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)if(new RegExp("("+ k +")").test(format))
    format = format.replace(RegExp.$1,
    RegExp.$1.length==1 ? o[k] :
    ("00"+ o[k]).substr((""+ o[k]).length));
    return format;
};

/**
 * 格式化日期，变为短日期 yyyy-MM-dd
 * @param val
 */
function formatShortDate(val){
	if(val)
		return val.substr(0,10);
	return val;
}

/**
 * 设置为主页 
 * @user 使用方法：setHome(this, '')
 * @author lqy
 * @since 2015-08-21
 */
function setHome(obj,vrl){
	try{ 
		obj.style.behavior='url(#default#homepage)';obj.setHomePage(vrl); 
	} catch(e){ 
		if(window.netscape) { 
			try { 
				netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect"); 
			}catch (e) { 
				alert("此操作被浏览器拒绝！\n请在浏览器地址栏输入“about:config”并回车\n然后将 [signed.applets.codebase_principal_support]的值设置为'true',双击即可。"); 
			} 
			var prefs = Components.classes['@mozilla.org/preferences-service;1'].getService(Components.interfaces.nsIPrefBranch); 
			prefs.setCharPref('browser.startup.homepage',vrl); 
		}else{ 
			alert("您的浏览器不支持，请按照下面步骤操作：\n1.打开浏览器设置。\n2.点击设置网页。\n3.输入："+vrl+"点击确定。"); 
		} 
	} 
};

/**
 * 加入收藏 兼容360和IE6 
 * @user setFavorite('官网', 'http://www.mkst.com/')
 * @author lqy
 * @since 2015-08-21
 */
function setFavorite(sTitle,sURL) { 
	try { 
		window.external.addFavorite(sURL, sTitle); 
	} catch (e) { 
		try { 
			window.sidebar.addPanel(sTitle, sURL, ""); 
		} catch (e) { 
			alert("加入收藏失败，请使用Ctrl+D进行添加"); 
		} 
	} 
};

function removeLastCode(str){
	if(str == null || str.length < 1){
		return str;
	}
	return str.substring(0, str.length-1);
};

/**
 * 获取焦点后光标在字符串后
 * @author lqy
 * @since 2015-08-21
 */
function inputFocus(id){
	var t=$("#"+id).val(); 
	$("#"+id).val("").focus().val(t);
};


/**
* 去掉字符串所有空格
* @param str 传入的字符串值
* @author lqy
* @since 2015-08-21
*/
function removeSpace(str){
	if(str == null){
		str = "";
	}
	return str.replace(/\s/g, "");
};


/**
 * 判断是否为空字符串，空返回True，否则返回False
 * @param z_check_value 要检查的值
 * @return true or false
 * @author lqy
 * @since 2015-08-21
*/
function isEmpty(z_check_value){
	if(z_check_value == null || $.trim(z_check_value) == ""){
		return true;
	}else{
		return false;
	}
};

/**
 * 检查字符之间是否有空格，有空格返回true，否false
 * @param z_check_value 要检查的值
 * @return 有空格返回true，否false
 * @author lqy
 * @since 2015-08-21
*/
function checkSpace(z_check_value){
	if(isEmpty(z_check_value)){
		return false;
	}else{
		var z_reg = /\s/g;
		return z_reg.test($.trim(z_check_value));
	}
};

/**
 * 检查手机号码
 * @param z_check_value 要检查的值
 * @return 符合返回true，否false
 * @author lqy
 * @since 2015-08-21
*/
function checkPhone(z_check_value) {
	if(isEmpty(z_check_value) || z_check_value.length != 11){
		return false;
	}
	var z_reg = /^13[0-9]{9}|15[012356789][0-9]{8}|18[0-9]{9}|(14[57][0-9]{8})|(17[015678][0-9]{8})$/;
	return z_reg.test(z_check_value);
};

/**
 * 检查手机号码
 * @param z_check_value 要检查的值
 * @param required 是否必填 boolean
 * @return 符合返回true，否false
 * @author lqy
 * @since 2015-08-21
*/
function checkPhoneWithRequired(z_check_value,required) {
	if(isEmpty(z_check_value)){
		if (required) {
			return false;
		}else{
			return true;
		}
	}else{
		return checkPhone(z_check_value);
	}
};


/**
 * 检查电子邮箱
 * @param z_check_value 要检查的值
 * @return 符合返回true，否false
 * @author lqy
 * @since 2015-08-21
*/
function checkEmail(z_check_value){
	//var emailReg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	var z_reg =  /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
	return z_reg.test($.trim(z_check_value));
};

/**
 * 检查电子邮箱
 * @param z_check_value 要检查的值
 * @param required boolean 是否必填 （true为必填，false否）
 * @return 符合返回true，否false
 * @author lqy
 * @since 2015-08-21
*/
function checkEmailWithRequired(z_check_value,required){
	if(isEmpty(z_check_value)){
		if (required) {
			return false;
		}else{
			return true;
		}
	}else{
		return checkEmail(z_check_value);
	}
};


/**
 * 检查登录名（由数字、26个英文字母或者下划线组成的字符串）
 * @param z_check_value 要检查的值
 * @return 符合返回true，否false
 * @author lqy
 * @since 2015-12-29
*/
function checkLoginName(z_check_value){
	var z_reg = /^\w+$/;
	return z_reg.test($.trim(z_check_value));
};

/**
 * 检查电话号码
 * @param z_check_value 要检查的值
 * @return 符合返回true，否false
 * @author lqy
 * @since 2015-08-21
*/
function checkTelePhone(z_check_value){
	var z_reg = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,4}))?$/;
	return z_reg.test($.trim(z_check_value));
};

/**
 * 检查电话号码
 * @param z_check_value 要检查的值
 * @param required boolean 是否必填 （true为必填，false否）
 * @return 符合返回true，否false
 * @author lqy
 * @since 2015-08-21
*/
function checkTelePhoneWithRequired(z_check_value,required){
	if(isEmpty(z_check_value)){
		if (required) {
			return false;
		}else{
			return true;
		}
	}else{
		return checkTelePhone(z_check_value);
	}
};

/**
 * 检查仅中文
 * @param z_check_value 要检查的值
 * @return 符合返回true，否false
 * @author lqy
 * @since 2015-08-21
*/
function checkChinese(z_check_value){
	var z_reg = /^[\u4E00-\u9FA5\uF900-\uFA2D]+$/;
	return z_reg.test($.trim(z_check_value));
};

/**
 * 检查仅中文
 * @param z_check_value 要检查的值
 * @param required boolean 是否必填 （true为必填，false否）
 * @return 符合返回true，否false
 * @author lqy
 * @since 2015-08-21
*/
function checkChineseWithRequired(z_check_value,required){
	if(isEmpty(z_check_value)){
		if (required) {
			return false;
		}else{
			return true;
		}
	}else{
		return checkChinese(z_check_value);
	}
};

/**
 * 设置select选中
 * @param selectId select的id值
 * @param checkValue 选中option的值
 * @author lqy
 * @since 2015-08-21
*/
function setOptionSelected(selectId, checkValue){
	var select = document.getElementById(selectId);
	for(var i=0; i<select.options.length; i++){
		if(select.options[i].innerHTML == checkValue){
			select.options[i].selected = true;
			break;
		}
	}
};

/**
 * 获取checkbox选中数量
 * @param name checkbox的name值
 * @author lqy
 * @since 2015-08-21
*/
function getCheckBoxLength(name){
	var length = 0;
	var checkboxes;
	if(name == null || name == ""){
		checkboxes = document.getElementsByTagName("input");
		for(var j=0;j<checkboxes.length;j++){
			if(checkboxes[j].type=='checkbox' && checkboxes[j].checked){
				length+=1;
			}
		}
	}else{
		checkboxes = document.getElementsByName(name);
		for(var i=0;i<checkboxes.length;i++){
			if(checkboxes[i].checked){
				length+=1;
			}
		}
	}
	return length;
};

function getCheckedLength(name){
	var length = 0;
	if(name == null || name == ""){
		alert("请添加参数name");
	}else{
		var inputs = document.getElementsByName(name);
		for(var i=0;i<inputs.length;i++){
			if(inputs[i].checked){
				length += 1;
			}
		}
	}
	return length;
};

function getCheckedValue(name){
	var value = null;
	if(name == null || name == ""){
		alert("请添加参数name");
	}else{
		var inputs = document.getElementsByName(name);
		for(var i=0;i<inputs.length;i++){
			if(inputs[i].checked){
				value = inputs[i].value;
				break;
			}
		}
	}
	return value;
};

/**
 * 身份证省份编号
 */
var vcity={ 11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",  
    21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",  
    33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",  
    42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",  
    51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",  
    63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"  
};  

//检查号码是否符合规范，包括长度，类型
function isCardNo(card){
    //身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X
    var reg = /(^\d{15}$)|(^\d{17}(\d|X)$)/;
    if(reg.test(card) == false){
        return false;
    }
    return true;
};

//取身份证前两位,校验省份
function checkProvince(card){
    var province = card.substr(0,2);
    if(vcity[province] == undefined){
        return false;
    }
    return true;
};

//检查生日是否正确
function checkBirthday(card){
    var len = card.length;
    //身份证15位时，次序为省（3位）市（3位）年（2位）月（2位）日（2位）校验位（3位），皆为数字
    if(len == '15'){
        var re_fifteen = /^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/; 
        var arr_data = card.match(re_fifteen);
        var year = arr_data[2];
        var month = arr_data[3];
        var day = arr_data[4];
        var birthday = new Date('19'+year+'/'+month+'/'+day);
        return verifyBirthday('19'+year,month,day,birthday);
    }
    //身份证18位时，次序为省（3位）市（3位）年（4位）月（2位）日（2位）校验位（4位），校验位末尾可能为X
    if(len == '18'){
        var re_eighteen = /^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/;
        var arr_data = card.match(re_eighteen);
        var year = arr_data[2];
        var month = arr_data[3];
        var day = arr_data[4];
        var birthday = new Date(year+'/'+month+'/'+day);
        return verifyBirthday(year,month,day,birthday);
    }
    return false;
};

//校验日期
function verifyBirthday(year,month,day,birthday){
    var now = new Date();
    var now_year = now.getFullYear();
    //年月日是否合理
    if(birthday.getFullYear() == year && (birthday.getMonth() + 1) == month && birthday.getDate() == day){
        //判断年份的范围（3岁到100岁之间)
        var time = now_year - year;
        if(time >= 3 && time <= 100){
            return true;
        }
        return false;
    }
    return false;
};

//校验位的检测
function checkParity(card){
    //15位转18位
    card = changeFivteenToEighteen(card);
    var len = card.length;
    if(len == '18'){
        var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
        var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'); 
        var cardTemp = 0, i, valnum; 
        for(i = 0; i < 17; i ++){ 
            cardTemp += card.substr(i, 1) * arrInt[i]; 
        } 
        valnum = arrCh[cardTemp % 11]; 
        if (valnum == card.substr(17, 1)){
            return true;
        }
        return false;
    }
    return false;
};
//15位转18位身份证号
function changeFivteenToEighteen(card){
    if(card.length == '15'){
        var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
        var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'); 
        var cardTemp = 0, i; 
        card = card.substr(0, 6) + '19' + card.substr(6, card.length - 6);
        for(i = 0; i < 17; i ++){ 
            cardTemp += card.substr(i, 1) * arrInt[i]; 
        } 
        card += arrCh[cardTemp % 11]; 
        return card;
    }
    return card;
};

/**
 * 身份证验证
 * @param card
 * @returns {Boolean}
 * @author lqyao
 * @since 2015-09-15
 */
function isIdCardNo(card){
    //是否为空
    if(card == null || card == ''){
        return false;
    }
    //校验长度，类型
    if(isCardNo(card) == false){
        return false;
    }
    //检查省份
    if(checkProvince(card) == false){
        return false;
    }
    //校验生日
    if(checkBirthday(card) == false){
        return false;
    }
    //检验位的检测
    if(checkParity(card) == false){
        return false;
    }
    return true;
};

function changeSymbol(str, oldSymbol, newSymbol){
	var returnString = "";
	if(str != null && $.trim(str) != ""){
		var arrayStr =str.split(oldSymbol);
		if(arrayStr != null && arrayStr.length > 0){
			for(var i=0; i<arrayStr.length; i++){
				if(arrayStr[i] != null && $.trim(arrayStr[i]) != ""){
					returnString += arrayStr[i] + newSymbol;
				}
			}
		}
	}
	if(returnString.indexOf(newSymbol) > -1){
		returnString = returnString.substring(0, returnString.length - 1);
    }
	return returnString;
};

//文档：http://layer.layui.com/api.html
/**
 * 根据Url打开窗口
 * @param title 窗口标题
 * @param url (建议绝对路径) 如：basePath + '/coOrgEdit.do'
 */
function openWithTitle(title, url){
	if(isBlank(title)){
		title = "窗口";
	}
	return layer.open({
		type: 2,
		title: title,
		shadeClose: true,
		area: ['98%', '98%'],
		maxmin: true,
		content: url
	});
};

/**
 * 根据Url打开窗口
 * @param url (建议绝对路径) 如：basePath + '/coOrgEdit.do'
 */
function openUrl(url){
	openWithTitle(null, url);
};


function verifyNumber(v){
	return /^[1-9][0-9]*$/.test(v);
}
/**
 * 提示
 * @param content 内容
 * @param icon 图标，1为绿色勾，2为红色叉
 */
function tip(content,icon){
	layer.msg(content, {time: 2000,icon:icon,shade:0.5,shadeClose:true});
};

/**
 * 错误提示
 * @param content 内容
 */
function error(content){
	tip(content, 2);
};

/**
 * 错误提示，后面有return;语句
 * @param content 内容
 */
function errorBreak(content){
	error(content);
	return;
};

/**
 * 成功
 * @param content 内容
 */
function success(content){
	tip(content, 1);
};
/**
 * 计算文件大小
 * @param value
 * @returns {String}
 */
function renderFileSize(value){
	var result = '';
	if(value/1024 > 10){
		result = (value/(1024*1024)).toFixed(2) + 'M';
	}else{
		result = (value/(1024)).toFixed(2) + 'K';
	}
	 	return result;
};
function formattime(str,fmt){
	if(str==""||str=="null"||str=="NULL"||(typeof str)=="undefined"){
		return "";
	}
	var time = new Date(Date.parse(str.replace(/-/g,"/")));
	return time.format(fmt);
}
/**
 * 禁用删除键
 * @param e
 * @returns {Boolean}
 * @author lqyao
 */
function forbidBackSpace(e) {
   var ev = e || window.event; //获取event对象 
   var obj = ev.target || ev.srcElement; //获取事件源 
   var t = obj.type || obj.getAttribute('type'); //获取事件源类型 
   //获取作为判断条件的事件类型 
   var vReadOnly = obj.readOnly;
   var vDisabled = obj.disabled;
   //处理undefined值情况 
   vReadOnly = (vReadOnly == undefined) ? false : vReadOnly;
   vDisabled = (vDisabled == undefined) ? true : vDisabled;
   //当敲Backspace键时，事件源类型为密码或单行、多行文本的， 
   //并且readOnly属性为true或disabled属性为true的，则退格键失效 
   var flag1 = ev.keyCode == 8 && (t == "password" || t == "text" || t == "textarea") && (vReadOnly == true || vDisabled == true);
   //当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效 
   var flag2 = ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea";
   //判断 
   if (flag2 || flag1) return false;
};
//禁止后退键 作用于Firefox、Opera
document.onkeypress = forbidBackSpace;
//禁止后退键  作用于IE、Chrome
document.onkeydown = forbidBackSpace;

 function lalert(msg,icon){
	 layer.msg(msg, {icon:icon,shade:0.2,shadeClose:true});
 };
 
 function checkHasCurAccount(){
	 var curAccountHas = $("#curAccountHas").val();
	 if(curAccountHas=="-1"){
		 return false;
	 }
	 return true;
 }
 // 文本框的输入长度校验
 $.extend($.fn.validatebox.defaults.rules, {
    minLength: {
        validator: function(value, param){
            return value.length >= param[0];
        },
        message: '请输入至少 {0}个字符。'
    },
    maxLength: {
        validator: function(value, param){
            return value.length <= param[0];
        },
        message: '不能输入超过{0}个字符。'
    }
});

/**
 * 将Datetime（2018-08-31 08:40:30）格式的时间转成Date（2018-08-31）格式
 * @param datetimeString
 * @returns
 */
function getDateFormat(datetimeString){
	var dateLength = 10;
	if(isBlank(datetimeString)){
		return "";
	}else if(datetimeString < dateLength){
		return datetimeString;
	}else{
		return datetimeString.substring(0, dateLength);
	}
};