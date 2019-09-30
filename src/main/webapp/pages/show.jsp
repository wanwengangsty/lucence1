<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <c:forEach items="${msgList}" var="msg">
        <tr>
            <td>${msg.id}</td>
            <td>${msg.title}</td>
            <td>${msg.msgcontent}</td>
            <td>${msg.msgCreateDate}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
