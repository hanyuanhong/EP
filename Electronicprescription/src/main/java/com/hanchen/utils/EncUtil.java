package com.hanchen.utils;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.symmetric.SymmetricCrypto;

import java.nio.charset.StandardCharsets;

/**
 * SM4工具
 * @author hyh
 */
public class EncUtil {

    /**
     * 公钥
     */
    private String appId = "0FF72E0A1ACB4624BB5A30B9D32C4E51";

    /**
     * 密钥
     */
    private String appSecret = "FBD55CAF544C45E39359C27C82956E15";

    /**
     * 真实密钥
     */
    private byte[] actualSecret;

    public EncUtil() {}

    public EncUtil(String appId, String appSecret) {
        this.appId = appId;
        this.appSecret = appSecret;
        this.actualSecret = encrypt(
                appId.substring(0, 16).getBytes(StandardCharsets.UTF_8),
                appSecret
        ).substring(0, 16).getBytes(StandardCharsets.UTF_8);
    }

    /**
     * 加密
     * @param data 明文
     */
    public String encrypt(String data) {
        return encrypt(actualSecret, data);
    }

    /**
     * 解密
     * @param encryptData 密文
     */
    public String decrypt(String encryptData) {
        return decrypt(actualSecret, encryptData);
    }

    /**
     * 加密
     * @param secretkey 密钥
     * @param data      明文
     */
    public String encrypt(byte[] secretkey, String data) {
        SymmetricCrypto sm4 = SmUtil.sm4(secretkey);
        return sm4.encryptHex(data.getBytes(StandardCharsets.UTF_8)).toUpperCase();
    }

    /**
     * 解密
     * @param secretkey   密钥
     * @param encryptData 密文
     */
    public String decrypt(byte[] secretkey, String encryptData) {
        SymmetricCrypto sm4 = SmUtil.sm4(secretkey);
        return sm4.decryptStr(encryptData, CharsetUtil.CHARSET_UTF_8);
    }
}
