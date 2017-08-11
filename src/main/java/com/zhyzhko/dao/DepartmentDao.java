package com.zhyzhko.dao;

import com.zhyzhko.models.Department;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by user on 03.07.17.
 */
public interface DepartmentDao {

    void createOrUpdate(Department department);

    int create(Department department);

    Department getById(int departmentId);

    Department getByName(String name);

    List<Department> getAll();

    boolean update(Department department);

    boolean delete(int departmentId);
}
