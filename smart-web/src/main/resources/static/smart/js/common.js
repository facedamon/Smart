/**
 *  通用方法封装
 *
 */


$(function () {

    /**
     * 时间控件绑定与校验
     */
    if ($('.select-time').length > 0) {
        layui.use('laydate', function () {
            //日期模板
            var laydate = layui.laydate;
            //开始渲染
            var startDate = laydate.render({
                elem: '#startTime',
                max: $('#endTime').val(),
                theme: '#393D49',
                //value: getDateStr(0),
                trigger: 'click',
                /**
                 * 校验器 结束时间大于开始时间
                 * @system value
                 * @system date 选中的日期
                 */
                done: function (value, date) {
                    if ('' !== value) {
                        endDate.config.min.year = date.year;
                        endDate.config.min.month = date.month - 1;
                        endDate.config.min.date = date.date;
                    } else {
                        endDate.config.min.year = '';
                        endDate.config.min.month = '';
                        endDate.config.min.date = '';
                    }
                }
            });

            var endDate = laydate.render({
                elem: '#endTime',
                min: $('#startTime').val(),
                theme: '#393D49',
                //value: getDateStr(1),
                trigger: 'click',
                /**
                 * 校验器 开始时间小于结束时间
                 * @system value
                 * @system date
                 */
                done: function (value, date) {
                    if ('' != value) {
                        startDate.config.max.year = date.year;
                        startDate.config.max.month = date.month - 1;
                        startDate.config.max.date = date.date;
                    } else {
                        startDate.config.max.year = '';
                        startDate.config.max.month = '';
                        startDate.config.max.date = '';
                    }
                }
            });
        })
    }

    /**
     * checkbox 复选框变更状态，修改按钮样式
     */
    $('#bootstrap-table').on('check.bs.table uncheck.bs.table check-all.bs.table uncheck-all.bs.table', function () {
        /**
         * 获取选中的id
         * @type {*|jQuery}
         */
        var ids = $('#bootstrap-table').bootstrapTable('getSelections');

        /**
         * 批量删除按钮 ids.length != 0
         */
        $('#toolbar .btn-del').toggleClass('disabled', !ids.length);

        /**
         * 单个修改按钮，ids.length != 1
         */
        $('#toolbar .btn-edit').toggleClass('disabled', ids.length != 1);
    })

    /**
     * tree 搜索关键字绑定
     */
    if ($('#keyword').length > 0) {
        $('#keyword').bind('focus', function focusKey(e) {
            if ($('#keyword').hasClass('empty')) {
                $('#keyword').removeClass('empty');
            }
        }).bind('blur', function blurKey(e) {
            if ($('#keyword').val() === "") {
                $('#keyword').addClass("empty");
            }
            $.tree.searchNode(e);
        }).bind("input propertychange", $.tree.searchNode);
    }

    /**
     * tree表格树 展开/折叠
     * @type {boolean}
     */
    var expandFlag = false;
    $('#expandAllBtn').click(function () {
        if (expandFlag) {
            $('#bootstrap-table').bootstrapTreeTable('expandAll');
        } else {
            $('#bootstrap-table').bootstrapTreeTable('collapseAll');
        }
        expandFlag = expandFlag ? false : true;
    })

    /**
     * select2 标签复选框事件绑定
     */
    if ($.fn.select2 != undefined) {
        $('select.form-control:not(.noselect2)').each(function () {
            $(this).select2().on("change", function () {
                $(this).valid();
            })
        })
    }

    /**
     * checkbox 事件绑定
     */
    if ($(".check-box").length > 0) {
        $(".check-box").iCheck({
            checkboxClass: 'icheckbox-blue',
            radioClass: 'iradio-blue',
        })
    }
    /**
     * radio 事件绑定
     */
    if ($(".radio-box").length > 0) {
        $(".radio-box").iCheck({
            checkboxClass: 'icheckbox-blue',
            radioClass: 'iradio-blue',
        })
    }
});

/**
 * 创建选项卡
 * @system dataUrl
 * @system menuName
 * @returns {boolean}
 */
function createMenuItem(dataUrl, menuName) {
    dataIndex = $.common.random(1, 100),
        flag = true;
    if (dataUrl == undefined || $.trim(dataUrl).length == 0) return false;
    var topWindow = $(window.parent.document);
    /**
     * 选项卡菜单已存在
     */
    $('.menuTab', topWindow).each(function () {
        if ($(this).data('id') == dataUrl) {
            if (!$(this).hasClass('active')) {
                $(this).addClass('active').siblings('.menuTab').removeClass('active');
                $('.page-tabs-content').animate({marginLeft: ""}, "fast");
                /**
                 * 显示tab对应的内容区
                 */
                $('.mainContent .Smart_iframe', topWindow).each(function () {
                    if ($(this).data('id') == dataUrl) {
                        $(this).show().siblings('.Smart_iframe').hide();
                        return false;
                    }
                });
            }
            flag = false;
            return false;
        }
    });

    /**
     * 选项卡菜单不存在
     */
    if (flag) {
        var str = '<a href="javascript:;" class="active menuTab" data-id="' + dataUrl + '">' + menuName + ' <i class="fa fa-times-circle"></i></a>';
        $('.menuTab', topWindow).removeClass('active');

        /**
         * 添加选项卡对应的iframe
         */
        var str1 = '<iframe class="Smart_iframe" name="iframe' + dataIndex + '" width="100%" height="100%" src="' + dataUrl + '" frameborder="0" data-id="' + dataUrl + '" seamless></iframe>';
        $('.mainContent', topWindow).find('iframe.Smart_iframe').hide().parents('.mainContent').append(str1);

        /**
         * 添加选项卡
         */
        $('.menuTabs .page-tabs-content', topWindow).append(str);
    }
    return false;
}

function getDateStr(addDay) {
    var today = new Date();
    today.setDate(today.getDate() + addDay);
    var y = today.getFullYear();
    var m = today.getMonth() + 1;
    var d = today.getDate();
    return y + "-" + m + "-" + d;
}
