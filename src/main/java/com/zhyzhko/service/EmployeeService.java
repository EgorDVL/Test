package com.zhyzhko.service;

import com.zhyzhko.models.Employee;

import java.util.List;

/**
 * Created by user on 03.07.17.
 */
public interface EmployeeService {

    void createOrUpdate(Employee employee);

    int createEmployee(Employee employee);

    Employee getEmployeeById(int employeeId);

    Employee getEmployeeByEmail(String email);

    List<Employee> getAllEmployee();

    List<Employee> getAllEmployeeByDepartmentId(int departmentId);

    boolean updateEmployee(Employee employee);

    boolean updateEmployeeDepartmentId(int departmentId);

    boolean deleteEmployee(int employeeId);
}
