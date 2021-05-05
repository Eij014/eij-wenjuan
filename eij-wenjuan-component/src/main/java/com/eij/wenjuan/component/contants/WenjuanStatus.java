package com.eij.wenjuan.component.contants;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-05-03
 */
public enum WenjuanStatus {
    UNPUBLISHED("未发布", "unPublished", 0),
    PUBLISHED("已发布", "published", 1),
    ALL("全部", "all", 2),
    UNKNOWN("未知", "unknown", -1);

    private String statusCn;

    private String statusEn;

    private int code;

    WenjuanStatus(String statusCn, String statusEn, int code) {
        this.statusCn = statusCn;
        this.statusEn = statusEn;
        this.code = code;
    }

    public static List<Integer> parse(String status) {
        for (WenjuanStatus wenjuanStatus : WenjuanStatus.values()) {
            if (wenjuanStatus.statusCn.equals(status)) {
                if (wenjuanStatus.code == 2) {
                    return new ArrayList<Integer>() {{
                        add(WenjuanStatus.UNPUBLISHED.code);
                        add(WenjuanStatus.PUBLISHED.code);
                    }};
                } else {
                    return Lists.newArrayList(wenjuanStatus.code);
                }
            }
        }
        return Lists.newArrayList(UNKNOWN.code);
    }
}
