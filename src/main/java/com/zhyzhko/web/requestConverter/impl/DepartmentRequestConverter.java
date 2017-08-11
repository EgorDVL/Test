package com.zhyzhko.web.requestConverter.impl;

import com.zhyzhko.models.Department;
import com.zhyzhko.web.requestConverter.HttpRequestConverter;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by user on 04.07.17.
 */
public class DepartmentRequestConverter implements HttpRequestConverter<Department> {

    @Override
    public Department fromRequest(HttpServletRequest req) {
        Department department = new Department();

        String departmentId = req.getParameter("departmentId");
        if (StringUtils.isEmpty(departmentId)) {
            departmentId = "0";
        }

        department.setDepartmentId(Integer.parseInt(departmentId));
        department.setName(req.getParameter("name"));
        return department;
    }
}
