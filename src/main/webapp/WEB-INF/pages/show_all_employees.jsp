<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 04.07.17
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" text="text/css" href="${pageContext.request.contextPath}/css/main.css"/>

    <title>Show all employees</title>
</head>
<body>
<%@include file="/include/main.jspf" %>

<div id="show-all-employees">
    <table class="table table-hover">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Surname</th>
            <th>Experience</th>
            <th>Email</th>
            <th>Date of birth</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${employeeList}" var="employee">
            <tr>
                <td>${employee.employeeId}</td>
                <td>${employee.name}</td>
                <td>${employee.surname}</td>
                <td>${employee.experience}</td>
                <td>${employee.email}</td>
                <td>${employee.dateOfBirth}</td>
                <td>
                    <a href="RedirectToEmployeeExecutePage?employeeId=${employee.employeeId}">
                        <input type="button" value="Update"/>
                    </a>
                </td>

                <td>
                    <form action="DeleteEmployee" method="post">
                        <input type="hidden" name="employeeId" value="${employee.employeeId}">
                        <button type="submit">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
