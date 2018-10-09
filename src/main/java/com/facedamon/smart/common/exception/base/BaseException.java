package com.facedamon.smart.common.exception.base;

import com.facedamon.smart.common.utils.MessageUtils;
import com.facedamon.smart.common.utils.StringUtils;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * @Description:    基础自定义异常
 * @Author:         facedamon
 * @CreateDate:     2018/10/1 下午5:18
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/10/1 下午5:18
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Getter
@ToString
@Builder
public class BaseException extends RuntimeException {

    /**
     * 所属模块
     */
    private String module;

    /**
     * 错误吗
     */
    private String code;

    /**
     * 错误吗对应参数
     */
    private Object[] args;

    /**
     * 错误消息
     */
    private String defaultMessage;

    @Override
    public String getMessage() {
        String message = "";
        if (StringUtils.isBlank(code)){
            message = defaultMessage;
        }else{
            message = MessageUtils.message(code);
        }
        return message;
    }

    public BaseException(String module, String code, Object[] args, String defaultMessage) {
        this.module = module;
        this.code = code;
        this.args = args;
        this.defaultMessage = defaultMessage;
    }

    public BaseException(String module, String code, Object[] args) {
        this(module, code, args, null);
    }

    public BaseException(String module, String defaultMessage) {
        this(module, null, null, defaultMessage);
    }

    public BaseException(String code, Object[] args) {
        this(null, code, args, null);
    }

    public BaseException(String defaultMessage) {
        this(null, null, null, defaultMessage);
    }
}
