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
     * @param encryptText
     * @param pwd
     * @return String
     * @throws Exception
     */
    public String encrypt(String encryptText, String pwd) throws Exception {
        return encrypt(encryptText, pwd, defMode);
    }

    /**
     * DES加密,根据传入加密模式，填充模式进行加密
     * 
     * @param encryptText
     * @param pwd
     * @param mode
     * @return String
     * @throws Exception
     */
    public String encrypt(String encryptText, String pwd, String mode) throws Exception {
        byte[] encrypt = encryptBytes(encryptText.getBytes(StandardCharsets.UTF_8), pwd, mode);
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
     * @param encryptText
     * @param pwd
     * @param mode
     * @return byte[]
     * @throws InvalidKeyException
     */
    public byte[] encryptBytes(byte[] encryptText, String pwd, String mode) throws Exception {

        if (mode == null || mode == "") {
            mode = defMode;
        }
        byte[] key = pwd.getBytes();
        DESKeySpec desKey = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(algorithm);
        SecretKey securekey = keyFactory.generateSecret(desKey);
        Cipher cipher = Cipher.getInstance(mode);
        cipher.init(Cipher.ENCRYPT_MODE, securekey);

        return cipher.doFinal(encryptText);

    }

    /**
     * DES解密，默认使用DES/ECB/PKCS5Padding
     * 
     * @param decryptText
     * @param key
     * @return String
     * @throws Exception
     */
    public String decrypt(String decryptText, String key) throws Exception {
        return decrypt(decryptText, key, defMode);
    }

    /**
     * DES解密,根据传入加密模式，填充模式进行加密
     * 
     * @param decryptText
     * @param key
     * @param mode
     * @return String
     * @throws Exception
     */
    public String decrypt(String decryptText, String key, String mode) throws Exception {
        return new String(
                decryptBytes(Base64.getDecoder().decode(decryptText.getBytes(StandardCharsets.UTF_8)), key, mode),
                StandardCharsets.UTF_8);

    }

    /**
     * DES解密，默认使用DES/ECB/PKCS5Padding
     * 
     * @param decryptText
     * @param pwd
     * @return byte[]
     * @throws Exception
     */
    public byte[] decryptBytes(byte[] decryptText, String pwd) throws Exception {
        return decryptBytes(decryptText, pwd, defMode);
    }

    /**
     * DES加密,根据传入加密模式，填充模式进行加密
     * 
     * @param decryptText
     * @param pwd
     * @param mode
     * @return byte[]
     * @throws Exception
     */
    public byte[] decryptBytes(byte[] decryptText, String pwd, String mode) throws Exception {

        if (mode == null || mode == "") {
            mode = defMode;
        }
        byte[] key = pwd.getBytes();
        DESKeySpec desKey = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(algorithm);
        SecretKey securekey = keyFactory.generateSecret(desKey);
        Cipher cipher = Cipher.getInstance(mode);
        cipher.init(Cipher.DECRYPT_MODE, securekey);
        return cipher.doFinal(decryptText);

    }
}
