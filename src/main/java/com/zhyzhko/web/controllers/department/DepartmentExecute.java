package com.zhyzhko.web.controllers.department;

import com.zhyzhko.models.Department;
import com.zhyzhko.service.DepartmentService;
import com.zhyzhko.util.validators.OvalValidator;
import com.zhyzhko.web.requestConverter.HttpRequestConverter;
import com.zhyzhko.web.requestConverter.impl.DepartmentRequestConverter;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.zhyzhko.util.page.Pages.DEPARTMENT_PAGE;

/**
 * Created by user on 04.07.17.
 */
@WebServlet("/DepartmentExecute")
public class DepartmentExecute extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(DepartmentExecute.class);
    private static final String ERROR_PAGE = DEPARTMENT_PAGE;
    private HttpRequestConverter<Department> requestConverter = new DepartmentRequestConverter();

    private DepartmentService departmentService;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        departmentService = (DepartmentService) servletContext.getAttribute("departmentService");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("Execute Department - start");
        Department department = requestConverter.fromRequest(req);

        if (OvalValidator.validation(department, ERROR_PAGE, req, resp)) {

            departmentService.createOrUpdate(department);

            resp.sendRedirect("ShowAllDepartments");
        }
        LOG.info("Execute Department - end");
    }

}