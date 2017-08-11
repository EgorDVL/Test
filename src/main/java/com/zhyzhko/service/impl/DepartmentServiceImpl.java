package com.zhyzhko.service.impl;

import com.zhyzhko.models.Department;
import com.zhyzhko.dao.DepartmentDao;
import com.zhyzhko.exception.ObtainObjectException;
import com.zhyzhko.exception.PersistException;
import com.zhyzhko.exception.ServiceException;
import org.apache.log4j.Logger;
import com.zhyzhko.service.DepartmentService;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by user on 03.07.17.
 */
public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger LOG = Logger.getLogger(DepartmentServiceImpl.class);

    private DepartmentDao departmentDao;

    public DepartmentServiceImpl(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public void createOrUpdate(Department department) {
        try {
            departmentDao.createOrUpdate(department);
        } catch (PersistException e) {
            LOG.error("Service department can't create or update");
            throw new ServiceException("Can't create or update department");
        }
    }

    @Override
    public int createDepartment(Department department) {
        try {
            return departmentDao.create(department);
        } catch (PersistException e) {
            LOG.error("Service department can't create");
            throw new ServiceException("Can't create department");
        }
    }

    @Override
    public Department getDepartmentById(int departmentId) {
        try {
            return departmentDao.getById(departmentId);
        } catch (ObtainObjectException e) {
            LOG.error("Service department can't get");
            throw new ServiceException("Can't get department");
        }
    }

    @Override
    public List<Department> getAllDepartments() {
        try {
            return departmentDao.getAll();
        } catch (ObtainObjectException e) {
            LOG.error("Service department can't get all");
            throw new ServiceException("Can't get all department");
        }
    }

    @Override
    public boolean updateDepartment(Department department) {
        try {
            return departmentDao.update(department);
        } catch (PersistException e) {
            LOG.error("Service department can't update");
            throw new ServiceException("Can't update department");
        }
    }

    @Override
    public boolean deleteDepartment(int departmentId) {
        try {
            return departmentDao.delete(departmentId);
        } catch (PersistException e) {
            LOG.error("Service department can't delete");
            throw new ServiceException("Can't delete department");
        }
    }

    @Override
    public Department getDepartmentByName(String name) {
        try {
            return departmentDao.getByName(name);
        } catch (ObtainObjectException e) {
            LOG.error("Service department can't get by name");
            throw new ServiceException("Can't get department by name");
        }
    }
}