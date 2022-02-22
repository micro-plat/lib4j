package com.lib4j.security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.util.Base64;

//处理DES加解密
public class DesHelper {
    protected String algorithm = "DES";
    protected String defMode = "DES/ECB/PKCS5Padding";

    public DesHelper(String algorithm, String defMode) {
        this.algorithm = algorithm;
        this.defMode = defMode;
    }

    /**
     * DES加密,默认使用DES/ECB/PKCS5Padding
     * 
     * @param text
     * @param pwd
     * @return String
     * @throws Exception
     */
    public String encrypt(String text, String pwd) throws Exception {
        return encrypt(text, pwd, defMode);
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
    public String encrypt(String text, String pwd, String mode) throws Exception {
        byte[] encrypt = encryptBytes(text.getBytes(StandardCharsets.UTF_8), pwd, mode);
        assert encrypt != null;
        return new String(Base64.getEncoder().encode(encrypt), StandardCharsets.UTF_8);
    }

    /**
     * DES加密,默认使用DES/ECB/PKCS5Padding
     * 
     * @param data
     * @param pwd
     * @return byte[]
     * @throws Exception
     */
    public byte[] encryptBytes(byte[] data, String pwd) throws Exception {
        return encryptBytes(data, pwd, defMode);
    }

    /**
     * DES加密,根据传入加密模式，填充模式进行加密
     * 
     * @param data
     * @param pwd
     * @param mode
     * @return byte[]
     * @throws InvalidKeyException
     */
    public byte[] encryptBytes(byte[] data, String pwd, String mode) throws Exception {

        if (mode == null || mode == "") {
            mode = defMode;
        }
        byte[] key = pwd.getBytes();
        DESKeySpec desKey = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(algorithm);
        SecretKey securekey = keyFactory.generateSecret(desKey);
        Cipher cipher = Cipher.getInstance(mode);
        cipher.init(Cipher.ENCRYPT_MODE, securekey);

        return cipher.doFinal(data);

    }

    /**
     * DES解密，默认使用DES/ECB/PKCS5Padding
     * 
     * @param password
     * @param key
     * @return String
     * @throws Exception
     */
    public String decrypt(String password, String key) throws Exception {
        return decrypt(password, key, defMode);
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
    public String decrypt(String password, String key, String mode) throws Exception {
        return new String(
                decryptBytes(Base64.getDecoder().decode(password.getBytes(StandardCharsets.UTF_8)), key, mode),
                StandardCharsets.UTF_8);

    }

    /**
     * DES解密，默认使用DES/ECB/PKCS5Padding
     * 
     * @param src
     * @param pwd
     * @return byte[]
     * @throws Exception
     */
    public byte[] decryptBytes(byte[] src, String pwd) throws Exception {
        return decryptBytes(src, pwd, defMode);
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
    public byte[] decryptBytes(byte[] src, String pwd, String mode) throws Exception {

        if (mode == null || mode == "") {
            mode = defMode;
        }
        byte[] key = pwd.getBytes();
        DESKeySpec desKey = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(algorithm);
        SecretKey securekey = keyFactory.generateSecret(desKey);
        Cipher cipher = Cipher.getInstance(mode);
        cipher.init(Cipher.DECRYPT_MODE, securekey);
        return cipher.doFinal(src);

    }
}
