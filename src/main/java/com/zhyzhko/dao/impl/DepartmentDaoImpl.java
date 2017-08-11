package com.zhyzhko.dao.impl;

import com.zhyzhko.exception.PersistException;
import com.zhyzhko.models.Department;
import com.zhyzhko.dao.DepartmentDao;
import com.zhyzhko.dao.rowmapper.impl.DepartmentRowMapper;
import com.zhyzhko.util.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by user on 03.07.17.
 */
public class DepartmentDaoImpl implements DepartmentDao {

    private static final String CREATE_DEPARTMENT = "insert into department values(default, ?)";
    private static final String GET_DEPARTMENT_BY_ID = "select * from department where id = ?";
    private static final String GET_ALL_DEPARTMENT = "select * from department";
    private static final String UPDATE_DEPARTMENT = "update department set name = ? where id = ?";
    private static final String DELETE_DEPARTMENT = "delete from department where id = ?";
    private static final String GET_DEPARTMENT_BY_NAME = "select * from department where name = ?";

    private JdbcTemplate<Department> jdbcTemplate = new JdbcTemplate<>();

    @Override
    public void createOrUpdate(Department department) {
        PreparedStatement st = null;
        Connection con = DBManager.getConnection();
        try {
            if (department.getDepartmentId() == 0) {
                st = con.prepareStatement(CREATE_DEPARTMENT);
            } else {
                st = con.prepareStatement(UPDATE_DEPARTMENT);
                st.setInt(2, department.getDepartmentId());
            }
            st.setString(1, department.getName());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new PersistException(e);
        } finally {
            DBManager.closeStat(st);
        }
    }

    @Override
    public int create(Department department) {
        return jdbcTemplate.create(CREATE_DEPARTMENT, department.getName());
    }

    @Override
    public Department getById(int departmentId) {
        return jdbcTemplate.get(GET_DEPARTMENT_BY_ID, new DepartmentRowMapper(), departmentId);
    }

    @Override
    public List<Department> getAll() {
        return jdbcTemplate.getAll(GET_ALL_DEPARTMENT, new DepartmentRowMapper());
    }

    @Override
    public boolean update(Department department) {
        return jdbcTemplate.update(UPDATE_DEPARTMENT, department.getName(), department.getDepartmentId());
    }

    @Override
    public boolean delete(int departmentId) {
        return jdbcTemplate.delete(DELETE_DEPARTMENT, departmentId);
    }

    @Override
    public Department getByName(String name) {
        return jdbcTemplate.get(GET_DEPARTMENT_BY_NAME, new DepartmentRowMapper(), name);
    }
}