package com.eij.wenjuan.component.dao;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.eij.wenjuan.component.bean.entity.Result;

/**
 * @author Zhu Haojie<eij00014@gmail.com>
 * Created on 2021-04-09
 */
@Lazy
@Repository
public class ResultDao  extends AbstractDao {

    private static final String TABLE_NAME = "`result`";

    private static final String SQL_INSERT = "insert into " + TABLE_NAME
            + " (`wenjuan_id`,`question_id`,`option_id_list`,`type`,`text`,`create_time`,`ip`)"
            + " values"
            + " (:wenjuanId,:questionId,:optionIdList,:type,:text,:createTime,:ip)";

    private static final String SQL_SELECT = "select * from " + TABLE_NAME
            + " where wenjuan_id = :wenjuanId";

    private static final String SQL_SELECT_COUNT_GROUP_BY_TIME = "select count(1),create_time from " + TABLE_NAME
            + " group_by create_time";

    private static final RowMapper<Result> ROW_MAPPER = new BeanPropertyRowMapper<>(Result.class);

    public int[] batchInsert(List<Result> resultList) {
        MapSqlParameterSource[] sources = resultList
                .stream()
                .map(result -> {
                    MapSqlParameterSource source = new MapSqlParameterSource();
                    source.addValue("wenjuanId", result.getWenjuanId());
                    source.addValue("questionId", result.getQuestionId());
                    source.addValue("optionIdList", result.getOptionIdList());
                    source.addValue("type", result.getType());
                    source.addValue("text", result.getText());
                    source.addValue("ip", result.getIp());
                    source.addValue("createTime", System.currentTimeMillis() / 1000);
                    return source;
                })
                .toArray(MapSqlParameterSource[]::new);
        return getWriter().batchUpdate(SQL_INSERT, sources);
    }

}
