package com.eij.wenjuan.component.bean.VO;

import com.eij.wenjuan.component.bean.entity.Result;

/**
 * @author Zhu Haojie<eij00014@gmail.com>
 * Created on 2021-04-10
 */
public class ResultVO extends Result {
    private String optionName;

    private String questionName;

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }
}
