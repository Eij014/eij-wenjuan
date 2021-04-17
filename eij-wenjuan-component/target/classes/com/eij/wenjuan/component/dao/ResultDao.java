package com.eij.wenjuan.component.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.eij.wenjuan.component.bean.VO.ResultVO;
import com.eij.wenjuan.component.bean.entity.Result;

/**
 * @author Zhu Haojie<eij00014@gmail.com>
 * Created on 2021-04-09
 */
@Lazy
@Repository
public class ResultDao  extends AbstractDao {

    private static final Logger logger = LoggerFactory.getLogger(ResultDao.class);

    private static final String TABLE_NAME = "`result`";

    private static final String SQL_INSERT = "insert into " + TABLE_NAME
            + " (`wenjuan_id`,`question_id`,`option_id`,`type`,`text`,`create_time`,`province`,`city`)"
            + " values"
            + " (:wenjuanId,:questionId,:optionId,:type,:text,:createTime,:province, :city)";

    private static final String SQL_SELECT = "select t1.*,t2.option_name from " + TABLE_NAME
            + " t1 left join `option` t2 on t1.option_id = t2.option_id"
            + " where t1.wenjuan_id = :wenjuanId";

    private static final String SQL_SELECT_COUNT_GROUP_BY_TIME = "select count(1),create_time from " + TABLE_NAME
            + " group_by create_time";


    private static final RowMapper<ResultVO> ROW_MAPPER = (rs, rowNum) -> {
        ResultVO resultVO = new ResultVO();
        resultVO.setId(rs.getInt("id"));
        resultVO.setWenjuanId(rs.getInt("wenjuan_id"));
        resultVO.setQuestionId(rs.getInt("question_id"));
        resultVO.setOptionId(rs.getInt("option_id"));
        resultVO.setOptionName(rs.getString("option_name"));
        resultVO.setText(rs.getString("text"));
        resultVO.setCreateTime(rs.getLong("create_time"));
        resultVO.setType(rs.getString("type"));
        resultVO.setProvince(rs.getString("province"));
        resultVO.setCity(rs.getString("city"));
        return resultVO;
    };

    public int[] batchInsert(List<Result> resultList) {
        MapSqlParameterSource[] sources = resultList
                .stream()
                .map(result -> {
                    MapSqlParameterSource source = new MapSqlParameterSource();
                    source.addValue("wenjuanId", result.getWenjuanId());
                    source.addValue("questionId", result.getQuestionId());
                    source.addValue("optionId", result.getOptionId());
                    source.addValue("type", result.getType());
                    source.addValue("text", result.getText());
                    source.addValue("province", result.getProvince());
                    source.addValue("city", result.getCity());
                    source.addValue("createTime", System.currentTimeMillis() / 1000);
                    return source;
                })
                .toArray(MapSqlParameterSource[]::new);
        return getWriter().batchUpdate(SQL_INSERT, sources);
    }

    public List<ResultVO> selectByWenjuanId(int wenjuanId) {
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("wenjuanId", wenjuanId);
        return getReader().query(SQL_SELECT, source, ROW_MAPPER);
    }

}
