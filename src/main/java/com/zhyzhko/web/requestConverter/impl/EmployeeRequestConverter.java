package com.zhyzhko.web.requestConverter.impl;

import com.zhyzhko.models.Employee;
import com.zhyzhko.util.date.DateUtils;
import com.zhyzhko.web.requestConverter.HttpRequestConverter;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by user on 04.07.17.
 */
public class EmployeeRequestConverter implements HttpRequestConverter<Employee> {
    private DateUtils dateUtils = new DateUtils(DateUtils.YEAR_MONTH_DAY);

    @Override
    public Employee fromRequest(HttpServletRequest req) {
        Employee employee = new Employee();

        String employeeId = req.getParameter("employeeId");
        if (StringUtils.isEmpty(employeeId)) {
            employeeId = "0";
        }
        employee.setEmployeeId(Integer.parseInt(employeeId));
        employee.setName(req.getParameter("name"));
        employee.setSurname(req.getParameter("surname"));

        String experience = req.getParameter("experience");
        if (StringUtils.isEmpty(experience)) {
            experience = "0";
        }
        employee.setExperience(Integer.parseInt(experience));

        employee.setEmail(req.getParameter("email"));
        employee.setDateOfBirth(dateUtils.parse(req.getParameter("dateOfBirth")));
        employee.setDepartmentId(Integer.parseInt(req.getParameter("departmentId")));

        return employee;
    }
}
