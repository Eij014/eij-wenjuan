package com.eij.wenjuan.component.bean.VO;

import java.util.List;

import com.eij.wenjuan.component.bean.entity.Option;
import com.eij.wenjuan.component.bean.entity.Question;

/**
 * @author Zhu Haojie<eij00014@gmail.com>
 * Created on 2021-03-14
 */
public class QuestionVO extends Question {
    private List<Option> optionList;

    private List<String> imgUrlList;

    public QuestionVO(List<Option> optionList) {
        this.optionList = optionList;
    }

    public List<Option> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<Option> optionList) {
        this.optionList = optionList;
    }
}
