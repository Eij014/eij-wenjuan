package com.eij.wenjuan.component.dao;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.eij.wenjuan.component.bean.entity.WenjuanBrowse;

/**
 * @author Eij<eij00014 @ gmail.com>
 * Created on 2021-04-23
 */
@Lazy
@Repository
public class WenjuanBrowseDao extends AbstractDao {
    private static final String TABLE_NAME = "`wenjuan_browse`";

    private static final String SQL_INSERT = "insert into " + TABLE_NAME
            + " (`wenjuan_id`, `browse_count`)"
            + " values"
            + " (:wenjuanId,1)"
            + " on duplicate key update"
            + " browse_count = browse_count+1";

    private static final String SQL_SELECT = "select * from" + TABLE_NAME
            + " where wenjuan_id = :wenjuanId";

    private static final RowMapper<WenjuanBrowse> ROW_MAPPER = BeanPropertyRowMapper.newInstance(WenjuanBrowse.class);

    public int insert(int wenjuanId) {
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("wenjuanId", wenjuanId);
        return getWriter().update(SQL_INSERT, source);
    }

    public int selectByWenjuanId(int wenjuanId) {
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("wenjuanId", wenjuanId);
        List<WenjuanBrowse> wenjuanBrowseList = getReader().query(SQL_SELECT, source, ROW_MAPPER);
        return wenjuanBrowseList.size() == 0
                ? 0
                : wenjuanBrowseList.get(0).getBrowseCount();
    }
}
