package com.eij.wenjuan.component.bean.result;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import java.util.List;

import com.eij.wenjuan.component.bean.Data;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-04-10
 */
public class EchartsBarOption extends EchartsOption {

    @JsonInclude(NON_NULL)
    private Data xAxis;

    @JsonInclude(NON_NULL)
    private Data yAxis;

    @JsonInclude(NON_NULL)
    private List<EchartsSeries> series;

    @SuppressWarnings("ParameterNumber")
    public EchartsBarOption(int questionId, String questionName, String formType, OptionTitle title,
                            Data legend, EchartsOptionHover tooltip, List<String> color, List<EchartsSeries> series) {
        this.setQuestionId(questionId);
        this.setQuestionName(questionName);
        this.setFormType(formType);
        this.setTooltip(tooltip);
        this.setTitle(title);
        this.setLegend(legend);
        this.setColor(color);
        this.series = series;
    }

    public EchartsBarOption() {

    }

    public Data getxAxis() {
        return xAxis;
    }

    public void setxAxis(Data xAxis) {
        this.xAxis = xAxis;
    }

    public Data getyAxis() {
        return yAxis;
    }

    public void setyAxis(Data yAxis) {
        this.yAxis = yAxis;
    }

    public List<EchartsSeries> getSeries() {
        return series;
    }

    public void setSeries(List<EchartsSeries> series) {
        this.series = series;
    }
}
