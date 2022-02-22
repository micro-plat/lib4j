package com.lib4j.sas;

import java.util.Map;

import com.lib4j.security.Aes;
import com.lib4j.security.Des;
import com.lib4j.security.Hmac;
import com.lib4j.security.Md5;
import com.lib4j.security.Rsa;
import com.lib4j.security.SHA;

public class SaS<T extends ISasData> {

    private T iSasData;

    public SaS(T t) {
        this.iSasData = t;
    }

    /**
     * @param uid
     * @return String
     * @throws Exception
     */
    private String getSecretKey(String uid) throws Exception {
        return Md5.encrypt(Md5.encrypt(uid));
    }

    /**
     * 获取uid的指定类型的密钥(解密后)
     * 
     * @param uid
     * @param tp
     * @return String
     * @throws Exception
     */
    public String getSecret(String uid, SasType tp) throws Exception {
        return Aes.Instance.decrypt(this.iSasData.getSecret(uid, tp.name()), getSecretKey(uid));
    }

    /**
     * 保存uid的指定类型的密钥
     * 
     * @param uid
     * @param tp
     * @param value
     * @throws Exception
     */
    public void setSecret(String uid, SasType tp, String value) throws Exception {
        this.iSasData.setSecret(uid, tp.name(), Aes.Instance.encrypt(value, getSecretKey(uid)));
    }

    /**
     * 根据用户、密钥类型、签名map，自动进行签名生成，采用默认签名方式生成签名原串
     * 
     * @param uid
     * @param tp
     * @param params
     * @return String
     * @throws Exception
     */
    public String genRaw(String uid, AlgorithmType tp, Map<String, Object> params) throws Exception {
        SasType stp = AlgorithmType.GetSasType(tp);
        String secret = this.getSecret(uid, stp);
        return SignMaker.Instance.genRaw(params, secret);
    }

    /**
     * 验证签名是否相同
     * 
     * @param uid
     * @param tp
     * @param params
     * @param outSign
     * @return boolean
     * @throws Exception
     */
    public boolean verify(String uid, AlgorithmType tp, Map<String, Object> params, String outSign) throws Exception {
        return genSignature(uid, tp, params).equalsIgnoreCase(outSign);
    }

    /**
     * 验证签名是否相同
     * 
     * @param raw
     * @param tp
     * @param secret
     * @param outSign
     * @return boolean
     * @throws Exception
     */
    public boolean verify(String raw, AlgorithmType tp, String secret, String outSign) throws Exception {
        return genSignature(raw, tp, secret).equalsIgnoreCase(outSign);
    }

    /**
     * 指定签名原串进行生成签名
     * 
     * @param raw
     * @param tp
     * @param secret
     * @return String
     * @throws Exception
     */
    public String genSignature(String raw, AlgorithmType tp, String secret) throws Exception {
        return SignMaker.Instance.genSignature(raw, tp, secret);
    }

    /**
     * 根据用户、密钥类型、签名map，自动进行签名生成，采用默认签名方式进行签名
     * 
     * @param uid
     * @param tp
     * @param params
     * @return String
     * @throws Exception
     */
    public String genSignature(String uid, AlgorithmType tp, Map<String, Object> params) throws Exception {
        String raw = genRaw(uid, tp, params);
        String secret = getSecret(uid, AlgorithmType.GetSasType(tp));
        return SignMaker.Instance.genSignature(raw, tp, secret);
    }

    /**
     * 根据用户存储的密码进行加密
     * 
     * @param uid
     * @param tp
     * @param params
     * @return String
     * @throws Exception
     */
    public String encrypt(String uid, AlgorithmType tp, String encryptText) throws Exception {
        String secret = getSecret(uid, AlgorithmType.GetSasType(tp));
        switch (tp) {
            case md5:
                return Md5.encrypt(encryptText);
            case sha1:
                return SHA.sha1(encryptText);
            case sha256:
                return SHA.sha256(encryptText);
            case sha512:
                return SHA.sha512(encryptText);
            case hmac:
                return Hmac.HmacSHA256.encrypt(encryptText, secret);
            case aes:
                return Aes.Instance.encrypt(encryptText, secret);
            case des:
                return Des.Instance.encrypt(encryptText, secret);
            case rsaPub:
                return Rsa.encrypt(encryptText, secret);
            default:
                throw new Exception(System.out.format("不支持的加密类型%s", tp).toString());

        }
    }

    /**
     * 根据用户存储的密码进行解密
     * 
     * @param uid
     * @param tp
     * @param decryptText
     * @return String
     * @throws Exception
     */
    public String decrypt(String uid, AlgorithmType tp, String decryptText) throws Exception {
        String secret = getSecret(uid, AlgorithmType.GetSasType(tp));
        switch (tp) {
            case aes:
                return Aes.Instance.decrypt(decryptText, secret);
            case des:
                return Des.Instance.decrypt(decryptText, secret);
            case rsaPri:
                return Rsa.decrypt(decryptText, secret);
            default:
                throw new Exception(System.out.format("不支持的解密类型%s", tp).toString());

        }
    }

}
