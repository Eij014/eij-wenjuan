package com.eij.wenjuan.component.dao;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.eij.wenjuan.component.bean.entity.WenjuanFolder;
import com.eij.wenjuan.component.utils.TimeUtils;
import com.google.common.collect.Lists;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-04-27
 */
@Lazy
@Repository
public class WenjuanFolderDao extends AbstractDao {

    private static final String TABLE_NAME = "`wenjuan_folder`";

    private static final String SQL_INSERT = "insert into " + TABLE_NAME
            + " (`folder_name`,`create_time`,`update_time`,`committer`)"
            + " values"
            + " (:folderName, :createTime,:updateTime,:committer)";

    private static final String SQL_SELECT = "select * from " + TABLE_NAME
            + " where committer = :committer";

    private static final String SQL_UPDATE = "update " + TABLE_NAME
            + " set folder_name = :folderName, update_time = :updateTime"
            + " where wenjuan_folder_id = :folderId";

    private static final String SQL_DELETE = "delete from " + TABLE_NAME
            + " where wenjuan_folder_id = :folderId";

    private static final String SQL_ORDER_BY = " order by update_time desc";

    private static final String LIMIT_OFFSET = " limit :limit offset :offset";

    private static final String SQL_SELECT_IDS_BY_CONDITION = "select wenjuan_folder_id from " + TABLE_NAME;

    private static final String SQL_SELECT_TOTAL = "select count(*) from " + TABLE_NAME
            + " where `committer` = :committer";

    private static final String SQL_CONDITION = " and wenjuan_folder_id in (:folderIdByCondition)";

    private static final String SQL_KEYWORDS  = " where folder_name like :keywords";

    private static final RowMapper<WenjuanFolder> ROW_MAPPER = BeanPropertyRowMapper.newInstance(WenjuanFolder.class);

    private static final RowMapper<Integer> INTEGER_ROW_MAPPER = (rs, rowNum) -> rs.getInt(1);

    public int insert(WenjuanFolder wenjuanFolder) {
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("folderName", wenjuanFolder.getFolderName());
        source.addValue("createTime", TimeUtils.getTimestamp());
        source.addValue("updateTime", TimeUtils.getTimestamp());
        source.addValue("committer", wenjuanFolder.getCommitter());

        return getWriter().update(SQL_INSERT, source);
    }

    public List<WenjuanFolder> getByUserName(String username) {
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("committer", username);
        return getReader().query(SQL_SELECT + SQL_ORDER_BY, source, ROW_MAPPER);
    }

    public int update(int folderId, String folderName) {
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("folderName", folderName);
        source.addValue("folderId", folderId);
        source.addValue("updateTime", TimeUtils.getTimestamp());
        return getWriter().update(SQL_UPDATE, source);
    }

    public int delete(int folderId) {
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("folderId", folderId);
        return getWriter().update(SQL_DELETE, source);
    }

    public List<Integer>  selectIdsByCondition(String keywords) {
        StringBuilder sql = new StringBuilder(SQL_SELECT_IDS_BY_CONDITION);
        MapSqlParameterSource source = new MapSqlParameterSource();
        if (StringUtils.isNotEmpty(keywords)) {
            sql.append(SQL_KEYWORDS);
        }
        source.addValue("keywords", "%" + keywords + "%");
        return getReader().query(sql.toString(), source, INTEGER_ROW_MAPPER);
    }

    public int selectTotalByUserName(String committer, List<Integer> folderIdByCondition) {
        if (folderIdByCondition.isEmpty()) {
            return 0;
        }
        StringBuilder sql = new StringBuilder(SQL_SELECT_TOTAL);
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("committer", committer);
        source.addValue("folderIdByCondition", folderIdByCondition);
        sql.append(SQL_CONDITION);

        int count = Optional.ofNullable(getReader().queryForObject(sql.toString(), source, Integer.class))
                .orElse(0);
        return count;
    }

    public List<WenjuanFolder> selectByUserName(String committer, List<Integer> folderIdByCondition, int limit, int offset) {
        if (folderIdByCondition.isEmpty()) {
            return Lists.newArrayList();
        }

        StringBuilder sql = new StringBuilder(SQL_SELECT);
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("committer", committer);
        source.addValue("folderIdByCondition", folderIdByCondition);
        sql.append(SQL_CONDITION);
        sql.append(SQL_ORDER_BY);
        sql.append(LIMIT_OFFSET);
        source.addValue("limit", limit);
        source.addValue("offset", offset);
        return getReader().query(sql.toString(), source, ROW_MAPPER);
    }

}
