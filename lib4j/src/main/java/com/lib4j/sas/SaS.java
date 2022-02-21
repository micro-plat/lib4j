package com.lib4j.sas;

public class SaS<T extends ISasData> {

    private T iSasData;

    public SaS(T t) {
        this.iSasData = t;
    }

    public String getSecret(String uid, SasType tp) {
        return this.iSasData.getSecret(uid, tp.name());
    }

    public String encrypt(String uid, AlgorithmType tp) {
        SasType stp = AlgorithmType.GetSasType(tp);
        String secret = this.getSecret(uid, stp);
        return secret;
    }

}
