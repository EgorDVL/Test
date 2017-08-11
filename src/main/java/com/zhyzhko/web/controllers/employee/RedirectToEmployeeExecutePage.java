package com.zhyzhko.web.controllers.employee;

import com.zhyzhko.models.Department;
import com.zhyzhko.models.Employee;
import com.zhyzhko.service.DepartmentService;
import com.zhyzhko.service.EmployeeService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.zhyzhko.util.page.Pages.EMPLOYEE_PAGE;

/**
 * Created by user on 04.07.17.
 */
@WebServlet("/RedirectToEmployeeExecutePage")
public class RedirectToEmployeeExecutePage extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(RedirectToEmployeeExecutePage.class);

    private EmployeeService employeeService;
    private DepartmentService departmentService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        employeeService = (EmployeeService) servletContext.getAttribute("employeeService");
        departmentService = (DepartmentService) servletContext.getAttribute("departmentService");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("Redirect to employee execute page - start");

        String employeeId = req.getParameter("employeeId");
        if (!StringUtils.isEmpty(employeeId)) {

            Employee employee = employeeService.getEmployeeById(Integer.parseInt(employeeId));

            req.setAttribute("employee", employee);
            req.setAttribute("employeeDepartment",
                    departmentService.getDepartmentById(employee.getDepartmentId()));

        }

        List<Department> departmentList = departmentService.getAllDepartments();
        req.setAttribute("departmentList", departmentList);

        req.getRequestDispatcher(EMPLOYEE_PAGE).forward(req, resp);

        LOG.info("Redirect to employee execute page - end");
    }
}
