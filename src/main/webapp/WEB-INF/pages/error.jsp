<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 10.07.17
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>

<html>
<head>
    <title>Error page</title>
</head>
<body>
<h3>Sorry an exception occurred!</h3>
<%=exception.getMessage()%>
</body>
</html>
