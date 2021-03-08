package com.eij.wenjuan.component.bean.view;

/**
 * @author Zhu Haojie<eij00014@gmail.com>
 * Created on 2021-02-06
 */
public class WenjuanResponseView {
    /**
     * 基础视图
     */
    public interface BasicView {

    }

    /**
     * 列表视图
     */
    public interface ListView extends BasicView {

    }

    /**
     * 具体视图
     */

    public interface InfoView extends ListView {

    }

    /**
     * admin视图
     */
    public interface AdminView extends BasicView {

    }
}
