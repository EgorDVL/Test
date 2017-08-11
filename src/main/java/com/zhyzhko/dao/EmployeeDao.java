package com.zhyzhko.dao;

import com.zhyzhko.models.Employee;

import java.util.List;

/**
 * Created by user on 03.07.17.
 */
public interface EmployeeDao {

    void createOrUpdate(Employee employee);

    int create(Employee employee);

    Employee getById(int employeeId);

    Employee getByEmail(String email);

    List<Employee> getAll();

    List<Employee> getAllByDepartmentId(int departmentId);

    boolean update(Employee employee);

    boolean updateDepartmentId(Employee employee);

    boolean delete(int employeeId);
}
