package com.zhyzhko.web.controllers.department;

import com.zhyzhko.models.Department;
import org.apache.log4j.Logger;
import com.zhyzhko.service.DepartmentService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static com.zhyzhko.util.page.Pages.SHOW_ALL_DEPARTMENTS;

/**
 * Created by user on 04.07.17.
 */
@WebServlet("/ShowAllDepartments")
public class ShowAllDepartments extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(ShowAllDepartments.class);
    private DepartmentService departmentService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        departmentService = (DepartmentService) servletContext.getAttribute("departmentService");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("Show all departments - start");

        List<Department> departmentList = departmentService.getAllDepartments();
        sortDepartmentsById(departmentList);

        req.setAttribute("departmentList", departmentList);
        req.getRequestDispatcher(SHOW_ALL_DEPARTMENTS).forward(req, resp);

        LOG.info("Show all departments - end");
    }

    private void sortDepartmentsById(List<Department> departmentList) {
        Collections.sort(departmentList, (d1, d2) -> d1.getDepartmentId() - d2.getDepartmentId());
    }

}
