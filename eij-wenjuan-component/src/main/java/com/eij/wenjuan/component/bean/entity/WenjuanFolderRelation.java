package com.eij.wenjuan.component.bean.entity;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-04-27
 */
public class WenjuanFolderRelation {

    private Integer id;

    private Integer wenjuanId;

    private Integer wenjuanFolderId;

    public WenjuanFolderRelation() {

    }

    public WenjuanFolderRelation(Integer wenjuanId, Integer wenjuanFolderId) {
        this.wenjuanId = wenjuanId;
        this.wenjuanFolderId = wenjuanFolderId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWenjuanId() {
        return wenjuanId;
    }

    public void setWenjuanId(Integer wenjuanId) {
        this.wenjuanId = wenjuanId;
    }

    public Integer getWenjuanFolderId() {
        return wenjuanFolderId;
    }

    public void setWenjuanFolderId(Integer wenjuanFolderId) {
        this.wenjuanFolderId = wenjuanFolderId;
    }
}
