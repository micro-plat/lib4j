package com.lib4j.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;


public final class Md5 {

    private static String algorithm = "MD5";

    private Md5() {
    }

    /**
     * @param encryptText
     * @return String
     */
    public static String encrypt(String encryptText) {
        try {
            byte[] buff = encryptText.getBytes(StandardCharsets.UTF_8);
            return encryptByes(buff);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @param encryptText
     * @return String
     */
    public static String encryptByes(byte[] encryptText) {
        try {
            MessageDigest md5 = MessageDigest.getInstance(algorithm);
            md5.update(encryptText, 0, encryptText.length);
            return Hex.encrypt(md5.digest());
        } catch (Exception e) {
            return null;
        }

    }

}
