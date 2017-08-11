package com.zhyzhko.util.validators;

import com.zhyzhko.dao.DepartmentDao;
import com.zhyzhko.dao.impl.DepartmentDaoImpl;
import com.zhyzhko.models.Department;
import com.zhyzhko.service.DepartmentService;
import com.zhyzhko.service.impl.DepartmentServiceImpl;
import com.zhyzhko.util.TransactionProxyManager;
import net.sf.oval.constraint.CheckWithCheck;

/**
 * Created by user on 11.07.17.
 */
public class DepartmentVerification implements CheckWithCheck.SimpleCheck {

    private static DepartmentDao departmentDao = new DepartmentDaoImpl();
    private static DepartmentService departmentService = TransactionProxyManager.getTransactionalWrapper(new DepartmentServiceImpl(departmentDao));

    public boolean isSatisfied(Object department, Object name) {

        Department department1 = (Department) department;

        Department departmentTest = departmentService.getDepartmentByName(department1.getName());
        if (departmentTest != null) {
            return departmentTest.getDepartmentId() == department1.getDepartmentId();
        }
        return true;
    }
}