package com.zhyzhko.util.validators;

import com.zhyzhko.util.EmployeeDepartmentSender;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.context.FieldContext;
import net.sf.oval.context.OValContext;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 07.07.17.
 */
public class OvalValidator {
    private static final Logger LOG = Logger.getLogger(OvalValidator.class);
    private static final String ERROR_MESSAGE = "errorMap";

    private static Map<String, List<String>> errorMap = new LinkedHashMap<>();

    public static boolean validation(Object o, String errorPage, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        errorMap.clear();
        String fieldName;

        net.sf.oval.Validator validator = new net.sf.oval.Validator();
        List<ConstraintViolation> violations = validator.validate(o);
        if (violations.size() > 0) {

            for (ConstraintViolation error : violations) {
                List<String> errorList = new ArrayList<>();

                OValContext ctx = error.getContext();
                fieldName = ((FieldContext) ctx).getField().getName();

                errorList.add(error.getMessage());
                LOG.error("Error message" + error.getMessage());

                errorMap.put(fieldName, errorList);
            }

            req.setAttribute(ERROR_MESSAGE, errorMap);

            EmployeeDepartmentSender.sendEmployeeDepartmentToPage(o, errorPage, req);
            req.getRequestDispatcher(errorPage).forward(req, resp);
            return false;
        }
        return true;
    }
}