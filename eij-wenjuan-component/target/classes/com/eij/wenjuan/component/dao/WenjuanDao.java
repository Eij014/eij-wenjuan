package com.eij.wenjuan.component.dao;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.eij.wenjuan.component.bean.entity.Wenjuan;
import com.google.common.collect.Lists;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-03-02
 */
@Lazy
@Repository
public class WenjuanDao extends AbstractDao {
    private static final String TABLE_NAME = "`wenjuan`";

    private static final String SQL_INSERT = "insert into " + TABLE_NAME
            + " ( `wenjuan_title`,`welcome_msg`,`committer`,`type`,`status`,`img_url`,`create_time`,`update_time`)"
            + " values"
            + " (:wenjuanTitle,:welcomeMsg,:committer,:type,:status,:imgUrl,:createTime,:updateTime)";

    private static final String SQL_SELECT_BY_ID = "select * from " + TABLE_NAME
            + " where wenjuan_id = :wenjuanId";

    private static final String SQL_SELECT_IDS_BY_CONDITION = "select t1.wenjuan_id from " + TABLE_NAME
            + " t1"
            + " left join `wenjuan_folder_relation` t2 on t1.wenjuan_id = t2.wenjuan_id"
            + " where t1.committer = :username";

    private static final String SQL_KEYWORDS  = " t1.wenjuan_title like :keywords";

    private static final String SQL_FOLDER_ID = " t2.wenjuan_folder_id = :folderId";


    private static final String SQL_ORDER_BY = " order by update_time";

    private static final String LIMIT_OFFSET = " limit :limit offset :offset";

    private static final String SQL_SELECT = "select * from " + TABLE_NAME
            + " where committer = :committer";

    private static final String SQL_DELETE = "delete from " + TABLE_NAME
            + " where wenjuan_id = :wenjuanId";

    private static final String SQL_UPDATE = "update " + TABLE_NAME
            + " set `wenjuan_title` = :wenjuanTitle, `img_url`=:imgUrl,"
            + " `welcome_msg` = :welcomeMsg,`update_time`=:updateTime"
            + " where `wenjuan_id` = :wenjuanId";

    private static final String SQL_UPDATE_STATUS = "update " + TABLE_NAME
            + " set `status` = :status"
            + " where `wenjuan_id` = :wenjuanId";


    private static final String SQL_SELECT_TOTAL = "select count(*) from " + TABLE_NAME
            + " where `committer` = :committer";

    private static final String SQL_CONDITION = " and wenjuan_id in (:wenjuanIdByCondition)";

    private static final RowMapper<Wenjuan> ROW_MAPPER = new BeanPropertyRowMapper<>(Wenjuan.class);

    private static final RowMapper<Integer> INTEGER_ROW_MAPPER = (rs, rowNum) -> rs.getInt(1);

    public int insert(Wenjuan wenjuan) {
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("wenjuanTitle", wenjuan.getWenjuanTitle());
        source.addValue("welcomeMsg", wenjuan.getWelcomeMsg());
        source.addValue("committer", wenjuan.getCommitter());
        source.addValue("type", wenjuan.getType());
        source.addValue("status", wenjuan.getStatus());
        source.addValue("imgUrl", wenjuan.getImgUrl());
        source.addValue("createTime", System.currentTimeMillis() / 1000);
        source.addValue("updateTime", System.currentTimeMillis() / 1000);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        getWriter().update(SQL_INSERT, source, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    public int delete(int wenjuanId) {
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("wenjuanId", wenjuanId);
        return getWriter().update(SQL_DELETE, source);
    }

    public List<Wenjuan> selectByUserName(String committer, List<Integer> wenjuanIdByCondition, int limit, int offset) {
        if (wenjuanIdByCondition.isEmpty()) {
            return Lists.newArrayList();
        }

        StringBuilder sql = new StringBuilder(SQL_SELECT);
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("committer", committer);
        source.addValue("wenjuanIdByCondition", wenjuanIdByCondition);
        sql.append(SQL_CONDITION);
        sql.append(SQL_ORDER_BY);
        sql.append(LIMIT_OFFSET);
        source.addValue("limit", limit);
        source.addValue("offset", offset);
        return getReader().query(sql.toString(), source, ROW_MAPPER);
    }

    public int selectTotalByUserName(String committer, List<Integer> wenjuanIdByCondition) {
        if (wenjuanIdByCondition.isEmpty()) {
            return 0;
        }
        StringBuilder sql = new StringBuilder(SQL_SELECT_TOTAL);
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("committer", committer);
        source.addValue("wenjuanIdByCondition", wenjuanIdByCondition);
        sql.append(SQL_CONDITION);

        int count = Optional.ofNullable(getReader().queryForObject(sql.toString(), source, Integer.class))
                .orElse(0);
        return count;
    }

    public Wenjuan selectByWenjuanId(int wenjuanId) {
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("wenjuanId", wenjuanId);
        List<Wenjuan> wenjuanList = getReader().query(SQL_SELECT_BY_ID, source, ROW_MAPPER);
        if (CollectionUtils.isNotEmpty(wenjuanList)) {
            return wenjuanList.get(0);
        } else {
            return null;
        }
    }

    public void updateWenjuan(int wenjuanId, String imgUrl, String wenjuanTitle, String welcomeMsg) {
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("wenjuanId", wenjuanId);
        source.addValue("imgUrl", imgUrl);
        source.addValue("welcomeMsg", welcomeMsg);
        source.addValue("wenjuanTitle", wenjuanTitle);
        source.addValue("updateTime", System.currentTimeMillis() / 1000);
        getWriter().update(SQL_UPDATE, source);
    }

    public int updateStatus(int wenjuanId, int status) {
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("wenjuanId", wenjuanId);
        source.addValue("status", status);
        return getWriter().update(SQL_UPDATE_STATUS, source);
    }

    public List<Integer> selectIdsByCondition(String username, int folderId, String keywords) {
        StringBuilder sql = new StringBuilder(SQL_SELECT_IDS_BY_CONDITION);
        MapSqlParameterSource source = new MapSqlParameterSource();
        List<String> condition = Lists.newArrayList();
        if (folderId != 0) {
            condition.add(SQL_FOLDER_ID);
        }
        if (StringUtils.isNotEmpty(keywords)) {
            condition.add(SQL_KEYWORDS);
        }
        if (CollectionUtils.isNotEmpty(condition)) {
            sql.append(" and ");
        }
        sql.append(String.join(" and ", condition));
        source.addValue("username", username);
        source.addValue("folderId", folderId);
        source.addValue("keywords", "%" + keywords + "%");
        return getReader().query(sql.toString(), source, INTEGER_ROW_MAPPER);
    }
}
