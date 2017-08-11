package com.zhyzhko.service;

import com.zhyzhko.models.Department;

import java.util.List;

/**
 * Created by user on 03.07.17.
 */
public interface DepartmentService {

    void createOrUpdate(Department department);

    int createDepartment(Department department);

    Department getDepartmentById(int departmentId);

    Department getDepartmentByName(String name);

    List<Department> getAllDepartments();

    boolean updateDepartment(Department department);

    boolean deleteDepartment(int departmentId);
}
