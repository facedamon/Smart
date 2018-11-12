/**
 *  通用方法封装
 *
 */


$(function () {

    /**
     * 时间控件绑定与校验
     */
    if ($('.select-time').length > 0){
        layui.use('laydate',function () {
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
                 * @param value
                 * @param date 选中的日期
                 */
                done: function (value,date) {
                    if ('' !== value){
                        endDate.config.min.year = date.year;
                        endDate.config.min.month = date.month - 1;
                        endDate.config.min.date = date.date;
                    } else {
                        endDate.config.min.year = '';
                        endDate.config.min.month ='';
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
                 * @param value
                 * @param date
                 */
                done: function (value,date) {
                    if ('' != value){
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
    $('#bootstrap-table').on('check.bs.table uncheck.bs.table check-all.bs.table uncheck-all.bs.table',function () {
        /**
         * 获取选中的id
         * @type {*|jQuery}
         */
        var ids = $('#bootstrap-table').bootstrapTable('getSelections');

        /**
         * 批量删除按钮 ids.length != 0
         */
        $('#toolbar .btn-del').toggleClass('disabled',!ids.length);

        /**
         * 单个修改按钮，ids.length != 1
         */
        $('#toolbar .btn-edit').toggleClass('disabled',ids.length!=1);
    })

    /**
     * tree 搜索关键字绑定
     */
    if ($('#keyword').length > 0){
        $('#keyword').bind('focus',function focusKey(e) {
            if ($('#keyword').hasClass('empty')){
                $('#keyword').removeClass('empty');
            }
        }).bind('blur',function blurKey(e){
            if ($('#keyword').val() === ""){
                $('#keyword').addClass("empty");
            }
            $.tree.searchNode(e);
        }).bind("input propertychange",$.tree.searchNode);
    }

    /**
     * tree表格树 展开/折叠
     * @type {boolean}
     */
    var expandFlag = false;
    $('#expandAllBtn').click(function () {
        if (expandFlag){
            $('#bootstrap-table').bootstrapTreeTable('expandAll');
        } else {
            $('#bootstrap-table').bootstrapTreeTable('collapseAll');
        }
        expandFlag = expandFlag ? false : true;
    })

    /**
     * select2 标签复选框事件绑定
     */
    if ($.fn.select2 != undefined){
        $('select.form-control:not(.noselect2)').each(function () {
            $(this).select2().on("change",function () {
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

function getDateStr(addDay){
    var today = new Date();
    today.setDate(today.getDate()+addDay);
    var y = today.getFullYear();
    var m = today.getMonth() + 1;
    var d = today.getDate();
    return y+"-"+m+"-"+d;
}
