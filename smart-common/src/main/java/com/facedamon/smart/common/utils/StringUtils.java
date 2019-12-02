package com.facedamon.smart.common.utils;

import com.facedamon.smart.common.support.StrFormatter;

import java.util.Locale;

/**
 * @Description: 继承apache stringutils 并添加扩展方法
 * @Author: facedamon
 * @CreateDate: 2018/9/30 下午9:11
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/9/30 下午9:11
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
    public static final String SEPARATOR = "_";

    /**
     * 获取参数不为空值
     *
     * @param value
     * @param defaultValue
     * @param <T>
     * @return
     */
    public static <T> T nvl(T value, T defaultValue) {
        return null != value ? value : defaultValue;
    }

    public static String firstChar2UpperCase(String str) {
        return isNotBlank(str) ?
                str.toUpperCase().substring(0, 1) + str.substring(1)
                : EMPTY;
    }

    public static String firstChar2Lower(String str) {
        return isNotBlank(str) ?
                str.toLowerCase().substring(0, 1) + str.substring(1)
                : EMPTY;
    }

    /**
     * 下划线转驼峰，首字母小写
     *
     * @param str
     * @return
     */
    public static String under2Camel2Lower(String str) {
        if (isNotBlank(str)) {
            String camel = under2Camel(str);
            return firstChar2Lower(camel);
        } else {
            return EMPTY;
        }
    }

    /**
     * 下划线转驼峰，首字母大写
     *
     * @param str
     * @return
     */
    public static String under2Camel2UpperCase(String str) {
        if (isNotBlank(str)) {
            String caml = under2Camel(str);
            return firstChar2UpperCase(caml);
        } else {
            return EMPTY;
        }
    }

    /**
     * 下划线转驼峰
     *
     * @param value
     * @return
     */
    public static String under2Camel(String value) {
        if (isBlank(value)) {
            return EMPTY;
        }
        StringBuffer sb = new StringBuffer();
        value = value.toLowerCase(Locale.ENGLISH);
        String[] str = value.split(SEPARATOR);
        for (String s : str) {
            sb.append(s.substring(0, 1).toUpperCase(Locale.ENGLISH));
            sb.append(s.substring(1));
        }
        return sb.toString();
    }

    /**
     * 驼峰转下划线
     *
     * @param value
     * @return
     */
    public static String camel2Under(String value) {
        if (isBlank(value)) {
            return EMPTY;
        }
        StringBuffer sb = new StringBuffer(value);
        int temp = 0;
        for (int i = 0; i < value.length(); i++) {
            if (Character.isUpperCase(value.charAt(i))) {
                sb.insert(i + temp, SEPARATOR);
                temp += 1;
            }
        }
        return sb.toString().toLowerCase(Locale.ENGLISH);
    }

    public static String format(String template, Object... params) {
        if (params == null || params.length < 1 || isBlank(template)) {
            return template;
        }
        return StrFormatter.format(template, params);
    }

}