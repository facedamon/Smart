package com.facedamon.smart.core.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description:    项目配置
 * @Author:         facedamon
 * @CreateDate:     2018/10/16 下午4:31
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/10/16 下午4:31
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Component
@ConfigurationProperties(prefix = "smart")
public class SmartConfig {

    /**
     * 项目名称
     */
    @Getter
    @Setter
    private String name;
    /**
     * 版本
     */
    @Getter
    @Setter
    private String version;
    /**
     * 版本年限
     */
    @Getter
    @Setter
    private String copyrightYear;
    /**
     * 上传路径
     */
    @Getter
    @Setter
    private static String profile;
    /**
     * 获取地址开关
     */
    @Getter
    @Setter
    private static boolean addressEnabled;

    public static String getAvatarPath() {
        return profile + "avatar/";
    }

    public static String getDownloadPath() {
        return profile + "download/";
    }
}
