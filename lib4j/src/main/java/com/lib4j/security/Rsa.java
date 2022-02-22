package com.lib4j.security;

import java.util.Base64;
import javax.crypto.Cipher;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class Rsa {

    private static String encoding = "utf-8";
    private static String algorithm = "RSA";
    private static int keyLen = 1024;

    /**
     * 随机生成密钥对
     * 
     *
     */
    public static Map<Integer, String> genKeyPair() throws Exception {
        Map<Integer, String> keyMap = new HashMap<Integer, String>();
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(algorithm);
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(keyLen, new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate(); // 得到私钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic(); // 得到公钥
        String publicKeyString = new String(Base64.getEncoder().encode(publicKey.getEncoded()));
        // 得到私钥字符串
        String privateKeyString = new String(Base64.getEncoder().encode((privateKey.getEncoded())));
        // 将公钥和私钥保存到Map
        keyMap.put(0, publicKeyString); // 0表示公钥
        keyMap.put(1, privateKeyString); // 1表示私钥
        return keyMap;
    }

    /**
     * RSA公钥加密
     * 
     * @param encryptText
     *                  加密字符串
     * @param publicKey
     *                  公钥
     * @return 密文
     * @throws Exception
     *                   加密过程中的异常信息
     */
    public static String encrypt(String encryptText, String publicKey) throws Exception {
        // base64编码的公钥
        byte[] decoded = Base64.getDecoder().decode(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance(algorithm)
                .generatePublic(new X509EncodedKeySpec(decoded));
        // RSA加密
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        String outStr = Base64.getEncoder().encodeToString(cipher.doFinal(encryptText.getBytes(encoding)));
        return outStr;
    }

    /**
     * RSA私钥解密
     * 
     * @param decryptText
     *                   加密字符串
     * @param privateKey
     *                   私钥
     * @return 铭文
     * @throws Exception
     *                   解密过程中的异常信息
     */
    public static String decrypt(String decryptText, String privateKey) throws Exception {
        // 64位解码加密后的字符串
        byte[] inputByte = Base64.getDecoder().decode(decryptText.getBytes(encoding));
        // base64编码的私钥
        byte[] decoded = Base64.getDecoder().decode(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance(algorithm)

                .generatePrivate(new PKCS8EncodedKeySpec(decoded));
        // RSA解密
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        String outStr = new String(cipher.doFinal(inputByte));
        return outStr;
    }

}
