package com.eij.wenjuan.component.bean.result;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-04-10
 */
public class OptionTitle {
    //标题
    private String text;
    //副标题
    private String subText;
    //水平位置
    private String x;
    //垂直位置
    private String y;

    public OptionTitle(String text, String subText, String x, String y) {
        this.text = text;
        this.subText = subText;
        this.x = x;
        this.y = y;
    }

    public OptionTitle() {
    }

    public OptionTitle(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSubText() {
        return subText;
    }

    public void setSubText(String subText) {
        this.subText = subText;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }
}
