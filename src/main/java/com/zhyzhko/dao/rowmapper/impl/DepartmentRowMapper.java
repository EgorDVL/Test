package com.zhyzhko.dao.rowmapper.impl;

import com.zhyzhko.models.Department;
import com.zhyzhko.dao.rowmapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by user on 03.07.17.
 */
public class DepartmentRowMapper implements RowMapper<Department> {

    @Override
    public Department extract(ResultSet set) throws SQLException {
        Department department = new Department();
        department.setDepartmentId(set.getInt(1));
        department.setName(set.getString(2));
        return department;
    }
}
