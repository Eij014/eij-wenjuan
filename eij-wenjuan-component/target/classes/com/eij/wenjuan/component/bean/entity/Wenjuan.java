package com.eij.wenjuan.component.bean.entity;

/**
 * @author Zhu Haojie<eij00014@gmail.com>
 * Created on 2021-03-02
 */

public class Wenjuan {
    private int wenjuanId;
    private String wenjuanTitle;
    private String committer;
    private String type;
    private int status;
    private String imgUrl;
    private long createTime;

    public int getWenjuanId() {
        return wenjuanId;
    }

    public void setWenjuanId(int wenjuanId) {
        this.wenjuanId = wenjuanId;
    }

    public String getWenjuanTitle() {
        return wenjuanTitle;
    }

    public void setWenjuanTitle(String wenjuanTitle) {
        this.wenjuanTitle = wenjuanTitle;
    }

    public String getCommitter() {
        return committer;
    }

    public void setCommitter(String committer) {
        this.committer = committer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "wenjuan{"
                + "wenjuanId=" + wenjuanId
                + ", wenjuanTitle='" + wenjuanTitle + '\''
                + ", committer='" + committer + '\''
                + ", type='" + type + '\''
                + ", status=" + status
                + ", imgUrl='" + imgUrl + '\''
                + ", createTime=" + createTime
                + '}';
    }
}
