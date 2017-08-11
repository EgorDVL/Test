<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 13.07.17
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" text="text/css" href="${pageContext.request.contextPath}/css/main.css"/>

    <title>Employee Execute</title>
</head>
<body>
<%@include file="/include/main.jspf" %>

<form id="container" action="EmployeeExecute" method="post">
    <input name="employeeId" type="hidden"
           value="<c:out value="${param.employeeId == null ? employee.employeeId : param.employeeId}"/>"/>

    <div>
        <label for="name">Name</label>
        <div>
            <input class="input-create-employee" id="name" placeholder="Vasua" name="name" type="text"
                   value="<c:out value="${param.name == null ? employee.name : param.name}"/>"/>
        </div>
        <c:forEach items="${errorMap}" var="error">
            <c:if test="${error.key eq 'name'}">
                <div class="error-message">
                        ${error.value}
                </div>
            </c:if>
        </c:forEach>
    </div>

    <div>
        <label for="surname">Surname</label>
        <div>
            <input class="input-create-employee" id="surname" placeholder="Pupkin" name="surname" type="text"
                   value="<c:out value="${param.surname == null ? employee.surname : param.surname}"/>"/>

        </div>
        <c:forEach items="${errorMap}" var="error">
            <c:if test="${error.key eq 'surname'}">
                <div class="error-message">
                        ${error.value}
                </div>
            </c:if>
        </c:forEach>
    </div>

    <div>
        <label for="experience">Experience</label>
        <div>
            <input class="input-create-employee" id="experience" name="experience" type="number" style="width: 244px;"
                   value="<c:out value="${param.experience == null ? employee.experience : param.experience}"/>"/>
        </div>
        <c:forEach items="${errorMap}" var="error">
            <c:if test="${error.key eq 'experience'}">
                <div class="error-message">
                        ${error.value}
                </div>
            </c:if>
        </c:forEach>
    </div>

    <div>
        <label for="email">Email</label>
        <div>
            <input class="input-create-employee" placeholder="Lolo@gmail.com" id="email" name="email" type="email"
                   value="<c:out value="${param.email == null ? employee.email : param.email}"/>"/>

        </div>
        <c:forEach items="${errorMap}" var="error">
            <c:if test="${error.key eq 'email'}">
                <div class="error-message">
                        ${error.value}
                </div>
            </c:if>
        </c:forEach>
    </div>

    <div>
        <label for="dateOfBirth">Date of birth</label>
        <div>
            <input class="input-create-employee" id="dateOfBirth" placeholder="1993-03-23" name="dateOfBirth"
                   type="text"
                   value="<c:out value="${param.dateOfBirth == null ? employee.dateOfBirth : param.dateOfBirth}"/>"/>
            <%--<fmt:formatDate value="${employee.dateOfBirth}" pattern="yyyy-MM-dd"/>--%>
        </div>
        <c:forEach items="${errorMap}" var="error">
            <c:if test="${error.key eq 'dateOfBirth'}">
                <div class="error-message">
                        ${error.value}
                </div>
            </c:if>
        </c:forEach>
    </div>

    <div>
        <label for="departmentId">Department</label>
        <div>
            <select class="input-create-employee" style="width: 244px;" id="departmentId" name="departmentId" required>
                <option
                        value="${employeeDepartment.departmentId}"
                        selected>${employeeDepartment.name}
                </option>

                <c:forEach items="${departmentList}" var="department">
                    <option value="${department.departmentId}">${department.name}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <button type="submit">Execute</button>
</form>

</body>
</html>