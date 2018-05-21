<%--
  Created by IntelliJ IDEA.
  User: hasee
  Date: 2018/5/18
  Time: 13:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/login" method="post">
    用户名：<input type="text" name="username">
    密 码：<input type="password" name="password">
    <input type="submit" value="登录"> <span style="color: red">${result}</span>
</form>
</body>
</html>
