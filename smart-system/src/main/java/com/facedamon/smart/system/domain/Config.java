package com.facedamon.smart.system.domain;

import com.facedamon.smart.common.base.BaseEntity;
import lombok.Builder;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @Description:    参数配置表
 * @Author:         facedamon
 * @CreateDate:     2018/10/29 16:22
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/10/29 16:22
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Data
@Builder
@Alias("Config")
public class Config extends BaseEntity {

    /**
     * 参数主键
     */
    private Long configId;

    /**
     * 参数名称
     */
    private String configName;

    /**
     * 参数键
     */
    private String configKey;

    /**
     * 参数值
     */
    private String configValue;

    /**
     * 是否系统内置(Y/N)
     */
    private String configType;
}
