<%--
  Created by IntelliJ IDEA.
  User: Buith
  Date: 9/30/2022
  Time: 8:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="login" method="post">
    <label for="un">Username: </label>
    <input id="un" type="text" name="username">
    <label for="ps">Password: </label>
    <input id="ps" type="text" name="password">
    <input type="submit" value="Login">
</form>
</body>
</html>
