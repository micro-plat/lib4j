package com.lib4j.sas;

import java.util.Arrays;
import java.util.Map;

import com.lib4j.security.Md5;

public class SignMaker {

    public static boolean verify(Map<String, Object> params, String secret, String outSign) {
        return GenMd5Sign(params, secret).equalsIgnoreCase(outSign);

    }

    public static boolean verify(Map<String, Object> params, String secret, String kc, String oc, ConnectMode mode,
            String outSign) {
        return GenMd5Sign(params, secret, kc, oc, mode).equalsIgnoreCase(outSign);
    }

    public static String GenMd5Sign(Map<String, Object> params, String secret) {
        return GetRaw(params, secret, "", "", ConnectMode.Tail);
    }

    public static String GenMd5Sign(Map<String, Object> params, String secret, String kc, String oc, ConnectMode mode) {
        String raw = GetRaw(params, secret, kc, oc, mode);
        return Md5.encrypt(raw);
    }

    public static String GetRaw(Map<String, Object> params, String secret, String kc, String oc, ConnectMode mode) {

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
