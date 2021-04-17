package com.eij.wenjuan.component.bean.result;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Eij<eij00014 @ gmail.com>
 * Created on 2021-04-14
 */
public class EchartsOptionHover {
    private String trigger;

    @JsonInclude(NON_NULL)
    private String formatter;

    public EchartsOptionHover(String trigger, String formatter) {
        this.trigger = trigger;
        this.formatter = formatter;
    }

    public String getTrigger() {
        return trigger;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }

    public String getFormatter() {
        return formatter;
    }

    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }
}
