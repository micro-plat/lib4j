package com.lib4j.sas;

import java.util.HashMap;
import java.util.Map;

public enum AlgorithmType {
    md5,
    sha1,
    sha256,
    sha512,
    hmac,
    aes,
    des,
    rsaPub,
    rsaPri;

    private static final Map<AlgorithmType, SasType> map = new HashMap<>();

    static {

        map.put(md5, SasType.md5_secret);
        map.put(sha1, SasType.sha1_secret);
        map.put(sha256, SasType.sha256_secret);
        map.put(sha512, SasType.sha512_secret);
        map.put(hmac, SasType.hmac_secret);
        map.put(aes, SasType.aes_secret);
        map.put(des, SasType.des_secret);
        map.put(rsaPub, SasType.rsa_public_secret);
        map.put(rsaPri, SasType.rsa_private_secret);
    }

    public static SasType GetSasType(AlgorithmType t) {
        return t.map.get(t);
    }

}
