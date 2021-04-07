package com.eij.wenjuan.component.dao;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.eij.wenjuan.component.bean.entity.Option;

/**
 * @author Zhu Haojie<eij00014@gmail.com>
 * Created on 2021-03-16
 */
@Lazy
@Repository
public class OptionDao extends AbstractDao {

    private static final String TABLE_NAME = "`option`";

    private static final String SQL_INSERT = "insert into " + TABLE_NAME
            + " (`question_id`, `option_name`, `option_index`)"
            + " values"
            + " (:questionId, :optionName, :optionIndex)";

    private static final String SQL_UPDATE = "update " + TABLE_NAME
            + " set `option_name` = :optionName, `option_index` = :optionIndex"
            + " where `option_id` = :optionId";

    private static final String SQL_SELECT_BY_QUESTION_IDS = "select * from " + TABLE_NAME
            + " where `question_id` in (:questionIdList)";

    public static final String SQL_DELETE_BY_IDS = "delete from " + TABLE_NAME
            + " where `option_id` in (:optionIdList)";

    public static final String SQL_DELETE_BY_QUESTION_IDS = "delete from " + TABLE_NAME
            + " where `question_id` in (:questionIdList)";

    private static final String ORDER = " order by option_index";

    private static final RowMapper<Option> ROW_MAPPER = new BeanPropertyRowMapper<>(Option.class);

    public void batchInsert(List<Option> optionList) {
        MapSqlParameterSource[] sources = optionList.stream()
                .filter(option -> option.getOptionId() == 0)
                .map(option -> {
                        MapSqlParameterSource source = new MapSqlParameterSource();
                        source.addValue("questionId", option.getQuestionId());
                        source.addValue("optionName", option.getOptionName());
                        source.addValue("optionIndex", option.getOptionIndex());
                        return source;
                })
                .toArray(MapSqlParameterSource[]::new);
        getWriter().batchUpdate(SQL_INSERT, sources);
    }

    public List<Option> selectByQuestionIds(List<Integer> questionIdList) {
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("questionIdList", questionIdList);
        return getReader().query(SQL_SELECT_BY_QUESTION_IDS, source, ROW_MAPPER);
    }

    public void batchUpdate(List<Option> optionList) {
        MapSqlParameterSource[] sources = optionList
                .stream()
                .map(option -> {
                    MapSqlParameterSource source = new MapSqlParameterSource();
                    source.addValue("optionId", option.getOptionId());
                    source.addValue("optionName", option.getOptionName());
                    source.addValue("optionIndex", option.getOptionIndex());
                    return source;
                })
                .toArray(MapSqlParameterSource[]::new);
        getWriter().batchUpdate(SQL_UPDATE, sources);
    }

    public void deleteByQuestionIds(List<Integer> questionIdList) {
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("questionIdList", questionIdList);
        getWriter().update(SQL_DELETE_BY_QUESTION_IDS, source);
    }

    public void deleteByOptionIds(List<Integer> optionIdList) {
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("optionIdList", optionIdList);
        getWriter().update(SQL_DELETE_BY_IDS, source);
    }

}
