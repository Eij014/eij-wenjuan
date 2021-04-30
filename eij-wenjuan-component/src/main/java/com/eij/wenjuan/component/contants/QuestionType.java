package com.eij.wenjuan.component.contants;

/**
 * @author Zhu Haojie<eij00014@gmail.com>
 * Created on 2021-04-09
 */
public enum QuestionType {
    SINGLE_CHOICE("singleChoice"),
    MULTIPLE_CHOICE("multipleChoice"),
    VIDEO("video"),
    PICTURE("picture"),
    INPUT("input"),
    UNKNOWN("unknown");

    private String nameCamel;

    QuestionType(String nameCamel) {
        this.nameCamel = nameCamel;
    }

    public static QuestionType parse(String nameCamel) {
        for (QuestionType questionType : QuestionType.values()) {
            if (questionType.nameCamel.equals(nameCamel)) {
                return questionType;
            }
        }
        return UNKNOWN;
    }

    public String getNameCamel() {
        return nameCamel;
    }

    public void setNameCamel(String nameCamel) {
        this.nameCamel = nameCamel;
    }
}
