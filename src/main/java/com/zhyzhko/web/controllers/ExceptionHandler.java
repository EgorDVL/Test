package com.zhyzhko.web.controllers;

import static com.zhyzhko.util.page.Pages.*;

import com.zhyzhko.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by user on 11.07.17.
 */
@WebServlet("/ExceptionHandler")
public class ExceptionHandler extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        throw new ServiceException("Service exception");
    }
}
