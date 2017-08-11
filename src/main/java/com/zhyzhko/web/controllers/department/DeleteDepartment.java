package com.zhyzhko.web.controllers.department;

import org.apache.log4j.Logger;
import com.zhyzhko.service.DepartmentService;
import com.zhyzhko.service.EmployeeService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by user on 04.07.17.
 */
@WebServlet("/DeleteDepartment")
public class DeleteDepartment extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(DeleteDepartment.class);

    private DepartmentService departmentService;
    private EmployeeService employeeService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        departmentService = (DepartmentService) servletContext.getAttribute("departmentService");
        employeeService = (EmployeeService) servletContext.getAttribute("employeeService");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("Delete Department - start");

        int departmentId = Integer.parseInt(req.getParameter("departmentId"));

        employeeService.updateEmployeeDepartmentId(departmentId);
        departmentService.deleteDepartment(departmentId);

        resp.sendRedirect("ShowAllDepartments");

        LOG.info("Delete Department - end");
    }
}
