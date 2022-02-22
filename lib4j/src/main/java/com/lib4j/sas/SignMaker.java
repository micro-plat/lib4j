package com.lib4j.sas;

import java.util.Arrays;
import java.util.Map;

import com.lib4j.security.Hmac;
import com.lib4j.security.Md5;
import com.lib4j.security.SHA;

public class SignMaker {

    public static SignMaker Instance = new SignMaker();

    public boolean verify(Map<String, Object> params, AlgorithmType tp, String secret, String outSign)
            throws Exception {
        return genSignature(params, tp, secret).equalsIgnoreCase(outSign);

    }

    public boolean verify(Map<String, Object> params, AlgorithmType tp, String secret, String kc, String oc,
            ConnectMode mode,
            String outSign) throws Exception {
        return genSignature(params, tp, secret, kc, oc, mode).equalsIgnoreCase(outSign);
    }

    public String genSignature(Map<String, Object> params, AlgorithmType tp, String secret) throws Exception {
        return genSignature(params, tp, secret);
    }

    public String genSignature(Map<String, Object> params, AlgorithmType tp, String secret, String kc, String oc,
            ConnectMode mode) throws Exception {
        String raw = genRaw(params, secret, kc, oc, mode);
        return genSignature(raw, tp, secret);
    }

    public String genSignature(String raw, AlgorithmType tp, String secret) throws Exception {
        switch (tp) {
            case md5:
                return Md5.encrypt(raw);
            case hmac:
                return Hmac.HmacSHA256.encrypt(raw, secret);
            case sha1:
                return SHA.sha1(raw);
            case sha256:
                return SHA.sha256(raw);
            case sha512:
                return SHA.sha512(raw);
            default:
            throw new Exception(System.out.format("不支持的签名类型%s", tp).toString());
        }
    }

    public String genRaw(Map<String, Object> params, String secret) {
        return genRaw(params, secret, "", "", ConnectMode.Tail);
    }

    public String genRaw(Map<String, Object> params, String secret, String kc, String oc, ConnectMode mode) {

        String[] keys = params.keySet().toArray(new String[0]);
        Arrays.sort(keys);

        StringBuilder query = new StringBuilder();
        if (mode == ConnectMode.Head || mode == ConnectMode.HeadAndTail) {
            query.append(secret);
        }
        for (String key : keys) {
            String value = params.get(key).toString();
            if (value != null && value != "") {
                query.append(key);
                if (kc != "" && kc != null) {
                    query.append(kc);
                }
                query.append(value);
                if (oc != "" && oc != null) {
                    query.append(oc);
                }
            }
        }
        if (mode == ConnectMode.Tail || mode == ConnectMode.HeadAndTail) {
            query.append(secret);
        }
        return query.toString();
    }
}
