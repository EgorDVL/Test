package com.zhyzhko.dao.impl;

import com.zhyzhko.exception.PersistException;
import com.zhyzhko.models.Department;
import com.zhyzhko.models.Employee;
import com.zhyzhko.dao.EmployeeDao;
import com.zhyzhko.dao.rowmapper.impl.EmployeeRowMapper;
import com.zhyzhko.util.DBManager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by user on 03.07.17.
 */
public class EmployeeDaoImpl implements EmployeeDao {

    private static final String CREATE_EMPLOYEES = "insert into employees values(default, ?,?,?,?,?,?)";
    private static final String GET_EMPLOYEES_BY_ID = "select * from employees where id = ?";
    private static final String GET_EMPLOYEES_BY_EMAIL = "select * from employees where email = ?";
    private static final String GET_ALL_EMPLOYEES = "select * from employees";
    private static final String GET_ALL_EMPLOYEES_BY_DEPARTMENT_ID = "select * from employees where departmentId = ?";
    private static final String UPDATE_EMPLOYEES = "update employees set name = ?, surname = ?, experience = ?, email = ?, dateOfBirth = ?, departmentId = ? where id = ?";
    private static final String DELETE_EMPLOYEES = "delete from employees where id = ?";

    private JdbcTemplate<Employee> jdbcTemplate = new JdbcTemplate<>();

    @Override
    public void createOrUpdate(Employee employee) {
        PreparedStatement st = null;
        Connection con = DBManager.getConnection();
        try {
            if (employee.getEmployeeId() == 0) {
                st = con.prepareStatement(CREATE_EMPLOYEES);
            } else {
                st = con.prepareStatement(UPDATE_EMPLOYEES);
                st.setInt(7, employee.getEmployeeId());
            }
            st.setString(1, employee.getName());
            st.setString(2, employee.getSurname());
            st.setInt(3, employee.getExperience());
            st.setString(4, employee.getEmail());
            st.setDate(5, new Date(employee.getDateOfBirth().getTime()));
            st.setInt(6, employee.getDepartmentId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new PersistException(e);
        } finally {
            DBManager.closeStat(st);
        }
    }

    @Override
    public int create(Employee employee) {
        return jdbcTemplate.create(CREATE_EMPLOYEES,
                employee.getName(), employee.getSurname(),
                employee.getExperience(), employee.getEmail(),
                employee.getDateOfBirth(), employee.getDepartmentId());
    }

    @Override
    public Employee getById(int employeeId) {
        return jdbcTemplate.get(GET_EMPLOYEES_BY_ID,
                new EmployeeRowMapper(),
                employeeId);
    }

    @Override
    public Employee getByEmail(String email) {
        return jdbcTemplate.get(GET_EMPLOYEES_BY_EMAIL, new EmployeeRowMapper(), email);
    }

    @Override
    public List<Employee> getAll() {
        return jdbcTemplate.getAll(GET_ALL_EMPLOYEES,
                new EmployeeRowMapper());
    }

    @Override
    public List<Employee> getAllByDepartmentId(int departmentId) {
        return jdbcTemplate.getAll(GET_ALL_EMPLOYEES_BY_DEPARTMENT_ID, new EmployeeRowMapper(), departmentId);
    }

    @Override
    public boolean update(Employee employee) {
        return jdbcTemplate.update(UPDATE_EMPLOYEES,
                employee.getName(),
                employee.getSurname(),
                employee.getExperience(),
                employee.getEmail(),
                employee.getDateOfBirth(),
                employee.getDepartmentId(),
                employee.getEmployeeId());
    }

    @Override
    public boolean updateDepartmentId(Employee employee) {
        return jdbcTemplate.update(UPDATE_EMPLOYEES,
                employee.getName(),
                employee.getSurname(),
                employee.getExperience(),
                employee.getEmail(),
                employee.getDateOfBirth(),
                null,
                employee.getEmployeeId());
    }

    @Override
    public boolean delete(int employeeId) {
        return jdbcTemplate.delete(DELETE_EMPLOYEES, employeeId);
    }
}
