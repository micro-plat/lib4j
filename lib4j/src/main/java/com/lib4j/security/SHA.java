package com.lib4j.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA {

    private static final String SHA_1 = "SHA-1";
    private static final String SHA_224 = "SHA-224";
    private static final String SHA_256 = "SHA-256";
    private static final String SHA_384 = "SHA-384";
    private static final String SHA_512 = "SHA-512";

    public static String sha1(String encryptText) {
        return getSha(encryptText, SHA_1);
    }

    public static String sha224(String encryptText) {
        return getSha(encryptText, SHA_224);
    }

    public static String sha256(String encryptText) {
        return getSha(encryptText, SHA_256);
    }

    public static String sha384(String encryptText) {
        return getSha(encryptText, SHA_384);
    }

    public static String sha512(String encryptText) {
        return getSha(encryptText, SHA_512);
    }

    private static String getSha(String encryptText, String algorithm) {
        // 输入的字符串转换成字节数组
        byte[] bytes = encryptText.getBytes(StandardCharsets.UTF_8);
        MessageDigest messageDigest;
        try {
            // 获得SHA转换器
            messageDigest = MessageDigest.getInstance(algorithm);
            // bytes是输入字符串转换得到的字节数组
            messageDigest.update(bytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA签名过程中出现错误,算法异常");
        }
        // 转换并返回结果，也是字节数组，包含16个元素
        byte[] digest = messageDigest.digest();

        return Hex.encrypt(digest);
    }
}
