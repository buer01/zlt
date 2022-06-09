package com.zlt.xxe.entity;

public class XXE {
    private String payload;

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "XXE{" +
                "payload='" + payload + '\'' +
                '}';
    }
}
