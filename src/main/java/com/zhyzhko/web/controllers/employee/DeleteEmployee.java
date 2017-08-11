package com.zhyzhko.web.controllers.employee;

import org.apache.log4j.Logger;
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
@WebServlet("/DeleteEmployee")
public class DeleteEmployee extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(DeleteEmployee.class);

    private EmployeeService employeeService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        employeeService = (EmployeeService) servletContext.getAttribute("employeeService");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("Delete Employee - start");

        int employeeId = Integer.parseInt(req.getParameter("employeeId"));
        employeeService.deleteEmployee(employeeId);
        
        resp.sendRedirect("ShowAllEmployees");

        LOG.info("Delete Employee - end");
    }
}
