package com.lib4j.sas;

public interface ISasData {
    public String getSecret(String uid, String type);
    public void setSecret(String uid, String type, String value);

}
