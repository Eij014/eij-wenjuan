package com.eij.wenjuan.component.dao;

import static com.eij.wenjuan.component.utils.TimeUtils.DAY_SHORT_FORMAT;

import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.eij.wenjuan.component.bean.VO.RecycleVO;
import com.eij.wenjuan.component.bean.entity.Recycle;
import com.eij.wenjuan.component.utils.TimeUtils;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-04-13
 */
@Lazy
@Repository
public class RecycleDao extends AbstractDao {

    private static final Logger logger = LoggerFactory.getLogger(RecycleDao.class);

    private static final String TABLE_NAME = "`recycle`";

    private static final String SQL_INSERT = "insert into " + TABLE_NAME
            + " (`wenjuan_id`, `province`,`city`,`create_time`)"
            + " values"
            + " (:wenjuanId,:province,:city,:createTime)";

    private static final String SQL_SELECT = "select * from " + TABLE_NAME
            + " where wenjuan_id = :wenjuanId";

    private static final String SQL_SELECT_BY_IDS = "select * from " + TABLE_NAME
            + " where wenjuan_id in (:wenjuanIdList)";


    private static final RowMapper<RecycleVO> ROW_MAPPER = (rs, rowNum) -> {
        RecycleVO recycleVO = new RecycleVO();
        recycleVO.setId(rs.getInt("id"));
        recycleVO.setWenjuanId(rs.getInt("wenjuan_id"));
        recycleVO.setProvince(rs.getString("province"));
        recycleVO.setCity(rs.getString("city"));
        recycleVO.setCreateTime(rs.getLong("create_time"));

        try  {
            recycleVO.setRecycleTime(TimeUtils.timestamp2str(recycleVO.getCreateTime(), DAY_SHORT_FORMAT));
        } catch (ParseException e) {
            logger.info("time parse error.time={}", recycleVO.getCreateTime());
            recycleVO.setRecycleTime("");
        }
        return recycleVO;
    };

    public int insert(Recycle recycle) {
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("wenjuanId", recycle.getWenjuanId());
        source.addValue("province", recycle.getProvince());
        source.addValue("city", recycle.getCity());
        source.addValue("createTime", TimeUtils.getTimestamp());
        return getWriter().update(SQL_INSERT, source);
    }

    public List<RecycleVO> selectByWenjuanId(int wenjuanId) {
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("wenjuanId", wenjuanId);
        return getReader().query(SQL_SELECT, source, ROW_MAPPER);
    }

    public List<RecycleVO> selectByWenjuanIds(List<Integer> wenjuanIdList) {
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("wenjuanIdList", wenjuanIdList);
        return getReader().query(SQL_SELECT_BY_IDS, source, ROW_MAPPER);
    }
}
