package com.facedamon.smart.framework.web.service;

import com.facedamon.smart.system.service.IConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:    html 调用thymeleaf实现参数管理
 * @Author:         facedamon
 * @CreateDate:     2018/10/29 16:18
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/10/29 16:18
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Service("config")
public class ConfigService {

    @Autowired
    private IConfigService configService;

    /**
     * 根据键查询参数配置信息
     * @param configKey
     * @return
     */
    public String getKey(String configKey){
        return configService.selectConfigByKey(configKey);
    }


}
