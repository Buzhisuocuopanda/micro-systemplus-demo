package com.mkst.mini.systemplus.common.enums;

public enum DevType {

    PHONE("手机", "1"),
    PC("电脑", "2"),
    CAMERA("摄像头", "3");

    private String name;
    private String value;

    DevType(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
