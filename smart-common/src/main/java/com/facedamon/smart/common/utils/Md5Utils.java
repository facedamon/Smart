package com.facedamon.smart.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;

/**
 * @Description: MD5 加密
 * @Author: facedamon
 * @CreateDate: 2018/10/29 15:23
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/29 15:23
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Slf4j
public class Md5Utils {

    private static byte[] md5(String s) {
        MessageDigest algorithm;
        try {
            algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(s.getBytes("UTF-8"));
            byte[] messageDigest = algorithm.digest();
            return messageDigest;
        } catch (Exception e) {
            log.error("MD5 Error...", e);
        }
        return null;
    }

    private static final String toHex(byte hash[]) {
        if (hash == null) {
            return null;
        }
        StringBuffer buf = new StringBuffer(hash.length * 2);
        int i;

        for (i = 0; i < hash.length; i++) {
            if ((hash[i] & 0xff) < 0x10) {
                buf.append("0");
            }
            buf.append(Long.toString(hash[i] & 0xff, 16));
        }
        return buf.toString();
    }

    public static String hash(String s) {
        try {
            return new String(toHex(md5(s)).getBytes("UTF-8"), "UTF-8");
        } catch (Exception e) {
            log.error("not supported charset...{}", e);
            return s;
        }
    }
}
