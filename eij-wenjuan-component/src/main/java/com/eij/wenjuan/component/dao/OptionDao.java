package com.eij.wenjuan.component.dao;

import java.util.List;

import org.jfaster.mango.crud.named.parser.op.Op;
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

    private static final String TABLE_NAME = "option";

    private static final String SQL_INSERT = "insert into " + TABLE_NAME
            + " (`question_id`, `option_name`, `option_index`, `img_url`)"
            + " values"
            + " (:questionId, :optionName, :optionIndex, :imgUrl)";

    private static final String SQL_SELECT_BY_QUESTION_IDS = "select * from " + TABLE_NAME
            + " where question_id in (:questionIdList)";

    private static final String ORDER = " order by option_index";

    private static final RowMapper<Option> ROW_MAPPER = new BeanPropertyRowMapper<>(Option.class);

    public void batchInsert(List<Option> optionList) {
        MapSqlParameterSource[] sources = optionList
                .stream()
                .map(option -> {
                    MapSqlParameterSource source = new MapSqlParameterSource();
                    source.addValue("questionId", option.getOptionId());
                    source.addValue("optionName", option.getOptionName());
                    source.addValue("optionIndex", option.getOptionIndex());
                    source.addValue("imgUrl", option.getImgUrl());
                    return source;
                })
                .toArray(MapSqlParameterSource[]::new);
        getWriter().batchUpdate(SQL_INSERT, sources);
    }

    public List<Option> selectByQuestionIds(List<Integer> questionIdList) {
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("questionIdList", questionIdList);
        return getReader().query(SQL_SELECT_BY_QUESTION_IDS, ROW_MAPPER);
    }
}
