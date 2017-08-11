package com.zhyzhko.service.impl;

import com.zhyzhko.models.Employee;
import com.zhyzhko.dao.EmployeeDao;
import com.zhyzhko.exception.ObtainObjectException;
import com.zhyzhko.exception.PersistException;
import com.zhyzhko.exception.ServiceException;
import org.apache.log4j.Logger;
import com.zhyzhko.service.EmployeeService;

import java.util.List;

/**
 * Created by user on 03.07.17.
 */
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = Logger.getLogger(EmployeeServiceImpl.class);

    private EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public void createOrUpdate(Employee employee) {
        try {
            employeeDao.createOrUpdate(employee);
        } catch (PersistException e) {
            LOG.error("Service employee can't create or update");
            throw new ServiceException("Can't create or update employee");
        }
    }

    @Override
    public int createEmployee(Employee employee) {
        try {
            return employeeDao.create(employee);
        } catch (PersistException e) {
            LOG.error("Service employee can't create");
            throw new ServiceException("Can't create employee");
        }
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        try {
            return employeeDao.getById(employeeId);
        } catch (ObtainObjectException e) {
            LOG.error("Service employee can't get");
            throw new ServiceException("Can't get employee");
        }
    }

    @Override
    public Employee getEmployeeByEmail(String email) {
        try {
            return employeeDao.getByEmail(email);
        } catch (ObtainObjectException e) {
            LOG.error("Service employee can't get by email");
            throw new ServiceException("Can't get employee by email");
        }
    }

    @Override
    public List<Employee> getAllEmployee() {
        try {
            return employeeDao.getAll();
        } catch (ObtainObjectException e) {
            LOG.error("Service employee can't get all");
            throw new ServiceException("Can't get all employee");
        }
    }

    @Override
    public List<Employee> getAllEmployeeByDepartmentId(int departmentId) {
        try {
            return employeeDao.getAllByDepartmentId(departmentId);
        } catch (ObtainObjectException e) {
            LOG.error("Service employee can't get all by department id");
            throw new ServiceException("Can't get all by department id employee");
        }
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        try {
            return employeeDao.update(employee);
        } catch (PersistException e) {
            LOG.error("Service employee can't update");
            throw new ServiceException("Can't update employee");
        }
    }

    @Override
    public boolean updateEmployeeDepartmentId(int departmentId) {
        try {
            List<Employee> employeeList = employeeDao.getAllByDepartmentId(departmentId);
            for (Employee employee : employeeList) {
                employeeDao.updateDepartmentId(employee);
            }
            return true;
        } catch (PersistException e) {
            LOG.error("Service employee can't update");
            throw new ServiceException("Can't update employee");
        }
    }

    @Override
    public boolean deleteEmployee(int employeeId) {
        try {
            return employeeDao.delete(employeeId);
        } catch (PersistException e) {
            LOG.error("Service employee can't delete");
            throw new ServiceException("Can't delete employee");
        }
    }
}