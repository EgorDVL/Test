package com.zhyzhko.util;

import com.zhyzhko.dao.DepartmentDao;
import com.zhyzhko.dao.impl.DepartmentDaoImpl;
import com.zhyzhko.models.Employee;
import com.zhyzhko.service.DepartmentService;
import com.zhyzhko.service.impl.DepartmentServiceImpl;

import javax.servlet.http.HttpServletRequest;

import static com.zhyzhko.util.page.Pages.EMPLOYEE_PAGE;

/**
 * Created by user on 11.07.17.
 */
public class EmployeeDepartmentSender {
    private static DepartmentDao departmentDao = new DepartmentDaoImpl();
    private static DepartmentService departmentService = TransactionProxyManager.getTransactionalWrapper(new DepartmentServiceImpl(departmentDao));

    public static void sendEmployeeDepartmentToPage(Object o, String errorPage, HttpServletRequest req) {
        if (errorPage.equalsIgnoreCase(EMPLOYEE_PAGE)) {
            Employee employee = (Employee) o;

            req.setAttribute("departmentList", departmentService.getAllDepartments());
            req.setAttribute("employeeDepartment", departmentService.getDepartmentById(employee.getDepartmentId()));
        }
    }
}
