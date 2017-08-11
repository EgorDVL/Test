package com.zhyzhko.dao.rowmapper.impl;

import com.zhyzhko.models.Employee;
import com.zhyzhko.dao.rowmapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by user on 03.07.17.
 */
public class EmployeeRowMapper implements RowMapper<Employee> {
    
    @Override
    public Employee extract(ResultSet set) throws SQLException {
        Employee employee = new Employee();
        employee.setEmployeeId(set.getInt(1));
        employee.setName(set.getString(2));
        employee.setSurname(set.getString(3));
        employee.setExperience(set.getInt(4));
        employee.setEmail(set.getString(5));
        employee.setDateOfBirth(set.getDate(6));
        employee.setDepartmentId(set.getInt(7));
        return employee;
    }
}
