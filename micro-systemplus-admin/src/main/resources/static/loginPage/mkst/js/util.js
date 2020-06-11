function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}

function toggleClass(target, className, callback) {
    $('.' + target).each(function(){
        $(this).click(function(e){
            $('.' + target).removeClass(className);
            $(this).addClass(className);
            if (callback) {
                callback($(this));
            }
        })
    });
}

function addAnimate(dom, className, callback) {
    $(dom).removeClass(className);
    $(dom).addClass(className);
    if (callback) {
        callback();
    }
    setTimeout(() => {
        $(dom).removeClass(className);
    }, 500);
}