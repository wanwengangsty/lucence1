
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>站内搜索</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/query" method="post">
    <input type="text" name="queryStr"><input type="submit" value="搜索">
</form>
</body>
</html>
