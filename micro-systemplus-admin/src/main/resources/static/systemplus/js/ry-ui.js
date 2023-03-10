/**
 * 通用js方法封装处理
 * Copyright (c) 2018 systemplus
 */
(function ($) {
    $.extend({
    	_treeTable: {},
    	_tree: {},
    	// 表格封装处理
    	table: {
            _option: {},
			btTable: {},
            _params: {},
            // 初始化表格参数
            init: function(options) {
                $.table._option = options;
				$.btTable = $('#' + options.id);
				$.table.initEvent();
                $.table._params = $.common.isEmpty(options.queryParams) ? $.table.queryParams : options.queryParams;
                _sortOrder = $.common.isEmpty(options.sortOrder) ? "asc" : options.sortOrder;
                _sortName = $.common.isEmpty(options.sortName) ? "" : options.sortName;
                _striped = $.common.isEmpty(options.striped) ? false : options.striped;
                _escape = $.common.isEmpty(options.escape) ? false : options.escape;
                $('#bootstrap-table').bootstrapTable({
                    url: options.url,                                   // 请求后台的URL（*）
                    contentType: "application/x-www-form-urlencoded",   // 编码类型
                    method: 'post',                                     // 请求方式（*）
                    cache: false,                                       // 是否使用缓存
                    striped: _striped,                                  // 是否显示行间隔色
                    sortable: true,                                     // 是否启用排序
                    sortStable: true,                                   // 设置为 true 将获得稳定的排序
                    sortName: _sortName,                                // 排序列名称
                    sortOrder: _sortOrder,                              // 排序方式  asc 或者 desc
                    pagination: $.common.visible(options.pagination),   // 是否显示分页（*）
                    pageNumber: 1,                                      // 初始化加载第一页，默认第一页
                    pageSize: 10,                                       // 每页的记录行数（*）
                    pageList: [10, 25, 50],                             // 可供选择的每页的行数（*）
                    escape: _escape,                                    // 转义HTML字符串
                    iconSize: 'outline',                                // 图标大小：undefined默认的按钮尺寸 xs超小按钮sm小按钮lg大按钮
        	        toolbar: '#toolbar',                                // 指定工作栏
                    sidePagination: "server",                           // 启用服务端分页
                    search: $.common.visible(options.search),           // 是否显示搜索框功能
                    showSearch: $.common.visible(options.showSearch),   // 是否显示检索信息
                    showRefresh: $.common.visible(options.showRefresh), // 是否显示刷新按钮
        			showColumns: $.common.visible(options.showColumns), // 是否显示隐藏某列下拉框
        			showToggle: $.common.visible(options.showToggle),   // 是否显示详细视图和列表视图的切换按钮
        			showExport: $.common.visible(options.showExport),   // 是否支持导出文件
                    queryParams: $.table._params,                       // 传递参数（*）
                    columns: options.columns,                           // 显示列信息（*）
                    responseHandler: $.table.responseHandler,            // 回调函数
					onLoadSuccess: $.table.onLoadSuccess               // 当所有数据被加载时触发处理函数
                });
            },
            // 查询条件
            queryParams: function(params) {
            	return {
        			// 传递参数查询参数
        			pageSize:       params.limit,
        			pageNum:        params.offset / params.limit + 1,
        			searchValue:    params.search,
        			orderByColumn:  params.sort,
        			isAsc:          params.order
        		};
            },
            // 请求获取数据后处理回调函数
            responseHandler: function(res) {
                if (res.code == 0) {
                    return { rows: res.rows, total: res.total };
                } else {
                	$.modal.alertWarning(res.msg);
                	return { rows: [], total: 0 };
                }
            },
			// 初始化事件
			initEvent: function(data) {
				// 触发行点击事件 加载成功事件
				$.btTable.on("check.bs.table uncheck.bs.table check-all.bs.table uncheck-all.bs.table load-success.bs.table", function () {
					// 工具栏按钮控制
					var rows = $.common.isEmpty($.table._option.uniqueId) ? $.table.selectFirstColumns() : $.table.selectColumns($.table._option.uniqueId);
					// 非多个禁用
					$('#' + $.table._option.toolbar + ' .multiple').toggleClass('disabled', !rows.length);
					// 非单个禁用
					$('#' + $.table._option.toolbar + ' .single').toggleClass('disabled', rows.length!=1);
				});
				// 绑定选中事件、取消事件、全部选中、全部取消
				$.btTable.on("check.bs.table check-all.bs.table uncheck.bs.table uncheck-all.bs.table", function (e, rows) {
					// 复选框分页保留保存选中数组
					var rowIds = $.table.affectedRowIds(rows);
					if ($.common.isNotEmpty($.table._option.rememberSelected) && $.table._option.rememberSelected) {
						func = $.inArray(e.type, ['check', 'check-all']) > -1 ? 'union' : 'difference';
						selectionIds = _[func](selectionIds, rowIds);
					}
				});
				// 图片预览事件
				$.btTable.on('click', '.img-circle', function() {
					var src = $(this).attr('src');
					var target = $(this).data('target');
					var height = $(this).data('height');
					var width = $(this).data('width');
					if($.common.equals("self", target)) {
						layer.open({
							title: false,
							type: 1,
							closeBtn: true,
							shadeClose: true,
							area: ['auto', 'auto'],
							content: "<img src='" + src + "' height='" + height + "' width='" + width + "'/>"
						});
					} else if ($.common.equals("blank", target)) {
						window.open(src);
					}
				});
				// 单击tooltip复制文本
				$.btTable.on('click', '.tooltip-show', function() {
					var input = $(this).prev();
					input.select();
					document.execCommand("copy");
				});
			},
			// 当所有数据被加载时触发
			onLoadSuccess: function(data) {
				if (typeof $.table._option.onLoadSuccess == "function") {
					$.table._option.onLoadSuccess(data);
				}
				// 浮动提示框特效
				$("[data-toggle='tooltip']").tooltip();
			},
			// 列超出指定长度浮动提示 target（copy单击复制文本 open弹窗打开文本）
			tooltip: function (value, length, target) {
				var _length = $.common.isEmpty(length) ? 20 : length;
				var _text = "";
				var _value = $.common.nullToStr(value);
				var _target = $.common.isEmpty(target) ? 'copy' : target;
				if (_value.length > _length) {
					_text = _value.substr(0, _length) + "...";
					_value = _value.replace(/\'/g,"’");
					var actions = [];
					actions.push($.common.sprintf('<input id="tooltip-show" style="opacity: 0;position: absolute;z-index:-1" type="text" value="%s"/>', _value));
					actions.push($.common.sprintf("<a href='###' class='tooltip-show' data-toggle='tooltip' data-target='%s' title='%s'>%s</a>", _target, _value, _text));
					return actions.join('');
				} else {
					_text = _value;
					return _text;
				}
			},
			// 图片预览
			imageView: function (value, height, width, target) {
				if ($.common.isEmpty(width)) {
					width = 'auto';
				}
				if ($.common.isEmpty(height)) {
					height = 'auto';
				}
				// blank or self
				var _target = $.common.isEmpty(target) ? 'self' : target;
				if ($.common.isNotEmpty(value)) {
					return $.common.sprintf("<img class='img-circle img-xs' data-height='%s' data-width='%s' data-target='%s' src='%s'/>", width, height, _target, value);
				} else {
					return $.common.nullToStr(value);
				}
			},
            // 搜索-默认第一个form
            search: function(formId) {
            	var currentId = $.common.isEmpty(formId) ? $('form').attr('id') : formId;
    		    var params = $("#bootstrap-table").bootstrapTable('getOptions');
    		    params.queryParams = function(params) {
    		        var search = {};
    		        $.each($("#" + currentId).serializeArray(), function(i, field) {
    		            search[field.name] = field.value;
    		        });
    		        search.pageSize = params.limit;
    		        search.pageNum = params.offset / params.limit + 1;
    		        search.searchValue = params.search;
    		        search.orderByColumn = params.sort;
    		        search.isAsc = params.order;
    		        return search;
    		    }
    		    $("#bootstrap-table").bootstrapTable('refresh', params);
    		},
    		// 下载-默认第一个form
    		exportExcel: function(formId) {
    			var currentId = $.common.isEmpty(formId) ? $('form').attr('id') : formId;
    			$.modal.loading("正在导出数据，请稍后...");
    			$.post($.table._option.exportUrl, $("#" + currentId).serializeArray(), function(result) {
    				if (result.code == web_status.SUCCESS) {
    			        window.location.href = ctx + "common/download?fileName=" + result.msg + "&delete=" + true;
    				} else {
    					$.modal.alertError(result.msg);
    				}
    				$.modal.closeLoading();
    			});
    		},
            // 刷新表格
            refresh: function() {
                $("#bootstrap-table").bootstrapTable('refresh', {
                    silent: true
                });
            },
            // 查询表格指定列值
            selectColumns: function(column) {
            	return $.map($('#bootstrap-table').bootstrapTable('getSelections'), function (row) {
        	        return row[column];
        	    });
            },
            // 查询表格首列值
            selectFirstColumns: function() {
            	return $.map($('#bootstrap-table').bootstrapTable('getSelections'), function (row) {
        	        return row[$.table._option.columns[1].field];
        	    });
            },
            // 回显数据字典
            selectDictLabel: function(datas, value) {
				var actions = [];
				$.each(datas, function(index, dict) {
					if (dict.dictValue == ('' + value)) {
						var listClass = $.common.equals("default", dict.listClass) || $.common.isEmpty(dict.listClass) ? "" : "badge badge-" + dict.listClass;
						actions.push($.common.sprintf("<span class='%s'>%s</span>", listClass, dict.dictLabel));
						return false;
					}
				});
				return actions.join('');
            },
            // 显示表格指定列
            showColumn: function(column) {
                $("#bootstrap-table").bootstrapTable('showColumn', column);
            },
            // 隐藏表格指定列
            hideColumn: function(column) {
            	$("#bootstrap-table").bootstrapTable('hideColumn', column);
            }
        },
        // 表格树封装处理
        treeTable: {
            _option: {},
            // 初始化表格
            init: function(options) {
                $.table._option = options;
                _striped = $.common.isEmpty(options.striped) ? false : options.striped;
                _expandColumn = $.common.isEmpty(options.expandColumn) ? '1' : options.expandColumn;
                var treeTable = $('#bootstrap-tree-table').bootstrapTreeTable({
                	code: options.code,                                 // 用于设置父子关系
        		    parentCode: options.parentCode,                     // 用于设置父子关系
        	    	type: $.common.isEmpty(options.type) ? 'get' : options.type,// 请求方式（*）
        	        url: options.url,                                   // 请求后台的URL（*）
        	        ajaxParams: {},                                     // 请求数据的ajax的data属性
        			expandColumn: _expandColumn,                        // 在哪一列上面显示展开按钮
        			striped: _striped,                                  // 是否显示行间隔色
        			bordered: true,                                     // 是否显示边框
        			toolbar: '#toolbar',                                // 指定工作栏
        			showRefresh: $.common.visible(options.showRefresh), // 是否显示刷新按钮
        			showColumns: $.common.visible(options.showColumns), // 是否显示隐藏某列下拉框
        			expandAll: $.common.visible(options.expandAll),     // 是否全部展开
        			expandFirst: $.common.visible(options.expandFirst), // 是否默认第一级展开--expandAll为false时生效
        	        columns: options.columns
        	    });
                $._treeTable = treeTable;
            },
            // 条件查询
            search: function(formId) {
            	var currentId = $.common.isEmpty(formId) ? $('form').attr('id') : formId;
            	var params = {};
            	$.each($("#" + currentId).serializeArray(), function(i, field) {
            		params[field.name] = field.value;
		        });
            	$._treeTable.bootstrapTreeTable('refresh', params);
            },
            // 刷新
            refresh: function() {
            	$._treeTable.bootstrapTreeTable('refresh');
            },
        },
        // 表单封装处理
    	form: {
    		// 表单重置
    		reset: function(formId) {
            	var currentId = $.common.isEmpty(formId) ? $('form').attr('id') : formId;
            	$("#" + currentId)[0].reset();
            },
            // 获取选中复选框项
            selectCheckeds: function(name) {
            	var checkeds = "";
        	    $('input:checkbox[name="' + name + '"]:checked').each(function(i) {
        	        if (0 == i) {
        	        	checkeds = $(this).val();
        	        } else {
        	        	checkeds += ("," + $(this).val());
        	        }
        	    });
        	    return checkeds;
            },
            // 获取选中下拉框项
            selectSelects: function(name) {
            	var selects = "";
        	    $('#' + name + ' option:selected').each(function (i) {
        	        if (0 == i) {
        	        	selects = $(this).val();
        	        } else {
        	        	selects += ("," + $(this).val());
        	        }
        	    });
        	    return selects;
            }
        },
        // 弹出层封装处理
    	modal: {
    		// 显示图标
    		icon: function(type) {
            	var icon = "";
        	    if (type == modal_status.WARNING) {
        	        icon = 0;
        	    } else if (type == modal_status.SUCCESS) {
        	        icon = 1;
        	    } else if (type == modal_status.FAIL) {
        	        icon = 2;
        	    } else {
        	        icon = 3;
        	    }
        	    return icon;
            },
    		// 消息提示
            msg: function(content, type) {
            	if (type != undefined) {
                    layer.msg(content, { icon: $.modal.icon(type), time: 1000, shift: 5 });
                } else {
                    layer.msg(content);
                }
            },
            // 错误消息
            msgError: function(content) {
            	$.modal.msg(content, modal_status.FAIL);
            },
            // 成功消息
            msgSuccess: function(content) {
            	$.modal.msg(content, modal_status.SUCCESS);
            },
            // 警告消息
            msgWarning: function(content) {
            	$.modal.msg(content, modal_status.WARNING);
            },
    		// 弹出提示
            alert: function(content, type) {
        	    layer.alert(content, {
        	        icon: $.modal.icon(type),
        	        title: "系统提示",
        	        btn: ['确认'],
        	        btnclass: ['btn btn-primary'],
        	    });
            },
            // 消息提示并刷新父窗体
            msgReload: function(msg, type) {
            	layer.msg(msg, {
            	    icon: $.modal.icon(type),
            	    time: 500,
            	    shade: [0.1, '#8F8F8F']
            	},
            	function() {
            	    $.modal.reload();
            	});
            },
            // 错误提示
            alertError: function(content) {
            	$.modal.alert(content, modal_status.FAIL);
            },
            // 成功提示
            alertSuccess: function(content) {
            	$.modal.alert(content, modal_status.SUCCESS);
            },
            // 警告提示
            alertWarning: function(content) {
            	$.modal.alert(content, modal_status.WARNING);
            },
            // 关闭窗体
            close: function () {
            	var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
            },
            // 确认窗体
            confirm: function (content, callBack) {
            	layer.confirm(content, {
        	        icon: 3,
        	        title: "系统提示",
        	        btn: ['确认', '取消'],
        	        btnclass: ['btn btn-primary', 'btn btn-danger'],
        	    }, function (index) {
        	    	layer.close(index);
        	        callBack(true);
        	    });
            },
            // 弹出层指定宽度
            open: function (title, url, width, height,callback) {
				//如果是移动端，就使用自适应大小弹窗
				if ($.common.isMobile()) {
					width = 'auto';
					height = 'auto';
				}
            	if ($.common.isEmpty(title)) {
                    title = false;
                };
                if ($.common.isEmpty(url)) {
                    url = "/404.html";
                };
                if ($.common.isEmpty(width)) {
                	width = 800;
                };
                if ($.common.isEmpty(height)) {
                	height = ($(window).height() - 50);
                };
				if ($.common.isEmpty(callback)) {
					callback = function(index, layero) {
						var iframeWin = layero.find('iframe')[0];
						iframeWin.contentWindow.submitHandler(index, layero);
					}
				};
            	layer.open({
            		type: 2,
            		area: [width + 'px', height + 'px'],
            		fix: false,
            		//不固定
            		maxmin: true,
            		shade: 0.3,
					title: title,
            		content: url,
            		btn: ['确定', '关闭'],
            	    // 弹层外区域关闭
            		shadeClose: false,
            		yes: callback,
            	    cancel: function(index) {
            	        return true;
            	    }
            	});
            },
            // 弹出层指定参数选项
            openOptions: function (options) {
            	var _url = $.common.isEmpty(options.url) ? "/404.html" : options.url;
            	var _title = $.common.isEmpty(options.title) ? "系统窗口" : options.title;
                var _width = $.common.isEmpty(options.width) ? "800" : options.width;
                var _height = $.common.isEmpty(options.height) ? ($(window).height() - 50) : options.height;
                layer.open({
                    type: 2,
            		maxmin: true,
                    shade: 0.3,
                    title: _title,
                    fix: false,
                    area: [_width + 'px', _height + 'px'],
                    content: _url,
                    shadeClose: true,
                    btn: ['<i class="fa fa-check"></i> 确认', '<i class="fa fa-close"></i> 关闭'],
                    yes: function (index, layero) {
                        options.callBack(index, layero)
                    }, cancel: function () {
                        return true;
                    }
                });
            },
            // 弹出层全屏
            openFull: function (title, url, width, height) {
            	//如果是移动端，就使用自适应大小弹窗
            	if (navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)) {
            	    width = 'auto';
            	    height = 'auto';
            	}
            	if ($.common.isEmpty(title)) {
                    title = false;
                };
                if ($.common.isEmpty(url)) {
                    url = "/404.html";
                };
                if ($.common.isEmpty(width)) {
                	width = 800;
                };
                if ($.common.isEmpty(height)) {
                	height = ($(window).height() - 50);
                };
                var index = layer.open({
            		type: 2,
            		area: [width + 'px', height + 'px'],
            		fix: false,
            		//不固定
            		maxmin: true,
            		shade: 0.3,
            		title: title,
            		content: url,
					btn: ['确定', '关闭'],
            		// 弹层外区域关闭
            		shadeClose: true,
					yes: function(index, layero) {
						var iframeWin = layero.find('iframe')[0];
						iframeWin.contentWindow.submitHandler(index, layero);
					},
					cancel: function(index) {
						return true;
					}
            	});
                layer.full(index);
            },
			// 选卡页方式打开
			openTab: function (title, url) {
				createMenuItem(url, title);
			},
			// 选卡页同一页签打开
			parentTab: function (title, url) {
				var dataId = window.frameElement.getAttribute('data-id');
				createMenuItem(url, title);
				closeItem(dataId);
			},
			// 关闭选项卡
			closeTab: function (dataId) {
				closeItem(dataId);
			},
			// 禁用按钮
			disable: function() {
				var doc = window.top == window.parent ? window.document : window.parent.document;
				$("a[class*=layui-layer-btn]", doc).addClass("layer-disabled");
			},
			// 启用按钮
			enable: function() {
				var doc = window.top == window.parent ? window.document : window.parent.document;
				$("a[class*=layui-layer-btn]", doc).removeClass("layer-disabled");
			},
            // 打开遮罩层
            loading: function (message) {
            	$.blockUI({ message: '<div class="loaderbox"><div class="loading-activity"></div> ' + message + '</div>' });
            },
            // 关闭遮罩层
            closeLoading: function () {
            	setTimeout(function(){
            		$.unblockUI();
            	}, 50);
            },
            // 重新加载
            reload: function () {
            	parent.location.reload();
            }
        },
        // 操作封装处理
        operate: {
        	// 提交数据
        	submit: function(url, type, dataType, data) {
        		$.modal.loading("正在处理中，请稍后...");
            	var config = {
        	        url: url,
        	        type: type,
        	        dataType: dataType,
        	        data: data,
        	        success: function(result) {
        	        	$.operate.ajaxSuccess(result);
        	        }
        	    };
        	    $.ajax(config)
            },
            // post请求传输
            post: function(url, data) {
            	$.operate.submit(url, "post", "json", data);
            },
			// get请求传输
			get: function(url, callback) {
				$.operate.submit(url, "get", "json", "", callback);
			},
			ajaxGet: function(url, callback) {
				var config = {
					url: url,
					type: "get",
					dataType: "json",
					data: data,
					beforeSend: function () {
						$.modal.loading("正在处理中，请稍后...");
					},
					success: function(result) {
						$.modal.closeLoading();
						if (typeof callback == "function") {
							callback(result);
						}
					}
				};
				$.ajax(config)
			},
            // 详细信息
            detail: function(id, width, height) {
            	var _url = $.common.isEmpty(id) ? $.table._option.detailUrl : $.table._option.detailUrl.replace("{id}", id);
                var _width = $.common.isEmpty(width) ? "800" : width;
                var _height = $.common.isEmpty(height) ? ($(window).height() - 50) : height;
            	//如果是移动端，就使用自适应大小弹窗
            	if (navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)) {
            	    _width = 'auto';
            	    _height = 'auto';
            	}
            	layer.open({
            		type: 2,
            		area: [_width + 'px', _height + 'px'],
            		fix: false,
            		//不固定
            		maxmin: true,
            		shade: 0.3,
            		title: $.table._option.modalName + "详细",
            		content: _url,
            		btn: '关闭',
            	    // 弹层外区域关闭
            		shadeClose: true,
            		success: function(layer) {
            			layer[0].childNodes[3].childNodes[0].attributes[0].value='layui-layer-btn1';
            		},
            		btn1: function(index) {
            			layer.close(index);
            	    }
            	});
            },
			// 详细访问地址
			detailUrl: function(id) {
				var url = "/404.html";
				if ($.common.isNotEmpty(id)) {
					url = table.options.detailUrl.replace("{id}", id);
				} else {
					var id = $.common.isEmpty(table.options.uniqueId) ? $.table.selectFirstColumns() : $.table.selectColumns(table.options.uniqueId);
					if (id.length == 0) {
						$.modal.alertWarning("请至少选择一条记录");
						return;
					}
					url = table.options.detailUrl.replace("{id}", id);
				}
				return url;
			},
            // 删除信息
            remove: function(id) {
            	$.modal.confirm("确定删除该条" + $.table._option.modalName + "信息吗？", function() {
	            	var url = $.common.isEmpty(id) ? $.table._option.removeUrl : $.table._option.removeUrl.replace("{id}", id);
	            	var data = { "ids": id };
	            	$.operate.submit(url, "post", "json", data);
            	});
            },
            // 批量删除信息
            removeAll: function() {
        		var rows = $.common.isEmpty($.table._option.uniqueId) ? $.table.selectFirstColumns() : $.table.selectColumns($.table._option.uniqueId);
        		if (rows.length == 0) {
        			$.modal.alertWarning("请至少选择一条记录");
        			return;
        		}
        		$.modal.confirm("确认要删除选中的" + rows.length + "条数据吗?", function() {
        			var url = $.table._option.removeUrl;
        			var data = { "ids": rows.join() };
        			$.operate.submit(url, "post", "json", data);
        		});
            },
            // 清空信息
            clean: function() {
            	$.modal.confirm("确定清空所有" + $.table._option.modalName + "吗？", function() {
	            	var url = $.table._option.cleanUrl;
	            	$.operate.submit(url, "post", "json", "");
            	});
            },
            // 添加信息
            add: function(id) {
                $.modal.open("添加" + $.table._option.modalName, $.operate.addUrl(id));
            },
			// 添加信息，以tab页展现
			addTab: function (id) {
				$.modal.openTab("添加" + $.table._option.modalName, $.operate.addUrl(id));
			},
			// 添加访问地址
			addUrl: function(id) {
				var url = $.common.isEmpty(id) ? $.table._option.createUrl.replace("{id}", "") : $.table._option.createUrl.replace("{id}", id);
				return url;
			},
            // 修改信息
            edit: function(id) {
            	var url = "/404.html";
            	if ($.common.isNotEmpty(id)) {
            	    url = $.table._option.updateUrl.replace("{id}", id);
            	} else {
            	    var id = $.common.isEmpty($.table._option.uniqueId) ? $.table.selectFirstColumns() : $.table.selectColumns($.table._option.uniqueId);
            	    if (id.length == 0) {
            			$.modal.alertWarning("请至少选择一条记录");
            			return;
            		}
            	    url = $.table._option.updateUrl.replace("{id}", id);
            	}
            	$.modal.open("修改" + $.table._option.modalName, url);
            },
            // 工具栏表格树修改
            editTree: function() {
            	var row = $('#bootstrap-tree-table').bootstrapTreeTable('getSelections')[0];
            	if ($.common.isEmpty(row)) {
        			$.modal.alertWarning("请至少选择一条记录");
        			return;
        		}
                var url = $.table._option.updateUrl.replace("{id}", row[$.table._option.uniqueId]);
                $.modal.open("修改" + $.table._option.modalName, url);
            },
            // 添加信息 全屏
            addFull: function(id) {
            	var url = $.common.isEmpty(id) ? $.table._option.createUrl : $.table._option.createUrl.replace("{id}", id);
                $.modal.openFull("添加" + $.table._option.modalName, url);
            },
			// 修改信息，以tab页展现
			editTab: function(id) {
				$.modal.openTab("修改" + $.table._option.modalName, $.operate.editUrl(id));
			},
			// 修改访问地址
			editUrl: function(id) {
				var url = "/404.html";
				if ($.common.isNotEmpty(id)) {
					url = $.table._option.updateUrl.replace("{id}", id);
				} else {
					var id = $.common.isEmpty($.table._option.uniqueId) ? $.table.selectFirstColumns() : $.table.selectColumns(table.options.uniqueId);
					if (id.length == 0) {
						$.modal.alertWarning("请至少选择一条记录");
						return;
					}
					url = $.table._option.updateUrl.replace("{id}", id);
				}
				return url;
			},
            // 修改信息 全屏
            editFull: function(id) {
            	var url = "/404.html";
            	if ($.common.isNotEmpty(id)) {
            	    url = $.table._option.updateUrl.replace("{id}", id);
            	} else {
            	    var row = $.common.isEmpty($.table._option.uniqueId) ? $.table.selectFirstColumns() : $.table.selectColumns($.table._option.uniqueId);
            	    url = $.table._option.updateUrl.replace("{id}", row);
            	}
            	$.modal.openFull("修改" + $.table._option.modalName, url);
            },
            // 保存信息
            save: function(url, data) {
            	$.modal.loading("正在处理中，请稍后...");
            	var config = {
        	        url: url,
        	        type: "post",
        	        dataType: "json",
        	        data: data,
        	        success: function(result) {
        	        	$.operate.successCallback(result);
        	        }
        	    };
        	    $.ajax(config)
            },
			// 保存选项卡信息
			saveTab: function(url, data, callback) {
				var config = {
					url: url,
					type: "post",
					dataType: "json",
					data: data,
					beforeSend: function () {
						$.modal.loading("正在处理中，请稍后...");
					},
					success: function(result) {
						if (typeof callback == "function") {
							callback(result);
						}
						$.operate.successTabCallback(result);
					}
				};
				$.ajax(config)
			},
            // 保存结果弹出msg刷新table表格
            ajaxSuccess: function (result) {
            	if (result.code == web_status.SUCCESS) {
                	$.modal.msgSuccess(result.msg);
            		$.table.refresh();
                } else {
                	$.modal.alertError(result.msg);
                }
            	$.modal.closeLoading();
            },
            // 成功结果提示msg（父窗体全局更新）
            saveSuccess: function (result) {
            	if (result.code == web_status.SUCCESS) {
            		$.modal.msgReload("保存成功,正在刷新数据请稍后……", modal_status.SUCCESS);
                } else {
                	$.modal.alertError(result.msg);
                }
            	$.modal.closeLoading();
            },
            // 成功回调执行事件（父窗体静默更新）
            successCallback: function(result) {
                if (result.code == web_status.SUCCESS) {
                    if (window.parent.$("#bootstrap-table").length > 0) {
                        $.modal.close();
                        window.parent.$.modal.msgSuccess(result.msg);
                        window.parent.$.table.refresh();
                    } else if (window.parent.$("#bootstrap-tree-table").length > 0) {
                        $.modal.close();
                        window.parent.$.modal.msgSuccess(result.msg);
                        window.parent.$.treeTable.refresh();
                    } else {
                        $.modal.msgReload("保存成功,正在刷新数据请稍后……", modal_status.SUCCESS);
                    }
                } else {
                    $.modal.alertError(result.msg);
                }
                $.modal.closeLoading();
            },
			// 选项卡成功回调执行事件（父窗体静默更新）
			successTabCallback: function(result) {
				if (result.code == web_status.SUCCESS) {
					var topWindow = $(window.parent.document);
					var currentId = $('.page-tabs-content', topWindow).find('.active').attr('data-panel');
					var $contentWindow = $('.SystemPlus_iframe[data-id="' + currentId + '"]', topWindow)[0].contentWindow;
					$.modal.close();
					$contentWindow.$.modal.msgSuccess(result.msg);
					$contentWindow.$(".layui-layer-padding").removeAttr("style");

					if ($contentWindow.$("#bootstrap-table").length > 0) {
						$contentWindow.$.table.refresh();
					} else if ($contentWindow.$("#bootstrap-tree-table").length > 0) {
						$contentWindow.$.treeTable.refresh();
					} else {
						$.modal.msgReload("保存成功,正在刷新数据请稍后……", modal_status.SUCCESS);
					}
					$.modal.closeTab();
				} else if (result.code == web_status.WARNING) {
					$.modal.alertWarning(result.msg)
				} else {
					$.modal.alertError(result.msg);
				}
				$.modal.closeLoading();
			}
        },
        // 校验封装处理
        validate: {
        	// 判断返回标识是否唯一 false 不存在 true 存在
        	unique: function (value) {
            	if (value == "0") {
                    return true;
                }
                return false;
            },
            // 表单验证
            form: function (formId) {
            	var currentId = $.common.isEmpty(formId) ? $('form').attr('id') : formId;
                return $("#" + currentId).validate().form();
            }
        },
        // 树插件封装处理
        tree: {
        	_option: {},
        	_lastValue: {},
        	// 初始化树结构
        	init: function(options) {
        		$.tree._option = options;
        		// 属性ID
        		var _id = $.common.isEmpty(options.id) ? "tree" : options.id;
        		// 展开等级节点
        		var _expandLevel = $.common.isEmpty(options.expandLevel) ? 0 : options.expandLevel;
        		// 树结构初始化加载
        	    var setting = {
        	    	check: options.check,
        	        view: { selectedMulti: false, nameIsHTML: true },
        	        data: { key: { title: "title" }, simpleData: { enable: true } },
        	        callback: { onClick: options.onClick }
        	    };
        	    $.get(options.url, function(data) {
        	    	var treeName = $("#treeName").val();
        			var treeId = $("#treeId").val();
        			tree = $.fn.zTree.init($("#" + _id), setting, data);
        			$._tree = tree;
        			// 展开第一级节点
        			var nodes = tree.getNodesByParam("level", 0);
        			for (var i = 0; i < nodes.length; i++) {
        				if(_expandLevel > 0) {
        					tree.expandNode(nodes[i], true, false, false);
        				}
    				    $.tree.selectByIdName(treeId, treeName, nodes[i]);
        			}
        			// 展开第二级节点
        			nodes = tree.getNodesByParam("level", 1);
        			for (var i = 0; i < nodes.length; i++) {
        				if(_expandLevel > 1) {
        					tree.expandNode(nodes[i], true, false, false);
        				}
    				    $.tree.selectByIdName(treeId, treeName, nodes[i]);
        			}
        			// 展开第三级节点
        			nodes = tree.getNodesByParam("level", 2);
        			for (var i = 0; i < nodes.length; i++) {
        				if(_expandLevel > 2) {
        					tree.expandNode(nodes[i], true, false, false);
        				}
    				    $.tree.selectByIdName(treeId, treeName, nodes[i]);
        			}
        		}, null, null, "正在加载，请稍后...");
        	},
        	// 搜索节点
        	searchNode: function() {
        		// 取得输入的关键字的值
        		var value = $.common.trim($("#keyword").val());
        		if ($.tree._lastValue === value) {
        		    return;
        		}
        		// 保存最后一次搜索名称
        		$.tree._lastValue = value;
        		var nodes = $._tree.getNodes();
        		// 如果要查空字串，就退出不查了。
        		if (value == "") {
        		    $.tree.showAllNode(nodes);
        		    return;
        		}
        		$.tree.hideAllNode(nodes);
        		// 根据搜索值模糊匹配
        		$.tree.updateNodes($._tree.getNodesByParamFuzzy("name", value));
        	},
        	// 根据Id和Name选中指定节点
        	selectByIdName: function(treeId, treeName, node) {
        		if ($.common.isNotEmpty(treeName) && $.common.isNotEmpty(treeId)) {
        			if (treeId == node.id && treeName == node.name) {
            			$._tree.selectNode(node, true);
            		}
        		}
        	},
        	// 显示所有节点
        	showAllNode: function(nodes) {
        		nodes = $._tree.transformToArray(nodes);
        		for (var i = nodes.length - 1; i >= 0; i--) {
        		    if (nodes[i].getParentNode() != null) {
        		    	$._tree.expandNode(nodes[i], true, false, false, false);
        		    } else {
        		    	$._tree.expandNode(nodes[i], true, true, false, false);
        		    }
        		    $._tree.showNode(nodes[i]);
        		    $.tree.showAllNode(nodes[i].children);
        		}
        	},
        	// 隐藏所有节点
        	hideAllNode: function(nodes) {
        	    var tree = $.fn.zTree.getZTreeObj("tree");
        	    var nodes = $._tree.transformToArray(nodes);
        	    for (var i = nodes.length - 1; i >= 0; i--) {
        	    	$._tree.hideNode(nodes[i]);
        	    }
        	},
        	// 显示所有父节点
        	showParent: function(treeNode) {
        		var parentNode;
        		while ((parentNode = treeNode.getParentNode()) != null) {
        			$._tree.showNode(parentNode);
        			$._tree.expandNode(parentNode, true, false, false);
        		    treeNode = parentNode;
        		}
        	},
        	// 显示所有孩子节点
        	showChildren: function(treeNode) {
        		if (treeNode.isParent) {
        		    for (var idx in treeNode.children) {
        		        var node = treeNode.children[idx];
        		        $._tree.showNode(node);
        		        $.tree.showChildren(node);
        		    }
        		}
        	},
        	// 更新节点状态
        	updateNodes: function(nodeList) {
        		$._tree.showNodes(nodeList);
        		for (var i = 0, l = nodeList.length; i < l; i++) {
        		    var treeNode = nodeList[i];
        		    $.tree.showChildren(treeNode);
        		    $.tree.showParent(treeNode)
        		}
        	},
        	// 获取当前被勾选集合
        	getCheckedNodes: function(column) {
        		var _column = $.common.isEmpty(column) ? "id" : column;
        		var nodes = $._tree.getCheckedNodes(true);
        		return $.map(nodes, function (row) {
        	        return row[_column];
        	    }).join();
        	},
        	// 不允许根父节点选择
        	notAllowParents: function(_tree) {
    		    var nodes = _tree.getSelectedNodes();
    		    for (var i = 0; i < nodes.length; i++) {
    		        if (nodes[i].level == 0) {
    		            $.modal.msgError("不能选择根节点（" + nodes[i].name + "）");
    		            return false;
    		        }
    		        if (nodes[i].isParent) {
    		            $.modal.msgError("不能选择父节点（" + nodes[i].name + "）");
    		            return false;
    		        }
    		    }
        		return true;
        	},
        	// 不允许最后层级节点选择
        	notAllowLastLevel: function(_tree) {
    		    var nodes = _tree.getSelectedNodes();
    		    for (var i = 0; i < nodes.length; i++) {
    		    	if (nodes[i].level == nodes.length + 1) {
    		    		$.modal.msgError("不能选择最后层级节点（" + nodes[i].name + "）");
    		            return false;
    		        }
    		    }
        		return true;
        	},
        	// 隐藏/显示搜索栏
        	toggleSearch: function() {
        		$('#search').slideToggle(200);
        		$('#btnShow').toggle();
        		$('#btnHide').toggle();
        		$('#keyword').focus();
        	},
        	// 折叠
        	collapse: function() {
        		$._tree.expandAll(false);
        	},
        	// 展开
        	expand: function() {
        		$._tree.expandAll(true);
        	}
        },
        // 通用方法封装处理
    	common: {
    		// 判断字符串是否为空
            isEmpty: function (value) {
                if (value == null || this.trim(value) == "") {
                    return true;
                }
                return false;
            },
            // 判断一个字符串是否为非空串
            isNotEmpty: function (value) {
            	return !$.common.isEmpty(value);
            },
			// 空对象转字符串
			nullToStr: function(value) {
				if ($.common.isEmpty(value)) {
					return "-";
				}
				return value;
			},
            // 是否显示数据 为空默认为显示
            visible: function (value) {
                if ($.common.isEmpty(value) || value == true) {
                    return true;
                }
                return false;
            },
            // 空格截取
            trim: function (value) {
                if (value == null) {
                    return "";
                }
                return value.toString().replace(/(^\s*)|(\s*$)|\r|\n/g, "");
            },
			// 比较两个字符串（大小写敏感）
			equals: function (str, that) {
				return str == that;
			},
			// 比较两个字符串（大小写不敏感）
			equalsIgnoreCase: function (str, that) {
				return String(str).toUpperCase() === String(that).toUpperCase();
			},
			// 将字符串按指定字符分割
			split: function (str, sep, maxLen) {
				if ($.common.isEmpty(str)) {
					return null;
				}
				var value = String(str).split(sep);
				return maxLen ? value.slice(0, maxLen - 1) : value;
			},
			// 字符串格式化(%s )
			sprintf: function (str) {
				var args = arguments, flag = true, i = 1;
				str = str.replace(/%s/g, function () {
					var arg = args[i++];
					if (typeof arg === 'undefined') {
						flag = false;
						return '';
					}
					return arg;
				});
				return flag ? str : '';
			},
            // 指定随机数返回
            random: function (min, max) {
                return Math.floor((Math.random() * max) + min);
            },
            startWith: function(value, start) {
                var reg = new RegExp("^" + start);
                return reg.test(value)
            },
            endWith: function(value, end) {
                var reg = new RegExp(end + "$");
                return reg.test(value)
            },
			// 数组去重
			uniqueFn: function(array) {
				var result = [];
				var hashObj = {};
				for (var i = 0; i < array.length; i++) {
					if (!hashObj[array[i]]) {
						hashObj[array[i]] = true;
						result.push(array[i]);
					}
				}
				return result;
			},
			// 数组中的所有元素放入一个字符串
			join: function(array, separator) {
				if ($.common.isEmpty(array)) {
					return null;
				}
				return array.join(separator);
			},
			// 获取form下所有的字段并转换为json对象
			formToJSON: function(formId) {
				var json = {};
				$.each($("#" + formId).serializeArray(), function(i, field) {
					if(json[field.name]) {
						json[field.name] += ("," + field.value);
					} else {
						json[field.name] = field.value;
					}
				});
				return json;
			},
			// 获取obj对象长度
			getLength: function(obj) {
				var count = 0;
				for (var i in obj) {
					if (obj.hasOwnProperty(i)) {
						count++;
					}
				}
				return count;
			},
			// 判断移动端
			isMobile: function () {
				return navigator.userAgent.match(/(Android|iPhone|SymbianOS|Windows Phone|iPad|iPod)/i);
			},
        }
    });
})(jQuery);

/** 表格类型 */
table_type = {
    bootstrapTable: 0,
    bootstrapTreeTable: 1
};

/** 消息状态码 */
web_status = {
    SUCCESS: 0,
    FAIL: 500
};

/** 弹窗状态码 */
modal_status = {
    SUCCESS: "success",
    FAIL: "error",
    WARNING: "warning"
};

/*将后台返回的时间戳id转换成日期字符串*/
function convertTimeUuidToDateStr(id){
    id = id+"";
    var year = "20"+id.substring(0,2);
    var month = id.substring(2,4);
    var day = id.substring(4,6);
    var hour = id.substring(6,8);
    var min = id.substring(8,10);
    var sec = id.substring(10,12);
    return	year+"-"+month+"-"+day+" "+hour+":"+min+":"+sec;
}