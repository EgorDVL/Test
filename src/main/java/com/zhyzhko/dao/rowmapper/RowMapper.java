package com.zhyzhko.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Egor on 06.01.2017.
 */
public interface RowMapper<T> {

    T extract(ResultSet set) throws SQLException;
}
