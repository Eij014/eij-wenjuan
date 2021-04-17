package com.eij.wenjuan.component.bean.result;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import java.util.List;

import com.eij.wenjuan.component.bean.Data;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-04-15
 */
public class EchartsPieOption extends EchartsOption {

    @JsonInclude(NON_NULL)
    private List<EchartsPieSeries> series;

    @SuppressWarnings("ParameterNumber")
    public EchartsPieOption(int questionId, String questionName, String formType, OptionTitle title,
                            Data legend, List<EchartsPieSeries> series, EchartsOptionHover tooltip, List<String> color) {
        this.setQuestionId(questionId);
        this.setQuestionName(questionName);
        this.setFormType(formType);
        this.setTooltip(tooltip);
        this.setTitle(title);
        this.setLegend(legend);
        this.setColor(color);
        this.series = series;
    }

    public EchartsPieOption() {

    }


    public List<EchartsPieSeries> getSeries() {
        return series;
    }

    public void setSeries(List<EchartsPieSeries> series) {
        this.series = series;
    }
}
