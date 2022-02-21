package com.lib4j.security;

import java.security.MessageDigest;


public final class Md5 {

    private static String encoding = "utf-8";
    private static String algorithm = "MD5";

    private Md5() {
    }

    /**
     * @param str
     * @return String
     */
    public static String encrypt(String str) {
        try {
            byte[] buff = str.getBytes(encoding);
            return encryptByes(buff);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @param buff
     * @return String
     */
    public static String encryptByes(byte[] buff) {
        try {
            MessageDigest md5 = MessageDigest.getInstance(algorithm);
            md5.update(buff, 0, buff.length);
            return Hex.encrypt(md5.digest());
        } catch (Exception e) {
            return null;
        }

    }

}
