package com.eij.wenjuan.component.dao;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.eij.wenjuan.component.bean.entity.Question;
import com.eij.wenjuan.component.bean.entity.User;

/**
 * @author Zhu Haojie<eij00014@gmail.com>
 * Created on 2021-03-14
 */
@Lazy
@Repository
public class QuestionDao extends AbstractDao {
    public static final String TABLE_NAME = "question";

    public static final String SQL_INSERT = "insert into " + TABLE_NAME
            + " (`wenjuan_id`,`type`,`must`,``title`,`img_urls`,`question_index`)"
            + " values"
            + " (:wenjuanId, :type, :must, :title, :imgUrls, :questionIndex)";

    public static final String SQL_SELECT_BY_WENJUAN_ID = "select * from " + TABLE_NAME
            + " where wenjuan_id = :wenjuanId";

    public static final String ORDER = " order by question_index ";

    private static final RowMapper<Question> ROW_MAPPER = new BeanPropertyRowMapper<>(Question.class);

    public void batchInsert(List<Question> questionList) {
        MapSqlParameterSource[] sources = questionList
                .stream()
                .map(question -> {
                    MapSqlParameterSource source = new MapSqlParameterSource();
                    source.addValue("wenjuanId", question.getWenjuanId());
                    source.addValue("type", question.getType());
                    source.addValue("must", question.getMust());
                    source.addValue("title", question.getTitle());
                    source.addValue("imgUrls", question.getImgUrls());
                    source.addValue("questionIndex", question.getQuestionIndex());
                    return source;
                })
                .toArray(MapSqlParameterSource[]::new);
        getWriter().batchUpdate(SQL_INSERT, sources);
    }

    public List<Question> selectByWenjuanId(int wenjuanId) {
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("wenjuanId", wenjuanId);
        return getReader().query(SQL_SELECT_BY_WENJUAN_ID + ORDER, ROW_MAPPER);
    }
}
