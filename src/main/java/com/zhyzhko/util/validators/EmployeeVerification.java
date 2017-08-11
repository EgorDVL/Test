package com.zhyzhko.util.validators;

import com.zhyzhko.dao.EmployeeDao;
import com.zhyzhko.dao.impl.EmployeeDaoImpl;
import com.zhyzhko.models.Employee;
import com.zhyzhko.service.EmployeeService;
import com.zhyzhko.service.impl.EmployeeServiceImpl;
import com.zhyzhko.util.TransactionProxyManager;
import net.sf.oval.constraint.CheckWithCheck;

/**
 * Created by user on 11.07.17.
 */
public class EmployeeVerification implements CheckWithCheck.SimpleCheck {

    private static EmployeeDao employeeDao = new EmployeeDaoImpl();
    private static EmployeeService employeeService = TransactionProxyManager.getTransactionalWrapper(new EmployeeServiceImpl(employeeDao));

    public boolean isSatisfied(Object employee, Object email) {

        Employee employee1 = (Employee) employee;
        employee1.setEmail((String) email);

        Employee employeeTest = employeeService.getEmployeeByEmail(employee1.getEmail());
        if (employeeTest != null) {
            return employeeTest.getEmployeeId() == employee1.getEmployeeId();
        }
        return true;
    }
}