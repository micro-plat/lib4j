package com.lib4j.security;

import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class Aes {

    protected String algorithm = "AES";
    protected String defMode = "AES/ECB/PKCS5Padding";
    public static Aes Instance = new Aes("AES/ECB/PKCS5Padding");

    public Aes(String defMode) {
        this.defMode = defMode;
    }

    // 加密
    public String encrypt(String encryptText, String sKey) throws Exception {
        // 判断Key是否为16位
        if (sKey == null || sKey.length() != 16) {
            throw new Exception("Key长度不是16位");
        }
        byte[] raw = sKey.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec skeySpec = new SecretKeySpec(raw, algorithm);
        Cipher cipher = Cipher.getInstance(defMode);// "算法/模式/补码方式"
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(encryptText.getBytes(StandardCharsets.UTF_8));
        return new Base64().encodeToString(encrypted);// 此处使用BASE64做转码功能，同时能起到2次加密的作用。
    }

    // 解密
    public String decrypt(String decryptText, String sKey) throws Exception {
        // 判断Key是否为16位
        if (sKey == null || sKey.length() != 16) {
            throw new Exception("Key长度不是16位");
        }

        byte[] raw = sKey.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec skeySpec = new SecretKeySpec(raw, algorithm);
        Cipher cipher = Cipher.getInstance(defMode);
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] encrypted1 = new Base64().decode(decryptText);// 先用base64解密

        byte[] original = cipher.doFinal(encrypted1);
        String originalString = new String(original, StandardCharsets.UTF_8);
        return originalString;

    }
}
