package com.eij.wenjuan.component.contants;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-04-29
 */
public enum WenjuanType {

    SELF("个人", "self", 0),
    TEMPLATE("模板", "template", 1),
    UNKNOWN("未知", "unknown", 100);

    private String typeCn;

    private String typeEn;

    private int code;

    WenjuanType(String typeCn, String typeEN, int code) {
        this.typeCn = typeCn;
        this.typeEn = typeEN;
        this.code = code;
    }

    public static int parse(String type) {
        for (WenjuanType wenjuanType : WenjuanType.values()) {
            if (wenjuanType.typeEn.equals(type)) {
                return wenjuanType.code;
            }
        }
        return UNKNOWN.code;
    }

    public String getTypeCn() {
        return typeCn;
    }

    public String getTypeEn() {
        return typeEn;
    }

    public int getCode() {
        return code;
    }
}
