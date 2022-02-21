package com.lib4j.security;

public class Des {

    private static DesHelper des = new DesHelper("DES", "DES/ECB/PKCS5Padding");

    /**
     * DES加密,默认使用DES/ECB/PKCS5Padding
     * 
     * @param text
     * @param pwd
     * @return String
     */
    public static String encrypt(String text, String pwd) {
        return des.encrypt(text, pwd);
    }

    /**
     * DES加密,根据传入加密模式，填充模式进行加密
     * 
     * @param text
     * @param pwd
     * @param mode
     * @return String
     */
    public static String encrypt(String text, String pwd, String mode) {
        return des.encrypt(text, pwd, mode);
    }

    /**
     * DES加密,默认使用DES/ECB/PKCS5Padding
     * 
     * @param data
     * @param pwd
     * @return byte[]
     */
    public static byte[] encryptBytes(byte[] data, String pwd) {
        return des.encryptBytes(data, pwd);
    }

    /**
     * DES加密,根据传入加密模式，填充模式进行加密
     * 
     * @param data
     * @param pwd
     * @param mode
     * @return byte[]
     */
    public static byte[] encryptBytes(byte[] data, String pwd, String mode) {
        return des.decryptBytes(data, pwd, mode);
    }

    /**
     * DES解密，默认使用DES/ECB/PKCS5Padding
     * 
     * @param password
     * @param key
     * @return String
     */
    public static String decrypt(String password, String key) {
        return des.decrypt(password, key);

    }

    /**
     * DES解密,根据传入加密模式，填充模式进行加密
     * 
     * @param password
     * @param key
     * @param mode
     * @return String
     */
    public static String decrypt(String password, String key, String mode) {
        return des.decrypt(password, key, mode);

    }

    /**
     * DES解密，默认使用DES/ECB/PKCS5Padding
     * 
     * @param src
     * @param pwd
     * @return byte[]
     * @throws Exception
     */
    public static byte[] decryptBytes(byte[] src, String pwd) {
        return des.decryptBytes(src, pwd);
    }

    /**
     * DES加密,根据传入加密模式，填充模式进行加密
     * 
     * @param src
     * @param pwd
     * @param mode
     * @return byte[]
     * @throws Exception
     */
    public static byte[] decryptBytes(byte[] src, String pwd, String mode) {
        return des.decryptBytes(src, pwd, mode);
    }
}
