package com.facedamon.smart.common.exception.job;

/**
 * @Description: 计划策略异常
 * @Author: facedamon
 * @CreateDate: 2018/11/26 16:14
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/11/26 16:14
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class TaskException extends Exception {

    public enum Code {
        /**
         * 配置错误
         */
        CONFIG_ERROR
    }

    private Code code;

    public Code getCode() {
        return code;
    }

    public TaskException(String msg, Code code) {
        this(msg, code, null);
    }

    public TaskException(String msg, Code code, Exception nestedEx) {
        super(msg, nestedEx);
        this.code = code;
    }
}
