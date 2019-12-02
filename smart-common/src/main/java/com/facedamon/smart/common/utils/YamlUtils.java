package com.facedamon.smart.common.utils;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description: 加载yml文件
 * @Author: facedamon
 * @CreateDate: 2018/10/25 17:51
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/25 17:51
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class YamlUtils {

    /**
     * 加载yml文件
     *
     * @param fileName
     * @return
     * @throws FileNotFoundException
     */
    public static Map<?, ?> loadYaml(String fileName) throws FileNotFoundException {
        InputStream in = YamlUtils.class.getClassLoader().getResourceAsStream(fileName);
        return StringUtils.isNotBlank(fileName) ? (LinkedHashMap<?, ?>) new Yaml().load(in) : null;
    }

    /**
     * 修改yml文件
     *
     * @param fileName
     * @param map      修改内容
     * @throws IOException
     */
    public static void dumpYaml(String fileName, Map<?, ?> map) throws IOException {
        if (StringUtils.isNotBlank(fileName)) {
            FileWriter fileWriter = new FileWriter(YamlUtils.class.getResource(fileName).getFile());
            DumperOptions options = new DumperOptions();
            options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
            Yaml yaml = new Yaml(options);
            yaml.dump(map, fileWriter);
        }
    }

    /**
     * 获取key-value
     *
     * @param map yml对象
     * @param key 关键词
     * @return
     */
    public static Object getProperty(Map<?, ?> map, Object key) {
        if (null != map && !map.isEmpty() && null != key) {
            String input = String.valueOf(key);
            if (StringUtils.isNotBlank(input)) {
                if (input.contains(".")) {
                    int firstIndex = input.indexOf(".");
                    String left = input.substring(0, firstIndex);
                    String right = input.substring(firstIndex + 1, input.length());
                    return getProperty((Map<?, ?>) map.get(left), right);
                } else if (map.containsKey(input)) {
                    return map.get(input);
                } else {
                    return null;
                }
            }
        }
        return null;
    }

    /**
     * 设置key-value
     *
     * @param map
     * @param key
     * @param value
     */
    public static void setProperty(Map<?, ?> map, Object key, Object value) {
        if (null != map && !map.isEmpty() && null != key) {
            String input = String.valueOf(key);
            if (StringUtils.isNotBlank(input)) {
                if (input.contains(".")) {
                    int firstIndex = input.indexOf(".");
                    String left = input.substring(0, firstIndex);
                    String right = input.substring(firstIndex + 1, input.length());
                    setProperty((Map<?, ?>) map.get(left), right, value);
                } else {
                    ((Map<Object, Object>) map).put(key, value);
                }
            }
        }
    }
}
