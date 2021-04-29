package com.eij.wenjuan.component.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.eij.wenjuan.component.bean.entity.WenjuanFolderRelation;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-04-28
 */
@Lazy
@Repository
public class WenjuanFolderRelationDao extends AbstractDao {

    private static final String TABLE_NAME = "`wenjuan_folder_relation`";

    private static final String SQL_INSERT = "insert into " + TABLE_NAME
            + " (`wenjuan_folder_id`,`wenjuan_id`)"
            + " values"
            + " (:wenjuanFolderId,:wenjuanId)";

    private static final String SQL_DELETE = "delete from " + TABLE_NAME
            + " where wenjuan_folder_id = :wenjuanFolderId"
            + " or wenjuan_id = :wenjuanId";

    public int insert(WenjuanFolderRelation wenjuanFolderRelation) {
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("wenjuanFolderId", wenjuanFolderRelation.getWenjuanFolderId());
        source.addValue("wenjuanId", wenjuanFolderRelation.getWenjuanId());
        return getWriter().update(SQL_INSERT, source);
    }

    public int delete(int folderId, int wenjuanId) {
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("wenjuanFolderId", folderId);
        source.addValue("wenjuanId", wenjuanId);
        return getWriter().update(SQL_DELETE, source);
    }
}
