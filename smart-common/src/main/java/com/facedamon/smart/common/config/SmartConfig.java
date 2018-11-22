package com.facedamon.smart.common.config;

import com.facedamon.smart.common.utils.StringUtils;
import com.facedamon.smart.common.utils.YamlUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 全局配置 单例模式
 * @Author: facedamon
 * @CreateDate: 2018/10/26 11:10
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/26 11:10
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Slf4j
public enum SmartConfig {

    /**
     * 实例
     */
    INSTANCE;

    /**
     * 配置文件名
     */
    private final String NAME = "application.yml";

    /**
     * 保存全局属性
     */
    private Map<String, String> map = new HashMap<>();

    /**
     * 获取配置
     *
     * @param key
     * @return
     */
    public String getConfig(String key) {
        String value = map.get(key);
        if (StringUtils.isBlank(value)) {
            Map<?, ?> yamlMap = null;
            try {
                yamlMap = YamlUtils.loadYaml(NAME);
                value = String.valueOf(YamlUtils.getProperty(yamlMap, key));
                map.put(key, StringUtils.isNotBlank(value) ? value : StringUtils.EMPTY);
            } catch (FileNotFoundException e) {
                log.error("获取全局配置异常,key={},exception={}", key, e);
            }
        }
        return value;
    }

    /**
     * 获取项目名称
     *
     * @return
     */
    public String getName() {
        return StringUtils.nvl(getConfig("smart.name"), "smart");
    }

    /**
     * 获取项目版本
     *
     * @return
     */
    public String getVersion() {
        return StringUtils.nvl(getConfig("smart.version"), "0.0.1");
    }

    /**
     * 获取版权年份
     *
     * @return
     */
    public String getCopyrightYear() {
        return StringUtils.nvl(getConfig("smart.copyrightYear"), "2018");
    }

    /**
     * 获取ip地址开关
     *
     * @return
     */
    public Boolean isAddressEnabled() {
        return Boolean.valueOf(getConfig("smart.addressEnabled"));
    }

    /**
     * 获取文件上传路径
     *
     * @return
     */
    public String getProfile() {
        return getConfig("smart.profile");
    }

    /**
     * 获取头像上传路径
     *
     * @return
     */
    public String getAvatarPath() {
        return getConfig("smart.profile") + "avatar/";
    }

    /**
     * 获取下载上传路径
     *
     * @return
     */
    public String getDownloadPath() {
        return getConfig("smart.profile") + "download/";
    }

    /**
     * 获取作者
     *
     * @return
     */
    public String getAuthor() {
        return StringUtils.nvl(getConfig("generator.author"), "facedamon");
    }

    /**
     * 生成包路径
     */
    public String getPackageName() {
        return StringUtils.nvl(getConfig("generator.packageName"), "com.facedamon.smart.project.module");
    }

    /**
     * 是否自动去除表前缀
     */
    public String getAutoRemovePre() {
        return StringUtils.nvl(getConfig("generator.autoRemovePre"), "true");
    }

    /**
     * 表前缀(类名不会包含表前缀)
     */
    public String getTablePrefix() {
        return StringUtils.nvl(getConfig("generator.tablePrefix"), "sys_");
    }

    /**
     * 工程路径
     * @return
     */
    public String getProjectPath(){return StringUtils.nvl(getConfig("generator.projectPath"),"main/java/com/facedamon/smart");}

    /**
     * mybatis 路径
     * @return
     */
    public String getMybatisPath(){return StringUtils.nvl(getConfig("generator.mybatisPath"),"main/resources/mybatis");}

    /**
     * html模板路径
     * @return
     */
    public String getTemplatesPath(){return StringUtils.nvl(getConfig("generator.templatesPath"),"main/resources/templates");}
}
