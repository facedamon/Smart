package com.facedamon.smart.common.support;

import com.facedamon.smart.common.utils.StringUtils;

/**
 * @Description: 格式化字符串
 * @Author: facedamon
 * @CreateDate: 2018/10/26 16:11
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/26 16:11
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class StrFormatter {

    private static final String EMPTY_JSON = "{}";

    /**
     * 格式化字符串<br>
     * 此方法只是简单占位符{} 按照顺序替换为参数<br>
     *
     * @param str 字符串模板
     * @param arr 参数列表
     * @return
     */
    public static String format(final String str, final Object... arr) {
        if (StringUtils.isBlank(str) || arr == null || arr.length < 1) {
            return str;
        }
        final int len = str.length();

        /**
         * 初始化定义好的长度以获得更好的性能
         */
        StringBuffer sbuf = new StringBuffer(len + 50);

        /**
         * 开始检索下标
         */
        int position = 0;
        /**
         * 占位符下标
         */
        int index;

        for (int argIndex = 0; argIndex < arr.length; argIndex++) {
            index = str.indexOf(EMPTY_JSON, position);
            /**
             * 没有{}
             */
            if (index < 0) {
                if (position == 0) {
                    return str;
                } else {
                    sbuf.append(str, position, len);
                    return sbuf.toString();
                }
            } else {
                sbuf.append(str, position, index)
                        .append(Convert.utf8Str(arr[argIndex]));
                position = index + 2;
            }
        }
        sbuf.append(str, position, len);

        return sbuf.toString();
    }
}
