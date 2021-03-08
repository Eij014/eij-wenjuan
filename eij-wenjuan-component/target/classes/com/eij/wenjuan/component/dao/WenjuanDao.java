package com.eij.wenjuan.component.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.eij.wenjuan.component.bean.entity.Wenjuan;

/**
 * @author Zhu Haojie<eij00014@gmail.com>
 * Created on 2021-03-02
 */
@Lazy
@Repository
public class WenjuanDao extends AbstractDao {
    private static final String TABLE_NAME = "wenjuan";

    private static final String SQL_INSERT = "insert into " + TABLE_NAME
            + " (`wenjuan_id`, `wenjuan_title`,`committer`,`type`,`status`,`img_url`,`create_time`)"
            + " values"
            + " (:wenjuanId,:wenjuanTitle,:committer,:type,:status,:imgUrl,:createTime)";

    private static final String LIMIT_OFFSET = " limit :limit offset :offset";

    private static final String SQL_SELECT = "select * from " + TABLE_NAME
            + " where committer = :committer";

    private static final String SQL_SELECT_TOTAL = "select count(*) from " + TABLE_NAME
            + " where committer = :committer";

    private static final RowMapper<Wenjuan> ROW_MAPPER = new BeanPropertyRowMapper<>(Wenjuan.class);

    private static final RowMapper<Integer> INTEGER_ROW_MAPPER = (rs, rowNum) -> rs.getInt(1);

    public List<Wenjuan> selectByUserName(String committer, int limit, int offset) {
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("committer", committer);
        source.addValue("limit", limit);
        source.addValue("offset", offset);
        return getReader().query((SQL_SELECT + LIMIT_OFFSET), source, ROW_MAPPER);
    }

    public int selectTotalByUserName(String committer) {
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("committer", committer);
        int count = Optional.ofNullable(getReader().queryForObject(SQL_SELECT_TOTAL, source, Integer.class))
                .orElse(0);
        return count;
    }

}
