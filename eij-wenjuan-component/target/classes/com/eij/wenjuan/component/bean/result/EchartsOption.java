package com.eij.wenjuan.component.bean.result;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import java.util.List;

import com.eij.wenjuan.component.bean.Data;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-04-17
 */
public class EchartsOption {
    @JsonInclude(NON_DEFAULT)
    private int questionId;

    @JsonInclude(NON_NULL)
    private String questionName;

    private String formType;

    @JsonInclude(NON_NULL)
    private EchartsOptionHover tooltip;

    private OptionTitle title;

    private Data legend;

    private List<String> color;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }

    public EchartsOptionHover getTooltip() {
        return tooltip;
    }

    public void setTooltip(EchartsOptionHover tooltip) {
        this.tooltip = tooltip;
    }

    public OptionTitle getTitle() {
        return title;
    }

    public void setTitle(OptionTitle title) {
        this.title = title;
    }

    public Data getLegend() {
        return legend;
    }

    public void setLegend(Data legend) {
        this.legend = legend;
    }

    public List<String> getColor() {
        return color;
    }

    public void setColor(List<String> color) {
        this.color = color;
    }
}
