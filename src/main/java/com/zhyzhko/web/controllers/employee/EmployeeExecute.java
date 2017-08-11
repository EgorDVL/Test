package com.zhyzhko.web.controllers.employee;

import com.zhyzhko.models.Employee;
import com.zhyzhko.service.EmployeeService;
import com.zhyzhko.util.validators.OvalValidator;
import com.zhyzhko.web.requestConverter.HttpRequestConverter;
import com.zhyzhko.web.requestConverter.impl.EmployeeRequestConverter;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.zhyzhko.util.page.Pages.EMPLOYEE_PAGE;

/**
 * Created by user on 04.07.17.
 */
@WebServlet("/EmployeeExecute")
public class EmployeeExecute extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(EmployeeExecute.class);
    private static final String ERROR_PAGE = EMPLOYEE_PAGE;

    private EmployeeService employeeService;
    private HttpRequestConverter<Employee> requestConverter = new EmployeeRequestConverter();


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        employeeService = (EmployeeService) servletContext.getAttribute("employeeService");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("Update Employee - start");
        Employee employee = requestConverter.fromRequest(req);

        if (OvalValidator.validation(employee, ERROR_PAGE, req, resp)) {

            employeeService.createOrUpdate(employee);

            resp.sendRedirect("ShowAllEmployees");
        }
        LOG.info("Update Employee - end");
    }
}
