package com.facedamon.smart.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.facedamon.smart.common.config.SmartConfig;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 获取地址类
 * @Author: facedamon
 * @CreateDate: 2018/10/15 下午7:25
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/15 下午7:25
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Slf4j
@Deprecated
public class AddressUtils {

    public static final String IP_URL = "http://ip.taobao.com/service/getIpInfo.php";

    public static String getRealAddressByIP(String ip) {
        String address = "XX XX";
        if (SmartConfig.INSTANCE.isAddressEnabled()) {
            String rspStr = HttpUtils.sendPost(IP_URL, "ip=" + ip);
            if (StringUtils.isEmpty(rspStr)) {
                log.error("获取地理位置异常 {}", ip);
                return address;
            }

            JSONObject obj = JSONObject.parseObject(rspStr);
            JSONObject data = obj.getObject("data", JSONObject.class);
            String region = data.getString("region");
            String city = data.getString("city");
            address = region + " " + city;
        }
        return address;
    }

}
