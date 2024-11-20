<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Hello JSP</title>
</head>
<body>
    <h1>Hello, ${name}!</h1>
    <fmt:formatDate value="${currentDate}" pattern="yyyy-MM-dd" />
</body>
</html>