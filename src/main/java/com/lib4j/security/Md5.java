package com.lib4j.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.lib4j.security.Hex;


public final class Md5 {

    private static String encoding = "utf-8";
    private static String md5Name = "MD5";


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
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param buff
     * @return String
     */
    public static String encryptByes(byte[] buff) {
        try {
            MessageDigest md5 = MessageDigest.getInstance(md5Name);
            md5.update(buff, 0, buff.length);
            return Hex.encrypt(md5.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

    }


}
