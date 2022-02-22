package com.lib4j.security;

public class Des3 {

    private static DesHelper des = new DesHelper("DESede", "DESede/ECB/PKCS5Padding");

    /**
     * DES加密,默认使用DES/ECB/PKCS5Padding
     * 
     * @param text
     * @param pwd
     * @return String
     * @throws Exception
     */
    public static String encrypt(String text, String pwd) throws Exception {
        return des.encrypt(text, pwd);
    }

    /**
     * DES加密,根据传入加密模式，填充模式进行加密
     * 
     * @param text
     * @param pwd
     * @param mode
     * @return String
     * @throws Exception
     */
    public static String encrypt(String text, String pwd, String mode) throws Exception {
        return des.encrypt(text, pwd, mode);
    }

    /**
     * DES加密,默认使用DES/ECB/PKCS5Padding
     * 
     * @param data
     * @param pwd
     * @return byte[]
     * @throws Exception
     */
    public static byte[] encryptBytes(byte[] data, String pwd) throws Exception {
        return des.encryptBytes(data, pwd);
    }

    /**
     * DES加密,根据传入加密模式，填充模式进行加密
     * 
     * @param data
     * @param pwd
     * @param mode
     * @return byte[]
     * @throws Exception
     */
    public static byte[] encryptBytes(byte[] data, String pwd, String mode) throws Exception {
        return des.decryptBytes(data, pwd, mode);
    }

    /**
     * DES解密，默认使用DES/ECB/PKCS5Padding
     * 
     * @param password
     * @param key
     * @return String
     * @throws Exception
     */
    public static String decrypt(String password, String key) throws Exception {
        return des.decrypt(password, key);

    }

    /**
     * DES解密,根据传入加密模式，填充模式进行加密
     * 
     * @param password
     * @param key
     * @param mode
     * @return String
     * @throws Exception
     */
    public static String decrypt(String password, String key, String mode) throws Exception {
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
    public static byte[] decryptBytes(byte[] src, String pwd) throws Exception {
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
    public static byte[] decryptBytes(byte[] src, String pwd, String mode) throws Exception {
        return des.decryptBytes(src, pwd, mode);
    }
}
