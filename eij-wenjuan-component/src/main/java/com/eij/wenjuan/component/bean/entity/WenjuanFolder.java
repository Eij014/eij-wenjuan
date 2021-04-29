package com.eij.wenjuan.component.bean.entity;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-04-27
 */
public class WenjuanFolder {
    private Integer wenjuanFolderId;

    private String folderName;

    private Long createTime;

    private Long updateTime;

    private String committer;

    public Integer getWenjuanFolderId() {
        return wenjuanFolderId;
    }

    public void setWenjuanFolderId(Integer wenjuanFolderId) {
        this.wenjuanFolderId = wenjuanFolderId;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getCommitter() {
        return committer;
    }

    public void setCommitter(String committer) {
        this.committer = committer;
    }
}
